package com.example.techcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj swoje imię: ");
        String name = scanner.nextLine().trim();
        if (name.isBlank()) {
            name = "Gracz";
        }

        Game game = new Game(name);
        game.run();
    }
}
