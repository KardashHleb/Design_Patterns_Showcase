package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PatternTest {

    @Test
    void containsAll23GofPatterns() {
        assertEquals(23, Pattern.values().length);
    }

    @Test
    void numbersAreUniqueAndGoFrom1To23() {
        Set<Integer> numbers = Arrays.stream(Pattern.values())
                .map(Pattern::getNumber)
                .collect(Collectors.toSet());
        Set<Integer> expected = IntStream.rangeClosed(1, 23).boxed().collect(Collectors.toSet());
        assertEquals(expected, numbers);
    }

    @Test
    void categoriesHaveExpectedSizes() {
        assertEquals(5, count(PatternCategory.CREATIONAL));
        assertEquals(7, count(PatternCategory.STRUCTURAL));
        assertEquals(11, count(PatternCategory.BEHAVIORAL));
    }

    @ParameterizedTest
    @EnumSource(Pattern.class)
    void everyDemoRunsAndPrintsSomething(Pattern pattern) {
        String output = ConsoleCapture.capture(() -> pattern.createDemo().run());
        assertFalse(output.isBlank(), pattern.getName() + " printed nothing");
    }

    private static long count(PatternCategory category) {
        return Arrays.stream(Pattern.values()).filter(p -> p.getCategory() == category).count();
    }
}
