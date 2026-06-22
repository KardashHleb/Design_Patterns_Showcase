package org.example.patterns.structural;

import org.example.PatternDemo;

import java.util.ArrayList;
import java.util.List;

public class CompositeDemo implements PatternDemo {

    interface FileSystemComponent {
        void show(String indent);
    }

    static class File implements FileSystemComponent {
        private final String name;

        File(String name) {
            this.name = name;
        }

        @Override
        public void show(String indent) {
            System.out.println(indent + "📄 " + name);
        }
    }

    static class Folder implements FileSystemComponent {
        private final String name;
        private final List<FileSystemComponent> children = new ArrayList<>();

        Folder(String name) {
            this.name = name;
        }

        void add(FileSystemComponent component) {
            children.add(component);
        }

        @Override
        public void show(String indent) {
            System.out.println(indent + "📁 " + name);
            for (FileSystemComponent child : children) {
                child.show(indent + "  ");
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Composite обрабатывает файлы и папки единообразно:");
        Folder root = new Folder("src");
        Folder main = new Folder("main");
        main.add(new File("Main.java"));
        main.add(new File("App.java"));
        root.add(main);
        root.add(new File("README.md"));
        root.show("  ");
    }
}
