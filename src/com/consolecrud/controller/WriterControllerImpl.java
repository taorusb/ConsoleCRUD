package com.consolecrud.controller;

import static java.lang.Long.parseLong;

import com.consolecrud.model.Writer;
import com.consolecrud.repository.io.JavaIOWriterRepositoryImpl;

import java.util.List;
import java.util.NoSuchElementException;

public class WriterControllerImpl implements Controller {

    private JavaIOWriterRepositoryImpl writerRepository;

    public WriterControllerImpl() {
    }

    public List<Writer> showAll() {
        return writerRepository.findAll();
    }

    public String addNewWriter(String firstName, String lastName) {

        if (!checkString(firstName) || !checkString(lastName)) {
            return nameError;
        }

        writerRepository.save(new Writer(firstName, lastName));

        return successful;
    }

    public String updateWriter(String id, String firstName, String lastName) {

        if (!checkId(id)) {
            return idError;
        }

        if (!checkString(firstName) || !checkString(lastName)) {
            return nameError;
        }

        try {
            writerRepository.update(new Writer(parseLong(id), firstName, lastName));
            showAll();
        } catch (NoSuchElementException e) {
            return elementNotFoundError;
        }
        return successful;
    }

    public String deleteWriter(String id) {

        if (!checkId(id)) {
            return idError;
        }

        try {
            writerRepository.deleteById(parseLong(id));
        } catch (NoSuchElementException e) {
            return elementNotFoundError;
        }
        return successful;
    }

    public String loadData() {
        writerRepository.loadData();
        return dataLoaded;
    }

    public void setWriterRepository(JavaIOWriterRepositoryImpl writerRepository) {
        this.writerRepository = writerRepository;
    }
}
