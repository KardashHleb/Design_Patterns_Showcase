package org.example;

import java.io.InputStream;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner;

    public ConsoleMenu() {
        this(System.in);
    }

    public ConsoleMenu(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
        printWelcome();
        while (true) {
            printMenu();
            System.out.print("Выберите паттерн (1-" + Pattern.values().length + ") или 0 для выхода: ");
            OptionalInt choice = readInt();
            if (choice.isEmpty() || choice.getAsInt() == 0) {
                break;
            }
            Pattern pattern = findByNumber(choice.getAsInt());
            if (pattern == null) {
                System.out.println("Неверный выбор. Попробуйте снова.\n");
                continue;
            }
            runPattern(pattern);
        }
        System.out.println();
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
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        System.out.println();
    }

    private Pattern findByNumber(int number) {
        return Arrays.stream(Pattern.values())
                .filter(p -> p.getNumber() == number)
                .findFirst()
                .orElse(null);
    }

    /** Читает число; пустой OptionalInt — ввод закончился (EOF, например Ctrl+D / Ctrl+Z). */
    private OptionalInt readInt() {
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                return OptionalInt.of(value);
            }
            scanner.next();
            System.out.print("Введите число: ");
        }
        return OptionalInt.empty();
    }
}
