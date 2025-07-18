package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class NiceArray {
    /**
     * Counts the number of subarrays with exactly k odd numbers.
     * <p>
     * Intuition:
     * - Use prefix sum with sliding window: Count subarrays with at most k odd numbers,
     * and subtract subarrays with at most (k - 1) to get exactly k.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int numberOfSubarrays(int[] nums, int k) {
        return freqLessThanEquals(nums, k) - freqLessThanEquals(nums, k - 1);
    }

    private int freqLessThanEquals(int[] nums, int goal) {
        if (goal < 0) return 0;
        int cnt = 0;
        int left = 0;
        int sm = 0;
        int right = 0;
        while (right < nums.length) {
            sm += (nums[right] % 2);
            while (sm > goal) {
                sm -= (nums[left] % 2);
                left++;
            }
            cnt += (right - left + 1);
            right++;
        }
        return cnt;
    }
}
