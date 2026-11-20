package org.example.patterns.behavioral;

import org.example.ConsoleCapture;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BehavioralPatternsTest {

    @Test
    void chainPassesRequestToFirstSuitableHandler() {
        ChainOfResponsibilityDemo.Handler chain = new ChainOfResponsibilityDemo.BankHandler();
        chain.linkWith(new ChainOfResponsibilityDemo.PayPalHandler())
                .linkWith(new ChainOfResponsibilityDemo.BitcoinHandler());

        assertTrue(ConsoleCapture.capture(() -> chain.handle(50)).contains("Bank"));
        assertTrue(ConsoleCapture.capture(() -> chain.handle(300)).contains("PayPal"));
        assertTrue(ConsoleCapture.capture(() -> chain.handle(2000)).contains("Bitcoin"));
    }

    @Test
    void chainReportsUnhandledRequest() {
        ChainOfResponsibilityDemo.Handler chain = new ChainOfResponsibilityDemo.BankHandler();
        assertTrue(ConsoleCapture.capture(() -> chain.handle(1000)).contains("not handled"));
    }

    @Test
    void commandCanBeUndone() {
        CommandDemo.Light light = new CommandDemo.Light();
        CommandDemo.RemoteControl remote = new CommandDemo.RemoteControl();
        ConsoleCapture.capture(() -> {
            remote.pressButton(new CommandDemo.LightOnCommand(light));
            assertTrue(light.isOn());
            remote.pressUndo();
            assertFalse(light.isOn());
            remote.pressUndo(); // empty history: no exception
        });
    }

    @Test
    void interpreterEvaluatesExpression() {
        InterpreterDemo.Expression expr = new InterpreterDemo.SubtractExpression(
                new InterpreterDemo.AddExpression(
                        new InterpreterDemo.VariableExpression("a"),
                        new InterpreterDemo.VariableExpression("b")),
                new InterpreterDemo.VariableExpression("c"));
        assertEquals(12, expr.interpret(Map.of("a", 10, "b", 5, "c", 3)));
    }

    @Test
    void iteratorTraversesForwardAndBackward() {
        IteratorDemo.BookShelf shelf = new IteratorDemo.BookShelf(3);
        shelf.add("A");
        shelf.add("B");
        shelf.add("C");

        List<String> forward = new ArrayList<>();
        shelf.forEach(forward::add);
        assertEquals(List.of("A", "B", "C"), forward);

        List<String> backward = new ArrayList<>();
        shelf.reverseIterator().forEachRemaining(backward::add);
        assertEquals(List.of("C", "B", "A"), backward);
    }

    @Test
    void iteratorThrowsWhenExhaustedAndShelfHasCapacity() {
        IteratorDemo.BookShelf shelf = new IteratorDemo.BookShelf(1);
        shelf.add("A");
        assertThrows(IllegalStateException.class, () -> shelf.add("B"));

        Iterator<String> it = shelf.iterator();
        it.next();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void mediatorDeliversBroadcastToOthersOnly() {
        ConsoleCapture.capture(() -> {
            MediatorDemo.ChatRoom room = new MediatorDemo.ChatRoom();
            MediatorDemo.User alice = new MediatorDemo.User("Alice", room);
            MediatorDemo.User bob = new MediatorDemo.User("Bob", room);
            MediatorDemo.User carol = new MediatorDemo.User("Carol", room);

            alice.send("hi");

            assertTrue(alice.getInbox().isEmpty());
            assertEquals(List.of("Alice: hi"), bob.getInbox());
            assertEquals(List.of("Alice: hi"), carol.getInbox());
        });
    }

    @Test
    void mediatorDeliversPrivateMessageToRecipientOnly() {
        String output = ConsoleCapture.capture(() -> {
            MediatorDemo.ChatRoom room = new MediatorDemo.ChatRoom();
            MediatorDemo.User alice = new MediatorDemo.User("Alice", room);
            MediatorDemo.User bob = new MediatorDemo.User("Bob", room);
            MediatorDemo.User carol = new MediatorDemo.User("Carol", room);

            bob.sendTo("Alice", "secret");
            bob.sendTo("Dave", "anyone?");

            assertEquals(List.of("Bob (private): secret"), alice.getInbox());
            assertTrue(carol.getInbox().isEmpty());
        });
        assertTrue(output.contains("Dave not found"));
    }

    @Test
    void mementoRestoresSavedState() {
        MementoDemo.Editor editor = new MementoDemo.Editor();
        editor.type("Hello");
        MementoDemo.EditorMemento snapshot = editor.save();
        editor.type(" World");
        editor.restore(snapshot);
        assertEquals("Hello", editor.getContent());
    }

    @Test
    void observerNotifiesAllSubscribers() {
        ObserverDemo.NewsAgency agency = new ObserverDemo.NewsAgency();
        agency.subscribe(new ObserverDemo.NewsChannel("A"));
        agency.subscribe(new ObserverDemo.NewsChannel("B"));
        String output = ConsoleCapture.capture(() -> agency.publish("news"));
        assertTrue(output.contains("A received news: news"));
        assertTrue(output.contains("B received news: news"));
    }

    @Test
    void stateChangesBehaviour() {
        StateDemo.VendingMachine machine = new StateDemo.VendingMachine();
        String output = ConsoleCapture.capture(() -> {
            machine.dispense();
            machine.insertCoin();
            machine.insertCoin();
            machine.dispense();
            machine.dispense();
        });
        String[] lines = output.split("\\R");
        assertTrue(lines[0].contains("Insert a coin first"));
        assertTrue(lines[1].contains("Coin accepted"));
        assertTrue(lines[2].contains("Coin already inserted"));
        assertTrue(lines[3].contains("Drink dispensed"));
        assertTrue(lines[4].contains("Insert a coin first"));
    }

    @Test
    void strategyCanBeSwappedAtRuntime() {
        StrategyDemo.ShoppingCart cart = new StrategyDemo.ShoppingCart();
        cart.setPaymentStrategy(new StrategyDemo.CreditCardPayment());
        assertTrue(ConsoleCapture.capture(() -> cart.checkout(10)).contains("by credit card"));
        cart.setPaymentStrategy(new StrategyDemo.PayPalPayment());
        assertTrue(ConsoleCapture.capture(() -> cart.checkout(10)).contains("PayPal"));
    }

    @Test
    void templateMethodRunsStepsInOrder() {
        String[] lines = ConsoleCapture.capture(() -> new TemplateMethodDemo.CsvMiner().mine()).split("\\R");
        assertTrue(lines[0].contains("Opened"));
        assertTrue(lines[1].contains("Extracted"));
        assertTrue(lines[2].contains("closed"));
    }

    @Test
    void visitorSumsAreas() {
        VisitorDemo.AreaCalculator calculator = new VisitorDemo.AreaCalculator();
        ConsoleCapture.capture(() -> {
            new VisitorDemo.Rectangle(4, 5).accept(calculator);
            new VisitorDemo.Circle(1).accept(calculator);
        });
        assertEquals(20 + Math.PI, calculator.getTotalArea(), 1e-9);
    }
}
