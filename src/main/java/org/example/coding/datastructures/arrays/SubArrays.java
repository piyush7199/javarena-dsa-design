package org.example.coding.datastructures.arrays;

import java.util.HashMap;

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

    /**
     * Finds the contiguous subarray within a one-dimensional array of numbers
     * which has the largest sum (Kadane's Algorithm).
     *
     * <p>Intuition: <br>
     * - We keep track of the running sum of the current subarray. <br>
     * - If adding the current element gives a smaller sum than starting fresh from the current element,
     * we reset the sum. <br>
     * - At each step, we update the global maximum.
     * <p>
     * Time Complexity: O(n), where n is the length of the array. <br>
     * Space Complexity: O(1), constant extra space used.
     */
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ans = nums[0];
        for (int ele : nums) {
            sum = Math.max(ele, sum + ele);
            ans = Math.max(sum, ans);
        }
        return ans;
    }

    /**
     * Finds the length of the longest subarray whose elements sum up to 0.
     * <p>
     * Intuition:
     * - Maintain a prefix sum and store first occurrence of each prefix sum in a HashMap.
     * - If a prefix sum repeats, it means elements in between sum to 0.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int maxLength(int[] arr) {
        // code here
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int preSum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            preSum += arr[i];
            if (preSum == 0) {
                ans = i + 1;
            } else if (map.containsKey(preSum)) {
                ans = Math.max(i - map.get(preSum), ans);
            } else {
                map.put(preSum, i);
            }
        }
        return ans;
    }

    public int countSubarrays(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            int sec = nums[i + 1];
            int th = nums[i + 2];
            if (2 * (first + th) == sec) cnt++;

        }

        return cnt;
    }

}
