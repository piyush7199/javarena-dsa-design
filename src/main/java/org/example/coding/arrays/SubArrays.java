package org.example.coding.arrays;

public class SubArrays {
    /**
     * Problem
     * <p>
     * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
     * </p>
     * Intuition
     * <p>
     * Use a sliding window to expand the right end until the sum ≥ target, then shrink from the left while updating the minimum window length
     * </p>
     * Complexity
     * <p>
     * Time complexity:{@code O(n)}
     * <p></p>
     * Space  complexity:{@code O(n)}
     */
    public int minSubArrayLen(int target, int[] nums) {
        int j = 0;
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                ans = Math.min(i - j + 1, ans);
                sum -= nums[j];
                j++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
