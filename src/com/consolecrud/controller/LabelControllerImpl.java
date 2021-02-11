package com.consolecrud.controller;

import static java.lang.Long.parseLong;

import com.consolecrud.model.Label;
import com.consolecrud.model.Post;
import com.consolecrud.repository.io.JavaIOLabelRepositoryImpl;
import com.consolecrud.repository.io.JavaIOPostRepositoryImpl;

import java.util.List;
import java.util.NoSuchElementException;

public class LabelControllerImpl implements Controller {

    private JavaIOLabelRepositoryImpl labelRepository;
    private JavaIOPostRepositoryImpl postRepository;
    private List<Label> labels;

    public LabelControllerImpl() {
    }

    public String showByPostId(String id) {

        if (!checkId(id)) {
            return idError;
        }

        try {
            labels = postRepository.getById(parseLong(id)).getLabels();
        } catch (NoSuchElementException e) {
            return elementNotFoundError;
        }
        return allRight;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public String addNewLabel(String postId, String name) {

        if (!checkId(postId)) {
            return idError;
        }

        try {
            Post post = postRepository.getById(parseLong(postId));
            post.addLabel(labelRepository.save(new Label(name)));
            postRepository.saveData();
        } catch (NoSuchElementException e) {
            return elementNotFoundError;
        }
        return successful;
    }

    public String updateLabel(String id, String name) {

        if (!checkId(id)) {
            return idError;
        }

        try {
            labelRepository.update(new Label(parseLong(id), name));
        } catch (NoSuchElementException e) {
            return elementNotFoundError;
        }
        return successful;
    }

    public String deleteLabel(String id) {

        if (!checkId(id)) {
            return idError;
        }

        try {
            labelRepository.deleteById(parseLong(id));
        } catch (NoSuchElementException e) {
            return elementNotFoundError;
        }
        return successful;
    }

    public void setLabelRepository(JavaIOLabelRepositoryImpl labelRepository) {
        this.labelRepository = labelRepository;
    }

    public void setPostRepository(JavaIOPostRepositoryImpl postRepository) {
        this.postRepository = postRepository;
    }
}
