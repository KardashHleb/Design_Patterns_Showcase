package org.example.patterns.structural;

import org.example.PatternDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlyweightDemo implements PatternDemo {

    /** Разделяемое (внутреннее) состояние: одинаково у многих деревьев. */
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
        private record Key(String name, String color) {
        }

        private final Map<Key, TreeType> cache = new HashMap<>();

        TreeType getTreeType(String name, String color) {
            return cache.computeIfAbsent(new Key(name, color), key -> new TreeType(name, color));
        }

        int cachedTypes() {
            return cache.size();
        }
    }

    /** Уникальное (внешнее) состояние: координаты + ссылка на общий TreeType. */
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
        TreeFactory factory = new TreeFactory();
        List<Tree> forest = new ArrayList<>();
        forest.add(new Tree(1, 2, factory.getTreeType("Дуб", "зелёный")));
        forest.add(new Tree(5, 8, factory.getTreeType("Дуб", "зелёный")));
        forest.add(new Tree(3, 4, factory.getTreeType("Сосна", "тёмно-зелёный")));
        forest.add(new Tree(7, 1, factory.getTreeType("Сосна", "тёмно-зелёный")));
        forest.add(new Tree(9, 6, factory.getTreeType("Дуб", "зелёный")));

        forest.forEach(Tree::draw);
        System.out.println("  Деревьев: " + forest.size() + ", объектов TreeType: " + factory.cachedTypes());
    }
}
