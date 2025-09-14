# HashMap and HashSet

## Introduction

**HashMap and HashSet** are fundamental data structures in **Java’s Collections Framework**, leveraging hashing to
provide
efficient key-based access and storage of unique elements, respectively.

- **HashMap:** A key-value pair data structure that maps unique keys to values, allowing fast retrieval, insertion, and
  deletion based on keys. It is implemented as an array of buckets, with collisions resolved via linked lists or
  balanced trees (in Java 8+).

- **HashSet:** A collection of unique elements, internally backed by a HashMap where elements are keys and values are
  dummy objects. It ensures no duplicates and provides fast lookup and modification.

---

## Complexity Analysis

Below is the complexity analysis for key operations in Java’s HashMap and HashSet:

### HashMap

- Time Complexity:
    - **Put (insert/update): `O(1)`**  average, `O(n)` worst case (due to collisions, e.g., poor hash function).
    - **Get (lookup): `O(1)`** average, `O(n)` worst case.
    - **Remove: `O(1)`** average, `O(n)` worst case.
    - **ContainsKey: `O(1)`** average, `O(n)` worst case.

- Space Complexity: `O(n)` for storing n key-value pairs, plus additional space for buckets (depends on load factor,
  default
  0.75 in Java).

> Note: Worst-case complexity occurs with many collisions (e.g., all keys hashing to one bucket). Java 8+ mitigates this
> by converting linked lists to balanced trees for large buckets, reducing worst-case complexity to O(log n) for
> affected
> operations. The load factor and resizing ensure amortized O(1) performance.

---

## Practice Problems

| #  | Problem                                                                                                                                   | Solution                                                           |
|----|-------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------|
| 1  | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)                                                         | [Solutions.java](./Solutions.java)                                 |
| 2  | [Two Sum](https://leetcode.com/problems/two-sum/)                                                                                         | [Solutions.java](./Solutions.java)                                 |
| 3  | [Count Subarrays with given XOR](https://www.geeksforgeeks.org/problems/count-subarray-with-given-xor/1)                                  | [Solutions.java](./Solutions.java)                                 |
| 4  | [Find Occurrences of an Element in an Array](https://leetcode.com/problems/find-occurrences-of-an-element-in-an-array/)                   | [Solutions.java](./Solutions.java)                                 |                               |
| 5  | [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/)                                                 | [RandomizedSet.java](./RandomizedSet.java)                         |
| 6  | [Subarrays with sum K](https://www.geeksforgeeks.org/problems/subarrays-with-sum-k/1)                                                     | [SubArraySumEqualsK.java](./SubArraySumEqualsK.java)               |
| 7  | [Divide Array Into Equal Pairs](https://leetcode.com/problems/divide-array-into-equal-pairs/)                                             | [DivideArrayIntoEqualPairs.java](./DivideArrayIntoEqualPairs.java) |
| 8  | [Design A Leaderboard](https://leetcode.com/problems/design-a-leaderboard/)                                                               | [DesignALeaderboard.java](DesignALeaderboard.java)                 |
| 9  | [Count Number of Pairs With Absolute Difference K](https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/)       | [CountPairsWithAbsDiffK.java](./CountPairsWithAbsDiffK.java)       |
| 10 | [Maximum Difference Between Even and Odd Frequency I](https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/) | [MaxDiffBtwOddEvenFreq.java](./MaxDiffBtwOddEvenFreq.java)         |
| 11 | [Find Most Frequent Vowel and Consonantl̥](https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/)                         | [SumOfMaxCAndMaxV.java](./SumOfMaxCAndMaxV.java)                   |
| 12 | [Vowel Spellchecker](https://leetcode.com/problems/vowel-spellchecker/)                                                                   | [VowelSpellChecker.java](./VowelSpellChecker.java)                 |
