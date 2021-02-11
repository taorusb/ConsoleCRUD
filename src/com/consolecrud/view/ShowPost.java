package com.consolecrud.view;

import com.consolecrud.controller.PostControllerImpl;
import com.consolecrud.model.Post;

public class ShowPost implements Show<Post> {

    private PostControllerImpl postController;
    private static final String[] template = {"%-8s%-26.20s%-12s%-12s%-8s%n", "id", "content", "created", "updated", "labelCount"};

    public ShowPost(PostControllerImpl postController) {
        this.postController = postController;
    }

    public void showByWriterId(String id) {

        String message = postController.showByWriterId(id);

        if (!message.equals("allRight")) {
            printWriter.println(message);
        } else {
            printWriter.printf(template[0], template[1], template[2], template[3], template[4], template[5]);
            postController.getList().forEach(x -> printWriter.printf
                    (template[0], x.getId(), x.getContent(), x.getCreated(), x.getUpdated(), x.getLabelCount()));
            printWriter.print("\n");

        }
    }

    public void addPost(String writerId, String content) {
        printWriter.println(postController.addNewPost(writerId, content));
    }

    public void updatePost(String id, String content) {
        printWriter.println(postController.updatePost(id, content));
    }

    public void deletePost(String id) {
        printWriter.println(postController.deletePost(id));
    }

}
