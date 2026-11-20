package org.example.patterns.creational;

import org.example.PatternDemo;

public class SingletonDemo implements PatternDemo {

    public static final class DatabaseConnection {
        private static DatabaseConnection instance;

        private DatabaseConnection() {
            System.out.println("  → Created the single database connection");
        }

        public static synchronized DatabaseConnection getInstance() {
            if (instance == null) {
                instance = new DatabaseConnection();
            }
            return instance;
        }

        public void query(String sql) {
            System.out.println("  → Executing query: " + sql);
        }
    }

    @Override
    public void run() {
        System.out.println("Two getInstance() calls return the same object:");
        DatabaseConnection first = DatabaseConnection.getInstance();
        DatabaseConnection second = DatabaseConnection.getInstance();
        System.out.println("  first == second: " + (first == second));
        first.query("SELECT * FROM users");
    }
}
