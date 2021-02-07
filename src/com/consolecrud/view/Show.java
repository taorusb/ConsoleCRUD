package com.consolecrud.view;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public interface Show<T> {

    InputStreamReader reader = new InputStreamReader(System.in);
    OutputStreamWriter writer = new OutputStreamWriter(System.out);
    PrintWriter printWriter = new PrintWriter(writer, true);

    void printMessage(String message);
    void showAll(List<T> list);
}
