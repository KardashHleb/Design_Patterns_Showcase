package org.example.patterns.behavioral;

import org.example.PatternDemo;

public class TemplateMethodDemo implements PatternDemo {

    abstract static class DataMiner {
        final void mine() {
            openFile();
            extractData();
            closeFile();
        }

        abstract void openFile();
        abstract void extractData();
        abstract void closeFile();
    }

    static class PdfMiner extends DataMiner {
        @Override
        void openFile() {
            System.out.println("  → Opened PDF file");
        }

        @Override
        void extractData() {
            System.out.println("  → Extracted data from PDF");
        }

        @Override
        void closeFile() {
            System.out.println("  → PDF file closed");
        }
    }

    static class CsvMiner extends DataMiner {
        @Override
        void openFile() {
            System.out.println("  → Opened CSV file");
        }

        @Override
        void extractData() {
            System.out.println("  → Extracted data from CSV");
        }

        @Override
        void closeFile() {
            System.out.println("  → CSV file closed");
        }
    }

    @Override
    public void run() {
        System.out.println("Template Method defines the algorithm skeleton, subclasses fill in the steps:");
        System.out.println("PDF:");
        new PdfMiner().mine();
        System.out.println("CSV:");
        new CsvMiner().mine();
    }
}
