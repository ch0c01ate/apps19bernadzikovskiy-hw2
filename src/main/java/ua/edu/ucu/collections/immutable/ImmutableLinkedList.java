package ua.edu.ucu.collections.immutable;

public class ImmutableLinkedList implements ImmutableList {
    private static int NOTFOUNDSTATUS = -1;
    private Node head;
    private int size;

    public ImmutableLinkedList() {
        this.head = new Node(null);
        this.size = 0;
    }

    public ImmutableLinkedList(Object firstValue) {
        this.head = new Node(firstValue);
        this.size = 1;
    }

    public ImmutableLinkedList(Object[] c) {
        this.head = new Node(null);
        this.insertFromArray(c, this.head);
        this.head = this.head.getNext();
        this.size = c.length;
    }

    private Node[] copyElements(Node fromNode, Node toNode, int number) {
        Node currentFromNode = fromNode;
        Node currentToNode = toNode;

        for (int i = 0; i < number; i++) {
            currentToNode.setNext(currentFromNode.copy());
            currentFromNode = currentFromNode.getNext();
            currentToNode = currentToNode.getNext();
        }

        return new Node[]{currentFromNode, currentToNode};
    }

    private Node insertFromArray(Object[] array, Node node) {
        Node currentNode = node;
        for (int i = 0; i < array.length; i++) {
            currentNode.setNext(new Node(array[i]));
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    private void checkIfIndexOutOfBounds(int index) {
        if (index >= this.size() || this.size() == 0 || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return this.add(this.size(), e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        return this.addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return this.addAll(this.size(), c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException("Index is greater than length or less than zero");
        }

        ImmutableLinkedList resultImmutableLinkedList = new ImmutableLinkedList();
        Node tailNode = resultImmutableLinkedList.getHead();
        Node currentFromNode = this.getHead();

        if (index != 0) {
            Node[] nodes = this.copyElements(currentFromNode, tailNode, index);
            currentFromNode = nodes[0];
            tailNode = nodes[1];
        }
        tailNode = this.insertFromArray(c, tailNode);
        this.copyElements(currentFromNode, tailNode, this.size() - index);
        resultImmutableLinkedList.setSize(c.length + this.size());
        resultImmutableLinkedList.setHead(resultImmutableLinkedList.getHead().getNext());

        return resultImmutableLinkedList;
    }

    @Override
    public Object get(int index) {
        checkIfIndexOutOfBounds(index);
        Node currentNode = this.getHead();
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode.getData();
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        return this.setOrRemove(index, null);
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        return this.setOrRemove(index, e);
    }

    private ImmutableLinkedList setOrRemove(int index, Object value) {
        checkIfIndexOutOfBounds(index);
        ImmutableLinkedList result = new ImmutableLinkedList();
        result.setSize(this.size());
        Node[] nodes = copyElements(this.getHead(), result.getHead(), index);
        Node currentFromNode = nodes[0];
        Node currentToNode = nodes[1];
        if (value != null) {
            currentToNode = new Node(value);
        } else {
            result.setSize(result.size() - 1);
        }
        currentFromNode = currentFromNode.getNext();
        copyElements(currentFromNode, currentToNode, this.size() - index - 1);
        result.setHead(result.getHead().getNext());
        return result;
    }

    @Override
    public int indexOf(Object e) {
        Node currentNode = this.getHead();
        for (int i = 0; i < this.size(); i++) {
            if (currentNode.getData().equals(e)) {
                return i;
            }
            currentNode = currentNode.getNext();
        }

        return NOTFOUNDSTATUS;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        Node currentNode = this.getHead();

        for (int i = 0; i < this.size(); i++) {
            result[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }

        return result;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return this.add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return this.add(e);
    }

    public Object getFirst() {
        return this.get(0);
    }

    public Object getLast() {
        return this.get(this.size() - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return this.remove(this.size() - 1);
    }

    static class Node {
        private Object data;
        private Node next;


        Node(Object data) {
            this.data = data;
            this.next = null;
        }

        public Object getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        Node copy() {
            return new Node(this.data);
        }
    }
}