package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        printWelcome();
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Выберите паттерн (1-23) или 0 для выхода: ");
            int choice = readInt();
            if (choice == 0) {
                running = false;
                continue;
            }
            Pattern pattern = findByNumber(choice);
            if (pattern == null) {
                System.out.println("Неверный выбор. Попробуйте снова.\n");
                continue;
            }
            runPattern(pattern);
        }
        System.out.println("До свидания!");
    }

    private void printWelcome() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     Design Patterns Showcase — 23 паттерна GoF           ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private void printMenu() {
        for (PatternCategory category : PatternCategory.values()) {
            System.out.println("── " + category.getTitle() + " ──");
            Arrays.stream(Pattern.values())
                    .filter(p -> p.getCategory() == category)
                    .forEach(p -> System.out.printf("  %2d. %-26s — %s%n",
                            p.getNumber(), p.getName(), p.getDescription()));
            System.out.println();
        }
    }

    private void runPattern(Pattern pattern) {
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════");
        System.out.printf("  %s (%s)%n", pattern.getName(), pattern.getCategory().getTitle());
        System.out.println("══════════════════════════════════════════════════════════");
        System.out.println();
        pattern.createDemo().run();
        System.out.println();
        System.out.print("Нажмите Enter для возврата в меню...");
        scanner.nextLine();
        System.out.println();
    }

    private Pattern findByNumber(int number) {
        return Arrays.stream(Pattern.values())
                .filter(p -> p.getNumber() == number)
                .findFirst()
                .orElse(null);
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Введите число: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
