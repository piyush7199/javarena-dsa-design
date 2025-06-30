# 🧠 Dynamic Programming

## Introduction

**Dynamic Programming (DP)** is a technique for solving problems by breaking them down into overlapping subproblems and
storing solutions to avoid redundant computations.

---

## Intuition

- **Idea:** Solve a problem by combining solutions to smaller subproblems, storing results in a table (memoization or
  tabulation) to avoid recomputation.

- **Why it Works:** For problems with overlapping subproblems and optimal substructure, DP reduces exponential time
  complexity (e.g., O(2^n)) to polynomial time (e.g., O(n²) or O(n)).

- **Approaches:**
    - **Top-Down (Memoization):** Recursive solution with a cache to store results.
    - **Bottom-Up (Tabulation):** Iterative solution building a table from smaller to larger subproblems.

> Read
> more [Dynamic Programming Introduction – Take U Forward](https://takeuforward.org/data-structure/dynamic-programming-introduction/)

---

## Use Cases

- **Real-World Applications:** Optimization in resource allocation, sequence alignment, and pathfinding in robotics or
  games.
- **Algorithm Design:** Used in problems requiring optimal solutions over multiple choices (e.g., minimum cost, maximum
  profit).

---

## Complexity Analysis

- **General Complexity:** Varies by problem, often `O(n²)` or `O(n*m)` for classic DP problems.

- **0/1 Knapsack:**
    - **Time Complexity: `O(n*W)`** where n is the number of items and W is the capacity.
    - **Space Complexity: `O(n*W)`** for the DP table, reducible to O(W) with optimization.

- **Longest Common Subsequence:**
    - **Time Complexity: `O(m*n)`** where m and n are the lengths of the sequences.
    - **Space Complexity: `O(m*n)`** for the DP table, reducible to O(min(m,n)).

---

## Algorithm (0/1 Knapsack - Bottom-Up)

**Intuition:** Build a table where `dp[i][w]` represents the maximum value for the first i items with capacity w. For
each item, decide whether to include it (if weight allows) or exclude it, choosing the maximum value.

```
function knapsack(values, weights, W):
    # Input: arrays of values and weights, capacity W
    # Output: maximum value achievable within capacity W
    Initialize dp as (n+1) x (W+1) table with 0s
    for i from 1 to n:
        for w from 0 to W:
            if weights[i-1] <= w:
                dp[i][w] = max(dp[i-1][w], dp[i-1][w - weights[i-1]] + values[i-1])
            else:
                dp[i][w] = dp[i-1][w]
    return dp[n][W]
```

---

## 🧪 Practice Problems

| # | Problem                                                                                      | Solution                                             |
|---|----------------------------------------------------------------------------------------------|------------------------------------------------------|
| 1 | [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum)       | [DynamicProgramming.java](./DynamicProgramming.java) |
| 2 | [Subset Sum Problem](https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1) | [DynamicProgramming.java](./DynamicProgramming.java) |