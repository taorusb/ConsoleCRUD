package com.consolecrud.view;

import com.consolecrud.model.Post;

import java.util.List;

public class ShowPost implements Show<Post> {

    private static final String[] template = {"%-8s%-26s%-12s%-12s%-8s%n", "id", "content", "created", "updated", "labelCount"};

    public ShowPost() {
    }

    @Override
    public void showAll(List<Post> posts) {

        printWriter.printf(template[0], template[1], template[2], template[3], template[4], template[5]);
        posts.forEach(x -> printWriter.printf
                (template[0], x.getId(), x.getContent(), x.getCreated(), x.getUpdated(), x.getLabelCount()));
        printWriter.print("\n");
    }

    @Override
    public void printMessage(String message) {
        printWriter.println(message);
    }
}
