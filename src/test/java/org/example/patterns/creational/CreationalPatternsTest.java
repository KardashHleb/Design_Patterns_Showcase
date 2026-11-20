package org.example.patterns.creational;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreationalPatternsTest {

    @Test
    void singletonReturnsSameInstance() {
        assertSame(SingletonDemo.DatabaseConnection.getInstance(),
                SingletonDemo.DatabaseConnection.getInstance());
    }

    @Test
    void factoryMethodCreatesDocumentOfSubclassType() {
        assertInstanceOf(FactoryMethodDemo.PdfDocument.class,
                new FactoryMethodDemo.PdfApplication().createDocument());
        assertInstanceOf(FactoryMethodDemo.WordDocument.class,
                new FactoryMethodDemo.WordApplication().createDocument());
    }

    @Test
    void abstractFactoryCreatesConsistentFamily() {
        AbstractFactoryDemo.GUIFactory win = new AbstractFactoryDemo.WinFactory();
        assertInstanceOf(AbstractFactoryDemo.WinButton.class, win.createButton());
        assertInstanceOf(AbstractFactoryDemo.WinCheckbox.class, win.createCheckbox());

        AbstractFactoryDemo.GUIFactory mac = new AbstractFactoryDemo.MacFactory();
        assertInstanceOf(AbstractFactoryDemo.MacButton.class, mac.createButton());
        assertInstanceOf(AbstractFactoryDemo.MacCheckbox.class, mac.createCheckbox());
    }

    @Test
    void builderUsesDefaultsAndOverrides() {
        assertEquals("Pizza{dough='thin', sauce='tomato', topping='cheese'}",
                new BuilderDemo.Pizza.Builder().build().toString());
        assertEquals("Pizza{dough='thin', sauce='barbecue', topping='cheese'}",
                new BuilderDemo.Pizza.Builder().sauce("barbecue").build().toString());
    }

    @Test
    void prototypeCopyIsDeepAndIndependent() {
        PrototypeDemo.Circle original = new PrototypeDemo.Circle(10, "red", List.of("background"));
        PrototypeDemo.Circle copy = original.copy();

        assertNotSame(original, copy);
        assertEquals(original.getRadius(), copy.getRadius());
        assertEquals(original.getColor(), copy.getColor());
        assertEquals(original.getTags(), copy.getTags());

        copy.setRadius(25);
        copy.setColor("blue");
        copy.addTag("button");

        assertEquals(10, original.getRadius());
        assertEquals("red", original.getColor());
        assertEquals(List.of("background"), original.getTags());
        assertEquals(List.of("background", "button"), copy.getTags());
    }

    @Test
    void prototypeRegistryReturnsNewCopyEachTime() {
        PrototypeDemo.ShapeRegistry registry = new PrototypeDemo.ShapeRegistry();
        registry.register("circle", new PrototypeDemo.Circle(1, "white", List.of()));

        assertNotSame(registry.create("circle"), registry.create("circle"));
        assertThrows(IllegalArgumentException.class, () -> registry.create("unknown"));
    }
}
