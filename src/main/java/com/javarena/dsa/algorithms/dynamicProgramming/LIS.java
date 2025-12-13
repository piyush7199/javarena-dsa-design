package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/longest-increasing-subsequence/">LeetCode - Longest Increasing Subsequence</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Dynamic Programming, Binary Search, Array
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * For each element, we have two choices: include it in LIS or skip it
 * - Include: Only if current element is greater than last included element
 * - Skip: Move to next element without including current
 * - Track the last included element to maintain increasing property
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Use recursion with two indices: current index and last included index</li>
 *   <li>At each step, try skipping current element</li>
 *   <li>If current > last, also try including current element</li>
 *   <li>Return maximum of both choices</li>
 *   <li>Use memoization to optimize overlapping subproblems</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(N²)<br>
 * With memoization, each state (i, j) is computed once
 *
 * <p><b>Space Complexity:</b> O(N²)<br>
 * For the DP table
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Single element: Return 1</li>
 *   <li>All decreasing: Return 1</li>
 *   <li>All increasing: Return array length</li>
 * </ul>
 */
public class LIS {
    
    /**
     * Brute force recursive solution.
     *
     * @param nums input array
     * @return length of longest increasing subsequence
     */
    public int lengthOfLISBrute(int[] nums) {
        int n = nums.length;

        return bruteHelper(nums, 0, -1);
    }

    /**
     * Helper for brute force recursion.
     *
     * @param nums array
     * @param ind current index
     * @param lastInd last included index
     * @return LIS length from current state
     */
    private int bruteHelper(int[] nums, int ind, int lastInd) {
        if (ind == nums.length) return 0;

        int ans = bruteHelper(nums, ind + 1, lastInd);
        if (lastInd == -1 || nums[lastInd] < nums[ind]) {
            ans = Math.max(ans, 1 + bruteHelper(nums, ind + 1, ind));
        }
        return ans;
    }

    /**
     * Optimized solution using memoization.
     *
     * @param nums input array
     * @return length of longest increasing subsequence
     */
    public int lengthOfLISMemo(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solveMemo(nums, 0, -1, dp);
    }

    /**
     * Helper for memoized recursion.
     *
     * @param nums array
     * @param i current index
     * @param j last included index
     * @param dp memoization table
     * @return LIS length
     */
    static int solveMemo(int[] nums, int i, int j, int[][] dp) {
        if (i == nums.length) return 0;
        if (dp[i][j + 1] != -1) return dp[i][j + 1];
        int len = solveMemo(nums, i + 1, j, dp);
        if (j == -1 || nums[i] > nums[j]) {
            len = Math.max(len, 1 + solveMemo(nums, i + 1, i, dp));
        }
        return dp[i][j + 1] = len;
    }
}
