package com.consolecrud.commandhandler.processingchain;


import java.util.Arrays;

public class AddOperationHandler {

    private OperationHandler operationHandler;

    public AddOperationHandler(OperationHandler operationHandler) {
        this.operationHandler = operationHandler;
    }

    public void doAction(String[] strings, String model) {

        if (strings.length > 4) {
            System.out.print("too much commands: ");

            Arrays.stream(strings).skip(4).forEach(x -> System.out.print(x + " "));

            System.out.println();
            return;
        } else if (strings.length < 4) {
            System.out.println("something is missing.");
            return;
        }

        switch (model) {
            case "writer":
                checkWriterField(strings[2], strings[3]);
                break;
            case "post":
                checkPostField(strings[2], strings[3]);
                break;
            case "label":
                checkLabelField(strings[2], strings[3]);
                break;
        }
    }

    private void checkWriterField(String writerFirstName, String writerLastName) {

        int firstNameLength = writerFirstName.length();
        int lastNameLength = writerLastName.length();

        String arg1 = writerFirstName.toLowerCase();
        String arg2 = writerLastName.toLowerCase();

        if (!arg1.startsWith("firstname=")) {
            System.out.println("invalid argument name: " + firstNameLength);
            return;
        }
        if (!arg2.startsWith("lastname=")) {
            System.out.println("invalid argument name: " + lastNameLength);
            return;
        }

        arg1 = writerFirstName.substring(10, firstNameLength);
        arg2 = writerLastName.substring(9, lastNameLength);

        operationHandler.getWriterController().addNewWriter(arg1, arg2);
    }

    private void checkPostField(String writerId, String postContent) {

        int writerIdLength = writerId.length();
        int postContentLength = postContent.length();

        String arg1 = writerId.toLowerCase();
        String arg2 = postContent.toLowerCase();

        if (!arg1.startsWith("writerid=")) {
            System.out.println("invalid argument name: " + writerId);
            return;
        }
        if (!arg2.startsWith("content=")) {
            System.out.println("invalid argument name: " + postContent);
            return;
        }
        arg1 = arg1.substring(9, writerIdLength);
        arg2 = postContent.substring(8, postContentLength);

        operationHandler.getPostController().addNewPost(arg1, arg2);
    }

    private void checkLabelField(String postId, String labelName) {

        int postIdLength = postId.length();
        int nameLength = labelName.length();

        String arg1 = postId.toLowerCase();
        String arg2 = labelName.toLowerCase();

        if (!arg1.startsWith("postid=")) {
            System.out.println("invalid argument name: " + postId);
            return;
        }
        if (!arg2.startsWith("name=")) {
            System.out.println("invalid argument name: " + labelName);
            return;
        }

        arg1 = arg1.substring(7, postIdLength);
        arg2 = labelName.substring(5, nameLength);

        operationHandler.getLabelController().addNewLabel(arg1, arg2);
    }
}
