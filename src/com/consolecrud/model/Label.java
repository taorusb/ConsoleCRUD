package com.consolecrud.model;

public class Label {

    private long id;
    private String name;

    public Label() {
    }

    public Label(long id) {
        this.id = id;
    }

    public Label(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Label(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Label: " + " id: " + id + ", name: "
                + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return id == label.id;
    }
}
