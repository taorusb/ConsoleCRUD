package com.consolecrud.model;

import java.util.LinkedList;
import java.util.List;

public class Writer {

    private long id;
    private String firstName;
    private String lastName;
    List<Post> posts = new LinkedList<>();

    public Writer() {
    }

    public Writer(long id) {
        this.id = id;
    }

    public Writer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public int getPostCount() { return posts.size(); }

    @Override
    public String toString() {
        return "Writer: " + "id: " + id + ", firstName: " + firstName +
                ", lastName: " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return id == writer.id;
    }

}
