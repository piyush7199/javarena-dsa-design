# 🌳 Fenwick Tree (Binary Indexed Tree)

## Introduction

The **Fenwick Tree** (also known as **Binary Indexed Tree**, or BIT) is an advanced data structure used for efficiently
computing **prefix sums** and **range sums**, as well as updating elements in an array. Unlike Segment Trees, Fenwick
Trees are more space-efficient and simpler to implement, leveraging bit manipulation to manage a tree-like structure for
partial sums.

Key characteristics:

- Supports **point updates** (update a single element) and **range queries** (sum over a range) in O(log n) time.
- Uses bit manipulation to navigate the tree, reducing code complexity compared to Segment Trees.
- Ideal for problems involving cumulative sums, frequency counts, or dynamic updates in arrays.

---

## Intuition

A Fenwick Tree is like a compact tree that stores partial sums to answer range queries efficiently:

- **Idea**: Represent an array as a tree where each node stores the sum of a range of elements. The tree is implicit,
  using bit manipulation to determine parent-child relationships.
- **Bit Manipulation**: The index of a node (e.g., i) manages the sum of elements from i to i - LSB(i) + 1, where LSB(i)
  is the least significant bit of i (i & -i).
    - **Update**: When updating an element at index i, update all nodes responsible for i by traversing up the tree (
      i += i & -i).
    - **Query**: To compute a prefix sum up to index i, sum the nodes by traversing down the tree (i -= i & -i).
- **Range Sum**: Compute sum[l, r] as prefixSum(r) - prefixSum(l-1).
- **Key Insight**:
    - The tree’s structure leverages binary representation of indices, making updates and queries logarithmic.
    - It’s more space-efficient (O(n)) than Segment Trees (O(n) or O(4n) depending on implementation) and simpler to
      code.
- **Example**: For array [1, 2, 3, 4], the Fenwick Tree stores partial sums (e.g., tree[4] = sum of elements 1 to 4,
  tree[2] = sum of elements 1 to 2), allowing fast updates and range queries.

---

## Use Cases

Fenwick Trees are used in:

- **Real-World Applications** (Relevant to Backend Development):
    - **Database Query Optimization**: Efficiently compute aggregate sums or counts in large datasets (e.g., time-series
      data in SQL/NoSQL).
    - **Real-Time Analytics**: Aggregate metrics in systems like Kafka Streams or Prometheus (e.g., sum of events in a
      time window).
    - **Log Analysis**: Count occurrences of events in log streams.
    - **Financial Systems**: Compute running totals or balances in transaction systems.
- **Algorithm Optimization**:
    - Reduces time complexity from O(n) per query/update to O(log n), critical for large-scale backend systems.
    - Useful in scenarios requiring frequent updates and queries, such as analytics dashboards or monitoring systems.

---

## Complexity Analysis

- **Construction**:
    - **Time Complexity**: O(n log n), as each element’s update affects O(log n) nodes.
    - **Space Complexity**: O(n) for the tree array.
- **Point Update** (update element at index i):
    - **Time Complexity**: O(log n), as it updates O(log n) nodes.
    - **Space Complexity**: O(1) additional space.
- **Prefix Sum Query** (sum from index 1 to i):
    - **Time Complexity**: O(log n), as it traverses O(log n) nodes.
    - **Space Complexity**: O(1) additional space.
- **Range Sum Query** (sum from index l to r):
    - **Time Complexity**: O(log n), as it computes prefixSum(r) - prefixSum(l-1).
    - **Space Complexity**: O(1) additional space.

> **Note**: Fenwick Trees are more efficient than Segment Trees for point updates and prefix/range sum queries but are
> less versatile (e.g., they don’t support range minimum/maximum queries without modification).

---

## Algorithm

Below are pseudocode and Java implementations for constructing a Fenwick Tree, performing point updates, and computing
range sum queries.

> Java Imp : [FenwickTree.java](./FenwickTree.java)

### Pseudocode (Fenwick Tree)

**Intuition**:

- **Construction**: Initialize the tree by updating each element’s contribution to the tree.
- **Update**: Add delta to the current index and all indices responsible for it, using bit manipulation to find the next
  index (index += index & -index).
- **Prefix Sum**: Sum the values at indices by traversing to parent nodes (index -= index & -index).
- **Range Sum**: Compute as the difference of two prefix sums.

```pseudo
function constructFenwickTree(arr, n):
    # Input: arr (array of numbers), n (array size)
    # Output: Fenwick Tree array
    tree = array of size n+1 initialized to 0
    for i from 0 to n-1:
        update(tree, n, i+1, arr[i])
    return tree

function update(tree, n, index, delta):
    # Update element at index with delta, propagate to affected nodes
    while index <= n:
        tree[index] += delta
        index += index & (-index)  # Move to next responsible node

function prefixSum(tree, index):
    # Compute sum from index 1 to index
    sum = 0
    while index > 0:
        sum += tree[index]
        index -= index & (-index)  # Move to parent node
    return sum

function rangeSum(tree, left, right):
    # Compute sum from index left to right
    return prefixSum(tree, right) - prefixSum(tree, left-1)
```

