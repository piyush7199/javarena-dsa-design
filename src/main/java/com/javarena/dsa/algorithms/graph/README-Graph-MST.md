# 🌳 Minimum Spanning Tree

## Introduction

A **Minimum Spanning Tree (MST)** is a subset of edges in a weighted, undirected graph that connects all vertices
without cycles and has the minimum total edge weight. MST is a fundamental graph problem used to
solve optimization problems like network design or clustering. Two primary algorithms for finding an MST are:

- **Kruskal’s Algorithm**: Uses a Disjoint Set to greedily select edges in increasing order of weight, avoiding cycles.
- **Prim’s Algorithm**: Grows the MST from a starting vertex, greedily selecting the minimum-weight edge to an unvisited
  vertex using a priority queue.

Key characteristics:

- Works on **weighted, undirected graphs** with non-negative edge weights.
- Produces a tree with **V-1 edges** (where V is the number of vertices).
- Ensures the **minimum total edge weight** among all possible spanning trees.

---

## Intuition

The MST problem is like designing a network to connect cities with the least total cost of roads, ensuring no cycles:

- **Kruskal’s Algorithm**: Sort all edges by weight and pick the smallest ones that don’t form a cycle, using a Disjoint
  Set to track connected components. It’s like building roads globally, starting with the cheapest connections.
- **Prim’s Algorithm**: Start from one city and greedily add the cheapest road to an unconnected city, using a priority
  queue to select the next edge. It’s like growing a network locally from a starting point.
- **Why it Works**: Both algorithms use a **greedy approach**, ensuring the minimum total weight by selecting locally
  optimal edges (safe edges) that maintain connectivity without cycles.
- **Key Insight**:
    - **Kruskal’s**: Focuses on edges, making it efficient for sparse graphs. Relies on Disjoint Set for cycle
      detection.
    - **Prim’s**: Focuses on vertices, making it efficient for dense graphs. Similar to Dijkstra’s but builds a tree
      instead of shortest paths.

---

## Use Cases

MST is used in:

- **Real-World Applications**:
    - **Network Design**: Minimizing cable length in computer or telecommunication networks.
    - **Transportation**: Optimizing road or railway networks for minimum construction cost.
    - **Clustering**: Grouping data points in machine learning (e.g., hierarchical clustering).
    - **Circuit Design**: Connecting components with minimum wire length.
- **Algorithm Optimization**:
    - Used as a subroutine in approximation algorithms (e.g., Traveling Salesman Problem).
    - Solving problems requiring minimum-cost connectivity in graphs.

---

## Algorithm

Below are pseudocode and Java implementations for **Kruskal’s** and **Prim’s** algorithms to find the MST.

### Kruskal’s Algorithm

**Intuition**: Sort edges by weight and greedily add those that don’t form cycles (checked via Disjoint Set). Stop when
V-1 edges are added.

- **Time Complexity**:
    - Sorting edges: O(E log E), where E is the number of edges.
    - Union-Find operations: O(E α(V)) for E unions/finds, where α(V) is the inverse Ackermann function (nearly
      constant).
    - Total: O(E log E), dominated by sorting (since E ≤ V², log E ≤ 2 log V, so O(E log E) ≈ O(E log V)).
- **Space Complexity**:
    - O(E) for storing edges.
    - O(V) for Disjoint Set (parent and rank/size arrays).
    - Total: O(V + E).

#### Pseudocode

```
function kruskal(graph, V, E):
    # Input: graph (list of edges [u, v, weight]), V vertices, E edges
    # Output: MST edges and total weight
    mst = empty list
    totalWeight = 0
    ds = new DisjointSet(V)
    edges = sort graph edges by weight

    for edge (u, v, weight) in edges:
        if ds.find(u) != ds.find(v):
            ds.union(u, v)
            mst.add(edge)
            totalWeight += weight
    
    return mst, totalWeight
```

### Prim’s Algorithm (with Priority Queue)

**Intuition**: Start from a node, greedily select the minimum-weight edge to an unvisited node, and grow the tree using
a priority queue to track the next edge.

- **Time Complexity**:
    - Initialization: O(V) for distances and priority queue.
    - Priority queue operations: O(V log V) for V insertions, O(E log V) for E updates (relaxations).
    - Total: O((V + E) log V) with a binary heap.
- **Space Complexity**:
    - O(V) for distances, visited set, and priority queue.
    - O(V + E) for adjacency list representation.
    - Total: O(V + E).

#### Pseudocode

```
function prim(graph, V, start):
    # Input: graph (adjacency list [node -> list of (neighbor, weight)]), V vertices, start node
    # Output: MST edges and total weight
    mst = empty list
    totalWeight = 0
    distances = array of size V initialized to infinity
    distances[start] = 0
    pq = new PriorityQueue() # Min-heap of (distance, node)
    pq.add((0, start))
    visited = empty set
    parent = array of size V initialized to -1

    while pq is not empty:
        (dist, node) = pq.remove()
        if node in visited:
            continue
        visited.add(node)
        totalWeight += dist
        if parent[node] != -1:
            mst.add(edge(parent[node], node, dist))
        
        for (neighbor, weight) in graph[node]:
            if neighbor not in visited and weight < distances[neighbor]:
                distances[neighbor] = weight
                parent[neighbor] = node
                pq.add((weight, neighbor))
    
    return mst, totalWeight
```

> **Note**: Kruskal’s is preferred for sparse graphs (E << V²), while Prim’s is better for dense graphs due to adjacency
> list access. Both achieve near-optimal performance with proper data structures.

---