package org.example.patterns.structural;

import org.example.PatternDemo;

public class DecoratorDemo implements PatternDemo {

    interface Coffee {
        String description();
        double cost();
    }

    static class SimpleCoffee implements Coffee {
        @Override
        public String description() {
            return "Эспрессо";
        }

        @Override
        public double cost() {
            return 2.0;
        }
    }

    abstract static class CoffeeDecorator implements Coffee {
        protected final Coffee wrapped;

        CoffeeDecorator(Coffee wrapped) {
            this.wrapped = wrapped;
        }
    }

    static class MilkDecorator extends CoffeeDecorator {
        MilkDecorator(Coffee wrapped) {
            super(wrapped);
        }

        @Override
        public String description() {
            return wrapped.description() + " + молоко";
        }

        @Override
        public double cost() {
            return wrapped.cost() + 0.5;
        }
    }

    static class SugarDecorator extends CoffeeDecorator {
        SugarDecorator(Coffee wrapped) {
            super(wrapped);
        }

        @Override
        public String description() {
            return wrapped.description() + " + сахар";
        }

        @Override
        public double cost() {
            return wrapped.cost() + 0.2;
        }
    }

    @Override
    public void run() {
        System.out.println("Decorator добавляет поведение без изменения базового класса:");
        Coffee coffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println("  → " + coffee.description() + " = $" + coffee.cost());
    }
}
