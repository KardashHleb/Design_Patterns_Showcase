package org.example.patterns.creational;

import org.example.PatternDemo;

public class SingletonDemo implements PatternDemo {

    public static final class DatabaseConnection {
        private static DatabaseConnection instance;

        private DatabaseConnection() {
            System.out.println("  → Создано единственное подключение к БД");
        }

        public static synchronized DatabaseConnection getInstance() {
            if (instance == null) {
                instance = new DatabaseConnection();
            }
            return instance;
        }

        public void query(String sql) {
            System.out.println("  → Выполнение запроса: " + sql);
        }
    }

    @Override
    public void run() {
        System.out.println("Два вызова getInstance() возвращают один и тот же объект:");
        DatabaseConnection first = DatabaseConnection.getInstance();
        DatabaseConnection second = DatabaseConnection.getInstance();
        System.out.println("  first == second: " + (first == second));
        first.query("SELECT * FROM users");
    }
}
