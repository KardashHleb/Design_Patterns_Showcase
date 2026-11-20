package org.example.patterns.creational;

import org.example.PatternDemo;

public class AbstractFactoryDemo implements PatternDemo {

    interface Button {
        void render();
    }

    interface Checkbox {
        void render();
    }

    static class WinButton implements Button {
        @Override
        public void render() {
            System.out.println("  → [Windows] Button");
        }
    }

    static class WinCheckbox implements Checkbox {
        @Override
        public void render() {
            System.out.println("  → [Windows] Checkbox");
        }
    }

    static class MacButton implements Button {
        @Override
        public void render() {
            System.out.println("  → [macOS] Button");
        }
    }

    static class MacCheckbox implements Checkbox {
        @Override
        public void render() {
            System.out.println("  → [macOS] Checkbox");
        }
    }

    interface GUIFactory {
        Button createButton();
        Checkbox createCheckbox();
    }

    static class WinFactory implements GUIFactory {
        @Override
        public Button createButton() {
            return new WinButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new WinCheckbox();
        }
    }

    static class MacFactory implements GUIFactory {
        @Override
        public Button createButton() {
            return new MacButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new MacCheckbox();
        }
    }

    static void paint(GUIFactory factory) {
        factory.createButton().render();
        factory.createCheckbox().render();
    }

    @Override
    public void run() {
        System.out.println("Abstract Factory creates a consistent family of UI components:");
        System.out.println("Windows UI:");
        paint(new WinFactory());
        System.out.println("macOS UI:");
        paint(new MacFactory());
    }
}
