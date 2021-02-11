package com.consolecrud.repository.io;

import com.consolecrud.model.Label;
import com.consolecrud.model.Post;
import com.consolecrud.repository.PostRepository;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class JavaIOPostRepositoryImpl implements PostRepository {

    private final static String POST_FILE_LOCATION = "src\\resources\\posts.txt";
    private JavaIOLabelRepositoryImpl labelRepository;
    private static JavaIOPostRepositoryImpl instance;
    private static List<Post> postList = new LinkedList<>();
    private final static SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd/MM/yyyy");
    private static long idCounter;

    private JavaIOPostRepositoryImpl() {
    }

    public static JavaIOPostRepositoryImpl getPostRepository() {
        if (instance == null) {
            instance = new JavaIOPostRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Post getById(Long id) {
        loadData();
        return postList
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst().orElseThrow();
    }

    @Override
    public void deleteById(Long id) {

        loadData();

        int index = postList.indexOf(new Post(id));
        if (index == -1) {
            throw new NoSuchElementException();
        }
        postList.remove(index);

        saveData();
    }

    @Override
    public List<Post> findAll() {
        loadData();
        return postList;
    }

    @Override
    public Post save(Post entity) {

        entity.setId(++idCounter);
        entity.setUpdated("-");
        entity.setCreated(dateFormat.format(new Date()));
        postList.add(entity);

        return entity;
    }

    @Override
    public Post update(Post entity) {

        loadData();

        int index = postList.indexOf(entity);
        if (index == -1) {
            throw new NoSuchElementException();
        }

        Post updatableEntity = postList.get(index);
        updatableEntity.setContent(entity.getContent());
        updatableEntity.setUpdated(dateFormat.format(new Date()));
        saveData();

        return updatableEntity;
    }

    public void loadData() {
        idCounter = DataIO.getId(POST_FILE_LOCATION);
        labelRepository.loadData();
        postList = DataIO.loadData(POST_FILE_LOCATION, this::convertFromString);
    }

    public void saveData() {
        DataIO.saveData(POST_FILE_LOCATION, postList, DataIO.POST_TYPE);
        labelRepository.saveData();
    }

    public void setLabelRepository(JavaIOLabelRepositoryImpl labelRepository) {
        this.labelRepository = labelRepository;
    }

    private Post convertFromString(String values) {

        String[] splittedValues = values.split("\\$");

        Post post = new Post(Long.parseLong(splittedValues[0]), splittedValues[1],
                splittedValues[2], splittedValues[3]);

        if (splittedValues.length == 5) {
            String[] labelIds = splittedValues[4].split(",");

            List<Label> collectedLabels = Arrays.stream(labelIds)
                    .map(id -> labelRepository
                            .findAll()
                            .stream()
                            .filter(label -> label.getId() == Long.parseLong(id))
                            .findFirst().orElse(new Label()))
                    .collect(Collectors.toList());

            post.setLabels(collectedLabels);
        }
        return post;
    }
}
