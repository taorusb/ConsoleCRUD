package com.consolecrud.repository;

import com.consolecrud.model.Label;
import com.consolecrud.repository.intrerfaces.DataIO;
import com.consolecrud.repository.intrerfaces.LabelRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LabelRepositoryImpl implements LabelRepository, DataIO {

    private static final String LABEL_FILE_LOCATION = "src\\resources\\labels.txt";
    private static List<Label> labelList = new LinkedList<>();
    private static long idCounter;
    private PostRepositoryImpl postRepository;
    private static LabelRepositoryImpl instance;

    private LabelRepositoryImpl() {
    }

    public static LabelRepositoryImpl getLabelRepository() {
        if (instance == null) {
            instance = new LabelRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Label getById(Long id) {
        return labelList
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst().orElseThrow();
    }

    @Override
    public void deleteById(Long id) {

        int index = labelList.indexOf(new Label(id));
        if (index == -1) throw new NoSuchElementException();

        labelList.remove(index);
    }

    @Override
    public List<Label> findAll() {
        return labelList;
    }

    @Override
    public Label save(Label entity) {

        entity.setId(++idCounter);
        labelList.add(entity);

        return entity;
    }

    @Override
    public Label update(Label entity) {

        int index = labelList.indexOf(entity);
        if (index == -1) throw new NoSuchElementException();

        Label updatableEntity = labelList.get(index);

        updatableEntity.setName(entity.getName());

        return updatableEntity;
    }

    @Override
    public void loadData() {

        try (Stream<String> labelStream =
                     Files.lines(Path.of(LABEL_FILE_LOCATION))) {

            labelList = labelStream
                    .map(this::convertLabel)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.err.printf(FILE_READING_ERROR, LABEL_FILE_LOCATION);
        }
    }

    @Override
    public void saveData() {
        try (FileOutputStream outputStream =
                     new FileOutputStream(LABEL_FILE_LOCATION, false)) {

            PrintStream printStream = new PrintStream(outputStream);

            for (Label label : labelList) {
                printStream.print(label.getId());
                printStream.print("$" + label.getName());
                printStream.print("\n");
            }

        } catch (IOException e) {
            System.err.printf(FILE_READING_ERROR, LABEL_FILE_LOCATION);
        }
    }

    public void setPostRepository(PostRepositoryImpl postRepository) {
        this.postRepository = postRepository;
    }

    private Label convertLabel(String values) {

        String[] splittedValues = values.split("\\$");
        return new Label(Long.parseLong(splittedValues[0]), splittedValues[1]);
    }
}

