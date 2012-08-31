package org.aerogear.proto.todos.data;

/**
 * Copyright (c) Red Hat, Inc., 2012
 */
public class ToDoItem {
    private String text;

    public ToDoItem() {
    }

    public ToDoItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
