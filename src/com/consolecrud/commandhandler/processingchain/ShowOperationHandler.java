package com.consolecrud.commandhandler.processingchain;

import java.util.Arrays;

public class ShowOperationHandler {

    private OperationHandler operationHandler;

    public ShowOperationHandler(OperationHandler operationHandler) {
        this.operationHandler = operationHandler;
    }

    public void doAction(String[] strings, String model) {

        if (strings.length > 3) {

            System.out.print("too much commands: ");

            Arrays.stream(strings).skip(3).forEach(x -> System.out.print(x + " "));

            System.out.println();
            return;
        } else if (strings.length < 3) {
            System.out.println("something is missing.");
            return;
        }

        String argument = strings[2].toLowerCase();

        int length = argument.length();

        switch (model) {
            case "writer":

                if (!argument.equals("all")) {
                    System.out.println("invalid argument name: " + strings[2]);
                    return;
                }

                operationHandler.getShowWriter().showAll();
                break;
            case "post":

                if (!argument.startsWith("writerid=")) {
                    System.out.println("invalid argument name: " + strings[2]);
                    return;
                }

                operationHandler.getShowPost().showByWriterId(argument.substring(9, length));
                break;
            case "label":

                if (!argument.startsWith("postid=")) {
                    System.out.println("invalid argument name: " + strings[2]);
                    return;
                }

                operationHandler.getShowLabel().showByPostId(argument.substring(7, length));
                break;
        }
    }
}

