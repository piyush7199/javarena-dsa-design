# 💰 Greedy Algorithms

## Introduction

**Greedy Algorithms** make locally optimal choices at each step to achieve a globally optimal solution. They are used
for optimization problems where a greedy choice property holds.

---

## Intuition

- **Idea:** At each step, choose the best option available without considering future consequences, assuming the greedy
  choice leads to an optimal solution.

- **Why it Works:** For problems with the greedy choice property (local optimum leads to global optimum) and optimal
  substructure, greedy algorithms are efficient and correct.

- **Example:** In the Activity Selection problem, selecting the activity that finishes earliest maximizes the number of
  non-conflicting activities.

---

## Use Cases

- **Real-World Applications:** Resource allocation, scheduling, and network routing (e.g., Huffman coding for
  compression).
- **Graph Algorithms:** Used in Kruskal’s and Prim’s algorithms for minimum spanning trees.

---

## Complexity Analysis

- **General Complexity:** Varies by problem, but greedy algorithms are often `O(n log n)` due to sorting or priority
  queue usage.

- **Activity Selection:**
    - **Time Complexity: `O(n log n)`** for sorting activities by finish time, O(n) for selection.
    - **Space Complexity: `O(1)`** auxiliary space (excluding input).

- **Fractional Knapsack:**
    - **Time Complexity: `O(n log n)`** for sorting items by value/weight ratio.
    - **Space Complexity: `O(1)`** auxiliary space.

---

## Algorithm (Activity Selection)

**Intuition:** Sort activities by finish time and greedily select the first activity that doesn’t conflict with the last
selected activity, maximizing the number of activities.

```
function activitySelection(activities):
    # Input: list of activities with start and finish times
    # Output: maximum set of non-conflicting activities
    Sort activities by finish time
    result = []
    lastFinish = -infinity
    for each activity in activities:
        if activity.start >= lastFinish:
            result.append(activity)
            lastFinish = activity.finish
    return result
```

---

## 🧪 Practice Problems

| #  | Problem                                                                                                 | Solution                                         |
|----|---------------------------------------------------------------------------------------------------------|--------------------------------------------------|
| 1  | [Assign Cookies](https://leetcode.com/problems/assign-cookies/description/)                             | [GreedyAlgorithms.java](./GreedyAlgorithms.java) |
| 2  | [Lemonade Change](https://leetcode.com/problems/lemonade-change/description/)                           | [GreedyAlgorithms.java](./GreedyAlgorithms.java) |
| 3  | [Minimum number of Coins](https://www.geeksforgeeks.org/problems/-minimum-number-of-coins4426/1)        | [GreedyAlgorithms.java](./GreedyAlgorithms.java) |
| 4  | [Fractional Knapsack](https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1)          | [GreedyAlgorithms.java](./GreedyAlgorithms.java) |
| 5  | [N meetings in one room](https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1)    | [GreedyAlgorithms.java](./GreedyAlgorithms.java) |
| 6  | [Jump Game](https://leetcode.com/problems/jump-game/description/)                                       | [GreedyAlgorithms.java](./GreedyAlgorithms.java) |
| 7  | [Largest Merge Of Two Strings](https://leetcode.com/problems/largest-merge-of-two-strings/description/) | [GreedyAlgorithms.java](./GreedyAlgorithms.java) |
| 8  | [Distant Barcodes](https://leetcode.com/problems/distant-barcodes/description/)                         | [GreedyAlgorithms.java](./GreedyAlgorithms.java) |
| 9  | [Minimum Platforms](https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1)              | [GreedyAlgorithms.java](./GreedyAlgorithms.java) |
| 10 | [Job Sequencing Problem](https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1)    | [GreedyAlgorithms.java](./GreedyAlgorithms.java) |
