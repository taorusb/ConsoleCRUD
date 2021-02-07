package com.consolecrud.controller;

import static java.lang.Long.parseLong;

import com.consolecrud.model.Writer;
import com.consolecrud.repository.WriterRepositoryImpl;
import com.consolecrud.view.ShowWriter;

import java.util.NoSuchElementException;

public class WriterControllerImpl implements Controller {

    private WriterRepositoryImpl writerRepository;
    private final ShowWriter showWriter = new ShowWriter();

    public WriterControllerImpl() {
    }

    public void showAll() {
        showWriter.showAll(writerRepository.findAll());
    }

    public void addNewWriter(String firstName, String lastName) {

        if (!checkString(firstName) || !checkString(lastName)) {
            showWriter.printMessage(nameError);
            return;
        }

        writerRepository.save(new Writer(firstName, lastName));

        showAll();
    }

    public void updateWriter(String id, String firstName, String lastName) {

        if (!checkId(id)) {
            showWriter.printMessage(idError);
            return;
        }

        if (!checkString(firstName) || !checkString(lastName)) {
            showWriter.printMessage(nameError);
            return;
        }

        try {
            writerRepository.update(new Writer(parseLong(id), firstName, lastName));
            showAll();
        } catch (NoSuchElementException e) {
            showWriter.printMessage(elementNotFoundError);
        }
    }

    public void deleteWriter(String id) {

        if (!checkId(id)) {
            showWriter.printMessage(idError);
            return;
        }

        try {
            writerRepository.deleteById(parseLong(id));
            showAll();
        } catch (NoSuchElementException e) {
            showWriter.printMessage(elementNotFoundError);
        }
    }

    public void loadData() {
        writerRepository.loadData();
        showWriter.printMessage(dataLoaded);
    }

    public void saveData() {
        writerRepository.saveData();
        showWriter.printMessage(dataSaved);
    }

    public void setWriterRepository(WriterRepositoryImpl writerRepository) {
        this.writerRepository = writerRepository;
    }
}
