package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvToJsonUserConverter {
    public static void process(File file) {
        File folder = file.getParentFile();
        try (BufferedReader bis = new BufferedReader(new FileReader(file))) {
            List<String> colNames = Arrays.asList(bis.readLine().split(" "));
            int nameColIndex = colNames.indexOf("name");
            int ageColIndex = colNames.indexOf("age");
            if (colNames.size() < 2 && nameColIndex == -1 && ageColIndex == -1) {
                System.out.println("column headers to process users not provided");
                return;
            }
            List<User> userList = new ArrayList<>();
            String str;
            while ((str = bis.readLine()) != null) {
                String[] row = str.split(" ");
                userList.add(new User(row[nameColIndex], Integer.parseInt(row[ageColIndex])));
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(userList);
            File outputFile = new File(folder.getPath() + "/users.json");
            try (FileWriter fw = new FileWriter(outputFile)){
                fw.write(json);
                fw.flush();
                System.out.println("File was successfully created -> " + outputFile.getCanonicalFile());
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
