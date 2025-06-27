# 📘 Low-Level Design (LLD) Basics

Welcome to the Low-Level Design (LLD) Basics guide! This resource is crafted for developers new to LLD, helping you
master the fundamentals of designing modular, scalable, and maintainable software systems. Whether you're preparing for
system design interviews or building robust applications, this guide covers Object-Oriented Programming (OOP), UML
diagrams, object relationships, SOLID principles, and design patterns with clear explanations and code examples.

Bookmark this guide as your go-to reference for LLD fundamentals! 🚀

---

## 🎯 Why Learn Low-Level Design?

Low-Level Design bridges the gap between high-level system architecture and actual code implementation. It focuses on
designing the internal structure of components—classes, objects, and their interactions—to ensure your code is clean,
flexible, and easy to maintain. Mastering LLD is critical for:

- Writing scalable and modular code.
- Excelling in technical interviews.
- Building systems that are easy to extend and debug.

---

## 📚 Prerequisites

To get the most out of this guide, you should have:

- Basic knowledge of a programming language (e.g., Java, Python, or C++).
- Familiarity with fundamental programming concepts (e.g., variables, functions, classes).
- A desire to write clean, maintainable, and scalable code!

---

## 🚀 Learning Outcomes

By the end of this guide, you will be able to:

- Apply the four pillars of OOP to design modular code.
- Create and interpret UML diagrams to visualize system designs.
- Implement object relationships like association, aggregation, and composition.
- Use SOLID principles to write clean and maintainable code.
- Recognize and apply common design patterns to solve recurring problems.

---

## 🧱 Introduction to Low-Level Design

### What is Low-Level Design?

**Low-Level Design (LLD)** details a system's components—classes, objects, methods, and their interactions—to implement
a high-level design. It translates abstract requirements into code-ready designs, ensuring modularity and scalability.

### Why LLD Matters for Developers

LLD helps you:

- Break down complex systems into manageable components.
- Write code that’s easy to test, debug, and extend.
- Prepare for system design interviews with structured problem-solving.

---

## 🧱 Object-Oriented Programming (OOP)

OOP models real-world entities as objects with properties (data) and behaviors (methods), forming the foundation of LLD.

### Core Concepts

OOP organizes code into reusable, modular units, mimicking real-world relationships.

### 🔑 Four Pillars of OOP

**1. Encapsulation**
→ Hides internal details, exposing only what’s needed—like a bank vault protecting its contents.

```java
class BankAccount {
    private double balance; // Hidden from outside

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
```

**2. Abstraction**
→ Shows essential details, hiding complexity—like a car’s dashboard concealing engine mechanics.

```java
abstract class Vehicle {
    abstract void move(); // Hides implementation
}

class Car extends Vehicle {
    void move() {
        System.out.println("Car moves on four wheels");
    }
}
```

**3. Inheritance**
→ Allows a class to inherit properties and behaviors (IS-A relationship).

```java
class Animal {
    void sound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}
```

**4. Polymorphism**
→ Enables one interface to represent multiple forms, like a remote controlling different devices.

```java
public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog(); // Dog behaves as Animal
        animal.sound(); // Outputs: Bark (runtime polymorphism)
    }
}
```

### Code Examples

See above for practical implementations of each pillar.

### Common Pitfalls to Avoid

- **Overusing Inheritance:** Leads to tight coupling; prefer composition.
- **Ignoring Encapsulation:** Exposing internals breaks abstraction.

---

## 📊 UML Diagram Basics

**UML (Unified Modeling Language)** visualizes system designs, acting as blueprints for LLD.

### What is UML?

UML standardizes the representation of system components and interactions.

### Types of UML Diagrams

| UML Diagram          | Purpose                                               |
|----------------------|-------------------------------------------------------|
| **Class Diagram**    | Shows classes, attributes, methods, and relationships |
| **Sequence Diagram** | Shows flow of messages between objects over time      |
| **Use Case Diagram** | Shows user interaction with system functionalities    |
| **Activity Diagram** | Shows flow of actions, decisions, and concurrency     |

### Example: Class Diagram

```
+---------------------+
|      User           |
+---------------------+
| - name: String      |
| - email: String     |
+---------------------+
| + login(): void     |
| + logout(): void    |
+---------------------+
```

- `+` means public, `-` means private.
- Use arrows for relationships (e.g., `→` for association, `◄|–` for inheritance).

---

## 🔗 Object Relationships in LLD

Understanding class interactions is key to cohesive system design.

### Overview of Relationships

Classes relate through various patterns, defining how they collaborate.

### Types of Relationships

| Type        | Meaning                     | Example             | Lifespan    | Code Tip                  |
|-------------|-----------------------------|---------------------|-------------|---------------------------|
| Association | General connection          | `Student ↔ Teacher` | Independent | Object as a field         |
| Dependency  | Temporary usage             | `Order → Payment`   | Short       | Passed via method         |
| Aggregation | Whole-part (can live alone) | `Library has Books` | Independent | Has-a relationship        |
| Composition | Whole-part (dies together)  | `Human has Heart`   | Dependent   | Initialize in constructor |

### Code Examples: Relationships

#### Association

```java
class Student {
    String name;
}

class Course {
    Student student; // Course has a Student
}
```

### 🧩 Dependency (uses)

```java
class OrderService {
    void placeOrder(Payment payment) {
        payment.pay(); // Temporary use of Payment
    }
}
```

### ⚪ Aggregation (whole-part, weak)

```java
class Book {
    String title;
}

class Library {
    List<Book> books; // Books can exist without Library
}
```

### ⚫ Composition (whole-part, strong)

```java
class Heart {
    void beat() { /* ... */ }
}

class Human {
    private final Heart heart = new Heart(); // Heart dies with Human
}
```

---

## 🧠 SOLID Principles

**SOLID principles** ensure clean, maintainable, and scalable code.

### Introduction to SOLID

SOLID is a set of five guidelines for robust OOP design.

| Principle                     | Meaning                                     | Simple Explanation                                                 |
|-------------------------------|---------------------------------------------|--------------------------------------------------------------------|
| **S** – Single Responsibility | One class, one job                          | A class shouldn’t handle unrelated tasks (e.g., User vs. Emailer). |
| **O** – Open/Closed           | Open to extend, closed to modify            | Extend behavior via interfaces, don’t modify code.                 |
| **L** – Liskov Substitution   | Subtypes act like their parent              | Subclasses should be swappable without breaking functionality.     |
| **I** – Interface Segregation | Don’t force unused methods                  | Use small, specific interfaces, not bloated ones.                  |
| **D** – Dependency Inversion  | Depend on abstractions, not implementations | Use interfaces to decouple high-level and low-level modules.       |

### Practical Applications

- Single Responsibility: Split User from email-sending logic.
- Open/Closed: Use a Payment interface to add new payment methods.

---

## 🏗️ Design Patterns Overview

Design patterns are reusable solutions to common design problems.

### What are Design Patterns?

Patterns provide tested approaches to structure code effectively.

### Categories of Design Patterns

### Creational Patterns

Control object creation to reduce complexity.

| Pattern                                                 | Purpose                                |
|---------------------------------------------------------|----------------------------------------|
| [Singleton](../patterns/creational/singleton/README.md) | Ensures only one instance exists       |
| Factory                                                 | Creates objects without exposing logic |
| Builder                                                 | Builds complex objects step-by-step    |
| Prototype                                               | Clone existing object                  |
| Abstract Factory                                        | Create families of related objects     |

### Structural Patterns

Organize classes and objects into larger structures.

| Pattern                                                 | Purpose                                  |
|---------------------------------------------------------|------------------------------------------|
| Adapter                                                 | Converts one interface into another      |
| [Decorator](../patterns/structural/decorator/README.md) | Add functionality dynamically            |
| Composite                                               | Tree structure of objects                |
| Proxy                                                   | Stand-in for another object              |
| Bridge                                                  | Decouple abstraction from implementation |
| Flyweight                                               | Share objects to save memory             |
| Facade                                                  | Simplified interface to a complex system |

### Behavioral Patterns

Handle object communication and responsibilities.

| Pattern                 | Purpose                                   |
|-------------------------|-------------------------------------------|
| Strategy                | Switch behavior at runtime                |
| Observer                | Notify dependents on change               |
| Command                 | Encapsulate a request as an object        |
| State                   | Object changes behavior by internal state |
| Template                | Define algorithm skeleton                 |
| Chain of Responsibility | Pass request along a chain                |
| Mediator                | Central controller for interactions       |
| Memento                 | Save/restore object state                 |
| Visitor                 | Add operations without modifying classes  |
| Interpreter             | Interpret expressions based on grammar    |

### When to Use Design Patterns

- Singleton: Single database connection.
- Factory: Create objects based on user input.
- Observer: Event-driven systems (e.g., UI updates).

---

## ✅ Summary

### Recap of LLD Concepts

* OOP models real-world entities.
* UML diagrams visualize designs.
* Object relationships define interactions.
* SOLID principles ensure clean code.
* Design patterns solve recurring problems.

