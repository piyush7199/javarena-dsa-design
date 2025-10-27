package com.javarena.dsa.algorithms.bitManupulation;

/**
 * 2419. Longest Subarray With Maximum Bitwise AND
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and/">LeetCode - Longest Subarray Max AND</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Array, Bit Manipulation, Greedy
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * You are given an integer array nums of size n. Consider a non-empty subarray from nums that has 
 * the maximum possible bitwise AND. In other words, let k be the maximum value of the bitwise AND 
 * of any subarray of nums. Find the length of the longest subarray that has bitwise AND equal to k.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: nums = [1,2,3,3,2,2]
 * Output: 2
 * Explanation: Maximum AND is 3. Longest subarray with AND = 3 is [3,3] (length 2).
 *
 * Input: nums = [1,2,3,4]
 * Output: 1
 * Explanation: Maximum AND is 4. Only [4] has AND = 4 (length 1).
 *
 * Input: nums = [311155,311155,311155]
 * Output: 3
 * Explanation: All elements are same, maximum AND is 311155, length is 3.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Key insight about bitwise AND operation:
 * - AND is a restrictive operation: a & b ≤ min(a, b)
 * - ANDing any two different numbers produces a result ≤ both
 * - For maximum possible AND value, all elements must be equal
 * - Therefore, maximum AND = maximum element in array
 * - Problem reduces to finding longest contiguous sequence of max element
 * - This is a simple array traversal problem in disguise
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Find the maximum element in array (this is max possible AND)</li>
 *   <li>Traverse array and track consecutive occurrences of max element</li>
 *   <li>Maintain currentCount for ongoing streak of max elements</li>
 *   <li>Update maxLength whenever we see a longer streak</li>
 *   <li>Reset currentCount when we encounter non-max element</li>
 *   <li>Return maxLength</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n)<br>
 * Two passes: one to find maximum, one to find longest consecutive sequence.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses a constant number of variables regardless of array size.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>All elements same: Return array length</li>
 *   <li>Single element: Return 1</li>
 *   <li>No consecutive max elements: Return 1</li>
 *   <li>Max element at boundaries: Handled correctly by reset logic</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/">Max Product Palindromes</a>
 */
public class LongestSubarrayWithMaxBitAnd {
    
    /**
     * Finds length of longest subarray with maximum bitwise AND value.
     *
     * @param nums array of positive integers
     * @return length of longest subarray where AND equals maximum possible
     */
    public int longestSubarray(int[] nums) {
        int max = 0;
        int currentCount = 0;
        int maxLength = 0;

        // Step 1: Find maximum element in array
        for (int x : nums) {
            if (x > max) {
                max = x;
            }
        }

        // Step 2: Find longest consecutive sequence of max element
        for (int x : nums) {
            if (x == max) {
                // Extend current streak
                currentCount++;
                maxLength = Math.max(maxLength, currentCount);
            } else {
                // Reset streak when non-max element encountered
                currentCount = 0;
            }
        }

        return maxLength;
    }
}
