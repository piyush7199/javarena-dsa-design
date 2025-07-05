# 🌐 Graph Algorithms in Java

This module focuses on core **graph algorithms** and their implementations in Java. It includes representation
techniques, traversal methods, and real-world problem-solving with BFS, DFS, and more.

---

## 📑 Table of Contents

1. [📘 Introduction](#-introduction)
2. [🧱 Graph Representation](#-graph-representation)
3. [🔄 BFS & DFS](#-bfs--dfs)
4. [🧪 Practice Problems](#-practice-problems)

---

## 📘 Introduction

A **graph** is a non-linear data structure consisting of nodes (vertices) and edges. It's useful for modeling networks,
social relationships, routing paths, etc.

- 📂 [Code Reference](./Graph.java)

---

## 🧱 Graph Representation

Graphs can be represented using:

- **Adjacency Matrix**
- **Adjacency List**
- **Edge List**

> Implementation available at: [`Graph.java`](./Graph.java)

---

## 🔄 BFS & DFS

Graph traversal algorithms help in searching or visiting nodes:

- **BFS (Breadth-First Search)**: Explores neighbors first (uses a queue).
    - 📖 [GFG: BFS](https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/)
- **DFS (Depth-First Search)**: Explores depth before breadth (uses a stack or recursion).
    - 📖 [GFG: DFS](https://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/)

> Implementation available at: [`GraphAlgorithms.java`](./GraphAlgorithms.java)

---

## 🧪 Practice Problems

| #  | Problem                                                                                                | Type                   | Solution File                                                                  |
|----|--------------------------------------------------------------------------------------------------------|------------------------|--------------------------------------------------------------------------------|
| 1  | [BFS of Graph](https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1)                        | Traversal              | [GraphAlgorithms.java](./GraphAlgorithms.java)                                 |
| 2  | [DFS of Graph](https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1)             | Traversal              | [GraphAlgorithms.java](./GraphAlgorithms.java)                                 |
| 3  | [Number of Provinces](https://leetcode.com/problems/number-of-provinces/)                              | Connected Components   | [BFSSolution.java](./BFSSolution.java), [DFSSolution.java](./DFSSolution.java) |
| 4  | [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)                                      | Traversal              | [BFSSolution.java](./BFSSolution.java)                                         |
| 5  | [Flood Fill](https://leetcode.com/problems/flood-fill/)                                                | Traversal              | [BFSSolution.java](./BFSSolution.java), [DFSSolution.java](./DFSSolution.java) |
| 6  | [Undirected Graph Cycle](https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1) | Cycle Detection        | [BFSSolution.java](./BFSSolution.java), [DFSSolution.java](./DFSSolution.java) |
| 7  | [Topological sort](https://www.geeksforgeeks.org/problems/topological-sort/1)                          | Topological Sorting    | [BFSSolution.java](./BFSSolution.java), [DFSSolution.java](./DFSSolution.java) |                                                                   |
| 8  | [01 Matrix](https://leetcode.com/problems/01-matrix/)                                                  | Traversal              | [BFSSolution.java](./BFSSolution.java)                                         |
| 9  | [Number of Enclaves](https://leetcode.com/problems/number-of-enclaves/)                                | Connected Components   | [Enclaves.java](./Enclaves.java)                                               |
| 10 | [Word Ladder](https://leetcode.com/problems/word-ladder/)                                              | Traversal              | [WordLadder.java](./WordLadder.java)                                           |
| 11 | [Surrounded Regions](https://leetcode.com/problems/surrounded-regions/)                                | Traversal,  Components | [SurroundedRegions.java](./SurroundedRegions.java)                             |
| 12 | [Find the number of islands](https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1)      | Traversal,  Components | [SurroundedRegions.java](./SurroundedRegions.java)                             |

---

## Resources

- 📖 [GFG: Graph and its Representations](https://www.geeksforgeeks.org/dsa/graph-and-its-representations/)
- 📖 [Graph - Take U Forward](https://takeuforward.org/graph/introduction-to-graph/)