package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * 494. Target Sum
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/target-sum/">LeetCode - Target Sum</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Dynamic Programming, Array, Backtracking
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * You are given an integer array nums and an integer target. You want to build an expression
 * by adding '+' or '-' before each integer in nums and concatenating all the integers.
 * Return the number of different expressions that can be built which evaluates to target.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways: -1+1+1+1+1 = 3, +1-1+1+1+1 = 3, +1+1-1+1+1 = 3, +1+1+1-1+1 = 3, +1+1+1+1-1 = 3
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Transform the problem into subset sum partition problem
 * - Let sum of positive numbers = P, sum of negative numbers = N
 * - P - N = target and P + N = totalSum
 * - Solving: P = (totalSum + target) / 2
 * - Find count of subsets with sum = P
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Calculate total sum of array</li>
 *   <li>Check if (sum + target) is even, otherwise return 0</li>
 *   <li>Calculate target sum P = (sum + target) / 2</li>
 *   <li>Use DP to count subsets with sum = P</li>
 *   <li>Return the count</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(N * Target)<br>
 * Where N is array length and Target is the calculated subset sum
 *
 * <p><b>Space Complexity:</b> O(N * Target)<br>
 * For the memoization table
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Target greater than total sum: Return 0</li>
 *   <li>(sum + target) is odd: Impossible to partition, return 0</li>
 *   <li>Array with zeros: Each zero doubles the count</li>
 * </ul>
 */
public class TargetSum {

    /**
     * Recursive solution - explores all possible + and - combinations.
     *
     * @param nums array of numbers
     * @param target target sum to achieve
     * @return number of ways to reach target
     */
    public int findTargetSumWaysRecursiveSol(int[] nums, int target) {
        return targetSumHelperRecursive(nums, target, 0);
    }

    /**
     * Helper for recursive solution.
     *
     * @param nums array
     * @param target current target
     * @param ind current index
     * @return count of ways
     */
    private int targetSumHelperRecursive(int[] nums, int target, int ind) {
        if (nums.length == ind) {
            if (target == 0) return 1;
            return 0;
        }

        return targetSumHelperRecursive(nums, target + nums[ind], ind + 1) + targetSumHelperRecursive(nums, target - nums[ind], ind + 1);
    }

    /**
     * Optimized solution using subset sum partition with memoization.
     *
     * @param nums array of numbers
     * @param target target sum
     * @return number of ways to achieve target
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }

        if (Math.abs(target) > sum) return 0; // abs important!
        int tag = sum + target;
        if (tag % 2 == 1) return 0;

        int findSum = tag / 2;

        int[][] dp = new int[nums.length + 1][findSum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return findPartition(nums, 0, findSum, dp);
    }

    /**
     * Helper to count subsets with given sum using memoization.
     *
     * @param nums array
     * @param ind current index
     * @param target remaining target
     * @param dp memoization table
     * @return count of subsets
     */
    public int findPartition(int[] nums, int ind, int target, int[][] dp) {
        if (ind == nums.length) {
            return target == 0 ? 1 : 0;
        }
        if (dp[ind][target] != -1) return dp[ind][target];

        int pick = 0;
        if (target >= nums[ind]) {
            pick = findPartition(nums, ind + 1, target - nums[ind], dp);
        }
        int notPick = findPartition(nums, ind + 1, target, dp);

        dp[ind][target] = pick + notPick;
        return dp[ind][target];
    }
}
