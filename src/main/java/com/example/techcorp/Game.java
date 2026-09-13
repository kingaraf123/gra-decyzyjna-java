package com.example.techcorp;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

// tutaj jest cała pętla gry - ja vs algorytm
public class Game {

    private static final int REQUIRED_WORK = 60;
    private static final double STARTING_CASH = 10000;
    private static final double EVENT_CHANCE = 0.35; // 35% szans na event w turze

    private final Company player;
    private final Company algorithm;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final List<GameEvent> eventPool = List.of(
            new MarketSlowdownEvent(1500),
            new BonusPaymentEvent(1200),
            new ProductivityBoostEvent(6)
    );
    private int turn = 0;

    public Game(String playerName) {
        this.player = new Company(playerName, STARTING_CASH, new Project("Projekt gracza", REQUIRED_WORK));
        this.algorithm = new Company("Algorytm", STARTING_CASH, new Project("Projekt algorytmu", REQUIRED_WORK));
    }

    public void run() {
        System.out.println("=== Gra decyzyjna TechCorp ===");
        System.out.println("Zatrudniaj pracowników w każdej turze i prześcignij algorytm w ukończeniu projektu.\n");

        while (!player.getProject().isFinished() && !algorithm.getProject().isFinished()) {
            turn++;
            System.out.println("--- Tura " + turn + " ---");
            printStatus();
            playerTurn();
            algorithmTurn();
            player.workOneTurn();
            algorithm.workOneTurn();
            triggerRandomEvent();
            System.out.println();
        }

        finishGame();
    }

    private void printStatus() {
        System.out.printf("%-10s gotówka=%.0f  pracownicy=%d  postęp=%d/%d%n",
                player.getName(), player.getCash(), player.getEmployees().size(),
                player.getProject().getProgress(), player.getProject().getRequiredWork());
        System.out.printf("%-10s gotówka=%.0f  pracownicy=%d  postęp=%d/%d%n",
                algorithm.getName(), algorithm.getCash(), algorithm.getEmployees().size(),
                algorithm.getProject().getProgress(), algorithm.getProject().getRequiredWork());
    }

    private void playerTurn() {
        System.out.println("Wybierz akcję:");
        System.out.println("1 - Zatrudnij Developera (koszt 4000, umiejętność 8)");
        System.out.println("2 - Zatrudnij Testera (koszt 2500, umiejętność 5)");
        System.out.println("3 - Zatrudnij Managera (koszt 3000, umiejętność 4)");
        System.out.println("4 - Pomiń turę (zaoszczędź gotówkę)");
        System.out.print("> ");

        int choice = readChoice();
        int teamSize = player.getEmployees().size() + 1;

        try {
            switch (choice) {
                case 1 -> player.hire(new Developer("Dev" + teamSize, 8, 5000), 4000);
                case 2 -> player.hire(new Tester("Tester" + teamSize, 5, 3500), 2500);
                case 3 -> player.hire(new Manager("Manager" + teamSize, 4, 4500), 3000);
                default -> System.out.println("Pomijasz tę turę.");
            }
        } catch (InsufficientFundsException e) {
            System.out.println("Nie udało się zatrudnić: " + e.getMessage());
        }
    }

    private int readChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            // jak ktoś wpisze coś dziwnego, po prostu traktuję to jako pominięcie tury
            return -1;
        }
    }

    private void algorithmTurn() {
        // prosta reguła dla bota, nic wymyślnego
        int teamSize = algorithm.getEmployees().size() + 1;
        try {
            if (algorithm.getCash() >= 4000 && algorithm.getEmployees().size() < 3) {
                algorithm.hire(new Developer("Bot-Dev" + teamSize, 8, 5000), 4000);
                System.out.println(algorithm.getName() + " zatrudnia Developera.");
            } else if (algorithm.getCash() >= 2500) {
                algorithm.hire(new Tester("Bot-Tester" + teamSize, 5, 3500), 2500);
                System.out.println(algorithm.getName() + " zatrudnia Testera.");
            } else {
                System.out.println(algorithm.getName() + " oszczędza gotówkę w tej turze.");
            }
        } catch (InsufficientFundsException e) {
            System.out.println(algorithm.getName() + " nie mógł zatrudnić: " + e.getMessage());
        }
    }

    private void triggerRandomEvent() {
        if (random.nextDouble() > EVENT_CHANCE) {
            return;
        }
        GameEvent event = eventPool.get(random.nextInt(eventPool.size()));
        Company target = random.nextBoolean() ? player : algorithm;
        event.apply(target);
        System.out.println("ZDARZENIE (" + target.getName() + "): " + event.getDescription());
    }

    private void finishGame() {
        System.out.println("=== KONIEC GRY po " + turn + " turach ===");
        printStatus();

        String winner;
        if (player.getProject().isFinished() && algorithm.getProject().isFinished()) {
            winner = "Remis";
        } else if (player.getProject().isFinished()) {
            winner = player.getName();
        } else {
            winner = algorithm.getName();
        }
        System.out.println("Zwycięzca: " + winner);

        ResultSaver.saveResult(player.getName(), turn, winner);

        System.out.println("\nHistoria wyników (results.txt):");
        ResultSaver.printHistory();
    }
}
