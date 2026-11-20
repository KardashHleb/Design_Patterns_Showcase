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
            System.out.println("  → Loading from disk: " + filename);
        }

        @Override
        public void display() {
            System.out.println("  → Displaying: " + filename);
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
        System.out.println("Proxy delays creating a heavy object until it is first used:");
        Image image = new ImageProxy("photo.png");
        System.out.println("  (object not loaded yet)");
        image.display();
        System.out.println("  (second call: no reload)");
        image.display();
    }
}
