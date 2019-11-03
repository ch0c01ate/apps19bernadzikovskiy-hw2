package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList immutableLinkedList;

    public Stack() {
        this.setImmutableLinkedList(new ImmutableLinkedList());
    }

    Object peek() {
        return this.getImmutableLinkedList().getFirst();
    }

    Object pop() {
        Object firstObject = this.getImmutableLinkedList().getFirst();
        this.setImmutableLinkedList(this.getImmutableLinkedList().removeFirst());
        return firstObject;
    }

    void push(Object e) {
        this.setImmutableLinkedList(this.getImmutableLinkedList().addFirst(e));
    }

    public ImmutableLinkedList getImmutableLinkedList() {
        return immutableLinkedList;
    }

    public void setImmutableLinkedList(ImmutableLinkedList immutableLinkedList) {
        this.immutableLinkedList = immutableLinkedList;
    }
}