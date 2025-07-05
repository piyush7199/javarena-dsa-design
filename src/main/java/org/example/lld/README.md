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

| # | Pattern Name                                           | Description                                                                      |
|---|--------------------------------------------------------|----------------------------------------------------------------------------------|
| 1 | [Singleton](./patterns/creational/singleton/README.md) | Ensure a class has only one instance and provide a global point of access to it. |
| 2 | [Builder](./patterns/creational/builder/README.md)     | Separates complex object construction from its representation.                   |
| 3 | [Factory](./patterns/creational/factory/README.md)     | Creates objects without exposing the instantiation logic.                        |

### 🧠 Behavioural Design Patterns

| # | Pattern Name                                                                      | Description                                                              |
|---|-----------------------------------------------------------------------------------|--------------------------------------------------------------------------|
| 1 | [Chain Of Responsibility](./patterns/behavioural/chainOfResponsibility/README.md) | Passes a request along a chain of handlers until one of them handles it. |

### 🧱 Structural Design Patterns

| # | Pattern Name                                                         | Description                                          |
|---|----------------------------------------------------------------------|------------------------------------------------------|
| 1 | [Chain Of Responsibility](./patterns/structural/decorator/README.md) | Dynamically adds new behavior to objects at runtime. |

> ✨ More patterns like Strategy, Observer, Adapter, Proxy, and Template coming soon...

---

## 🧪 Practice Problems

| # | System Design Problem | Description | Example |
|---|-----------------------|-------------|---------|
