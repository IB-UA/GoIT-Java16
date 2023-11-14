package mycollections.AbstractMyLinkedList;

import java.util.Objects;

public abstract class AbstractMyLinkedList<T> {

    static protected class Node<Y> {
        private Node<Y> prevNode;
        private Node<Y> nextNode;
        private Y value;

        public Node(Y value) {
            this.value = value;
        }

        public Node<Y> getPrevNode() {
            return prevNode;
        }

        public Node<Y> getNextNode() {
            return nextNode;
        }

        public Y getValue() {
            return value;
        }

        public void setPrevNode(Node<Y> prevNode) {
            this.prevNode = prevNode;
        }

        public void setNextNode(Node<Y> nextNode) {
            this.nextNode = nextNode;
        }

        public void setValue(Y value) {
            this.value = value;
        }
    }

    protected Node<T> firstNode;
    protected Node<T> lastNode;

    private int size;

    protected AbstractMyLinkedList() {
        size = 0;
    }

    public int size() {
        return size;
    }

    protected T remove(int index) {
        Node<T> node = getNode(index);
        Node<T> prevNode = node.getPrevNode();
        Node<T> nextNode = node.getNextNode();

        if (prevNode == null) {
            firstNode = nextNode;
        } else {
            prevNode.setNextNode(nextNode);
        }

        if (nextNode == null) {
            lastNode = prevNode;
        } else {
            nextNode.setPrevNode(prevNode);
        }

        size--;

        return node.getValue();
    }

    protected void add(int index, T value) throws IndexOutOfBoundsException {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index is larger than ");
        }

        Node<T> node = new Node<>(value);

        if (index == 0) {
            if (firstNode == null) {
                firstNode = node;
                lastNode = node;
            } else {
                node.setNextNode(firstNode);
                firstNode.setPrevNode(node);
                firstNode = node;
            }
        } else if (index == size) {
            node.setPrevNode(lastNode);
            lastNode.setNextNode(node);
            lastNode = node;
        } else {
           Node<T> nNode = getNode(index);
           Node<T> pNode = nNode.getPrevNode();
           node.setNextNode(nNode);
           node.setPrevNode(pNode);
           pNode.setNextNode(node);
           nNode.setPrevNode(node);
        }

        size++;
    }

    protected void add(T value) {
        this.add(size, value);
    }

    public void clear() {
        size = 0;
        firstNode = null;
        lastNode = null;
    }

    protected T get(int index) throws IndexOutOfBoundsException {
        return getNode(index).getValue();
    }

    private Node<T> getNode(int index) {
        Objects.checkIndex(index, size);
        Node<T> currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }

    @Override
    public String toString() {
        Node<T> currentNode = firstNode;
        String[] arr = new String[size];
        for (int i = 0; i < size; i++, currentNode = currentNode.getNextNode()) {
            arr[i] = currentNode.getValue().toString();
        }
        return String.join(", ", arr);
    }
}
