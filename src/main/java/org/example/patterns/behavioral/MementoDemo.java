package org.example.patterns.behavioral;

import org.example.PatternDemo;

public class MementoDemo implements PatternDemo {

    static class EditorMemento {
        private final String content;

        EditorMemento(String content) {
            this.content = content;
        }

        String getContent() {
            return content;
        }
    }

    static class Editor {
        private String content = "";

        void type(String text) {
            content += text;
        }

        EditorMemento save() {
            return new EditorMemento(content);
        }

        void restore(EditorMemento memento) {
            content = memento.getContent();
        }

        String getContent() {
            return content;
        }
    }

    @Override
    public void run() {
        System.out.println("Memento saves and restores state:");
        Editor editor = new Editor();
        editor.type("Hello");
        EditorMemento snapshot = editor.save();
        editor.type(" World!");
        System.out.println("  → After changes: \"" + editor.getContent() + "\"");
        editor.restore(snapshot);
        System.out.println("  → After restore: \"" + editor.getContent() + "\"");
    }
}
