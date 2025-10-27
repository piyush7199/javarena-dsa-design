# 📍 Two-Pointer & 🪟 Sliding Window Algorithms

This directory contains implementations of **Two-Pointer** and **Sliding Window** techniques - two powerful algorithmic approaches for efficiently solving array and string problems.

---

## 📂 Directory Structure

This directory includes:
- **Core Algorithm Files**: Base implementations of Two-Pointer and Sliding Window techniques
- **Practice Problems**: Curated problems from LeetCode, GeeksforGeeks, and other platforms
- **Detailed READMEs**: Comprehensive guides for each technique

---

## 📘 Core Files

| File | Description |
|------|-------------|
| [TwoPointer.java](./TwoPointer.java) | Core two-pointer implementations including celebrity problem, merge sorted arrays |
| [TwoPointerSum.java](./TwoPointerSum.java) | Sum-based problems using two-pointer technique (3Sum, 4Sum) |
| [SlidingWindow.java](./SlidingWindow.java) | Core sliding window implementations for substring/subarray problems |

---

## 📖 Detailed Documentation

For comprehensive explanations, intuition, complexity analysis, and algorithms:

- **[Two-Pointer Technique →](./README-TwoPointer.md)**
  - Opposite Ends pattern
  - Fast-Slow Pointers pattern
  - Same Direction pattern
  - Celebrity problem, Array merging, and more

- **[Sliding Window Algorithm →](./README-SlidingWindow.md)**
  - Fixed-Size Window
  - Variable-Size Window
  - Substring problems, Subarray problems, and more

---

## 🧪 Practice Problems

### Two-Pointer Problems

| # | Problem | Solution | Difficulty |
|---|---------|----------|------------|
| 1 | [The Celebrity Problem](https://www.geeksforgeeks.org/problems/the-celebrity-problem/1) | [TwoPointer.java](./TwoPointer.java) | Medium |
| 2 | [3Sum](https://leetcode.com/problems/3sum/) | [TwoPointerSum.java](./TwoPointerSum.java) | Medium |
| 3 | [4Sum](https://leetcode.com/problems/4sum/) | [TwoPointerSum.java](./TwoPointerSum.java) | Medium |
| 4 | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | [TwoPointer.java](./TwoPointer.java) | Easy |
| 5 | [3Sum Closest](https://leetcode.com/problems/3sum-closest/) | [ThreeSumClosest.java](./ThreeSumClosest.java) | Medium |
| 6 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | [LongestPalindromic.java](./LongestPalindromic.java) | Medium |
| 7 | [Maximum Points from Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/) | [MaxPoints.java](./MaxPoints.java) | Medium |

### Sliding Window Problems

| # | Problem | Solution | Difficulty |
|---|---------|----------|------------|
| 1 | [Shortest Beautiful String](https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/) | [SlidingWindow.java](./SlidingWindow.java) | Hard |
| 2 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | [SlidingWindow.java](./SlidingWindow.java) | Medium |
| 3 | [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) | [SlidingWindow.java](./SlidingWindow.java) | Medium |
| 4 | [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) | [SlidingWindow.java](./SlidingWindow.java) | Medium |
| 5 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | [MinimumWindowSubstring.java](./MinimumWindowSubstring.java) | Hard |
| 6 | [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) | [SubarrayWithKDifferentInteger.java](./SubarrayWithKDifferentInteger.java) | Hard |
| 7 | [Find All Anagrams](https://leetcode.com/problems/find-all-anagrams-in-a-string/) | [FindAllAnagramsInAString.java](./FindAllAnagramsInAString.java) | Medium |
| 8 | [Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/) | [MaximumSumOfDistinctSubarraysWithLengthK.java](./MaximumSumOfDistinctSubarraysWithLengthK.java) | Medium |

### Combined Technique Problems

| # | Problem | Solution | Difficulty |
|---|---------|----------|------------|
| 1 | [Longest Substring with K Uniques](https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1) | [LongestSubstring.java](./LongestSubstring.java) | Medium |
| 2 | [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) | [RepeatingChars.java](./RepeatingChars.java) | Medium |
| 3 | [Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/) | [BinarySubarray.java](./BinarySubarray.java) | Medium |
| 4 | [Count Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/) | [NiceArray.java](./NiceArray.java) | Medium |
| 5 | [Count Substrings with All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/) | [CountSubstrings.java](./CountSubstrings.java) | Medium |
| 6 | [Longest Subarray After Deleting One Element](https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/) | [LongestSubarrayOfOneAfterDeletingOneEle.java](./LongestSubarrayOfOneAfterDeletingOneEle.java) | Medium |
| 7 | [Longest Even Odd Subarray](https://leetcode.com/problems/longest-even-odd-subarray-with-threshold/) | [LongestEvenOddSubarrayWithThreshold.java](./LongestEvenOddSubarrayWithThreshold.java) | Easy |
| 8 | [Reschedule Meetings](https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/) | [RescheduleMeetings.java](./RescheduleMeetings.java) | Medium |

---

## 🎯 Quick Reference

### When to Use Two-Pointer?

- Finding pairs in sorted arrays
- Merging sorted arrays
- Detecting cycles in linked lists
- Removing duplicates in-place
- Partitioning arrays

### When to Use Sliding Window?

- Finding subarrays/substrings with specific properties
- Maximum/minimum in fixed-size windows
- Longest/shortest subarray meeting a condition
- Count of valid subarrays/substrings
- Problems involving contiguous elements

---

## 📚 Resources

- [LeetCode Two Pointers Pattern](https://leetcode.com/tag/two-pointers/)
- [LeetCode Sliding Window Pattern](https://leetcode.com/tag/sliding-window/)
- [GeeksforGeeks - Two Pointer Technique](https://www.geeksforgeeks.org/two-pointers-technique/)
- [GeeksforGeeks - Window Sliding Technique](https://www.geeksforgeeks.org/window-sliding-technique/)

