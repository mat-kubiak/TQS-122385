package org.example;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private LinkedList<T> collection;

    public TqsStack() {
        collection = new LinkedList<T>();
    }

    public T pop() {
        return collection.pop();
    }
    public void push(T val) {
        collection.push(val);
    }
    public T peek() {
        if (collection.isEmpty()) {
            throw new NoSuchElementException();
        }
        return collection.peek();
    }
    public int size() {
        return collection.size();
    }
    public boolean isEmpty() {
        return collection.isEmpty();
    }
}
