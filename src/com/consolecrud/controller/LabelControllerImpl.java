package com.consolecrud.controller;

import static java.lang.Long.parseLong;

import com.consolecrud.model.Label;
import com.consolecrud.model.Post;
import com.consolecrud.repository.LabelRepositoryImpl;
import com.consolecrud.repository.PostRepositoryImpl;
import com.consolecrud.view.ShowLabel;

import java.util.NoSuchElementException;

public class LabelControllerImpl implements Controller {

    private LabelRepositoryImpl labelRepository;
    private PostRepositoryImpl postRepository;
    private ShowLabel showLabel = new ShowLabel();

    public void showByPostId(String postId) {

        if (!checkId(postId)) {
            showLabel.printMessage(idError);
            return;
        }

        try {
            showLabel.showAll(postRepository.getById(parseLong(postId)).getLabels());
        } catch (NoSuchElementException e) {
            showLabel.printMessage(elementNotFoundError);
        }
    }

    public void addNewLabel(String postId, String name) {

        if (!checkId(postId)) {
            showLabel.printMessage(idError);
        }

        try {
            Post post = postRepository.getById(parseLong(postId));
            post.addLabel(labelRepository.save(new Label(name)));
            showLabel.printMessage(successful);
        } catch (NoSuchElementException e) {
            showLabel.printMessage(elementNotFoundError);
        }
    }

    public void updateLabel(String id, String name) {

        if (!checkId(id)) {
            showLabel.printMessage(idError);
            return;
        }

        try {
            labelRepository.update(new Label(parseLong(id), name));
            showLabel.printMessage(successful);
        } catch (NoSuchElementException e) {
            showLabel.printMessage(elementNotFoundError);
        }
    }

    public void deleteLabel(String id) {

        if (!checkId(id)) {
            showLabel.printMessage(idError);
            return;
        }

        try {
            labelRepository.deleteById(parseLong(id));
            showLabel.printMessage(successful);
        } catch (NoSuchElementException e) {
            showLabel.printMessage(elementNotFoundError);
        }
    }

    public void setLabelRepository(LabelRepositoryImpl labelRepository) {
        this.labelRepository = labelRepository;
    }

    public void setPostRepository(PostRepositoryImpl postRepository) {
        this.postRepository = postRepository;
    }
}
