package org.example.patterns.behavioral;

import org.example.PatternDemo;

import java.util.Map;

public class InterpreterDemo implements PatternDemo {

    interface Expression {
        int interpret(Map<String, Integer> context);
    }

    static class VariableExpression implements Expression {
        private final String name;

        VariableExpression(String name) {
            this.name = name;
        }

        @Override
        public int interpret(Map<String, Integer> context) {
            return context.getOrDefault(name, 0);
        }
    }

    static class AddExpression implements Expression {
        private final Expression left;
        private final Expression right;

        AddExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Map<String, Integer> context) {
            return left.interpret(context) + right.interpret(context);
        }
    }

    static class SubtractExpression implements Expression {
        private final Expression left;
        private final Expression right;

        SubtractExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Map<String, Integer> context) {
            return left.interpret(context) - right.interpret(context);
        }
    }

    @Override
    public void run() {
        System.out.println("Interpreter вычисляет выражение a + b - c:");
        Map<String, Integer> context = Map.of("a", 10, "b", 5, "c", 3);
        Expression expr = new SubtractExpression(
                new AddExpression(new VariableExpression("a"), new VariableExpression("b")),
                new VariableExpression("c")
        );
        System.out.println("  → a=10, b=5, c=3 → результат: " + expr.interpret(context));
    }
}
