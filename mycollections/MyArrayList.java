package mycollections;


import java.util.*;

public class MyArrayList<T>{
    public static void main(String[] args) {
        MyArrayList<String> list =  new MyArrayList<>();
        list.add("Hello");
        list.add("Hello1");
        list.add("Hello4");
        System.out.println("list = " + list);
        list.remove(0);
        System.out.println("list = " + list);
    }

    private int size;
    private Object[] items;

    public MyArrayList() {
        size = 0;
        items = new Object[10];
    }

    public void add(Object value) {
        if (size == items.length) {
            grow();
        }
        items[size] = value;
        size++;
    }

    public void remove(int index) {
        System.arraycopy(items, index + 1, items, index, items.length - 1 - index);
        size--;
    }

    public void clear() {
        items = new Object[10];
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        return (T) items[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String[] list = new String[size];
        for (int i = 0; i < size; i++) {
            list[i] = items[i].toString();
        }
        return String.join(", ", list);
    }

    private void grow() {
        items = Arrays.copyOf(items, items.length * 2);
    }

}
