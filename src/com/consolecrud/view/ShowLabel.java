package com.consolecrud.view;

import com.consolecrud.controller.LabelControllerImpl;
import com.consolecrud.model.Label;

public class ShowLabel implements Show<Label> {

    private LabelControllerImpl labelController;
    private static final String[] template = {"%-8s%-22s%n", "id", "name"};

    public ShowLabel(LabelControllerImpl labelController) {
        this.labelController = labelController;
    }

    public void showByPostId(String id) {

        String message = labelController.showByPostId(id);
        if (!message.equals("allRight")) {
            printWriter.println(message);
        } else {
            printWriter.printf(template[0], template[1], template[2]);
            labelController.getLabels()
                    .forEach(x -> printWriter.printf
                            (template[0], x.getId(), x.getName()));
            printWriter.print("\n");
        }
    }

    public void addLabel(String postId, String name) {
        printWriter.println(labelController.addNewLabel(postId, name));
    }

    public void updateLabel(String id, String name) {
        printWriter.println(labelController.updateLabel(id, name));
    }

    public void deleteLabel(String id) {
        printWriter.println(labelController.deleteLabel(id));
    }
}
