# Javarena: DSA, LLD & HLD in Java

Welcome to **Javarena** – a structured, Java-based repository crafted to help you master **coding interviews** and *
*system design** with real-world examples, design patterns, and company-specific problem tracking.

---

## 📌 Table of Contents

- [🎯 Overview](#-overview)
- [📁 Project Structure](#-project-structure)
- [📘 DSA Topics](#-dsa-topics)
- [🏢 Company-Wise Problems](#-company-wise-problems)
- [🧩 Low-Level Design (LLD)](#-low-level-design-lld)
- [🏗 High-Level Design (HLD)](#-high-level-design-hld)
- [🚀 Getting Started](#-getting-started)
- [🏷️ Badges](#%EF%B8%8F-badges)

---

## 🎯 Overview

This repository includes:

- ✅ **Data Structures & Algorithms (DSA)** – Topic-wise and company-wise curated problems from **LeetCode**, *
  *GeeksforGeeks**, etc.
- 🏢 **Company-Wise Tracking** – Solved and categorized interview problems for top tech companies like Amazon, Adobe,
  Google, etc.
- 🧩 **Low-Level Design (LLD)** – Core OOP design patterns with hands-on examples. *(In Progress)*
- 🏗 **High-Level Design (HLD)** – Scalable architecture design with trade-offs and system diagrams. *(In Progress)*

---

## 📁 Project Structure

```
src/
└── main/
└── java/
└── org/
└── example/
├── coding/ # DSA by topic
├── companies/ # DSA by company
├── lld/ # Low-Level Design
└── hld/ # High-Level Design
```

---

## 📘 DSA Topics

| # | Topic                                                                                      | 
|---|--------------------------------------------------------------------------------------------|
| 1 | [Algorithms](./src/main/java/org/example/coding/algorithms/README.md)                      |
| 2 | [Array](./src/main/java/org/example/coding/datastructures/arrays/README.md)                |
| 3 | [Linked List](./src/main/java/org/example/coding/datastructures/linkedList/README.md)      |
| 4 | [Stack & Queue](./src/main/java/org/example/coding/datastructures/stackAndQueue/README.md) |
| 5 | [Graph](./src/main/java/org/example/coding/datastructures/graph/README.md)                 |
| 6 | [Segment Tree](./src/main/java/org/example/coding/datastructures/segmentTree/README.md)    |

---

## 🏢 Company-Wise Problems

Solve company-tagged questions like real interviews and track your progress here:

| Company                                          | File                                                               |
|--------------------------------------------------|--------------------------------------------------------------------|
| Amazon                                           | [Amazon.md](./src/main/java/org/example/companies/Amazon.md)       |
| Adobe                                            | [Adobe.md](./src/main/java/org/example/companies/Adobe.md)         |
| Google                                           | [Google.md](./src/main/java/org/example/companies/Google.md)       |
| Microsoft                                        | [Microsoft.md](./src/main/java/org/example/companies/Microsoft.md) |
| [More...](./src/main/java/org/example/companies) | 📁 View all                                                        |

Each file contains:

- 🎯 **Problem link**
- 📂 **Solution file path**
- ⚡️ **Difficulty & topic tags**

---

## 🧩 Low-Level Design (LLD)

### 📚 Basics

| # | Pattern Name                                                   | Description       | 
|---|----------------------------------------------------------------|-------------------|
| 1 | [LLD Basics](./src/main/java/org/example/lld/basics/README.md) | OOPs & LLD basics |

### 🔨 Creational Design Patterns

| # | Pattern Name                                                                         | Description                                                                      |
|---|--------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| 1 | [Singleton](./src/main/java/org/example/lld/patterns/creational/singleton/README.md) | Ensure a class has only one instance and provide a global point of access to it. |
| 2 | [Builder](./src/main/java/org/example/lld/patterns/creational/builder/README.md)     | Separates complex object construction from its representation.                   |
| 3 | [Factory](./src/main/java/org/example/lld/patterns/creational/factory/README.md)     | Creates objects without exposing the instantiation logic.                        |

### 🧠 Behavioural Design Patterns

| # | Pattern Name                                                                                                    | Description                                                                                                                        |
|---|-----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| 1 | [Chain Of Responsibility](./src/main/java/org/example/lld/patterns/behavioural/chainOfResponsibility/README.md) | Passes a request along a chain of handlers until one of them handles it.                                                           |
| 2 | [Observer](./src/main/java/org/example/lld/patterns/behavioural/observer/README.md)                             | Defines a one-to-many dependency so that when one object changes state, all its dependents are notified and updated automatically. |
| 3 | [Strategy](./src/main/java/org/example/lld/patterns/behavioural/strategy/README.md)                             | Enables selecting an algorithm's behavior at runtime by encapsulating it within a class and making it interchangeable.             |

### 🧱 Structural Design Patterns

| # | Pattern Name                                                                         | Description                                          |
|---|--------------------------------------------------------------------------------------|------------------------------------------------------|
| 1 | [Decorator](./src/main/java/org/example/lld/patterns/structural/decorator/README.md) | Dynamically adds new behavior to objects at runtime. |

---

## 🏗 High-Level Design (HLD)

### Concepts

- [Database Design and Databases](./src/main/java/org/example/hld/concepts/database_design.md)
- [Consistency Patterns](./src/main/java/org/example/hld/concepts/consistency_models.md)
- [Isolation Level](./src/main/java/org/example/hld/concepts/isolation_levels.md)
- [Cache](./src/main/java/org/example/hld/concepts/cache.md)
- [Networks & Communication](./src/main/java/org/example/hld/concepts/networks.md)
- [Rate Limiting](./src/main/java/org/example/hld/concepts/rate-limiting.md)

| # | System         | Status         | Description                   |
|---|----------------|----------------|-------------------------------|
| 1 | URL Shortener  | 🛠 In Progress | Service decomposition planned |
| 2 | WhatsApp Clone | 🛠 In Progress | Scalable chat architecture    |

---

## 🚀 Getting Started

```bash
git clone https://github.com/piyush7199/javarena-dsa-design.git
cd javarena-dsa-design
```

Open in your favorite IDE (IntelliJ or VSCode recommended for Java).

---

## 🏷️ Badges

![Java](https://img.shields.io/badge/language-Java-orange)
![LeetCode](https://img.shields.io/badge/platform-LeetCode-blue)
![GFG](https://img.shields.io/badge/platform-GeeksforGeeks-blue)
![Design](https://img.shields.io/badge/focus-HLD/LLD-success)