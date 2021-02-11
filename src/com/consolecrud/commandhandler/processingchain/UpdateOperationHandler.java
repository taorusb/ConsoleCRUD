package com.consolecrud.commandhandler.processingchain;

import java.util.Arrays;

public class UpdateOperationHandler {

    private OperationHandler operationHandler;

    public UpdateOperationHandler(OperationHandler operationHandler) {
        this.operationHandler = operationHandler;
    }

    public void doAction(String[] strings, String model) {

        if (strings.length > 5) {
            System.out.print("too much commands: ");

            Arrays.stream(strings).skip(5).forEach(x -> System.out.print(x + " "));

            System.out.println();
            return;
        } else if (strings.length < 4) {
            System.out.println("something is missing.");
            return;
        }

        switch (model) {
            case "writer":
                if (strings.length < 5) {
                    System.out.println("something is missing.");
                    return;
                }
                checkWriterField(strings[2], strings[3], strings[4]);
                break;
            case "post":
                checkPostField(strings[2], strings[3]);
                break;
            case "label":
                checkLabelField(strings[2], strings[3]);
                break;
        }
    }

    private void checkWriterField(String writerId, String writerFirstName, String writerLastName) {

        int writerIdLength = writerId.length();
        int writerFirstNameLength = writerFirstName.length();
        int writerLastNameLength = writerLastName.length();

        String arg1 = writerId.toLowerCase();
        String arg2 = writerFirstName.toLowerCase();
        String arg3 = writerLastName.toLowerCase();

        if (!arg1.startsWith("id=")) {
            System.out.println("invalid argument name: " + writerId);
            return;
        }
        if (!arg2.startsWith("firstname=")) {
            System.out.println("invalid argument name: " + writerFirstName);
            return;
        }
        if (!arg3.startsWith("lastname=")) {
            System.out.println("invalid argument name: " + writerLastName);
            return;
        }

        arg1 = arg1.substring(3, writerIdLength);
        arg2 = writerFirstName.substring(10, writerFirstNameLength);
        arg3 = writerLastName.substring(9, writerLastNameLength);

        operationHandler.getShowWriter().updateWriter(arg1, arg2, arg3);
    }

    private void checkPostField(String postId, String postContent) {

        int postIdLength = postId.length();
        int postContentLength = postContent.length();

        String arg1 = postId.toLowerCase();
        String arg2 = postContent.toLowerCase();

        if (!arg1.startsWith("id=")) {
            System.out.println("invalid argument name: " + postId);
            return;
        }
        if (!arg2.startsWith("content=")) {
            System.out.println("invalid argument name: " + postContent);
            return;
        }

        arg1 = arg1.substring(3, postIdLength);
        arg2 = postContent.substring(8, postContentLength);

        operationHandler.getShowPost().updatePost(arg1, arg2);
    }

    private void checkLabelField(String labelId, String labelName) {

        int labelIdLength = labelId.length();
        int nameLength = labelName.length();

        String arg1 = labelId.toLowerCase();
        String arg2 = labelName.toLowerCase();

        if (!arg1.startsWith("id=")) {
            System.out.println("invalid argument name: " + labelId);
            return;
        }
        if (!arg2.startsWith("name=")) {
            System.out.println("invalid argument name: " + labelName);
            return;
        }

        arg1 = arg1.substring(3, labelIdLength);
        arg2 = arg2.substring(5, nameLength);

        operationHandler.getShowLabel().updateLabel(arg1, arg2);
    }
}
