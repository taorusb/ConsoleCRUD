package com.consolecrud.repository;

import com.consolecrud.model.Label;
import com.consolecrud.model.Post;
import com.consolecrud.repository.intrerfaces.DataIO;
import com.consolecrud.repository.intrerfaces.PostRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PostRepositoryImpl implements PostRepository, DataIO {

    private final static String POST_FILE_LOCATION = "src\\resources\\posts.txt";
    private LabelRepositoryImpl labelRepository;
    private static PostRepositoryImpl instance;
    private static List<Post> postList = new LinkedList<>();
    private final static SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd/MM/yyyy");
    private static long idCounter;

    private PostRepositoryImpl() {
    }

    public static PostRepositoryImpl getPostRepository() {
        if (instance == null)
            instance = new PostRepositoryImpl();
        return instance;
    }

    @Override
    public Post getById(Long id) {
        return postList
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst().orElseThrow();
    }

    @Override
    public void deleteById(Long id) {

        int index = postList.indexOf(new Post(id));
        if (index == -1) throw new NoSuchElementException();

        postList.remove(index);
    }

    @Override
    public List<Post> findAll() {
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

        int index = postList.indexOf(entity);
        if (index == -1) throw new NoSuchElementException();

        Post updatableEntity = postList.get(index);
        updatableEntity.setContent(entity.getContent());
        updatableEntity.setUpdated(dateFormat.format(new Date()));

        return updatableEntity;
    }

    @Override
    public void loadData() {

        labelRepository.loadData();

        try (Stream<String> postStream =
                     Files.lines(Path.of(POST_FILE_LOCATION))) {

            postList = postStream
                    .map(this::convertFromString)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.err.printf(FILE_READING_ERROR, POST_FILE_LOCATION);
        }
    }

    @Override
    public void saveData() {

        try (FileOutputStream outputStream =
                     new FileOutputStream(POST_FILE_LOCATION, false)) {

            PrintStream printStream = new PrintStream(outputStream);

            for (Post post : postList) {

                printStream.print(post.getId());
                printStream.print("$" + post.getContent() + "$");
                printStream.print((post.getCreated() + "$"));

                if ((post.getUpdated() == null)) {
                    printStream.print("-$");
                } else {
                    printStream.print(post.getUpdated() + "$");
                }
                post.getLabels().forEach(x -> printStream.print(x.getId() + ","));
                printStream.print("\n");
            }

        } catch (IOException e) {
            System.err.printf(FILE_READING_ERROR, POST_FILE_LOCATION);
        }
        labelRepository.saveData();
    }

    public void setLabelRepository(LabelRepositoryImpl labelRepository) {
        this.labelRepository = labelRepository;
    }

    private Post convertFromString(String values) {

        String[] splittedValues = values.split("\\$");
        String[] labelIds = splittedValues[4].split(",");

        List<Label> collectedLabels = Arrays.stream(labelIds)
                .map(id -> labelRepository
                        .findAll()
                        .stream()
                        .filter(label -> label.getId() == Long.parseLong(id))
                        .findFirst().orElse(new Label()))
                .collect(Collectors.toList());

        Post post = new Post(Long.parseLong(splittedValues[0]), splittedValues[1],
                splittedValues[2], splittedValues[3]);
        post.setLabels(collectedLabels);

        return post;
    }
}
