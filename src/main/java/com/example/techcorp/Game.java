package com.example.techcorp;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Turn-based race: the player's company vs. an algorithm-controlled company.
 * Both start with the same cash and the same required work; whoever finishes
 * the project first wins.
 */
public class Game {

    private static final int REQUIRED_WORK = 60;
    private static final double STARTING_CASH = 10000;
    private static final double EVENT_CHANCE = 0.35;

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
        this.player = new Company(playerName, STARTING_CASH, new Project("Player Project", REQUIRED_WORK));
        this.algorithm = new Company("Algorithm", STARTING_CASH, new Project("Algorithm Project", REQUIRED_WORK));
    }

    public void run() {
        System.out.println("=== TechCorp Decision Game ===");
        System.out.println("Hire employees each turn and race the algorithm to finish your project first.\n");

        while (!player.getProject().isFinished() && !algorithm.getProject().isFinished()) {
            turn++;
            System.out.println("--- Turn " + turn + " ---");
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
        System.out.printf("%-10s cash=%.0f  employees=%d  progress=%d/%d%n",
                player.getName(), player.getCash(), player.getEmployees().size(),
                player.getProject().getProgress(), player.getProject().getRequiredWork());
        System.out.printf("%-10s cash=%.0f  employees=%d  progress=%d/%d%n",
                algorithm.getName(), algorithm.getCash(), algorithm.getEmployees().size(),
                algorithm.getProject().getProgress(), algorithm.getProject().getRequiredWork());
    }

    private void playerTurn() {
        System.out.println("Choose an action:");
        System.out.println("1 - Hire Developer (cost 4000, skill 8)");
        System.out.println("2 - Hire Tester (cost 2500, skill 5)");
        System.out.println("3 - Hire Manager (cost 3000, skill 4)");
        System.out.println("4 - Skip this turn (save cash)");
        System.out.print("> ");

        int choice = readChoice();
        int teamSize = player.getEmployees().size() + 1;

        try {
            switch (choice) {
                case 1 -> player.hire(new Developer("Dev" + teamSize, 8, 5000), 4000);
                case 2 -> player.hire(new Tester("Tester" + teamSize, 5, 3500), 2500);
                case 3 -> player.hire(new Manager("Manager" + teamSize, 4, 4500), 3000);
                default -> System.out.println("Skipping this turn.");
            }
        } catch (InsufficientFundsException e) {
            System.out.println("Could not hire: " + e.getMessage());
        }
    }

    private int readChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /** Very simple algorithm opponent: hires while it can afford it, otherwise saves cash. */
    private void algorithmTurn() {
        int teamSize = algorithm.getEmployees().size() + 1;
        try {
            if (algorithm.getCash() >= 4000 && algorithm.getEmployees().size() < 3) {
                algorithm.hire(new Developer("Bot-Dev" + teamSize, 8, 5000), 4000);
                System.out.println(algorithm.getName() + " hires a Developer.");
            } else if (algorithm.getCash() >= 2500) {
                algorithm.hire(new Tester("Bot-Tester" + teamSize, 5, 3500), 2500);
                System.out.println(algorithm.getName() + " hires a Tester.");
            } else {
                System.out.println(algorithm.getName() + " saves cash this turn.");
            }
        } catch (InsufficientFundsException e) {
            System.out.println(algorithm.getName() + " could not hire: " + e.getMessage());
        }
    }

    /** With EVENT_CHANCE probability, applies a random event to a randomly picked company. */
    private void triggerRandomEvent() {
        if (random.nextDouble() > EVENT_CHANCE) {
            return;
        }
        GameEvent event = eventPool.get(random.nextInt(eventPool.size()));
        Company target = random.nextBoolean() ? player : algorithm;
        event.apply(target);
        System.out.println("EVENT (" + target.getName() + "): " + event.getDescription());
    }

    private void finishGame() {
        System.out.println("=== GAME OVER after " + turn + " turns ===");
        printStatus();

        String winner;
        if (player.getProject().isFinished() && algorithm.getProject().isFinished()) {
            winner = "Tie";
        } else if (player.getProject().isFinished()) {
            winner = player.getName();
        } else {
            winner = algorithm.getName();
        }
        System.out.println("Winner: " + winner);

        ResultSaver.saveResult(player.getName(), turn, winner);

        System.out.println("\nResult history (results.txt):");
        ResultSaver.printHistory();
    }
}
