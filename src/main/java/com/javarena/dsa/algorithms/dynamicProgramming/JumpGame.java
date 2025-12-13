package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * Jump Game
 *
 * <p><b>Problem Statement:</b><br>
 * Determine if you can reach last index starting from first index.
 * Each element represents maximum jump length from that position.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Reachability problem with multiple approaches:
 * 
 * 1. Brute Force O(2^N): Try all possible jumps recursively
 * 2. Memoization O(N²): Cache reachability for each index
 * 3. Greedy O(N): Track furthest reachable index
 * 
 * Greedy approach (optimal):
 * - Maintain maxReach variable
 * - For each position i ≤ maxReach:
 *   - Update maxReach = max(maxReach, i + nums[i])
 *   - If maxReach ≥ lastIndex: return true
 * - If loop completes without reaching end: return false
 *
 * <p><b>Time Complexity:</b> O(N) for greedy, O(N²) for DP
 * <br><b>Space Complexity:</b> O(1) for greedy, O(N) for DP/memoization
 */
public class JumpGame {
    
    /**
     * Brute force recursive approach.
     */
    public boolean canJumpBrute(int[] nums) {
        return bruteHelper(nums, 0);
    }

    private boolean bruteHelper(int[] nums, int ind) {
        if (ind >= nums.length - 1) return true;
        
        for (int i = 1; i <= nums[ind]; i++) {
            if (bruteHelper(nums, ind + i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Memoization approach.
     */
    public boolean canJumpMemo(int[] nums) {
        Boolean[] dp = new Boolean[nums.length];
        return memoHelper(nums, 0, dp);
    }

    private boolean memoHelper(int[] nums, int ind, Boolean[] dp) {
        if (ind >= nums.length - 1) return true;
        if (dp[ind] != null) return dp[ind];
        
        for (int i = 1; i <= nums[ind]; i++) {
            if (memoHelper(nums, ind + i, dp)) {
                return dp[ind] = true;
            }
        }
        return dp[ind] = false;
    }

    /**
     * Greedy approach - optimal O(N).
     */
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) return true;
        }
        
        return maxReach >= nums.length - 1;
    }
}
