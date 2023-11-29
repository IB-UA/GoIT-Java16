package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {

    static void print(HashMap<String, Integer> map) {
        ArrayList<Integer> list = new ArrayList<>();
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        list.sort(Collections.reverseOrder());
        for (int num : list) {
            for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        sortedMap.forEach((k, v) -> System.out.printf("%s %d\n", k, v));
    }

    public static void calc(File file) {
        HashMap<String, Integer> counter = new HashMap<>(0);
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
               String[] row = scanner.nextLine().split(" ");
               for (String word: row) {
                   if (!word.isBlank()) {
                       counter.put(word, counter.getOrDefault(word, 0) + 1);
                   }
               }
            }

            print(counter);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
