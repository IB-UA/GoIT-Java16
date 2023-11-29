package org.example;

import java.io.*;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        PhoneParser.parse(new File(
                Objects.requireNonNull(Main.class.getResource("/phones.txt")).getFile()
        ));
        System.out.println("\n\n____________________________________________________\n\n");
        CsvToJsonUserConverter.process(new File(
                Objects.requireNonNull(Main.class.getResource("/file.txt")).getFile()
        ));
        System.out.println("____________________________________________________\n\n");
        WordCounter.calc(new File(
                Objects.requireNonNull(Main.class.getResource("/words.txt")).getFile()
        ));


    }
}