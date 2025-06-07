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

    /**
     * Problem <p>
     * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
     * </p>
     * Approach <p>
     * Imagine a sliding window traversing the array. We keep track of the product of elements within the window. If the product becomes greater than or equal to k, we need to shrink the window from the left side until the product becomes less than k. This process ensures that all subarrays considered have a product strictly less than k.
     * </p>
     * Complexity
     * <p>
     * Time complexity:{@code O(n)}
     * <p></p>
     * Space  complexity:{@code O(1)}
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int i = 0, product = 1, count = 0;

        for (int j = 0; j < nums.length; j++) {
            product *= nums[j];

            while (product >= k) {
                product /= nums[i];
                i++;
            }

            count += (j - i + 1);
        }

        return count;
    }
}
