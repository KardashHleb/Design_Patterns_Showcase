package org.example.patterns.structural;

import org.example.ConsoleCapture;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StructuralPatternsTest {

    @Test
    void adapterRoutesFormatsToTheRightPlayer() {
        AdapterDemo.MediaPlayer player = new AdapterDemo.AudioPlayer();
        assertTrue(ConsoleCapture.capture(() -> player.play("a.mp3")).contains("MP3"));
        assertTrue(ConsoleCapture.capture(() -> player.play("b.vlc")).contains("VLC"));
        assertTrue(ConsoleCapture.capture(() -> player.play("c.avi")).contains("Неподдерживаемый"));
    }

    @Test
    void bridgeDelegatesToChosenRenderer() {
        String output = ConsoleCapture.capture(() ->
                new BridgeDemo.Circle(new BridgeDemo.RasterRenderer(), 3).draw());
        assertTrue(output.contains("[Raster]"));
    }

    @Test
    void compositeShowsNestedStructureWithIndentation() {
        CompositeDemo.Folder root = new CompositeDemo.Folder("root");
        CompositeDemo.Folder sub = new CompositeDemo.Folder("sub");
        sub.add(new CompositeDemo.File("a.txt"));
        root.add(sub);

        String[] lines = ConsoleCapture.capture(() -> root.show("")).split("\\R");
        assertEquals(3, lines.length);
        assertTrue(lines[2].startsWith("    ") && lines[2].endsWith("a.txt"));
    }

    @Test
    void decoratorsAddCostExactly() {
        DecoratorDemo.Coffee coffee = new DecoratorDemo.SugarDecorator(
                new DecoratorDemo.MilkDecorator(new DecoratorDemo.SimpleCoffee()));
        assertEquals(new BigDecimal("2.70"), coffee.cost());
        assertEquals("Эспрессо + молоко + сахар", coffee.description());
    }

    @Test
    void facadeStartsAllSubsystems() {
        String output = ConsoleCapture.capture(() ->
                new FacadeDemo.HomeTheaterFacade().watchMovie("Film"));
        assertTrue(output.contains("Усилитель"));
        assertTrue(output.contains("Проектор"));
        assertTrue(output.contains("Воспроизведение: Film"));
    }

    @Test
    void flyweightSharesTreeTypes() {
        FlyweightDemo.TreeFactory factory = new FlyweightDemo.TreeFactory();
        FlyweightDemo.TreeType oak1 = factory.getTreeType("Дуб", "зелёный");
        FlyweightDemo.TreeType oak2 = factory.getTreeType("Дуб", "зелёный");
        FlyweightDemo.TreeType pine = factory.getTreeType("Сосна", "зелёный");

        assertSame(oak1, oak2);
        assertNotSame(oak1, pine);
        assertEquals(2, factory.cachedTypes());
    }

    @Test
    void flyweightKeysDoNotCollide() {
        FlyweightDemo.TreeFactory factory = new FlyweightDemo.TreeFactory();
        assertNotSame(factory.getTreeType("ab", "c"), factory.getTreeType("a", "bc"));
    }

    @Test
    void proxyLoadsRealImageOnlyOnceAndLazily() {
        String output = ConsoleCapture.capture(() -> {
            ProxyDemo.Image image = new ProxyDemo.ImageProxy("photo.png");
            System.out.println("created");
            image.display();
            image.display();
        });
        assertEquals(1, output.split("Загрузка с диска", -1).length - 1);
        assertTrue(output.indexOf("created") < output.indexOf("Загрузка с диска"));
    }
}
