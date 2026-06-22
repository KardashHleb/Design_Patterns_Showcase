package org.example.patterns.creational;

import org.example.PatternDemo;

public class PrototypeDemo implements PatternDemo {

    interface Shape extends Cloneable {
        Shape clone();
        void draw();
    }

    static class Circle implements Shape {
        private final int radius;
        private final String color;

        Circle(int radius, String color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public Shape clone() {
            return new Circle(radius, color);
        }

        @Override
        public void draw() {
            System.out.println("  → Круг: r=" + radius + ", color=" + color);
        }
    }

    @Override
    public void run() {
        System.out.println("Prototype клонирует объект без знания конкретного класса:");
        Shape original = new Circle(10, "красный");
        System.out.print("Оригинал: ");
        original.draw();
        Shape copy = original.clone();
        System.out.print("Клон:     ");
        copy.draw();
        System.out.println("  original != copy: " + (original != copy));
    }
}
