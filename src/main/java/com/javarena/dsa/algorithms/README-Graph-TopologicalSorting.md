# 🔢 Topological Sorting

## Introduction

**Topological Sorting** is an algorithm used to order the vertices of a **Directed Acyclic Graph (DAG)** such that for
every directed edge `(u, v)`, vertex `u` comes before vertex `v` in the ordering. It produces a linear arrangement of
vertices that respects the partial order defined by the graph’s edges.

There are two primary algorithms for Topological Sorting:

- **DFS-Based:** Uses Depth-First Search to explore the graph, appending nodes to the result after their dependencies
  are
  processed.
- **Kahn’s Algorithm:** Uses in-degree counting and a queue to iteratively process nodes with no incoming edges.

---

## Intuition

Topological Sorting ensures that for any dependency (edge) in a DAG, the dependent node appears after its prerequisites
in the final order. Here’s the intuition behind each algorithm:

### DFS-Based Topological Sort:

- **Idea:** Start at a node and explore its dependencies (outgoing edges) as far as possible using DFS. Once all
  dependencies of a node are visited, add the node to the result. This ensures that a node is placed after all its
  dependents.

- **Why it Works:** DFS naturally processes nodes in a post-order fashion (i.e., a node is processed after its
  children). By reversing this order (or using a stack), we get a valid topological order where nodes appear after their
  dependencies.

- **Key Insight:** The deepest nodes (those with no outgoing edges) are added first to the result, and their parents
  follow, ensuring the correct order.

### Kahn’s Algorithm:

- **Idea:** Start with nodes that have no incoming edges (in-degree = 0), as they have no dependencies and can be placed
  first. Remove these nodes and their edges, update in-degrees, and repeat until all nodes are processed.

- **Why it Works:** Nodes with in-degree 0 are `“ready”` to be included in the topological order. Removing their edges
  simulates completing those tasks, reducing the in-degree of their dependents, which may then become ready.

- **Key Insight:** The queue ensures that nodes are processed in a valid order, and cycle detection is possible if not
  all nodes are included in the result.

> Both algorithms guarantee a valid topological order for a DAG. If a cycle exists, topological sorting is impossible,
> and both algorithms can detect this (DFS via back edges, Kahn’s via unprocessed nodes).

---

## Use Cases

Topological Sorting is essential for scenarios involving dependencies or ordering, including:

- **Task Scheduling:** Order tasks where some depend on others (e.g., course prerequisites, build systems like Make or
  Gradle).

- **Dependency Resolution:** Resolve dependencies in software package managers (e.g., npm, pip).

- **Course Scheduling:** Arrange courses where some require prerequisites (e.g., LeetCode’s “Course Schedule”).

- **Deadlock Detection:** Detect cycles in a graph to ensure a valid topological order exists (only possible in DAGs).

- **Real-World Applications:** Used in job scheduling, workflow management, circuit design, and build pipelines to
  ensure proper execution order.

---

## Algorithms

Below is the pseudocode for both Topological Sorting algorithms, written in a clear, FAANG-interview-ready format.

### DFS-Based Topological Sort

#### **Intuition:**

- Start DFS from an unvisited node, recursively visiting all its neighbors (dependencies).
- Once all neighbors are processed, append the current node to the result.
- Reversing the result ensures nodes appear after their dependencies.
- **Cycle Detection:** If a node is visited during DFS and is already in the recursion stack (e.g., using a separate
  “in-stack” set), a cycle exists.

#### **Time Complexity:**

- **Construction: `O(V + E)`**, where `V` is the number of vertices and `E` is the number of edges. DFS visits each
  vertex and edge once.
- **Cycle Detection:** Included in `O(V + E)` if checking for back edges during traversal.

#### **Space Complexity:**

- **`O(V)`** for the recursion stack (call stack during DFS) and the output array storing the topological order.
- **`O(V)`** for the visited array to track explored nodes.

#### **Pseudo Code**

```
function topologicalSortDFS(graph):
    # Input: graph as adjacency list (map of node to list of neighbors)
    # Output: list of nodes in topological order
    Initialize result as empty list
    Initialize visited as empty set

    function dfs(node):
        if node in visited:
            return
        visited.add(node)
        for neighbor in graph[node]:
            dfs(neighbor)
        result.append(node)

    for each node in graph:
        if node not in visited:
            dfs(node)

    return reverse(result) # Reverse to get correct order
```

### Kahn’s Algorithm (Queue-Based)

#### **Intuition:**

- Compute the in-degree (number of incoming edges) for each node.
- Start with nodes having in-degree 0 (no dependencies) and add them to a queue.
- Process each node, reduce the in-degree of its neighbors, and add neighbors with in-degree 0 to the queue.
- If all nodes are processed, the result is a valid topological order; otherwise, a cycle exists.
- **Cycle Detection:** If the result list doesn’t include all nodes, a cycle prevents a valid topological order.

#### **Time Complexity:**

- **Construction: `O(V + E)`**, where `V` is the number of vertices and `E` is the number of edges. The algorithm
  processes each vertex and edge once while maintaining a queue.
- **Cycle Detection:** Included in `O(V + E)` if checking for unprocessed nodes (indicating a cycle).

#### **Space Complexity:**

- **`O(V)`** for the queue, in-degree array, and output array storing the topological order.
- **`O(V)`** for additional bookkeeping (e.g., visited nodes or in-degree counts).

#### **Pseudo Code**

```
function topologicalSortKahn(graph):
    # Input: graph as adjacency list (map of node to list of neighbors)
    # Output: list of nodes in topological order or detect cycle
    Initialize result as empty list
    Initialize inDegree as map of node to count of incoming edges
    Initialize queue as empty queue

    # Calculate in-degrees for all nodes
    for each node in graph:
        for neighbor in graph[node]:
            inDegree[neighbor] += 1

    # Add nodes with in-degree 0 to queue
    for each node in graph:
        if inDegree[node] == 0:
            queue.enqueue(node)

    while queue is not empty:
        node = queue.dequeue()
        result.append(node)
        for neighbor in graph[node]:
            inDegree[neighbor] -= 1
            if inDegree[neighbor] == 0:
                queue.enqueue(neighbor)

    if result.length != number of nodes:
        return "Cycle detected, no valid topological order"
    return result
```

---

## 🧪 Practice Problems

| # | Problem                                                                               | Solution                                                                                                                   |
|---|---------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| 1 | [Topological sort](https://www.geeksforgeeks.org/problems/topological-sort/1)         | [BFSSolution.java](../datastructures/graph/BFSSolution.java), [DFSSolution.java](../datastructures/graph/DFSSolution.java) |
| 2 | [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)               | [TopologicalSorting.java](../datastructures/graph/TopologicalSorting.java)                                                 |
| 3 | [Course Schedule](https://leetcode.com/problems/course-schedule/)                     | [TopologicalSorting.java](../datastructures/graph/TopologicalSorting.java)                                                 |
| 4 | [Find Eventual Safe States](https://leetcode.com/problems/find-eventual-safe-states/) | [TopologicalSorting.java](../datastructures/graph/TopologicalSorting.java)                                                 |
| 5 | [Alien Dictionary](https://www.geeksforgeeks.org/problems/alien-dictionary/1)         | [TopologicalSorting.java](../datastructures/graph/TopologicalSorting.java)                                                 |

---

## Resources

- [Topological Sorting GFG](https://www.geeksforgeeks.org/dsa/topological-sorting/)