package com.javarena.dsa.algorithms.binarySearch;

import java.util.Arrays;

/**
 * 611. Valid Triangle Number
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/valid-triangle-number/">LeetCode - Valid Triangle Number</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Array, Two Pointers, Binary Search, Greedy, Sorting
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer array nums, return number of triplets (i,j,k) where i<j<k and nums forms a valid triangle.
 * Triangle inequality: sum of any two sides > third side.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: nums = [2,2,3,4]
 * Output: 3
 * Explanation: Valid triangles: [2,3,4], [2,3,4], [2,2,3]
 *
 * Input: nums = [4,2,3,4]
 * Output: 4
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Sort array. Fix largest side, use two pointers to find valid pairs.
 * If a+b>c (sorted), all elements between a and b also work with b.
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Sort nums array</li>
 *   <li>Fix largest side at position k (iterate right to left)</li>
 *   <li>Use two pointers: i=0, j=k-1</li>
 *   <li>If nums[i]+nums[j]>nums[k]: count (j-i) triangles, j--</li>
 *   <li>Else: i++</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n²)<br>
 * Sorting O(n log n), two-pointer O(n²).
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Less than 3 elements: Return 0</li>
 *   <li>All zeros: No valid triangles</li>
 *   <li>All equal: Many valid triangles</li>
 * </ul>
 */
public class ValidTriangleNumber {
    
    /**
     * Brute force: try all triplets O(n³).
     */
    public int triangleNumberBrute(int[] nums) {
        int n = nums.length, count = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int a = nums[i], b = nums[j], c = nums[k];
                    if (a + b > c && a + c > b && b + c > a) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Optimized O(n²) using sort + two pointers.
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, count = 0;

        for (int k = n - 1; k >= 2; k--) {
            int i = 0, j = k - 1;
            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    count += (j - i);
                    j--;
                } else {
                    i++;
                }
            }
        }
        return count;
    }
}
