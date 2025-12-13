package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * 416. Partition Equal Subset Sum
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/partition-equal-subset-sum/">LeetCode - Partition Equal Subset Sum</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Dynamic Programming, Array
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given a non-empty array nums containing only positive integers, determine if the array
 * can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11]
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Transform the problem into subset sum problem
 * - If total sum is odd, partition is impossible
 * - If total sum is even, we need to find a subset with sum = totalSum / 2
 * - If such subset exists, remaining elements automatically form the other subset
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Calculate total sum of array</li>
 *   <li>If sum is odd, return false immediately</li>
 *   <li>Calculate target = sum / 2</li>
 *   <li>Use DP to check if subset with sum = target exists</li>
 *   <li>Use memoization to optimize overlapping subproblems</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(N * Sum/2)<br>
 * Where N is array length and Sum is total sum of array
 *
 * <p><b>Space Complexity:</b> O(N * Sum/2)<br>
 * For the memoization table
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Single element: Cannot partition, return false</li>
 *   <li>Odd sum: Impossible to partition equally, return false</li>
 *   <li>All zeros: Can partition (special case)</li>
 * </ul>
 */
public class EqualSumPartition {
    
    /**
     * Brute force recursive solution.
     *
     * @param nums array of positive integers
     * @return true if can partition into equal sum subsets
     */
    public boolean canPartitionSolution1(int[] nums) {
        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        return findPartition(nums, nums.length - 1, target);
    }

    /**
     * Helper for recursive partition check.
     *
     * @param nums array
     * @param ind current index
     * @param target remaining target
     * @return true if subset with target sum exists
     */
    private boolean findPartition(int[] nums, int ind, int target) {
        if (target == 0) return true;
        if (target < 0 || ind == 0) return false;

        boolean pick = findPartition(nums, ind - 1, target - nums[ind]);
        boolean notPick = findPartition(nums, ind - 1, target);
        return pick || notPick;
    }

    /**
     * Optimized solution using memoization.
     *
     * @param nums array of positive integers
     * @return true if can partition into equal sum subsets
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        int[][] dp = new int[nums.length][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return findPartition(nums, 0, target, dp);
    }

    /**
     * Helper for memoized partition check.
     *
     * @param nums array
     * @param ind current index
     * @param target remaining target
     * @param dp memoization table
     * @return true if subset with target sum exists
     */
    private boolean findPartition(int[] nums, int ind, int target, int[][] dp) {
        if (target == 0) return true;
        if (target < 0 || ind == nums.length) return false;
        if (dp[ind][target] != -1) return dp[ind][target] == 1;
        boolean pick = findPartition(nums, ind + 1, target - nums[ind], dp);
        if (pick) {
            dp[ind][target] = 1;
            return true;
        }
        boolean notPick = findPartition(nums, ind + 1, target, dp);
        dp[ind][target] = notPick ? 1 : 0;
        return notPick;
    }
}
