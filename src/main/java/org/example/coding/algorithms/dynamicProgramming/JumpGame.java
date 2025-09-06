package org.example.coding.algorithms.dynamicProgramming;

import java.util.Arrays;

public class JumpGame {
    /**
     * Determines whether you can reach the last index starting from the first index
     * using a brute force recursive strategy.
     *
     * <p><b>Problem Intuition:</b>
     * Each element in the array represents the maximum number of steps you can move
     * forward from that position. Starting from index 0, we recursively explore
     * every possible jump to see if we can eventually reach or go beyond the last index.
     *
     * <p><b>Approach:</b>
     * <ol>
     *     <li>Start at index 0.</li>
     *     <li>From the current index, try every possible jump from 1 to nums[index].</li>
     *     <li>Recursively call the helper function for each jump.</li>
     *     <li>If any recursive path reaches or exceeds the last index, return true.</li>
     *     <li>If no path works, return false.</li>
     * </ol>
     *
     * <p><b>Time Complexity:</b>
     * <ul>
     *     <li>Worst-case: O(2^n) because at each index you explore multiple branches,
     *     potentially doubling at each step.</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b>
     * <ul>
     *     <li>O(n) due to recursive call stack depth.</li>
     * </ul>
     *
     * @param nums the array representing maximum jump lengths from each position.
     * @return true if the last index can be reached, false otherwise.
     */
    public boolean canJumpBrute(int[] nums) {
        return bruteHelper(nums, 0);
    }

    /**
     * Helper method for recursively checking all possible jump paths from a given index.
     *
     * @param nums the array of maximum jump lengths.
     * @param ind  the current index being explored.
     * @return true if a path exists to reach the last index or beyond, false otherwise.
     */
    private boolean bruteHelper(int[] nums, int ind) {
        // Base case: if current index is at or beyond the last index, return true
        if (ind >= nums.length - 1) {
            return true;
        }

        // Try every possible jump from 1 to nums[ind]
        for (int i = 1; i <= nums[ind]; i++) {
            if (bruteHelper(nums, ind + i)) {
                return true; // Found a successful path
            }
        }

        // No valid path found from this index
        return false;
    }

    /**
     * Determines whether you can reach the last index starting from the first index
     * using a recursive memoized approach.
     *
     * <p><b>Intuition:</b>
     * - At each index, you can jump between 1 and nums[i] steps forward.
     * - Use recursion to explore each possible jump.
     * - Use memoization to avoid recalculating results for the same index.
     *
     * <p><b>Approach:</b>
     * <ol>
     *     <li>Use a DP array `dp` where:</li>
     *     <ul>
     *         <li>-1 → Not visited yet</li>
     *         <li>1 → Reachable</li>
     *         <li>2 → Not reachable</li>
     *     </ul>
     *     <li>Recursively explore all possible jumps from the current index.</li>
     *     <li>If any path leads to the last index, mark it as reachable (1).</li>
     *     <li>If none work, mark it as not reachable (2).</li>
     * </ol>
     *
     * <p><b>Time Complexity:</b> O(n^2) in the worst case
     * <b>Space Complexity:</b> O(n) due to recursion stack and DP array
     *
     * @param nums array where each element represents the maximum jump length from that index
     * @return true if the last index can be reached, false otherwise
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1]; // DP array for memoization
        Arrays.fill(dp, -1);       // Initialize all states to -1 (unvisited)
        return helper(nums, 0, dp);
    }

    /**
     * Recursive helper method to check if it's possible to reach the end from the current index.
     *
     * @param nums array of maximum jump lengths
     * @param ind  current index
     * @param dp   memoization array
     * @return true if reachable, false otherwise
     */
    private boolean helper(int[] nums, int ind, int[] dp) {
        // Base case: If we are at or beyond the last index, return true
        if (ind >= nums.length - 1) {
            return true;
        }

        // If already computed, return stored result
        if (dp[ind] != -1) {
            return dp[ind] == 1;
        }

        // Try every possible jump from 1 to nums[ind]
        for (int i = 1; i <= nums[ind]; i++) {
            if (helper(nums, ind + i, dp)) {
                dp[ind] = 1; // Mark current index as reachable
                return true;
            }
        }

        // Mark as not reachable
        dp[ind] = 2;
        return false;
    }

    public static boolean canJumpEff(int[] nums) {
        // Initialize the maximum
        // index that can be reached
        int maxIndex = 0;

        // Iterate through each
        // index of the array
        for (int i = 0; i < nums.length; i++) {
            // If the current index is greater
            // than the maximum reachable index
            // it means we cannot move forward
            // and should return false
            if (i > maxIndex) {
                return false;
            }

            // Update the maximum index
            // that can be reached by comparing
            // the current maxIndex with the sum of
            // the current index and the
            // maximum jump from that index
            maxIndex = Math.max(maxIndex, i + nums[i]);
        }

        // If we complete the loop,
        // it means we can reach the
        // last index
        return true;
    }

}
