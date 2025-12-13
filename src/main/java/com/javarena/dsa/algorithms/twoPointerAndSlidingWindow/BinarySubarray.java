package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Binary Subarrays With Sum
 *
 * <p><b>Problem Statement:</b><br>
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with sum equal to goal.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use sliding window technique to count subarrays
 * - Key insight: count(sum = goal) = count(sum ≤ goal) - count(sum ≤ goal-1)
 * - For "at most k sum": use sliding window with two pointers
 * - Expand right pointer, shrink left pointer when sum exceeds k
 * - For each valid window, all subarrays ending at right are valid: (right - left + 1)
 * - Subtract the two counts to get exact sum = goal
 *
 * <p><b>Time Complexity:</b> O(N) - Two passes through array
 * <br><b>Space Complexity:</b> O(1) - Constant extra space
 */
public class BinarySubarray {

    /**
     * Counts the number of subarrays with sum exactly equal to goal.
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        return freqLessThanEquals(nums, goal) - freqLessThanEquals(nums, goal - 1);
    }

    private int freqLessThanEquals(int[] nums, int goal) {
        if (goal < 0) return 0;
        int cnt = 0;
        int left = 0;
        int sm = 0;
        int right = 0;
        while (right < nums.length) {
            sm += nums[right];
            while (sm > goal) {
                sm -= nums[left];
                left++;
            }
            cnt += (right - left + 1);
            right++;
        }
        return cnt;
    }
}
