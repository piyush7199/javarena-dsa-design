package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class LongestSubarrayOfOneAfterDeletingOneEle {
    /**
     * Problem: Given a binary array `nums`, return the length of the longest subarray
     * of 1's after deleting exactly one element (which must be deleted).
     * <p>
     * Intuition:
     * - Since we must delete one element, the problem reduces to finding the longest subarray
     * of 1's allowing at most one 0 inside it.
     * - We use a sliding window (two pointers `l` and `r`) to maintain a valid window
     * where at most one 0 is included.
     * - If we encounter a second 0, we shrink the window from the left until we remove the first 0.
     * <p>
     * Approach:
     * - Maintain two pointers: `l` (left boundary) and `r` (right boundary).
     * - Use variable `d` to record the index of the last 0 in the current window.
     * - Expand `r` forward:
     * - If `nums[r]` is 1, simply continue expanding.
     * - If `nums[r]` is 0 and no zero has been used (`d == -1`), mark its position.
     * - If a second zero is encountered, update max length (excluding one zero),
     * then shrink window by moving `l` past the first zero (`d + 1`).
     * - Finally, compute the maximum subarray length by considering the last window.
     * <p>
     * Time Complexity: O(n)
     * - Each element is visited at most twice (once by `r`, once by `l`).
     * <p>
     * Space Complexity: O(1)
     * - Uses only a few integer variables, independent of input size.

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
