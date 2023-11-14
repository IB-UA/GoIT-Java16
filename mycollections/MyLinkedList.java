package mycollections;

import mycollections.AbstractMyLinkedList.AbstractMyLinkedList;

public class MyLinkedList <T> extends AbstractMyLinkedList<T> {
    public MyLinkedList() {
        super();
    }

    @Override
    public T remove(int index) {
        return super.remove(index);
    }

    @Override
    public void add(T value) {
        super.add(value);
    }

    @Override
    public void add(int index, T value) {
        super.add(index, value);
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        return super.get(index);
    }
}
