# 🪟 Sliding Window Algorithm

## Introduction

The **Sliding Window Algorithm** is a technique for solving problems on arrays or strings by maintaining a “window” of
elements that satisfies a condition, expanding or shrinking it as needed. It’s widely used in FAANG interviews for
problems involving subarrays or substrings.

---

## Intuition

- **Idea:** Use two pointers (left and right) to define a window that slides over the array/string, expanding to include
  elements and shrinking to maintain a condition (e.g., sum <= k, distinct characters).

- **Why it Works:** By avoiding redundant computations (e.g., recomputing sums for overlapping subarrays), it reduces
  time complexity from O(n²) to O(n) or O(n log n).

- **Types:**
    - **Fixed-Size Window:** Window size is constant (e.g., max sum of k elements).
    - **Variable-Size Window:** Window size adjusts based on a condition (e.g., longest substring with k distinct
      characters).

---

## Use Cases

- **Real-World Applications:** Pattern matching, data stream processing, and substring searches in text processing.
- **Optimization:** Efficiently process contiguous segments of data.

---

## Complexity Analysis

- **Fixed-Size Window:**
    - **Time Complexity: `O(n)`** as each element is added and removed at most once.
    - **Space Complexity: `O(1)` or `O(k)`** for storing window data (e.g., sum or frequency map).

- **Variable-Size Window:**
    - **Time Complexity: `O(n)`** as each element is processed at most twice (added and removed).
    - **Space Complexity: `O(k)`** for tracking elements in the window (e.g., hash map for characters).

---

## Algorithm (Variable-Size Sliding Window for Longest Substring with K Distinct Characters)

**Intuition:** Slide the right pointer to expand the window, adding characters to a frequency map. If the number of
distinct
characters exceeds k, shrink the window from the left until the condition is satisfied, updating the maximum length.

```
function longestSubstringWithKDistinct(s, k):
    # Input: string s, integer k (max distinct characters)
    # Output: length of longest substring with at most k distinct characters
    Initialize charCount as empty hash map
    Initialize left = 0, maxLength = 0
    for right from 0 to s.length - 1:
        charCount[s[right]] += 1
        while charCount.size > k:
            charCount[s[left]] -= 1
            if charCount[s[left]] == 0:
                remove s[left] from charCount
            left += 1
        maxLength = max(maxLength, right - left + 1)
    return maxLength
```

---

## 🪟 Sliding Window Algorithm

| # | Problem                                                                                                                                                         | Solution                                   | Companies   |
|---|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------|-------------|
| 1 | [Shortest and Lexicographically Smallest Beautiful String](https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/description/) | [SlidingWindow.java](./SlidingWindow.java) |             |
| 2 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)                     | [SlidingWindow.java](./SlidingWindow.java) | `Microsoft` |
| 3 | [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/description/)                                                                 | [SlidingWindow.java](./SlidingWindow.java) |             |
| 4 | [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/description/)                                                                             | [SlidingWindow.java](./SlidingWindow.java) |             |
