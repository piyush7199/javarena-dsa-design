# 📍 Dijkstra’s Algorithm

## Introduction

**Dijkstra’s Algorithm** is a greedy algorithm used to find the shortest path from a single source node to all other
nodes in a weighted graph with non-negative edge weights. It is widely used in solving problems involving shortest
paths, such as network routing or pathfinding in graphs.

Key characteristics:

- Works on **directed or undirected graphs** with non-negative edge weights.
- Uses a **priority queue** (min-heap) to greedily select the node with the smallest tentative distance.
- Produces a **shortest-path tree** from the source to all reachable nodes.

---

## Intuition

Dijkstra’s Algorithm operates like a navigator finding the fastest route from a starting point to all destinations in a
city, choosing the shortest available path at each step:

- **Idea**: Start at the source node and maintain a priority queue of nodes, prioritized by their tentative distance
  from the source. For each node, explore its neighbors, update their distances if a shorter path is found, and mark the
  node as processed.
- **Why it Works**: The greedy approach ensures that when a node is processed (dequeued), its shortest distance is
  finalized, as all shorter paths have been explored. The priority queue ensures efficient selection of the next node to
  process.
- **Key Insight**: The algorithm relies on non-negative edge weights to guarantee correctness, as negative weights could
  lead to shorter paths being discovered later (handled by algorithms like Bellman-Ford).

---

## Use Cases

Dijkstra’s Algorithm is used in:

- **Real-World Applications**:
    - **Network Routing**: Computing the shortest path for data packets in computer networks (e.g., OSPF protocol).
    - **GPS Navigation**: Finding the fastest route in road networks.
    - **Robotics/Game Development**: Pathfinding for AI agents or characters (e.g., A* is an extension of Dijkstra’s).
    - **Telecommunications**: Optimizing signal transmission paths.
- **Algorithm Optimization**:
    - Used as a subroutine in more complex algorithms (e.g., finding shortest paths in dynamic programming or graph
      traversal).
    - Adapted for grid-based problems (e.g., shortest path in a maze with weighted cells).

---

## Algorithm

Below is intuition, time and space complexity and pseudocode for Dijkstra’s Algorithm.

> Java Implementation - [Dijkstra.java](../datastructures/graph/Dijkstra.java)

**Intuition**: Initialize distances to infinity except for the source (0). Use a priority queue to process nodes in
order of increasing distance. For each node, relax its neighbors’ distances if a shorter path is found. Skip processed
nodes to avoid cycles.

- **Time Complexity**:
    - **Initialization**: O(V) to initialize distances and the priority queue, where V is the number of vertices.
    - **Priority Queue Operations**:
        - Inserting V vertices: O(V log V) with a binary heap.
        - Updating distances (relaxation) for E edges: O(E log V) in total, as each edge may cause a priority queue
          update.
    - **Total**: O((V + E) log V) with a binary heap, or O(V²) with an array-based implementation (less efficient for
      sparse graphs).
- **Space Complexity**:
    - O(V) for the distance array, visited set, and priority queue.
    - O(V + E) for the adjacency list representation of the graph.
    - Total: O(V + E).

> **Note**: Using a Fibonacci heap reduces time complexity to O(V log V + E), but it’s rarely used in practice due to
> implementation complexity. The binary heap is standard.

```pseudo
function dijkstra(graph, source):
    # Input: graph (adjacency list), source node
    # Output: array of shortest distances from source
    distances = array of size V initialized to infinity
    distances[source] = 0
    pq = new PriorityQueue() # Min-heap of (distance, node)
    pq.add((0, source))
    visited = empty set

    while pq is not empty:
        (dist, node) = pq.remove() # Get node with smallest distance
        if node in visited:
            continue
        visited.add(node)
        
        for (neighbor, weight) in graph[node]:
            if dist + weight < distances[neighbor]:
                distances[neighbor] = dist + weight
                pq.add((distances[neighbor], neighbor))
    
    return distances
```

---

## 🧪 Practice Problems

| # | Problem                                                                                           | Solution                                                                                 |
|---|---------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------|
| 1 | [Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/)               | [ShortestPathUsingDijkstra.java](../datastructures/graph/ShortestPathUsingDijkstra.java) |
| 2 | [Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/) | [ShortestPathUsingDijkstra.java](../datastructures/graph/ShortestPathUsingDijkstra.java) |                                                                                    |
| 3 | [Network Delay Time](https://leetcode.com/problems/network-delay-time/)                           | [NetworkDelay.java](../datastructures/graph/NetworkDelay.java)                           |
|   |

---

## Resources

- [GFG | Dijkstra’s Algorithm ](https://www.geeksforgeeks.org/dsa/dijkstras-shortest-path-algorithm-greedy-algo-7/)