package org.example.patterns.behavioral;

import org.example.PatternDemo;

public class VisitorDemo implements PatternDemo {

    interface Shape {
        void accept(ShapeVisitor visitor);
    }

    interface ShapeVisitor {
        void visit(Circle circle);
        void visit(Rectangle rectangle);
    }

    static class Circle implements Shape {
        private final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        double getRadius() {
            return radius;
        }

        @Override
        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }
    }

    static class Rectangle implements Shape {
        private final double width;
        private final double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        double getWidth() {
            return width;
        }

        double getHeight() {
            return height;
        }

        @Override
        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }
    }

    static class AreaCalculator implements ShapeVisitor {
        private double totalArea;

        @Override
        public void visit(Circle circle) {
            double area = Math.PI * circle.getRadius() * circle.getRadius();
            totalArea += area;
            System.out.printf("  → Circle r=%.1f, area=%.2f%n", circle.getRadius(), area);
        }

        @Override
        public void visit(Rectangle rectangle) {
            double area = rectangle.getWidth() * rectangle.getHeight();
            totalArea += area;
            System.out.printf("  → Rectangle %.1fx%.1f, area=%.2f%n",
                    rectangle.getWidth(), rectangle.getHeight(), area);
        }

        double getTotalArea() {
            return totalArea;
        }
    }

    @Override
    public void run() {
        System.out.println("Visitor adds operations on elements without changing their classes:");
        Shape[] shapes = {new Circle(3), new Rectangle(4, 5), new Circle(2)};
        AreaCalculator calculator = new AreaCalculator();
        for (Shape shape : shapes) {
            shape.accept(calculator);
        }
        System.out.printf("  → Total area: %.2f%n", calculator.getTotalArea());
    }
}
