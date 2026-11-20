package org.example.patterns.behavioral;

import org.example.PatternDemo;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorDemo implements PatternDemo {

    /**
     * The collection stores books in an array but does not expose that structure:
     * clients only get iterators.
     */
    static class BookShelf implements Iterable<String> {
        private final String[] books;
        private int size;

        BookShelf(int capacity) {
            this.books = new String[capacity];
        }

        void add(String book) {
            if (size == books.length) {
                throw new IllegalStateException("The shelf is full");
            }
            books[size++] = book;
        }

        @Override
        public Iterator<String> iterator() {
            return new ForwardIterator();
        }

        Iterator<String> reverseIterator() {
            return new ReverseIterator();
        }

        private class ForwardIterator implements Iterator<String> {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return books[index++];
            }
        }

        private class ReverseIterator implements Iterator<String> {
            private int index = size - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return books[index--];
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Iterator traverses a collection without exposing its internal structure (an array):");
        BookShelf shelf = new BookShelf(5);
        shelf.add("Clean Code");
        shelf.add("Design Patterns");
        shelf.add("Effective Java");

        System.out.println("Forward traversal (for-each uses iterator()):");
        for (String book : shelf) {
            System.out.println("  → " + book);
        }

        System.out.println("Reverse traversal (another iterator over the same collection):");
        Iterator<String> reverse = shelf.reverseIterator();
        while (reverse.hasNext()) {
            System.out.println("  → " + reverse.next());
        }
    }
}
