package com.consolecrud.view;

import com.consolecrud.model.Writer;

import java.util.List;

public class ShowWriter implements Show<Writer> {

    private static final String[] template = {"%-8s%-16s%-16s%-8s%n", "id", "firstName", "lastName", "postCount"};

    public ShowWriter() {
    }

    @Override
    public void showAll(List<Writer> writerList) {

        printWriter.printf(template[0], template[1], template[2], template[3], template[4]);
        writerList
                .forEach(x -> printWriter.printf
                        (template[0], x.getId(), x.getFirstName(), x.getLastName(), x.getPostCount()));
        printWriter.print("\n");
    }

    @Override
    public void printMessage(String message) {
        printWriter.println(message);
    }

}
