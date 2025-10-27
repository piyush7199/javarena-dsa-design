# 🌐 Disjoint Set (Union-Find)

## Introduction

The **Disjoint Set**, also known as **Union-Find**, is a data structure that manages a collection of non-overlapping
sets, supporting efficient **Find**, **Union**, and **Connected** operations. It is widely used in graph problems, such
as detecting cycles, finding connected components, or implementing Kruskal’s algorithm for minimum spanning trees. The
Disjoint Set is optimized using **path compression** for Find operations and either **union by rank** or
**union by size** for Union operations, achieving near-constant time complexity (amortized O(α(n)), where α is the
inverse Ackermann
function).

Key operations:

- **Find**: Returns the representative (root) of the set containing an element.
- **Union**: Merges two sets by linking their representatives, using either:
    - **Union by Rank**: Balances trees based on their height (rank).
    - **Union by Size**: Balances trees based on the number of elements in each set.
- **Connected**: Checks if two elements are in the same set.

---

## Intuition

The Disjoint Set is like managing groups of friends in a social network, where you merge groups (Union) or check if two
people are in the same group (Find). The data structure uses a forest of trees, where each tree represents a set, and
the root is the set’s representative.

- **Find with Path Compression**: When finding the root of an element’s set, make all nodes in the path point directly
  to the root, flattening the tree for future efficiency.
- **Union Optimizations**:
    - **Union by Rank**: Merge trees by attaching the tree with lower rank (height) to the tree with higher rank,
      keeping trees balanced to minimize height.
    - **Union by Size**: Merge trees by attaching the smaller tree (fewer elements) to the larger tree, reducing the
      number of nodes moved and maintaining balance.
- **Why it Works**: Path compression ensures Find operations are nearly constant time by flattening trees. Both union by
  rank and union by size prevent trees from becoming too deep, achieving amortized O(α(n)) time for operations.
- **Key Insight**:
    - **Union by Rank**: Focuses on tree height, simpler to implement but may not always minimize node updates.
    - **Union by Size**: Tracks the exact number of elements, potentially leading to fewer node updates but requiring
      additional space for size tracking.
    - Both optimizations yield similar performance (O(α(n))), but union by size may be preferred when minimizing node
      updates is critical.

---

## Use Cases

The Disjoint Set is used in:

- **Real-World Applications**:
    - **Network Connectivity**: Determining connected components in computer or social networks.
    - **Image Processing**: Grouping pixels into regions (e.g., connected component labeling).
    - **Graph Algorithms**: Used in clustering, community detection, or maze generation.
    - **Distributed Systems**: Managing partitions or consensus in distributed databases.
- **Algorithm Optimization**:
    - Efficiently tracking set membership or connectivity in dynamic graph problems.
    - Used as a subroutine in algorithms requiring frequent merging or querying of groups.

---

## Algorithm

Below are pseudocode and Java implementations for the Disjoint Set with both **union by rank** and **union by size**,
including path compression.

> Java Implementation - [DisjointSet.java](../datastructures/graph/DisjointSet.java)


**Intuition**:

- **Union by Rank**: Uses `rank` to approximate tree height, merging the shorter tree under the taller one to minimize
  height.
- **Union by Size**: Uses `size` to track the number of elements in each set, merging the smaller set into the larger
  one to minimize node updates.
- Both use **path compression** in Find to flatten trees, ensuring near-constant time operations.

- **Time Complexity**:
    - **Find**: Amortized O(α(n)), where α(n) is the inverse Ackermann function, effectively constant for practical
      inputs.
    - **Union**: Amortized O(α(n)) for both union by rank and union by size, due to path compression and balanced
      merging.
    - **Connected**: Amortized O(α(n)), as it involves two Find operations.
    - **Initialization**: O(n) to initialize the parent and rank/size arrays for n elements.
- **Space Complexity**:
    - **Union by Rank**: O(n) for the parent and rank arrays.
    - **Union by Size**: O(n) for the parent and size arrays.
    - Total: O(n) for the data structure in both cases.

> **Note**: The inverse Ackermann function α(n) grows extremely slowly, making operations practically O(1). Without
> optimizations, Find and Union can degrade to O(n) in the worst case (linear tree).

### Pseudocode (Union by Rank)

```pseudo
class DisjointSetRank:
    parent = array of size n
    rank = array of size n

    function initialize(n):
        for i from 0 to n-1:
            parent[i] = i
            rank[i] = 0

    function find(x):
        if parent[x] != x:
            parent[x] = find(parent[x]) # Path compression
        return parent[x]

    function union(x, y):
        rootX = find(x)
        rootY = find(y)
        if rootX != rootY:
            if rank[rootX] < rank[rootY]:
                parent[rootX] = rootY
            else if rank[rootX] > rank[rootY]:
                parent[rootY] = rootX
            else:
                parent[rootY] = rootX
                rank[rootX] += 1

    function connected(x, y):
        return find(x) == find(y)
```

### Pseudocode (Union by Size)

```pseudo
class DisjointSetSize:
    parent = array of size n
    size = array of size n

    function initialize(n):
        for i from 0 to n-1:
            parent[i] = i
            size[i] = 1

    function find(x):
        if parent[x] != x:
            parent[x] = find(parent[x]) # Path compression
        return parent[x]

    function union(x, y):
        rootX = find(x)
        rootY = find(y)
        if rootX != rootY:
            if size[rootX] < size[rootY]:
                parent[rootX] = rootY
                size[rootY] += size[rootX]
            else:
                parent[rootY] = rootX
                size[rootX] += size[rootY]

    function connected(x, y):
        return find(x) == find(y)
```

---

## 🧪 Practice Problems

| # | Problem                                                                                                 | Solution                                                                     |
|---|---------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| 1 | [Process Restricted Friend Requests](https://leetcode.com/problems/process-restricted-friend-requests/) | [ProcessRestrictions.java](../datastructures/graph/ProcessRestrictions.java) |

---

## Resources

- [GFG | Disjoint Set](https://www.geeksforgeeks.org/dsa/introduction-to-disjoint-set-data-structure-or-union-find-algorithm/)
- [Techdose | Disjoint Set | UNION and FIND](https://www.youtube.com/watch?v=eTaWFhPXPz4)
- [TakeUForward | Disjoint Set](https://www.youtube.com/watch?v=aBxjDBC4M1U)