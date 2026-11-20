package org.example.patterns.creational;

import org.example.PatternDemo;

public class FactoryMethodDemo implements PatternDemo {

    interface Document {
        void open();
    }

    static class PdfDocument implements Document {
        @Override
        public void open() {
            System.out.println("  → Opened a PDF document");
        }
    }

    static class WordDocument implements Document {
        @Override
        public void open() {
            System.out.println("  → Opened a Word document");
        }
    }

    abstract static class Application {
        abstract Document createDocument();

        void newDocument() {
            Document doc = createDocument();
            doc.open();
        }
    }

    static class PdfApplication extends Application {
        @Override
        Document createDocument() {
            return new PdfDocument();
        }
    }

    static class WordApplication extends Application {
        @Override
        Document createDocument() {
            return new WordDocument();
        }
    }

    @Override
    public void run() {
        System.out.println("Factory Method creates the right type of document:");
        Application pdfApp = new PdfApplication();
        pdfApp.newDocument();
        Application wordApp = new WordApplication();
        wordApp.newDocument();
    }
}
