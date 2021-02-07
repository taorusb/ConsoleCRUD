package com.consolecrud.commandhandler;

import com.consolecrud.commandhandler.processingchain.OperationHandler;
import com.consolecrud.controller.LabelControllerImpl;
import com.consolecrud.controller.PostControllerImpl;
import com.consolecrud.controller.WriterControllerImpl;

public class CommandHandler {

    private static OperationHandler operationHandler;
    private static CommandHandler instance;

    private CommandHandler() {
    }

    public static CommandHandler getCommandHandler() {
        if (instance == null) {
            instance = new CommandHandler();
            operationHandler = new OperationHandler();
        }
        return instance;
    }

    public void startApp(String[] command) {
        operationHandler.doAction(command);
    }

    public void setWriterController(WriterControllerImpl writerController) {
        operationHandler.setWriterController(writerController);
    }

    public void setPostController(PostControllerImpl postController) {
        operationHandler.setPostController(postController);
    }

    public void setLabelController(LabelControllerImpl labelController) {
        operationHandler.setLabelController(labelController);
    }
}
