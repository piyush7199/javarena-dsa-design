# Segment Tree

A **Segment Tree** is a powerful data structure that enables efficient processing of range-based queries and updates on
arrays. It supports operations like computing sums, finding minimum/maximum values in a subarray, and updating
individual elements — all in `O(log n)` time.

This project includes two implementations:

- **[STUsingArray](./impl/STUsingArray.java):** A Segment Tree implemented using an array for storage, optimized for
  memory and speed.
- **[STUsingNode](./impl/STUsingNode.java):** A Segment Tree implemented using a node-based tree structure, offering
  flexibility for complex operations.

___

## Use Cases

**Segment Trees** are widely used in scenarios requiring efficient range queries and updates, including:

- **Range Sum Queries:** Compute the sum of elements in a given range [L, R] (e.g., sum of array elements).
- **Range Minimum/Maximum Queries:** Find the minimum or maximum value in a range.
- **Point Updates:** Update a single element in the array and reflect it in the Segment Tree.
- **Range Updates with Lazy Propagation:** Update a range of elements efficiently (requires extension).
- **Competitive Programming:** Solve problems like range sum, range min/max, or interval-based queries on platforms like
  LeetCode and GFG.
- **Real-World Applications:** Used in databases, time-series analysis, and computational geometry for interval-based
  computations.

---

## Complexity Analysis

Below is the space and time complexity for both Segment Tree implementations:

### [STUsingArray](./impl/STUsingArray.java)

- **Space Complexity: O(n)**, where `n` is the size of the input array. The Segment Tree array requires approximately
  `4n` space to store all nodes (including leaf and internal nodes).

- **Time Complexity:**
    - **Construction: `O(n)`** to build the tree by recursively processing each element.
    - **Query: `O(log n)`** for range sum queries, as it traverses the tree height (log n) to compute the result.
    - **Update: `O(log n)`** for point updates, as it updates nodes along a single path from leaf to root.

### [STUsingNode](./impl/STUsingNode.java)

- **Space Complexity:** `O(n)`, where n is the size of the input array. Each node represents a segment, and the tree has
  approximately 2n-1 nodes for n elements.

- **Time Complexity:**
    - **Construction: `O(n)`** to build the tree by recursively creating nodes for each segment.
    - **Query: `O(log n)`** for range sum queries, as it traverses the tree height (log n) to compute the result.
    - **Update: `O(log n)`** for point updates, as it updates nodes along a single path from leaf to root.

> 🔧 Note: Both implementations are optimized for range sum queries and point updates. For range updates or other
> operations (e.g., min/max queries), modifications like lazy propagation may be needed, which could affect complexity.

---

## 💻 Problem Tracker

| # | Problem                                                                             | Solution File                                            |
|---|-------------------------------------------------------------------------------------|----------------------------------------------------------|
| 1 | [Range Minimum Query](https://www.geeksforgeeks.org/problems/range-minimum-query/1) | [RangeMinimumQuery.java](./RangeMinimumQuery.java)       |
| 2 | [Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable/) | [STUsingArray.java](./impl/STUsingArray.java)            |
| 3 | [Block Placement Queries](https://leetcode.com/problems/block-placement-queries/)   |                                                          |
| 4 | [Fruits Into Baskets III](https://leetcode.com/problems/fruits-into-baskets-iii/)   | [FruitsIntoBasketsIII.java](./FruitsIntoBasketsIII.java) |