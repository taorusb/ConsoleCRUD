package com.consolecrud.commandhandler.processingchain;

import java.util.Arrays;

public class DeleteOperationHandler {

    private OperationHandler operationHandler;

    public DeleteOperationHandler(OperationHandler operationHandler) {
        this.operationHandler = operationHandler;
    }

    public DeleteOperationHandler() {
    }

    public void doAction(String[] strings, String model) {

        if (strings.length > 3) {

            System.out.print("too much commands: ");

            Arrays.stream(strings).skip(3).forEach(x -> System.out.print(x + " "));

            System.out.println();
            return;
        } else if (strings.length < 3) {
            System.out.println("something is missing.");
        }

        if (!strings[2].startsWith("id=")) {
            System.out.println("invalid argument name: " + strings[2]);
            return;
        }

        String arg;
        int length = strings[2].length();

        switch (model) {
            case "writer":
                arg = strings[2].substring(3, length);
                operationHandler.getWriterController().deleteWriter(arg);
                break;
            case "post":
                arg = strings[2].substring(3, length);
                operationHandler.getPostController().deletePost(arg);
                break;
            case "label":
                arg = strings[2].substring(3, length);
                operationHandler.getLabelController().deleteLabel(arg);
                break;
        }
    }
}
