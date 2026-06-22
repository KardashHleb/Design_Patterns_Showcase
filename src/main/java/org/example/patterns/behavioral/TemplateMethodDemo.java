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
            System.out.println("  → Открыт PDF-файл");
        }

        @Override
        void extractData() {
            System.out.println("  → Извлечены данные из PDF");
        }

        @Override
        void closeFile() {
            System.out.println("  → PDF-файл закрыт");
        }
    }

    static class CsvMiner extends DataMiner {
        @Override
        void openFile() {
            System.out.println("  → Открыт CSV-файл");
        }

        @Override
        void extractData() {
            System.out.println("  → Извлечены данные из CSV");
        }

        @Override
        void closeFile() {
            System.out.println("  → CSV-файл закрыт");
        }
    }

    @Override
    public void run() {
        System.out.println("Template Method задаёт скелет алгоритма, детали — в подклассах:");
        System.out.println("PDF:");
        new PdfMiner().mine();
        System.out.println("CSV:");
        new CsvMiner().mine();
    }
}
