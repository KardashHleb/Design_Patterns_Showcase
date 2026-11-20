package org.example.patterns.structural;

import org.example.PatternDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlyweightDemo implements PatternDemo {

    /** Shared (intrinsic) state: the same for many trees. */
    static class TreeType {
        private final String name;
        private final String color;

        TreeType(String name, String color) {
            this.name = name;
            this.color = color;
        }

        void render(int x, int y) {
            System.out.println("  → Tree " + name + " (" + color + ") at (" + x + ", " + y + ")");
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

    /** Unique (extrinsic) state: coordinates + a reference to a shared TreeType. */
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
        System.out.println("Flyweight shares common state (TreeType) between trees:");
        TreeFactory factory = new TreeFactory();
        List<Tree> forest = new ArrayList<>();
        forest.add(new Tree(1, 2, factory.getTreeType("Oak", "green")));
        forest.add(new Tree(5, 8, factory.getTreeType("Oak", "green")));
        forest.add(new Tree(3, 4, factory.getTreeType("Pine", "dark green")));
        forest.add(new Tree(7, 1, factory.getTreeType("Pine", "dark green")));
        forest.add(new Tree(9, 6, factory.getTreeType("Oak", "green")));

        forest.forEach(Tree::draw);
        System.out.println("  Trees: " + forest.size() + ", TreeType objects: " + factory.cachedTypes());
    }
}
