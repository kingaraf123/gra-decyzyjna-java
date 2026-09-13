package com.example.techcorp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Zapisuje i odczytuje wyniki gier z prostego pliku tekstowego, jedna linia na grę. */
public class ResultSaver {

    private static final String FILE_NAME = "results.txt";

    public static void saveResult(String playerName, int turns, String winner) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String line = timestamp + " | gracz=" + playerName + " | tury=" + turns + " | zwyciezca=" + winner;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Nie udało się zapisać wyniku do pliku: " + e.getMessage());
        }
    }

    public static void printHistory() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Brak wcześniejszych wyników.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Nie udało się odczytać pliku wyników: " + e.getMessage());
        }
    }
}
