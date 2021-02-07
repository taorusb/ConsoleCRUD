package com.consolecrud.commandhandler.processingchain;

import java.util.function.BiConsumer;

public class ModelHandler {

    private String operationType;
    private ShowOperationHandler showOperationHandler;
    private AddOperationHandler addOperationHandler;
    private UpdateOperationHandler updateOperationHandler;
    private DeleteOperationHandler deleteOperationHandler;
    private OperationHandler operationHandler;

    public ModelHandler(OperationHandler operationHandler) {
        this.operationHandler = operationHandler;
        showOperationHandler = new ShowOperationHandler(operationHandler);
        addOperationHandler = new AddOperationHandler(operationHandler);
        updateOperationHandler = new UpdateOperationHandler(operationHandler);
        deleteOperationHandler = new DeleteOperationHandler(operationHandler);
    }

    public void doAction(String[] strings, String operationType) {

        this.operationType = operationType;

        if (strings.length < 2) {
            System.out.println("no any model.");
            return;
        }

        String model = strings[1].toLowerCase();
        BiConsumer<String[], String> check = this::checkModelAction;

        switch (model) {
            case "writer":
                check.accept(strings, model);
                break;
            case "post":
                check.accept(strings, model);
                break;
            case "label":
                check.accept(strings, model);
                break;
            default:
                System.out.println("wrong model: " + model);
        }
    }

    void checkModelAction(String[] arr, String model) {
        switch (operationType) {
            case "show":
                showOperationHandler.doAction(arr, model);
                break;
            case "add":
                addOperationHandler.doAction(arr, model);
                break;
            case "update":
                updateOperationHandler.doAction(arr, model);
                break;
            case "delete":
                deleteOperationHandler.doAction(arr, model);
                break;
            default:
                System.out.println("wrong command type: " + operationType);
        }
    }
}
