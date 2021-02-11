package com.consolecrud.view;

import com.consolecrud.controller.WriterControllerImpl;
import com.consolecrud.model.Writer;

public class ShowWriter implements Show<Writer> {

    private static final String[] template = {"%-8s%-16s%-16s%-8s%n", "id", "firstName", "lastName", "postCount"};
    private WriterControllerImpl writerController;

    public ShowWriter(WriterControllerImpl writerController) {
        this.writerController = writerController;
    }

    public void showAll() {

        printWriter.printf(template[0], template[1], template[2], template[3], template[4]);
        writerController.showAll()
                .forEach(x -> printWriter.printf
                        (template[0], x.getId(), x.getFirstName(), x.getLastName(), x.getPostCount()));
        printWriter.print("\n");
    }

    public void updateWriter(String id, String firstName, String lastName) {
        printWriter.println(writerController.updateWriter(id, firstName, lastName));
    }

    public void addWriter(String firstName, String lastName) {
        printWriter.println(writerController.addNewWriter(firstName, lastName));
    }

    public void deleteWriter(String id) {
        printWriter.println(writerController.deleteWriter(id));
    }

    public void loadData() {
        printWriter.println(writerController.loadData());
    }
}
