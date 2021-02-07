package com.consolecrud.view;

import com.consolecrud.model.Label;

import java.util.List;

public class ShowLabel implements Show<Label> {

    private static final String[] template = {"%-8s%-22s%n", "id", "name"};

    @Override
    public void showAll(List<Label> labels) {

        printWriter.printf(template[0], template[1], template[2]);
        labels
                .forEach(x -> printWriter.printf
                        (template[0], x.getId(), x.getName()));
        printWriter.print("\n");
    }

    @Override
    public void printMessage(String message) {
        printWriter.println(message);
    }
}
