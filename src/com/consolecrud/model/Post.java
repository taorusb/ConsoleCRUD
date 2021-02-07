package com.consolecrud.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Post {

    private long id;
    private String content;
    private String created;
    private String updated;
    private long writerId;
    private List<Label> labels = new ArrayList<>();

    public Post() {
    }

    public Post(long id) {
        this.id = id;
    }

    public Post(String content) {
        this.content = content;
    }

    public Post(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Post(String content, long writerId) {
        this.content = content;
        this.writerId = writerId;
    }

    public Post(long id, String content, long writerId) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
    }

    public Post(long id, String content, String created, String updated) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public long getWriterId() {
        return writerId;
    }

    public void setWriterId(long writerId) {
        this.writerId = writerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public void addLabel (Label label) {
        labels.add(label);
    }

    public int getLabelCount() { return labels.size(); }

    @Override
    public String toString() {
        return "Post: " +
                "id: " + id + ", content: " + content + ", created: " + created +
                ", updated: " + updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }
}
