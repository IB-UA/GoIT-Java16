package mycollections;

import java.util.Arrays;

public class MyHashMap <K, V> {
    private static class Entry <EK, EV> {
        private final EK key;
        private EV value;

        private Entry<EK, EV> nextEl;


        public Entry(EK key, EV value) {
            this.key = key;
            this.value = value;
        }

        public EK getKey() {
            return key;
        }

        public EV getValue() {
            return value;
        }

        public void setValue(EV value) {
            this.value = value;
        }

        public Entry<EK, EV> getNextEl() {
            return nextEl;
        }

        public void setNextEl(Entry<EK, EV> nextEl) {
            this.nextEl = nextEl;
        }
    }

    private int size;

    private final Entry<K, V>[] buckets = new Entry[10];

    public int size() {
        return size;
    }
    public void put(K key, V value) {
        int hash = key.hashCode();
        int bucketNumber = hash % buckets.length;
        Entry<K, V> entry = new Entry<>(key, value);
        if (buckets[bucketNumber] == null) {
            buckets[bucketNumber] = entry;
        } else {
            Entry<K, V> current = buckets[bucketNumber];
            Entry<K, V> prev = null;
            do {
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    return;
                }
                prev = current;
                current = current.getNextEl();
            } while (current != null);
            prev.setNextEl(entry);
        }
        size++;
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        return entry != null ? entry.getValue() : null;
    }

    public void clear() {
        Arrays.fill(buckets, null);
        size = 0;
    }

    public V remove(K key) {
        int hash = key.hashCode();
        int bucketNumber = hash % buckets.length;
        if (buckets[bucketNumber] == null) {
            return null;
        }
        Entry<K, V> prev = null;
        Entry<K, V> currentEntry = buckets[bucketNumber];
        Entry<K, V> next = currentEntry.getNextEl();

        do {
            if(currentEntry.getKey().equals(key)){
                if (prev == null) {
                    buckets[bucketNumber] = next;
                } else {
                    prev.setNextEl(next);
                }
                size--;
                return currentEntry.getValue();
            }
            prev = currentEntry;
            currentEntry = next;
            if (currentEntry != null) {
                next = currentEntry.getNextEl();
            }
        } while (next != null);

        return null;

    }

    private Entry<K, V> getEntry(K key) {
        int hash = key.hashCode();
        int bucketNumber = hash % buckets.length;
        var entry = buckets[bucketNumber];
        while (entry != null) {
            var entryKey = entry.getKey();
            if (entryKey.equals(key)) {
                return entry;
            } else {
                entry = entry.getNextEl();
            }
        }
        return null;
    }
}


