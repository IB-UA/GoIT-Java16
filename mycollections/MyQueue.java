package mycollections;

import mycollections.AbstractMyLinkedList.AbstractMyLinkedList;

public class MyQueue<T> extends AbstractMyLinkedList<T> {
    public MyQueue() {
        super();
    }

    public void add(T value) {
        super.add(0, value);
    }
    public T peek() {
        return super.get(size() - 1);
    }
    public T poll() {
        return super.remove(size() - 1);
    }
}
