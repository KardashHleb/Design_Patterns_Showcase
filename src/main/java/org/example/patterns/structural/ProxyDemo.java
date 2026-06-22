package org.example.patterns.structural;

import org.example.PatternDemo;

public class ProxyDemo implements PatternDemo {

    interface Image {
        void display();
    }

    static class RealImage implements Image {
        private final String filename;

        RealImage(String filename) {
            this.filename = filename;
            loadFromDisk();
        }

        private void loadFromDisk() {
            System.out.println("  → Загрузка с диска: " + filename);
        }

        @Override
        public void display() {
            System.out.println("  → Отображение: " + filename);
        }
    }

    static class ImageProxy implements Image {
        private final String filename;
        private RealImage realImage;

        ImageProxy(String filename) {
            this.filename = filename;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            }
            realImage.display();
        }
    }

    @Override
    public void run() {
        System.out.println("Proxy откладывает создание тяжёлого объекта до первого использования:");
        Image image = new ImageProxy("photo.png");
        System.out.println("  (объект ещё не загружен)");
        image.display();
        System.out.println("  (повторный вызов — без повторной загрузки)");
        image.display();
    }
}
