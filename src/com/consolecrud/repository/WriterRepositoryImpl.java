package com.consolecrud.repository;

import com.consolecrud.model.Post;
import com.consolecrud.model.Writer;
import com.consolecrud.repository.intrerfaces.DataIO;
import com.consolecrud.repository.intrerfaces.WriterRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriterRepositoryImpl implements WriterRepository, DataIO {

    private final static String WRITER_FILE_LOCATION = "src\\resources\\writers.txt";
    private PostRepositoryImpl postRepository;
    private static List<Writer> writerList = new LinkedList<>();
    private static WriterRepositoryImpl instance;
    private static long idCounter = 0;

    private WriterRepositoryImpl() {
    }

    public void setPostRepository(PostRepositoryImpl postRepository) {
        this.postRepository = postRepository;
    }

    public static WriterRepositoryImpl getWriterRepository() {
        if (instance == null)
            instance = new WriterRepositoryImpl();
        return instance;
    }

    @Override
    public Writer getById(Long id) {
        return writerList.stream()
                .filter(x -> x.getId() == id)
                .findFirst().orElseThrow();
    }

    @Override
    public void deleteById(Long id) {

        int index = writerList.indexOf(new Writer(id));
        if (index == -1) throw new NoSuchElementException();

        writerList.remove(index);
    }

    @Override
    public List<Writer> findAll() {
        return writerList;
    }

    @Override
    public Writer save(Writer entity) {

        entity.setId(++idCounter);
        writerList.add(entity);

        return entity;
    }

    @Override
    public Writer update(Writer entity) {

        int index = writerList.indexOf(entity);
        if (index == -1) throw new NoSuchElementException();

        Writer updatableEntity = writerList.get(index);
        updatableEntity.setFirstName(entity.getFirstName());
        updatableEntity.setLastName(entity.getLastName());

        return updatableEntity;
    }

    @Override
    public void loadData() {

        postRepository.loadData();

        try (Stream<String> writerStream =
                     Files.lines(Path.of(WRITER_FILE_LOCATION))) {

            writerList = writerStream
                    .map(this::convertFromString)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.err.printf(FILE_READING_ERROR, WRITER_FILE_LOCATION);
        }
    }

    @Override
    public void saveData() {

        try (FileOutputStream outputStream =
                     new FileOutputStream(WRITER_FILE_LOCATION, false)) {

            PrintStream printStream = new PrintStream(outputStream);

            for (Writer writer : writerList) {
                printStream.print(writer.getId());
                printStream.print("$" + writer.getFirstName() + "$");
                printStream.print(writer.getLastName() + "$");
                writer.getPosts().forEach(post -> printStream.print(post.getId() + ","));
                printStream.print("\n");
            }

        } catch (IOException e) {
            System.err.printf(FILE_READING_ERROR, WRITER_FILE_LOCATION);
        }
        postRepository.saveData();
    }

    private Writer convertFromString(String values) {

        String[] splittedValues = values.split("\\$");
        String[] postsIds = splittedValues[3].split(",");

        List<Post> collectedPosts = Arrays.stream(postsIds)
                .map(id -> postRepository
                        .findAll()
                        .stream()
                        .filter(post -> post.getId() == Long.parseLong(id))
                        .findFirst().orElse(new Post()))
                .collect(Collectors.toList());

        Writer writer = new Writer(Long.parseLong(splittedValues[0]),
                splittedValues[1], splittedValues[2]);
        writer.setPosts(collectedPosts);

        return writer;
    }
}
