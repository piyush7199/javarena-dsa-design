package com.javarena.dsa.algorithms.greedy;

import java.util.Arrays;

/**
 * Jump Game II
 *
 * <p><b>Problem Statement:</b><br>
 * Given array where each element represents maximum jump length from that position,
 * return minimum number of jumps to reach last index. Guaranteed to reach last index.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy BFS-style level traversal:
 * - Treat array as levels: elements reachable in same jumps = one level
 * - Track current level boundary and farthest reachable in level
 * - When reach end of current level, must make jump to next level
 * - Increment jump count, extend boundary to farthest reachable
 * 
 * Key insight: Don't need to try all jump distances
 * - Just track furthest point reachable in current level
 * - Move to next level when current exhausted
 * 
 * Alternative: DP O(N²) but greedy is optimal O(N).
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(1) - Constant space for greedy, O(N) for DP
 */
public class JumpGameII {
    
    /**
     * DP approach with memoization (for comparison).
     */
    public int jumpDP(int[] nums) {
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

    /**
     * Greedy approach - optimal O(N).
     */
    public static int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                
                if (currentEnd >= n - 1) break;
            }
        }

        return jumps;
    }
}
