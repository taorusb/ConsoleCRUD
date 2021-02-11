package com.consolecrud.repository.io;

import com.consolecrud.model.Label;
import com.consolecrud.repository.LabelRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class JavaIOLabelRepositoryImpl implements LabelRepository {

    private static final String LABEL_FILE_LOCATION = "src\\resources\\labels.txt";
    private static List<Label> labelList = new LinkedList<>();
    private static long idCounter;
    private JavaIOPostRepositoryImpl postRepository;
    private static JavaIOLabelRepositoryImpl instance;

    private JavaIOLabelRepositoryImpl() {
    }

    public static JavaIOLabelRepositoryImpl getLabelRepository() {
        if (instance == null) {
            instance = new JavaIOLabelRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Label getById(Long id) {
        loadData();
        return labelList
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst().orElseThrow();
    }

    @Override
    public void deleteById(Long id) {

        loadData();
        int index = labelList.indexOf(new Label(id));
        if (index == -1) {
            throw new NoSuchElementException();
        }

        labelList.remove(index);
        saveData();
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

        loadData();

        int index = labelList.indexOf(entity);
        if (index == -1) {
            throw new NoSuchElementException();
        }

        Label updatableEntity = labelList.get(index);

        updatableEntity.setName(entity.getName());
        saveData();

        return updatableEntity;
    }


    public void loadData() {
        idCounter = DataIO.getId(LABEL_FILE_LOCATION);
        labelList = DataIO.loadData(LABEL_FILE_LOCATION, this::convertLabel);
    }

    public void saveData() {
        DataIO.saveData(LABEL_FILE_LOCATION, labelList, DataIO.LABEL_TYPE);
    }

    public void setPostRepository(JavaIOPostRepositoryImpl postRepository) {
        this.postRepository = postRepository;
    }

    private Label convertLabel(String values) {

        String[] splittedValues = values.split("\\$");
        return new Label(Long.parseLong(splittedValues[0]), splittedValues[1]);
    }
}

