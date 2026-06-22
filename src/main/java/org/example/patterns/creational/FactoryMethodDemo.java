package org.example.patterns.creational;

import org.example.PatternDemo;

public class FactoryMethodDemo implements PatternDemo {

    interface Document {
        void open();
    }

    static class PdfDocument implements Document {
        @Override
        public void open() {
            System.out.println("  → Открыт PDF-документ");
        }
    }

    static class WordDocument implements Document {
        @Override
        public void open() {
            System.out.println("  → Открыт Word-документ");
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
        System.out.println("Фабричный метод создаёт нужный тип документа:");
        Application pdfApp = new PdfApplication();
        pdfApp.newDocument();
        Application wordApp = new WordApplication();
        wordApp.newDocument();
    }
}
