# Javarena: Data Structures & Algorithms in Java

Welcome to **Javarena** – a comprehensive, Java-based repository crafted to help you master **coding interviews** with
carefully curated DSA problems organized by topics and companies.

---

## 📌 Table of Contents

- [🎯 Overview](#-overview)
- [📁 Project Structure](#-project-structure)
- [📘 DSA Topics](#-dsa-topics)
- [🏢 Company-Wise Problems](#-company-wise-problems)
- [🚀 Getting Started](#-getting-started)
- [🏷️ Badges](#%EF%B8%8F-badges)

---

## 🎯 Overview

This repository includes:

- ✅ **Data Structures & Algorithms (DSA)** – Comprehensive topic-wise and company-wise curated problems from **LeetCode
  **, **GeeksforGeeks**, and more
- 🏢 **Company-Wise Tracking** – Solved and categorized interview problems for top tech companies like Amazon, Adobe,
  Google, Microsoft, and many others
- 📊 **Progress Tracking** – Built-in topic suggester to help you practice consistently

---

## 📁 Project Structure

```
src/main/java/com/javarena/dsa/
├── algorithms/              # Algorithm implementations & problems
│   ├── binarySearch/       # Binary search problems
│   ├── bitManupulation/    # Bit manipulation problems
│   ├── dynamicProgramming/ # DP problems
│   ├── greedy/             # Greedy algorithm problems
│   ├── recursionAndBacktracking/ # Recursion & backtracking
│   ├── string/             # String algorithms (KMP, etc.)
│   ├── twoPointerAndSlidingWindow/ # Two pointer problems
│   └── miscellaneous/      # Other algorithm problems
│
├── datastructures/         # Data structure implementations
│   ├── arrays/            # Array problems
│   ├── binaryTree/        # Binary tree problems
│   ├── graph/             # Graph algorithms
│   ├── linkedList/        # Linked list problems
│   ├── stackAndQueue/     # Stack & queue problems
│   ├── string/            # String data structure problems
│   ├── trie/              # Trie implementations
│   ├── segmentTree/       # Segment tree
│   ├── fenwickTree/       # Fenwick tree (BIT)
│   └── hashMapAndSet/     # HashMap & HashSet problems
│
├── companies/             # Company-wise problem tracking (386 companies)
│   ├── Amazon.md
│   ├── Google.md
│   ├── Microsoft.md
│   └── ... (and many more)
│
└── utils/                 # Utility classes
    ├── Main.java          # Main entry point
    └── TopicSuggester.java # Daily topic suggestion tool
```

---

## 📘 DSA Topics

### Algorithms

| # | Topic                                                                                                      | 
|---|------------------------------------------------------------------------------------------------------------|
| 1 | [Binary Search](./src/main/java/com/javarena/dsa/algorithms/binarySearch/)                                 |
| 2 | [Bit Manipulation](./src/main/java/com/javarena/dsa/algorithms/README-BitManipulation.md)                  |
| 3 | [Dynamic Programming](./src/main/java/com/javarena/dsa/algorithms/README-DynamicProgramming.md)            |
| 4 | [Greedy Algorithms](./src/main/java/com/javarena/dsa/algorithms/README-GreedyAlgorithms.md)                |
| 5 | [Recursion & Backtracking](./src/main/java/com/javarena/dsa/algorithms/README-RecursionAndBacktracking.md) |
| 6 | [String Algorithms](./src/main/java/com/javarena/dsa/algorithms/README-StringAlgorithms.md)                |
| 7 | [Two Pointer](./src/main/java/com/javarena/dsa/algorithms/README-TwoPointer.md)                            |
| 8 | [Sliding Window](./src/main/java/com/javarena/dsa/algorithms/README-SlidingWindowAlgorithms.md)            |

### Data Structures

| # | Topic                                                                                    | 
|---|------------------------------------------------------------------------------------------|
| 1 | [Arrays](./src/main/java/com/javarena/dsa/datastructures/arrays/README.md)               |
| 2 | [Linked List](./src/main/java/com/javarena/dsa/datastructures/linkedList/README.md)      |
| 3 | [Stack & Queue](./src/main/java/com/javarena/dsa/datastructures/stackAndQueue/README.md) |
| 4 | [Binary Tree](./src/main/java/com/javarena/dsa/datastructures/binaryTree/README.md)      |
| 5 | [Graph](./src/main/java/com/javarena/dsa/datastructures/graph/README.md)                 |
| 6 | [Trie](./src/main/java/com/javarena/dsa/datastructures/trie/README.md)                   |
| 7 | [Segment Tree](./src/main/java/com/javarena/dsa/datastructures/segmentTree/README.md)    |
| 8 | [Fenwick Tree](./src/main/java/com/javarena/dsa/datastructures/fenwickTree/README.md)    |

---

## 🏢 Company-Wise Problems

Solve company-tagged questions like real interviews and track your progress here:

| Company                                                    | File                                                                    |
|------------------------------------------------------------|-------------------------------------------------------------------------|
| Amazon                                                     | [Amazon.md](./src/main/java/com/javarena/dsa/companies/Amazon.md)       |
| Adobe                                                      | [Adobe.md](./src/main/java/com/javarena/dsa/companies/Adobe.md)         |
| Google                                                     | [Google.md](./src/main/java/com/javarena/dsa/companies/Google.md)       |
| Microsoft                                                  | [Microsoft.md](./src/main/java/com/javarena/dsa/companies/Microsoft.md) |
| Meta                                                       | [Meta.md](./src/main/java/com/javarena/dsa/companies/Meta.md)           |
| Apple                                                      | [Apple.md](./src/main/java/com/javarena/dsa/companies/Apple.md)         |
| [+380 More...](./src/main/java/com/javarena/dsa/companies) | 📁 View all companies                                                   |

Each file contains:

- 🎯 **Problem link**
- 📂 **Solution file path**
- ⚡️ **Difficulty & topic tags**

---

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/piyush7199/javarena-dsa.git
cd javarena-dsa
```

### Build with Maven

```bash
mvn clean compile
```

### Run Topic Suggester

```bash
mvn exec:java -Dexec.mainClass="com.javarena.dsa.utils.TopicSuggester"
```

### IDE Setup

Open in your favorite IDE:

- **IntelliJ IDEA**: File → Open → Select project directory
- **VS Code**: Install Java Extension Pack, then open project directory
- **Eclipse**: File → Import → Maven → Existing Maven Projects

---

## 🏷️ Badges

![Java](https://img.shields.io/badge/language-Java-orange)
![LeetCode](https://img.shields.io/badge/platform-LeetCode-blue)
![GFG](https://img.shields.io/badge/platform-GeeksforGeeks-blue)
![DSA](https://img.shields.io/badge/focus-Data%20Structures%20%26%20Algorithms-success)
![Interview Prep](https://img.shields.io/badge/goal-Interview%20Preparation-brightgreen)