package com.consolecrud.controller;


public interface Controller {

    String idError = "Incorrect argument. The id value must not contain any letters or special characters.";
    String nameError = "The meaning of the first or last name must not contain numbers or special characters.";
    String elementNotFoundError = "A entity with such id does'n exist.";
    String successful = "successful.";
    String dataSaved = "data saved successfully.\n";
    String dataLoaded = "data loaded successfully.\n";

    default boolean checkString(String s) {
        return s.matches("[\\p{L}|]+");
    }

    default boolean checkId(String s) {
        return s.matches("\\d+");
    }
}
