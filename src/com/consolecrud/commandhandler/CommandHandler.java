package com.consolecrud.commandhandler;

import com.consolecrud.commandhandler.processingchain.OperationHandler;
import com.consolecrud.controller.LabelControllerImpl;
import com.consolecrud.controller.PostControllerImpl;
import com.consolecrud.controller.WriterControllerImpl;
import com.consolecrud.view.ShowLabel;
import com.consolecrud.view.ShowPost;
import com.consolecrud.view.ShowWriter;

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

    public void setShowWriter(ShowWriter showWriter) {
        operationHandler.setShowWriter(showWriter);
    }

    public void setShowPost(ShowPost showPost) {
        operationHandler.setShowPost(showPost);
    }

    public void setShowLabel(ShowLabel showLabel) {
        operationHandler.setShowLabel(showLabel);
    }
}
