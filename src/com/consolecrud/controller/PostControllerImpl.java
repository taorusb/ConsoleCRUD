package com.consolecrud.controller;

import static java.lang.Long.parseLong;

import com.consolecrud.model.Post;
import com.consolecrud.model.Writer;
import com.consolecrud.repository.PostRepositoryImpl;
import com.consolecrud.repository.WriterRepositoryImpl;
import com.consolecrud.view.ShowPost;

import java.util.NoSuchElementException;

public class PostControllerImpl implements Controller {

    private WriterRepositoryImpl writerRepository;
    private PostRepositoryImpl postRepository;
    private ShowPost showPost = new ShowPost();

    public void showByWriterId(String writerId) {

        if (!checkId(writerId)) {
            showPost.printMessage(idError);
            return;
        }

        try {
            showPost.showAll(writerRepository.getById(parseLong(writerId)).getPosts());
        } catch (NoSuchElementException e) {
            showPost.printMessage(elementNotFoundError);
        }
    }

    public void addNewPost(String writerId, String content) {

        if (!checkId(writerId)) {
            showPost.printMessage(idError);
            return;
        }

        try {
            Writer writer = writerRepository.getById(parseLong(writerId));
            writer.addPost(postRepository.save(new Post(content)));
            showPost.printMessage(successful);
        } catch (NoSuchElementException e) {
            showPost.printMessage(elementNotFoundError);
        }
    }

    public void updatePost(String id, String content) {

        if (!checkId(id)) {
            showPost.printMessage(idError);
            return;
        }

        try {
            postRepository.update(new Post(parseLong(id), content));
            showPost.printMessage(successful);
        } catch (NoSuchElementException e) {
            showPost.printMessage(elementNotFoundError);
        }
    }

    public void deletePost(String id) {

        if (!checkId(id)) {
            showPost.printMessage(idError);
            return;
        }

        try {
            postRepository.deleteById(parseLong(id));
            showPost.printMessage(successful);
        } catch (NoSuchElementException e) {
            showPost.printMessage(elementNotFoundError);
        }
    }

    public void setWriterRepository(WriterRepositoryImpl writerRepository) {
        this.writerRepository = writerRepository;
    }

    public void setPostRepository(PostRepositoryImpl postRepository) {
        this.postRepository = postRepository;
    }
}
