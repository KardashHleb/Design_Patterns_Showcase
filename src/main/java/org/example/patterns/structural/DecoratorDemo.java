package org.example.patterns.structural;

import org.example.PatternDemo;

import java.math.BigDecimal;

public class DecoratorDemo implements PatternDemo {

    interface Coffee {
        String description();

        BigDecimal cost();
    }

    static class SimpleCoffee implements Coffee {
        @Override
        public String description() {
            return "Эспрессо";
        }

        @Override
        public BigDecimal cost() {
            return new BigDecimal("2.00");
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
        public BigDecimal cost() {
            return wrapped.cost().add(new BigDecimal("0.50"));
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
        public BigDecimal cost() {
            return wrapped.cost().add(new BigDecimal("0.20"));
        }
    }

    @Override
    public void run() {
        System.out.println("Decorator добавляет поведение без изменения базового класса:");
        Coffee espresso = new SimpleCoffee();
        System.out.println("  → " + espresso.description() + " = $" + espresso.cost());
        Coffee latte = new MilkDecorator(espresso);
        System.out.println("  → " + latte.description() + " = $" + latte.cost());
        Coffee sweetLatte = new SugarDecorator(latte);
        System.out.println("  → " + sweetLatte.description() + " = $" + sweetLatte.cost());
    }
}
