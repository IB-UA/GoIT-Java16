package mycollections;

import mycollections.AbstractMyLinkedList.AbstractMyLinkedList;

public class MyStack<T> extends AbstractMyLinkedList<T> {

    public void push(T value) {
        super.add(0, value);
    }
    public T peek() {
        return super.get(0);
    }
    public T pop() {
        return super.remove(0);
    }
}
