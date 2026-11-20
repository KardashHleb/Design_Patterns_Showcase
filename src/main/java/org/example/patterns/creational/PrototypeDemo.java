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

        /** Deep copy: the tags list is copied, not shared with the original. */
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
            System.out.println("Circle: r=" + radius + ", color=" + color + ", tags=" + tags);
        }
    }

    /** Registry of ready-made prototypes: the client knows only a key, not the concrete class. */
    static class ShapeRegistry {
        private final Map<String, Shape> prototypes = new HashMap<>();

        void register(String key, Shape prototype) {
            prototypes.put(key, prototype);
        }

        Shape create(String key) {
            Shape prototype = prototypes.get(key);
            if (prototype == null) {
                throw new IllegalArgumentException("No prototype: " + key);
            }
            return prototype.copy();
        }
    }

    @Override
    public void run() {
        System.out.println("Prototype creates objects by copying a ready-made sample:");
        ShapeRegistry registry = new ShapeRegistry();
        registry.register("red-circle", new Circle(10, "red", List.of("background")));

        Circle original = (Circle) registry.create("red-circle");
        Circle copy = original.copy();
        copy.setRadius(25);
        copy.setColor("blue");
        copy.addTag("button");

        System.out.print("  → Original: ");
        original.draw();
        System.out.print("  → Copy:     ");
        copy.draw();
        System.out.println("  Changing the copy did not affect the original (deep copy).");
    }
}
