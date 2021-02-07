package com.consolecrud.commandhandler.processingchain;

import com.consolecrud.controller.LabelControllerImpl;
import com.consolecrud.controller.PostControllerImpl;
import com.consolecrud.controller.WriterControllerImpl;

public class OperationHandler {

    private ModelHandler modelHandler;
    protected WriterControllerImpl writerController;
    protected PostControllerImpl postController;
    protected LabelControllerImpl labelController;

    public OperationHandler() {
        modelHandler = new ModelHandler(this);
    }

    public void doAction(String[] strings) {

        if (strings.length == 0) {
            System.out.println("no any command.");
        }

        String operation = strings[0].toLowerCase();

        switch (operation) {
            case "show":
                modelHandler.doAction(strings, operation);
                break;
            case "add":
                modelHandler.doAction(strings, operation);
                break;
            case "update":
                modelHandler.doAction(strings, operation);
                break;
            case "delete":
                modelHandler.doAction(strings, operation);
                break;
            case "loaddata":
                writerController.loadData();
                break;
            case "savedata":
                writerController.saveData();
                break;
            default:
                System.out.println("wrong command: " + strings[0]);
        }
    }

    public void setWriterController(WriterControllerImpl writerController) {
        this.writerController = writerController;
    }

    public void setPostController(PostControllerImpl postController) {
        this.postController = postController;
    }

    public void setLabelController(LabelControllerImpl labelController) {
        this.labelController = labelController;
    }

    public WriterControllerImpl getWriterController() {
        return writerController;
    }

    public PostControllerImpl getPostController() {
        return postController;
    }

    public LabelControllerImpl getLabelController() {
        return labelController;
    }
}
