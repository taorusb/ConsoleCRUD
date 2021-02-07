package com.consolecrud.repository.intrerfaces;

import java.nio.ByteBuffer;

public interface DataIO {
    String FILE_READING_ERROR = "Error reading \"%s\". Check the file and restart the program.";
    void loadData();
    void saveData();
    default long getLong(String s) {
        return  ByteBuffer.wrap(s.getBytes()).getLong();
    }
}
