# 📐 Low-Level Design (LLD)

This repository contains structured notes and implementations related to Low-Level Design, including design patterns and
practice problems.

---

## 🎯 Folder Structure

```
lld/
├── basics/
│   └── README.md             # OOP, UML, SOLID, etc.
├── patterns/
│   ├── creational/
│   │   ├── singleton/
│   │   │   └── Singleton.java
│   │   ├── factory/
│   │   │   └── FactoryPattern.java
│   │   └── builder/
│       │   └── Builder.java
│
│   ├── structural/
│   │   └── decorator/
│   │       └── DecoratorPattern.java
│
│   └── behavioural/
│       └── chainOfResponsibility/
│           └── ChainOfResponsibility.java
│
├── practice/
│   └── ParkingLot/
│       └── [Classes implementing Parking Lot System]

```

---

## 📚 Basics

| # | Pattern Name                     | Description       | 
|---|----------------------------------|-------------------|
| 1 | [LLD Basics](./basics/README.md) | OOPs & LLD basics |

---

## 📦 Design Patterns

### 🔨 Creational Design Patterns

| # | Pattern Name                                                        | Description                                                                                                            |
|---|---------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|
| 1 | [Singleton](./patterns/creational/singleton/README.md)              | Ensure a class has only one instance and provide a global point of access to it.                                       |
| 2 | [Builder](./patterns/creational/builder/README.md)                  | Separates complex object construction from its representation.                                                         |
| 3 | [Factory](./patterns/creational/factory/README.md)                  | Creates objects without exposing the instantiation logic.                                                              |
| 4 | [Prototype](./patterns/creational/prototype/README.md)              | Create new objects by copying existing ones, reducing the cost of creation.                                            |
| 5 | [Abstract Factory](./patterns/creational/abstractFactory/README.md) | Provides an interface for creating families of related or dependent objects without specifying their concrete classes. |

### 🧠 Behavioural Design Patterns

| # | Pattern Name                                                                      | Description                                                                                                                        |
|---|-----------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| 1 | [Chain Of Responsibility](./patterns/behavioural/chainOfResponsibility/README.md) | Passes a request along a chain of handlers until one of them handles it.                                                           |
| 2 | [Observer](./patterns/behavioural/observer/README.md)                             | Defines a one-to-many dependency so that when one object changes state, all its dependents are notified and updated automatically. |
| 3 | [Strategy](./patterns/behavioural/strategy/README.md)                             | Enables selecting an algorithm's behavior at runtime by encapsulating it within a class and making it interchangeable.             |
| 4 | [Command](./patterns/behavioural/command/README.md)                               | Encapsulates a request as an object, thereby allowing users to parameterize clients, delay execution, or queue and log operations. |
| 5 | [State](./patterns/behavioural/state/README.md)                                   | Allows an object to change its behavior when its internal state changes, appearing as if it changed its class.                     |

### 🧱 Structural Design Patterns

| # | Pattern Name                                           | Description                                                                 |
|---|--------------------------------------------------------|-----------------------------------------------------------------------------|
| 1 | [Decorator](./patterns/structural/decorator/README.md) | Dynamically adds new behavior to objects at runtime.                        |
| 2 | [Flyweight](./patterns/structural/flyweight/README.md) | Reduces memory usage by sharing common parts of object state among objects. |
| 3 | [Adapter](./patterns/structural/adapter/README.md)     | Converts one interface into another expected by the client.                 |

> ✨ More patterns like Strategy, Observer, Adapter, Proxy, and Template coming soon...

---

## 🧪 LLD Interview Questions

| # | System Design Problem | Description | Example |
|---|-----------------------|-------------|---------|
