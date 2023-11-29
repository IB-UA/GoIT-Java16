package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneParser {

    private static boolean isValidPhoneNumber(String str) {
        Pattern pattern = Pattern.compile("(^\\(\\d{3}\\)\\s)|(\\d{3}-)\\d{3}-\\d{4}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static void parse(File file) {
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (isValidPhoneNumber(line)) {
                    System.out.println(line);
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
