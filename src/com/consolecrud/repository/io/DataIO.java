package com.consolecrud.repository.io;

import com.consolecrud.model.Label;
import com.consolecrud.model.Post;
import com.consolecrud.model.Writer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataIO {

    private static final String FILE_READING_ERROR = "Error reading \"%s\". Check the file and restart the program.";
    static final String WRITER_TYPE = "writer";
    static final String POST_TYPE = "post";
    static final String LABEL_TYPE = "label";

    static<T> List<T> loadData(String fileLocation, Function<? super String, T> function) {

        try (Stream<String> stream =
                     Files.lines(Path.of(fileLocation))) {

            return stream
                    .skip(1)
                    .map(function)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.err.printf(FILE_READING_ERROR, fileLocation);
        }
        return new LinkedList<>();
    }

    static<T> void saveData(String fileLocation, List<T> savingList, String modelType) {

        try (FileOutputStream outputStream =
                     new FileOutputStream(fileLocation, false)) {

            PrintStream printStream = new PrintStream(outputStream);

            switch (modelType) {
                case WRITER_TYPE:

                    Writer writerForGetId = new Writer();
                    for (T item : savingList) {
                        writerForGetId = (Writer) item;
                    }
                    printStream.print(writerForGetId.getId() + "\n");

                    for (T value : savingList) {
                        Writer writer = (Writer) value;

                        printStream.print(writer.getId());
                        printStream.print("$" + writer.getFirstName() + "$");
                        printStream.print(writer.getLastName() + "$");
                        for (Post post : writer.getPosts()) {
                            printStream.print(post.getId() + ",");
                        }
                        printStream.print("\n");
                    }
                    break;
                case POST_TYPE:

                    Post postForGetId = new Post();
                    for (T t : savingList) {
                        postForGetId = (Post) t;
                    }

                    printStream.print(postForGetId.getId() + "\n");
                    for (T t : savingList) {
                        Post post = (Post) t;

                        printStream.print(post.getId());
                        printStream.print("$" + post.getContent() + "$");
                        printStream.print(post.getCreated() + "$");

                        if (post.getUpdated() == null) {
                            printStream.print("-$");
                        } else {
                            printStream.print(post.getUpdated() + "$");
                        }
                        post.getLabels().forEach(x -> printStream.print(x.getId() + ","));
                        printStream.print("\n");
                    }
                    break;
                case LABEL_TYPE:

                    Label labelForGetId = new Label();
                    for (T t : savingList) {
                        labelForGetId = (Label) t;
                    }

                    printStream.print(labelForGetId.getId() + "\n");
                    for (T t : savingList) {
                        Label label = (Label) t;

                        printStream.print(label.getId());
                        printStream.print("$" + label.getName());
                        printStream.print("\n");
                    }
            }
        } catch (IOException e) {
            System.err.printf(FILE_READING_ERROR, fileLocation);
        }
    }

    static long getId(String fileLocation)  {

        String idFromFile = "";

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileLocation))){
          idFromFile = reader.readLine();

        } catch (IOException e) {
            System.err.printf(FILE_READING_ERROR, fileLocation);
        }
        return (idFromFile != null) ? Long.parseLong(idFromFile) : 0;
    }
}
