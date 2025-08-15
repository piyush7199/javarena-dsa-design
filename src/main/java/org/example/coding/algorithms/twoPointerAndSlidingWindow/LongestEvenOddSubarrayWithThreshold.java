package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class LongestEvenOddSubarrayWithThreshold {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int res = 0;
        int i = 0;
        while (i < nums.length) {
            if ((nums[i] & 1) == 1 || nums[i] > threshold) {
                i++;
                continue;
            }
            int j = i + 1;
            while (j < nums.length && ((nums[j] & 1) != (nums[j - 1] & 1)) && nums[j] <= threshold) j++;
            res = Math.max(res, j - i);
            i = j;
        }
        return res;
    }
}
