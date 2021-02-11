package com.consolecrud;

import com.consolecrud.commandhandler.CommandHandler;
import com.consolecrud.controller.LabelControllerImpl;
import com.consolecrud.controller.PostControllerImpl;
import com.consolecrud.controller.WriterControllerImpl;
import com.consolecrud.repository.io.JavaIOLabelRepositoryImpl;
import com.consolecrud.repository.io.JavaIOPostRepositoryImpl;
import com.consolecrud.repository.io.JavaIOWriterRepositoryImpl;
import com.consolecrud.view.ShowLabel;
import com.consolecrud.view.ShowPost;
import com.consolecrud.view.ShowWriter;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputQuery;

        WriterControllerImpl writerController = new WriterControllerImpl();
        writerController.setWriterRepository(JavaIOWriterRepositoryImpl.getWriterRepository());
        JavaIOWriterRepositoryImpl.getWriterRepository().setPostRepository(JavaIOPostRepositoryImpl.getPostRepository());
        JavaIOPostRepositoryImpl.getPostRepository().setLabelRepository(JavaIOLabelRepositoryImpl.getLabelRepository());
        JavaIOLabelRepositoryImpl.getLabelRepository().setPostRepository(JavaIOPostRepositoryImpl.getPostRepository());

        PostControllerImpl postController = new PostControllerImpl();
        postController.setPostRepository(JavaIOPostRepositoryImpl.getPostRepository());
        postController.setWriterRepository(JavaIOWriterRepositoryImpl.getWriterRepository());

        LabelControllerImpl labelController = new LabelControllerImpl();
        labelController.setLabelRepository(JavaIOLabelRepositoryImpl.getLabelRepository());
        labelController.setPostRepository(JavaIOPostRepositoryImpl.getPostRepository());

        CommandHandler commandHandler = CommandHandler.getCommandHandler();
        commandHandler.setShowWriter(new ShowWriter(writerController));
        commandHandler.setShowPost(new ShowPost(postController));
        commandHandler.setShowLabel(new ShowLabel(labelController));


        while (true) {
            inputQuery = reader.readLine();
            commandHandler.startApp(inputQuery.split(" "));
        }
    }
}
