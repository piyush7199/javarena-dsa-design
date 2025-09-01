# 🔍 Searching Algorithms

## Introduction

**Searching Algorithms** are techniques to find a specific element or its position in a data structure (e.g., array,
list). They are fundamental for solving problems efficiently in competitive programming and FAANG interviews. Common
searching algorithms include:

* **Linear Search:** Sequentially checks each element.
* **Binary Search:** Efficiently searches sorted arrays by dividing the search space in half.

---

## Intuition

- **Linear Search:** Check every element until the target is found or the list is exhausted. It’s simple but slow for
  large
  datasets.

- **Binary Search:** Exploit the sorted nature of the data to eliminate half the search space in each step, making it
  much
  faster for large, sorted arrays.
    - **Why it Works:** By comparing the target with the middle element, we can decide whether to search the left or
      right half,
      reducing the problem size logarithmically.

---

## Use Cases

- **Real-World Applications:** Search in databases, autocomplete features, or finding items in sorted datasets.

- **Optimization Problems:** Binary Search on answers (e.g., finding the minimum value satisfying a condition).

---

## Complexity Analysis

### [Linear Search](./SearchingAlgorithms.java):

- **Time Complexity: `O(n)`** for searching `n` elements.
- **Space Complexity: `O(1)`** as it uses constant extra space.

### [Binary Search](./SearchingAlgorithms.java):

- **Time Complexity: `O(log n)`** for searching `n` elements in a sorted array.
- **Space Complexity: `O(1)`** for iterative version, `O(log n)` for recursive version due to call stack.

---

## Algorithm (Binary Search)

**Intuition:** Repeatedly divide the search space in half by comparing the middle element to the target, narrowing down
to
the correct half until the target is found or the search space is empty.

```
function binarySearch(arr, target):
    # Input: sorted array arr, target value to find
    # Output: index of target or -1 if not found
    left = 0
    right = arr.length - 1
    while left <= right:
        mid = (left + right) / 2
        if arr[mid] == target:
            return mid
        else if arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1
```

---

## 🧪 Problems on Searching Algorithms

| #  | Problem                                                                                                                                                       | Solution                                                                                  |
|----|---------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| 1  | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)                                                                     | [SearchingAlgorithms.java](./SearchingAlgorithms.java)                                    |
| 2  | [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/description/)                                                                           | [SearchingAlgorithms.java](./SearchingAlgorithms.java)                                    |
| 3  | [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/) | [SearchingAlgorithms.java](./SearchingAlgorithms.java)                                    |
| 4  | [Find Peak Element](https://leetcode.com/problems/find-peak-element/)                                                                                         | [SearchingAlgorithms.java](./SearchingAlgorithms.java)                                    |
| 5  | [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)                                                   | [SearchingAlgorithms.java](./SearchingAlgorithms.java)                                    |                                                
| 6  | [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)                                                               | [SearchingAlgorithms.java](./SearchingAlgorithms.java)                                    |                                            
| 7  | [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii)                                                          | [SearchingAlgorithms.java](./SearchingAlgorithms.java)                                    |
| 8  | [Implement Lower Bound](https://www.geeksforgeeks.org/problems/implement-lower-bound/1)                                                                       | [SearchingAlgorithms.java](./SearchingAlgorithms.java)                                    |
| 9  | [Implement Upper Bound](https://www.geeksforgeeks.org/problems/implement-upper-bound/1)                                                                       | [SearchingAlgorithms.java](./SearchingAlgorithms.java)                                    |
| 10 | [Search Insert Position](https://leetcode.com/problems/search-insert-position/)                                                                               | [SearchingAlgorithms.java](./SearchingAlgorithms.java)                                    |                                             |
| 11 | [Search in 2d](https://leetcode.com/problems/search-a-2d-matrix/)                                                                                             | [SearchInMatrix.java](./binarySearch/SearchInMatrix.java)                                 |
| 12 | [Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/)                                                                             | [SplitArrayLargestSum.java](./binarySearch/SplitArrayLargestSum.java)                     |
| 13 | [Aggressive Cows](https://www.geeksforgeeks.org/problems/aggressive-cows/1)                                                                                   | [AggressiveCows.java](./binarySearch/AggressiveCows.java)                                 |
| 14 | [Allocate Minimum Pages](https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1)                                                       | [AllocateMinimumPages.java](./binarySearch/AllocateMinimumPages.java)                     |
| 15 | [Find the Smallest Divisor Given a Threshold](https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold)                                      | [SmallestDivisorGivenAThreshold.java](./binarySearch/SmallestDivisorGivenAThreshold.java) |
| 16 | [Valid Triangle Number](https://leetcode.com/problems/valid-triangle-number/)                                                                                 | [ValidTriangleNumber.java](./binarySearch/ValidTriangleNumber.java)                       |
| 17 | [Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/)                                         | [MinimumDaysMakeBouquets.java](./binarySearch/MinimumDaysMakeBouquets.java)               |
| 18 | [Magnetic Force Between Two Balls](https://leetcode.com/problems/magnetic-force-between-two-balls/)                                                           | [MagneticForce.java](./binarySearch/MagneticForce.java)                                   |