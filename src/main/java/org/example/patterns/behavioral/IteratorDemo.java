package org.example.patterns.behavioral;

import org.example.PatternDemo;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorDemo implements PatternDemo {

    /**
     * Коллекция хранит книги в массиве, но наружу эту структуру не показывает:
     * клиент получает только итераторы.
     */
    static class BookShelf implements Iterable<String> {
        private final String[] books;
        private int size;

        BookShelf(int capacity) {
            this.books = new String[capacity];
        }

        void add(String book) {
            if (size == books.length) {
                throw new IllegalStateException("Полка заполнена");
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
        System.out.println("Iterator обходит коллекцию, не раскрывая её внутреннее устройство (массив):");
        BookShelf shelf = new BookShelf(5);
        shelf.add("Clean Code");
        shelf.add("Design Patterns");
        shelf.add("Effective Java");

        System.out.println("Прямой обход (for-each использует iterator()):");
        for (String book : shelf) {
            System.out.println("  → " + book);
        }

        System.out.println("Обратный обход (другой итератор той же коллекции):");
        Iterator<String> reverse = shelf.reverseIterator();
        while (reverse.hasNext()) {
            System.out.println("  → " + reverse.next());
        }
    }
}
