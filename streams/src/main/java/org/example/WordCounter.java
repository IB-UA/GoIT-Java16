package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class WordCounter {
    private static class DefaultHashMap<K,V> extends HashMap<K,V> {
        protected V defaultValue;
        public DefaultHashMap(V defaultValue) {
            this.defaultValue = defaultValue;
        }
        @Override
        public V get(Object k) {
            return containsKey(k) ? super.get(k) : defaultValue;
        }
    }

    public static void calc(File file) {
        DefaultHashMap<String, Integer> counter = new DefaultHashMap<>(0);
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
               String[] row = scanner.nextLine().split(" ");
               for (String word: row) {
                   if (!word.isBlank()) {
                       counter.put(word, counter.get(word) + 1);
                   }
               }
            }

            counter.forEach((k, v) -> System.out.println(k + " " + v.toString()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
