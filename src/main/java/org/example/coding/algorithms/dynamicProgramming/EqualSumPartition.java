package org.example.coding.algorithms.dynamicProgramming;

import java.util.Arrays;

public class EqualSumPartition {
    // https://leetcode.com/problems/partition-equal-subset-sum/
    public boolean canPartitionSolution1(int[] nums) {
        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        return findPartition(nums, nums.length - 1, target);
    }

    private boolean findPartition(int[] nums, int ind, int target) {
        if (target == 0) return true;
        if (target < 0 || ind == 0) return false;

        boolean pick = findPartition(nums, ind - 1, target - nums[ind]);
        boolean notPick = findPartition(nums, ind - 1, target);
        return pick || notPick;
    }

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
