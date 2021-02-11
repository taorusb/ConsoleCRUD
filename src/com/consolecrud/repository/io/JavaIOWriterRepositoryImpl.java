package com.consolecrud.repository.io;

import com.consolecrud.model.Post;
import com.consolecrud.model.Writer;
import com.consolecrud.repository.WriterRepository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class JavaIOWriterRepositoryImpl implements WriterRepository {

    private final static String WRITER_FILE_LOCATION = "src\\resources\\writers.txt";
    private JavaIOPostRepositoryImpl postRepository;
    private static List<Writer> writerList = new LinkedList<>();
    private static JavaIOWriterRepositoryImpl instance;
    private static long idCounter = 0;

    private JavaIOWriterRepositoryImpl() {
    }

    public void setPostRepository(JavaIOPostRepositoryImpl postRepository) {
        this.postRepository = postRepository;
    }

    public static JavaIOWriterRepositoryImpl getWriterRepository() {
        if (instance == null)
            instance = new JavaIOWriterRepositoryImpl();
        return instance;
    }

    @Override
    public Writer getById(Long id) {
        loadData();
        return writerList.stream()
                .filter(x -> x.getId() == id)
                .findFirst().orElseThrow();
    }

    @Override
    public void deleteById(Long id) {

        loadData();

        int index = writerList.indexOf(new Writer(id));
        if (index == -1) {
            throw new NoSuchElementException();
        }

        writerList.remove(index);
        saveData();
    }

    @Override
    public List<Writer> findAll() {
        loadData();
        return writerList;
    }

    @Override
    public Writer save(Writer entity) {

        loadData();
        entity.setId(++idCounter);
        writerList.add(entity);

        saveData();
        return entity;
    }

    @Override
    public Writer update(Writer entity) {

        loadData();

        int index = writerList.indexOf(entity);
        if (index == -1) {
            throw new NoSuchElementException();
        }

        Writer updatableEntity = writerList.get(index);
        updatableEntity.setFirstName(entity.getFirstName());
        updatableEntity.setLastName(entity.getLastName());
        saveData();

        return updatableEntity;
    }

    public void loadData() {
        idCounter = DataIO.getId(WRITER_FILE_LOCATION);
        postRepository.loadData();
        writerList = DataIO.loadData(WRITER_FILE_LOCATION, this::convertFromString);
    }

    public void saveData() {
        DataIO.saveData(WRITER_FILE_LOCATION, writerList, DataIO.WRITER_TYPE);
        postRepository.saveData();
    }

    private Writer convertFromString(String values) {

        String[] splittedValues = values.split("\\$");

        Writer writer = new Writer(Long.parseLong(splittedValues[0]),
                splittedValues[1], splittedValues[2]);

        if (splittedValues.length == 4 ) {
            String[] postsIds = splittedValues[3].split(",");

            List<Post> collectedPosts = Arrays.stream(postsIds)
                    .map(id -> postRepository
                            .findAll()
                            .stream()
                            .filter(post -> post.getId() == Long.parseLong(id))
                            .findFirst().orElse(new Post()))
                    .collect(Collectors.toList());

            writer.setPosts(collectedPosts);
        }
        return writer;
    }
}
