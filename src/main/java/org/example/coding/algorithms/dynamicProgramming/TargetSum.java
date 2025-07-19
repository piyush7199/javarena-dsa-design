package org.example.coding.algorithms.dynamicProgramming;

import java.util.Arrays;

public class TargetSum {

    // https://leetcode.com/problems/target-sum/
    public int findTargetSumWaysRecursiveSol(int[] nums, int target) {
        return targetSumHelperRecursive(nums, target, 0);
    }

    private int targetSumHelperRecursive(int[] nums, int target, int ind) {
        if (nums.length == ind) {
            if (target == 0) return 1;
            return 0;
        }

        return targetSumHelperRecursive(nums, target + nums[ind], ind + 1) + targetSumHelperRecursive(nums, target - nums[ind], ind + 1);
    }

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
