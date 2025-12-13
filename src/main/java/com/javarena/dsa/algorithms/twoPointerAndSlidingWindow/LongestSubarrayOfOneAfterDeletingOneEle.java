package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Longest Subarray of 1's After Deleting One Element
 *
 * <p><b>Problem Statement:</b><br>
 * Given a binary array nums, return the length of the longest contiguous subarray of 1's
 * after deleting exactly one element. You must delete one element.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Since we must delete exactly one element, find longest subarray with at most one 0
 * - Use sliding window with two pointers (left and right)
 * - Track position of the last 0 seen in current window
 * - When encountering first 0, mark its position and continue expanding
 * - When encountering second 0, update max length (minus the deleted element)
 * - Shrink window by moving left pointer past the first 0
 * - Final answer excludes one element (the deleted one)
 *
 * <p><b>Time Complexity:</b> O(N) - Each element visited at most twice
 * <br><b>Space Complexity:</b> O(1) - Only a few integer variables
 */
public class LongestSubarrayOfOneAfterDeletingOneEle {
    /**
     * Returns length of longest subarray of 1's after deleting exactly one element.
     */
    public int longestSubarray(int[] nums) {
        int l = 0;       // left pointer of the window
        int r = 0;       // right pointer of the window
        int d = -1;      // index of the last zero encountered (-1 means no zero used yet)
        int max = 0;     // stores the maximum length found
        int len = nums.length;

        while (r < len) {
            if (nums[r] == 0) {
                if (d == -1) {
                    // first zero in the current window, just mark its index
                    d = r;
                    r++;
                } else {
                    // second zero found → update result before shrinking window
                    max = Math.max(max, r - l - 1);
                    // move left boundary past the first zero
                    l = d + 1;
                    // reset d (no zero in current window now)
                    d = -1;
                }
            } else {
                // if nums[r] == 1, just expand window
                r++;
            }
        }

        // check last window (since loop may end without updating max)
        return Math.max(max, r - l - 1);
    }
}
