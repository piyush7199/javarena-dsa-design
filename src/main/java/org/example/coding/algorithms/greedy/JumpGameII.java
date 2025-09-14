package org.example.coding.algorithms.greedy;

import java.util.Arrays;

public class JumpGameII {

    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return helper(nums, 0, dp);
    }

    private int helper(int[] nums, int ind, int[] dp) {
        int n = nums.length;
        if (ind == n - 1) return 0;
        if (nums[ind] == 0) return (int) 1e9;
        if (dp[ind] != -1) return dp[ind];
        int steps = (int) 1e9;
        for (int i = 1; i <= nums[ind]; i++) {
            int nextInd = ind + i;
            if (nextInd < n) {
                steps = Math.min(steps, 1 + helper(nums, nextInd, dp));
            }
        }
        dp[ind] = steps;
        return steps;
    }

    public static int minJumps(int[] nums) {
        int n = nums.length;

        // If there's only one element, we're already at the end
        if (n == 1) return 0;

        int jumps = 0;          // Minimum jumps needed
        int currentEnd = 0;     // Current level boundary
        int farthest = 0;       // Farthest point reachable

        // We don't need to check the last index, because reaching it means done
        for (int i = 0; i < n - 1; i++) {
            // Update the farthest point reachable so far
            farthest = Math.max(farthest, i + nums[i]);

            // When we reach the end of the current range
            if (i == currentEnd) {
                jumps++;            // We need to make a jump
                currentEnd = farthest;  // Extend the range to the farthest point

                // Optimization: If currentEnd already covers the last index, stop early
                if (currentEnd >= n - 1) break;
            }
        }

        return jumps;
    }

    public static int minJumpsOptimized(int[] nums) {
        int n = nums.length;

        // If there's only one element, we're already at the end
        if (n == 1) return 0;

        int jumps = 0;          // Minimum jumps needed
        int currentEnd = 0;     // Current level boundary
        int farthest = 0;       // Farthest point reachable

        // We don't need to check the last index, because reaching it means done
        for (int i = 0; i < n - 1; i++) {
            // Update the farthest point reachable so far
            farthest = Math.max(farthest, i + nums[i]);

            // When we reach the end of the current range
            if (i == currentEnd) {
                jumps++;            // We need to make a jump
                currentEnd = farthest;  // Extend the range to the farthest point

                // Optimization: If currentEnd already covers the last index, stop early
                if (currentEnd >= n - 1) break;
            }
        }

        return jumps;
    }
}
