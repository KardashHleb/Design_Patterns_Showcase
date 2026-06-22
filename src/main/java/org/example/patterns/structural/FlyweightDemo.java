package org.example.patterns.structural;

import org.example.PatternDemo;

import java.util.HashMap;
import java.util.Map;

public class FlyweightDemo implements PatternDemo {

    static class TreeType {
        private final String name;
        private final String color;

        TreeType(String name, String color) {
            this.name = name;
            this.color = color;
        }

        void render(int x, int y) {
            System.out.println("  → Дерево " + name + " (" + color + ") в (" + x + ", " + y + ")");
        }
    }

    static class TreeFactory {
        private static final Map<String, TreeType> cache = new HashMap<>();

        static TreeType getTreeType(String name, String color) {
            String key = name + color;
            return cache.computeIfAbsent(key, k -> new TreeType(name, color));
        }
    }

    static class Tree {
        private final int x;
        private final int y;
        private final TreeType type;

        Tree(int x, int y, TreeType type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        void draw() {
            type.render(x, y);
        }
    }

    @Override
    public void run() {
        System.out.println("Flyweight разделяет общее состояние (TreeType) между деревьями:");
        TreeType oak = TreeFactory.getTreeType("Дуб", "зелёный");
        TreeType pine = TreeFactory.getTreeType("Сосна", "тёмно-зелёный");
        new Tree(1, 2, oak).draw();
        new Tree(5, 8, oak).draw();
        new Tree(3, 4, pine).draw();
        System.out.println("  Уникальных TreeType в кэше: 2 (вместо 3 объектов)");
    }
}
