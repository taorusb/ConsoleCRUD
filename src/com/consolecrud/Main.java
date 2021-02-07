package com.consolecrud;

import com.consolecrud.commandhandler.CommandHandler;
import com.consolecrud.controller.LabelControllerImpl;
import com.consolecrud.controller.PostControllerImpl;
import com.consolecrud.controller.WriterControllerImpl;
import com.consolecrud.repository.LabelRepositoryImpl;
import com.consolecrud.repository.PostRepositoryImpl;
import com.consolecrud.repository.WriterRepositoryImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputQuery;

        WriterControllerImpl writerController = new WriterControllerImpl();
        writerController.setWriterRepository(WriterRepositoryImpl.getWriterRepository());
        WriterRepositoryImpl.getWriterRepository().setPostRepository(PostRepositoryImpl.getPostRepository());
        PostRepositoryImpl.getPostRepository().setLabelRepository(LabelRepositoryImpl.getLabelRepository());
        LabelRepositoryImpl.getLabelRepository().setPostRepository(PostRepositoryImpl.getPostRepository());

        PostControllerImpl postController = new PostControllerImpl();
        postController.setPostRepository(PostRepositoryImpl.getPostRepository());
        postController.setWriterRepository(WriterRepositoryImpl.getWriterRepository());

        LabelControllerImpl labelController = new LabelControllerImpl();
        labelController.setLabelRepository(LabelRepositoryImpl.getLabelRepository());
        labelController.setPostRepository(PostRepositoryImpl.getPostRepository());

        CommandHandler commandHandler = CommandHandler.getCommandHandler();
        commandHandler.setWriterController(writerController);
        commandHandler.setPostController(postController);
        commandHandler.setLabelController(labelController);

        while (true) {
            inputQuery = reader.readLine();
            commandHandler.startApp(inputQuery.split(" "));
        }
    }
}
