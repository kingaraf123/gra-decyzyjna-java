package com.example.techcorp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Saves and reads game results from a simple text file, one line per game. */
public class ResultSaver {

    private static final String FILE_NAME = "results.txt";

    public static void saveResult(String playerName, int turns, String winner) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String line = timestamp + " | player=" + playerName + " | turns=" + turns + " | winner=" + winner;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Could not save the result to file: " + e.getMessage());
        }
    }

    public static void printHistory() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No previous results yet.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Could not read the results file: " + e.getMessage());
        }
    }
}
