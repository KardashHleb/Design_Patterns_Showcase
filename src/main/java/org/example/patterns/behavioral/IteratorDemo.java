package org.example.patterns.behavioral;

import org.example.PatternDemo;

import java.util.Iterator;
import java.util.List;

public class IteratorDemo implements PatternDemo {

    static class BookCollection implements Iterable<String> {
        private final List<String> books;

        BookCollection(List<String> books) {
            this.books = books;
        }

        @Override
        public Iterator<String> iterator() {
            return books.iterator();
        }
    }

    @Override
    public void run() {
        System.out.println("Iterator обходит коллекцию без знания её внутренней структуры:");
        BookCollection collection = new BookCollection(List.of("Clean Code", "Design Patterns", "Effective Java"));
        for (String book : collection) {
            System.out.println("  → " + book);
        }
    }
}
