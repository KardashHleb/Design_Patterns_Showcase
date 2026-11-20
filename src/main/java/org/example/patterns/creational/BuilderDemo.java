package org.example.patterns.creational;

import org.example.PatternDemo;

public class BuilderDemo implements PatternDemo {

    static class Pizza {
        private final String dough;
        private final String sauce;
        private final String topping;

        private Pizza(Builder builder) {
            this.dough = builder.dough;
            this.sauce = builder.sauce;
            this.topping = builder.topping;
        }

        @Override
        public String toString() {
            return "Pizza{dough='" + dough + "', sauce='" + sauce + "', topping='" + topping + "'}";
        }

        static class Builder {
            private String dough = "thin";
            private String sauce = "tomato";
            private String topping = "cheese";

            Builder dough(String dough) {
                this.dough = dough;
                return this;
            }

            Builder sauce(String sauce) {
                this.sauce = sauce;
                return this;
            }

            Builder topping(String topping) {
                this.topping = topping;
                return this;
            }

            Pizza build() {
                return new Pizza(this);
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Builder assembles an object step by step:");
        Pizza pizza = new Pizza.Builder()
                .dough("thick")
                .sauce("barbecue")
                .topping("pepperoni")
                .build();
        System.out.println("  → " + pizza);
    }
}
