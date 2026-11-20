package org.example.patterns.creational;

import org.example.PatternDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrototypeDemo implements PatternDemo {

    interface Shape {
        Shape copy();

        void draw();
    }

    static class Circle implements Shape {
        private int radius;
        private String color;
        private final List<String> tags;

        Circle(int radius, String color, List<String> tags) {
            this.radius = radius;
            this.color = color;
            this.tags = new ArrayList<>(tags);
        }

        /** Глубокая копия: список тегов копируется, а не разделяется с оригиналом. */
        private Circle(Circle source) {
            this(source.radius, source.color, source.tags);
        }

        @Override
        public Circle copy() {
            return new Circle(this);
        }

        void setRadius(int radius) {
            this.radius = radius;
        }

        void setColor(String color) {
            this.color = color;
        }

        void addTag(String tag) {
            tags.add(tag);
        }

        int getRadius() {
            return radius;
        }

        String getColor() {
            return color;
        }

        List<String> getTags() {
            return List.copyOf(tags);
        }

        @Override
        public void draw() {
            System.out.println("Круг: r=" + radius + ", color=" + color + ", tags=" + tags);
        }
    }

    /** Реестр готовых прототипов: клиент знает только ключ, а не конкретный класс. */
    static class ShapeRegistry {
        private final Map<String, Shape> prototypes = new HashMap<>();

        void register(String key, Shape prototype) {
            prototypes.put(key, prototype);
        }

        Shape create(String key) {
            Shape prototype = prototypes.get(key);
            if (prototype == null) {
                throw new IllegalArgumentException("Нет прототипа: " + key);
            }
            return prototype.copy();
        }
    }

    @Override
    public void run() {
        System.out.println("Prototype создаёт объекты копированием готового образца:");
        ShapeRegistry registry = new ShapeRegistry();
        registry.register("red-circle", new Circle(10, "красный", List.of("фон")));

        Circle original = (Circle) registry.create("red-circle");
        Circle copy = original.copy();
        copy.setRadius(25);
        copy.setColor("синий");
        copy.addTag("кнопка");

        System.out.print("  → Оригинал: ");
        original.draw();
        System.out.print("  → Копия:    ");
        copy.draw();
        System.out.println("  Изменения копии не затронули оригинал (глубокое копирование).");
    }
}
