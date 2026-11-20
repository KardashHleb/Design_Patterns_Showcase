package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(5)
class ConsoleMenuTest {

    private static String runWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        return ConsoleCapture.capture(() -> new ConsoleMenu(in).run());
    }

    @Test
    void exitsOnZero() {
        assertTrue(runWithInput("0\n").contains("До свидания!"));
    }

    @Test
    void exitsGracefullyWhenInputEnds() {
        assertTrue(runWithInput("").contains("До свидания!"));
    }

    @Test
    void exitsGracefullyWhenInputEndsAfterGarbage() {
        String output = runWithInput("abc");
        assertTrue(output.contains("Введите число"));
        assertTrue(output.contains("До свидания!"));
    }

    @Test
    void runsSelectedPatternAndReturnsToMenu() {
        String output = runWithInput("1\n\n0\n");
        assertTrue(output.contains("Singleton (Порождающие)"));
        assertTrue(output.contains("first == second: true"));
        assertTrue(output.contains("До свидания!"));
    }

    @Test
    void rejectsUnknownNumber() {
        assertTrue(runWithInput("99\n0\n").contains("Неверный выбор"));
    }
}
