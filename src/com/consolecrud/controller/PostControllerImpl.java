package com.consolecrud.controller;

import static java.lang.Long.parseLong;

import com.consolecrud.model.Post;
import com.consolecrud.model.Writer;
import com.consolecrud.repository.io.JavaIOPostRepositoryImpl;
import com.consolecrud.repository.io.JavaIOWriterRepositoryImpl;

import java.util.List;
import java.util.NoSuchElementException;

public class PostControllerImpl implements Controller {

    private JavaIOWriterRepositoryImpl writerRepository;
    private JavaIOPostRepositoryImpl postRepository;
    private List<Post> posts;

    public String showByWriterId(String id) {

        if (!checkId(id)) {
            return idError;
        }

        try {
            posts = writerRepository.getById(parseLong(id)).getPosts();
        } catch (NoSuchElementException e) {
            return elementNotFoundError;
        }
        return allRight;
    }

    public List<Post> getList() {
        return posts;
    }

    public String addNewPost(String writerId, String content) {

        if (!checkId(writerId)) {
            return idError;
        }

        try {
            Writer writer = writerRepository.getById(parseLong(writerId));
            writer.addPost(postRepository.save(new Post(content)));

            writerRepository.saveData();
        } catch (NoSuchElementException e) {
            return elementNotFoundError;
        }
        return successful;
    }

    public String updatePost(String id, String content) {

        if (!checkId(id)) {
            return idError;
        }

        try {
            postRepository.update(new Post(parseLong(id), content));
        } catch (NoSuchElementException e) {
            return elementNotFoundError;
        }
        return successful;
    }

    public String deletePost(String id) {

        if (!checkId(id)) {
            return idError;
        }

        try {
            postRepository.deleteById(parseLong(id));
        } catch (NoSuchElementException e) {
            return elementNotFoundError;
        }
        return successful;
    }

    public void setWriterRepository(JavaIOWriterRepositoryImpl writerRepository) {
        this.writerRepository = writerRepository;
    }

    public void setPostRepository(JavaIOPostRepositoryImpl postRepository) {
        this.postRepository = postRepository;
    }
}
