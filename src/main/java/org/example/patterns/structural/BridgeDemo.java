package org.example.patterns.structural;

import org.example.PatternDemo;

public class BridgeDemo implements PatternDemo {

    interface Renderer {
        void renderCircle(float radius);
    }

    static class VectorRenderer implements Renderer {
        @Override
        public void renderCircle(float radius) {
            System.out.println("  → [Vector] Рисуем круг r=" + radius);
        }
    }

    static class RasterRenderer implements Renderer {
        @Override
        public void renderCircle(float radius) {
            System.out.println("  → [Raster] Рисуем круг r=" + radius + " пикселями");
        }
    }

    abstract static class Shape {
        protected final Renderer renderer;

        Shape(Renderer renderer) {
            this.renderer = renderer;
        }

        abstract void draw();
    }

    static class Circle extends Shape {
        private final float radius;

        Circle(Renderer renderer, float radius) {
            super(renderer);
            this.radius = radius;
        }

        @Override
        void draw() {
            renderer.renderCircle(radius);
        }
    }

    @Override
    public void run() {
        System.out.println("Bridge разделяет абстракцию (Shape) и реализацию (Renderer):");
        Shape vectorCircle = new Circle(new VectorRenderer(), 5);
        Shape rasterCircle = new Circle(new RasterRenderer(), 5);
        vectorCircle.draw();
        rasterCircle.draw();
    }
}
