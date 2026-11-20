package org.example;

public enum PatternCategory {
    CREATIONAL("Creational"),
    STRUCTURAL("Structural"),
    BEHAVIORAL("Behavioral");

    private final String title;

    PatternCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
