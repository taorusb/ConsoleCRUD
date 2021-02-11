package com.consolecrud.commandhandler.processingchain;


import com.consolecrud.view.ShowLabel;
import com.consolecrud.view.ShowPost;
import com.consolecrud.view.ShowWriter;

public class OperationHandler {

    private ModelHandler modelHandler;
    private ShowWriter showWriter;
    private ShowPost showPost;
    private ShowLabel showLabel;

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
                showWriter.loadData();
                break;
            default:
                System.out.println("wrong command: " + strings[0]);
        }
    }

    public void setShowWriter(ShowWriter showWriter) {
        this.showWriter = showWriter;
    }

    public void setShowPost(ShowPost showPost) {
        this.showPost = showPost;
    }

    public void setShowLabel(ShowLabel showLabel) {
        this.showLabel = showLabel;
    }

    public ShowWriter getShowWriter() {
        return showWriter;
    }

    public ShowPost getShowPost() {
        return showPost;
    }

    public ShowLabel getShowLabel() {
        return showLabel;
    }
}
