package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList immutableLinkedList;

    public Queue() {
        this.setImmutableLinkedList(new ImmutableLinkedList());
    }

    public Object peek() {
        return this.getImmutableLinkedList().getFirst();
    }

    public Object dequeue() {
        Object firstObject = this.getImmutableLinkedList().getFirst();
        this.setImmutableLinkedList(this.getImmutableLinkedList().removeFirst());
        return firstObject;
    }

    public void enqueue(Object e) {
        this.setImmutableLinkedList(this.getImmutableLinkedList().addLast(e));
    }

    public ImmutableLinkedList getImmutableLinkedList() {
        return immutableLinkedList;
    }

    public void setImmutableLinkedList(ImmutableLinkedList immutableLinkedList) {
        this.immutableLinkedList = immutableLinkedList;
    }
}
