package org.example;

import org.example.patterns.behavioral.*;
import org.example.patterns.creational.*;
import org.example.patterns.structural.*;

import java.util.function.Supplier;

public enum Pattern {
    SINGLETON(1, "Singleton", PatternCategory.CREATIONAL, "Единственный экземпляр класса", SingletonDemo::new),
    FACTORY_METHOD(2, "Factory Method", PatternCategory.CREATIONAL, "Создание объектов через фабричный метод", FactoryMethodDemo::new),
    ABSTRACT_FACTORY(3, "Abstract Factory", PatternCategory.CREATIONAL, "Семейства связанных объектов", AbstractFactoryDemo::new),
    BUILDER(4, "Builder", PatternCategory.CREATIONAL, "Пошаговое создание сложных объектов", BuilderDemo::new),
    PROTOTYPE(5, "Prototype", PatternCategory.CREATIONAL, "Клонирование объектов", PrototypeDemo::new),

    ADAPTER(6, "Adapter", PatternCategory.STRUCTURAL, "Совместимость несовместимых интерфейсов", AdapterDemo::new),
    BRIDGE(7, "Bridge", PatternCategory.STRUCTURAL, "Разделение абстракции и реализации", BridgeDemo::new),
    COMPOSITE(8, "Composite", PatternCategory.STRUCTURAL, "Древовидная структура объектов", CompositeDemo::new),
    DECORATOR(9, "Decorator", PatternCategory.STRUCTURAL, "Динамическое добавление поведения", DecoratorDemo::new),
    FACADE(10, "Facade", PatternCategory.STRUCTURAL, "Упрощённый интерфейс к подсистеме", FacadeDemo::new),
    FLYWEIGHT(11, "Flyweight", PatternCategory.STRUCTURAL, "Разделение общего состояния", FlyweightDemo::new),
    PROXY(12, "Proxy", PatternCategory.STRUCTURAL, "Заместитель с отложенной загрузкой", ProxyDemo::new),

    CHAIN_OF_RESPONSIBILITY(13, "Chain of Responsibility", PatternCategory.BEHAVIORAL, "Цепочка обработчиков", ChainOfResponsibilityDemo::new),
    COMMAND(14, "Command", PatternCategory.BEHAVIORAL, "Инкапсуляция запроса как объекта", CommandDemo::new),
    INTERPRETER(15, "Interpreter", PatternCategory.BEHAVIORAL, "Интерпретация грамматики", InterpreterDemo::new),
    ITERATOR(16, "Iterator", PatternCategory.BEHAVIORAL, "Последовательный обход коллекции", IteratorDemo::new),
    MEDIATOR(17, "Mediator", PatternCategory.BEHAVIORAL, "Централизованное взаимодействие", MediatorDemo::new),
    MEMENTO(18, "Memento", PatternCategory.BEHAVIORAL, "Сохранение и восстановление состояния", MementoDemo::new),
    OBSERVER(19, "Observer", PatternCategory.BEHAVIORAL, "Подписка на изменения", ObserverDemo::new),
    STATE(20, "State", PatternCategory.BEHAVIORAL, "Поведение в зависимости от состояния", StateDemo::new),
    STRATEGY(21, "Strategy", PatternCategory.BEHAVIORAL, "Взаимозаменяемые алгоритмы", StrategyDemo::new),
    TEMPLATE_METHOD(22, "Template Method", PatternCategory.BEHAVIORAL, "Скелет алгоритма в базовом классе", TemplateMethodDemo::new),
    VISITOR(23, "Visitor", PatternCategory.BEHAVIORAL, "Операции над элементами структуры", VisitorDemo::new);

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
