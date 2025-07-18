# 📍 Two-Pointer Technique

## Introduction

The Two-Pointer Technique is a powerful algorithmic approach used to solve problems involving arrays, strings, or linked
lists by maintaining two pointers (indices or references) that traverse the data structure in a coordinated way.

The technique has several patterns:

- **Opposite Ends:** Two pointers start from the ends of an array and move toward each other (e.g., finding a pair with
  a
  given sum in a sorted array).
- **Fast-Slow Pointers:** One pointer moves faster than the other, often used in linked lists or cyclic problems (e.g.,
  detecting a cycle).
- **Same Direction:** Two pointers move in the same direction at different speeds or conditions (e.g., removing
  duplicates).

---

## Intuition

The Two-Pointer Technique optimizes problem-solving by reducing the need for nested loops through coordinated pointer
movement:

* **Opposite Ends:**
    * **Idea:** Initialize two pointers, one at the start (left) and one at the end (right) of an array. Move them
      toward
      each other based on a condition (e.g., sum of elements at pointers compared to a target).
    * **Why it Works:** By leveraging the sorted or structured nature of the data, it eliminates unnecessary
      comparisons,
      achieving O(n) time.
    * **Key Insight:** Ideal for problems like finding pairs or partitioning arrays, as it processes elements in a
      single
      pass.
* **Fast-Slow Pointers:**
    * **Idea:** Use two pointers where one (fast) moves faster (e.g., twice as fast) than the other (slow). Common in
      linked lists to detect cycles or find midpoints.
    * **Why it Works:** The relative speed difference allows detecting patterns (e.g., cycle detection when fast meets
      slow) in O(n) time.
    * **Key Insight:** Efficient for problems requiring relative positioning or cycle detection without extra space.
* **Same Direction:**
    * **Idea:** Both pointers move in the same direction, but one advances based on a condition (e.g., removing
      duplicates by keeping a write pointer for valid elements).
    * **Why it Works:** It processes the array in one pass, updating elements in-place or tracking a subarray.
    * **Key Insight:** Useful for problems like removing duplicates or finding valid subarrays with minimal space.

---

## Use Cases

- **Real-World Applications:**
    - **Opposite Ends:** Merging sorted data streams, optimizing search in sorted datasets, or partitioning data in
      databases.
    - **Fast-Slow Pointers:** Cycle detection in graph algorithms, memory management, or linked list operations in
      system design.
    - **Same Direction:** Data compression (removing duplicates), real-time stream processing, or in-place array
      manipulation.
- **Algorithm Optimization:** Reduces time complexity for problems that would otherwise require nested loops, such as
  finding pairs, detecting patterns, or partitioning data.

---

## Algorithms

Below is pseudocode for key Two-Pointer patterns

### Opposite Ends (Two Sum II - Sorted Array)

- **Intuition:** In a sorted array, move left right if the sum is too small, or right left if the sum is too large,
  converging on the target pair.
- **Time Complexity: `O(n)`** as each pointer traverses at most n elements, meeting in the middle.
- **Space Complexity: `O(1)`** for in-place operations, O(n) if storing results (e.g., pair indices).

```
function twoSum(numbers, target):
    # Input: sorted array numbers, target sum
    # Output: indices of two numbers summing to target (1-based)
    left = 0
    right = numbers.length - 1
    while left < right:
        sum = numbers[left] + numbers[right]
        if sum == target:
            return [left + 1, right + 1]
        else if sum < target:
            left += 1
        else:
            right -= 1
    return []
```

### Fast-Slow Pointers (Linked List Cycle)

- **Intuition:** The fast pointer moves twice as fast as the slow pointer. If they meet, a cycle exists; otherwise, the
  fast pointer reaches the end.
- **Time Complexity: `O(n)`** in most cases, as the fast pointer traverses the list in linear time (e.g., cycle
  detection).
- **Worst case may be `O(n²)`** for pathological inputs without cycles.
- **Space Complexity: `O(1)`** as only two pointers are used.

```
function hasCycle(head):
    # Input: head of a linked list
    # Output: true if cycle exists, false otherwise
    if head is null or head.next is null:
        return false
    slow = head
    fast = head
    while fast is not null and fast.next is not null:
        slow = slow.next
        fast = fast.next.next
        if slow == fast:
            return true
    return false
```

### Same Direction (Remove Duplicates from Sorted Array)

- **Intuition:** Use a write pointer to track the position for unique elements and a read pointer to scan the array,
  copying
  unique elements in-place.
- **Time Complexity: `O(n)`** as each pointer traverses the array once.
- **Space Complexity:` O(1)`** for in-place operations, O(n) if storing results.

```
function removeDuplicates(nums):
    # Input: sorted array nums
    # Output: length of array with duplicates removed (in-place)
    if nums.length == 0:
        return 0
    write = 1
    for read from 1 to nums.length - 1:
        if nums[read] != nums[read - 1]:
            nums[write] = nums[read]
            write += 1
    return write
```

---

## 🧪 Practice Problems

| #  | Problem                                                                                                                                     | Solution                                                                    |
|----|---------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| 1  | [The Celebrity Problem](https://www.geeksforgeeks.org/problems/the-celebrity-problem/1)                                                     | [TwoPointer.java](./TwoPointer.java)                                        |
| 2  | [3Sum](https://leetcode.com/problems/3sum/)                                                                                                 | [TwoPointerSum.java](./TwoPointerSum.java)                                  |
| 3  | [4Sum](https://leetcode.com/problems/4sum/)                                                                                                 | [TwoPointerSum.java](./TwoPointerSum.java)                                  |
| 4  | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)                                                                     | [TwoPointer.java](./TwoPointer.java)                                        |
| 5  | [Longest Substring with K Uniques](https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1)                      | [LongestSubstring.java](./twoPointerAndSlidingWindow/LongestSubstring.java) |
| 6  | [Longest Substring with At Most K Distinct Characters](https://www.naukri.com/code360/problems/distinct-characters_2221410)                 | [LongestSubstring.java](./twoPointerAndSlidingWindow/LongestSubstring.java) |
| 7  | [Maximum Points You Can Obtain from Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/)                         | [MaxPoints.java](./twoPointerAndSlidingWindow/MaxPoints.java)               |
| 8  | [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)                           | [RepeatingChars.java](./twoPointerAndSlidingWindow/RepeatingChars.java)     |
| 9  | [Binary subarray with sum](https://leetcode.com/problems/binary-subarrays-with-sum/)                                                        | [BinarySubarray.java](./twoPointerAndSlidingWindow/BinarySubarray.java)     |
| 10 | [Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/)                                             | [NiceArray.java](./twoPointerAndSlidingWindow/NiceArray.java)               |
| 11 | [Number of Substrings Containing All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/) | [CountSubstrings.java](./twoPointerAndSlidingWindow/CountSubstrings.java)   |