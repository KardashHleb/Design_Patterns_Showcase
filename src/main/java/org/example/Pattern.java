package org.example;

import org.example.patterns.behavioral.*;
import org.example.patterns.creational.*;
import org.example.patterns.structural.*;

import java.util.function.Supplier;

public enum Pattern {
    SINGLETON(1, "Singleton", PatternCategory.CREATIONAL, "A class with a single instance", SingletonDemo::new),
    FACTORY_METHOD(2, "Factory Method", PatternCategory.CREATIONAL, "Object creation via a factory method", FactoryMethodDemo::new),
    ABSTRACT_FACTORY(3, "Abstract Factory", PatternCategory.CREATIONAL, "Families of related objects", AbstractFactoryDemo::new),
    BUILDER(4, "Builder", PatternCategory.CREATIONAL, "Step-by-step construction of complex objects", BuilderDemo::new),
    PROTOTYPE(5, "Prototype", PatternCategory.CREATIONAL, "Creating objects by copying", PrototypeDemo::new),

    ADAPTER(6, "Adapter", PatternCategory.STRUCTURAL, "Making incompatible interfaces work together", AdapterDemo::new),
    BRIDGE(7, "Bridge", PatternCategory.STRUCTURAL, "Separating abstraction from implementation", BridgeDemo::new),
    COMPOSITE(8, "Composite", PatternCategory.STRUCTURAL, "Tree structures of objects", CompositeDemo::new),
    DECORATOR(9, "Decorator", PatternCategory.STRUCTURAL, "Adding behavior dynamically", DecoratorDemo::new),
    FACADE(10, "Facade", PatternCategory.STRUCTURAL, "Simple interface to a subsystem", FacadeDemo::new),
    FLYWEIGHT(11, "Flyweight", PatternCategory.STRUCTURAL, "Sharing common state", FlyweightDemo::new),
    PROXY(12, "Proxy", PatternCategory.STRUCTURAL, "Placeholder with lazy loading", ProxyDemo::new),

    CHAIN_OF_RESPONSIBILITY(13, "Chain of Responsibility", PatternCategory.BEHAVIORAL, "Chain of handlers", ChainOfResponsibilityDemo::new),
    COMMAND(14, "Command", PatternCategory.BEHAVIORAL, "Request as an object", CommandDemo::new),
    INTERPRETER(15, "Interpreter", PatternCategory.BEHAVIORAL, "Interpreting a grammar", InterpreterDemo::new),
    ITERATOR(16, "Iterator", PatternCategory.BEHAVIORAL, "Sequential traversal of a collection", IteratorDemo::new),
    MEDIATOR(17, "Mediator", PatternCategory.BEHAVIORAL, "Centralized communication", MediatorDemo::new),
    MEMENTO(18, "Memento", PatternCategory.BEHAVIORAL, "Saving and restoring state", MementoDemo::new),
    OBSERVER(19, "Observer", PatternCategory.BEHAVIORAL, "Subscribing to changes", ObserverDemo::new),
    STATE(20, "State", PatternCategory.BEHAVIORAL, "Behavior depends on state", StateDemo::new),
    STRATEGY(21, "Strategy", PatternCategory.BEHAVIORAL, "Interchangeable algorithms", StrategyDemo::new),
    TEMPLATE_METHOD(22, "Template Method", PatternCategory.BEHAVIORAL, "Algorithm skeleton in a base class", TemplateMethodDemo::new),
    VISITOR(23, "Visitor", PatternCategory.BEHAVIORAL, "Operations on elements of a structure", VisitorDemo::new);

    private final int number;
    private final String name;
    private final PatternCategory category;
    private final String description;
    private final Supplier<PatternDemo> demoFactory;

    Pattern(int number, String name, PatternCategory category, String description, Supplier<PatternDemo> demoFactory) {
        this.number = number;
        this.name = name;
        this.category = category;
        this.description = description;
        this.demoFactory = demoFactory;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public PatternCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public PatternDemo createDemo() {
        return demoFactory.get();
    }
}
