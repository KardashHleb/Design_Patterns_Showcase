package org.example;

public enum PatternCategory {
    CREATIONAL("Порождающие"),
    STRUCTURAL("Структурные"),
    BEHAVIORAL("Поведенческие");

    private final String title;

    PatternCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
