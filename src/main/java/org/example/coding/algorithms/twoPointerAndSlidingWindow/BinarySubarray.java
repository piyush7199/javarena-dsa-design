package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class BinarySubarray {

    /**
     * Counts the number of subarrays with sum exactly equal to `goal`.
     * <p>
     * Intuition:
     * - Similar to counting number of subarrays with exactly k odd elements,
     * but this time count subarrays with sum equal to `goal` using sliding window
     * for at most `goal` and at most `goal - 1`.
     * <p>
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
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
