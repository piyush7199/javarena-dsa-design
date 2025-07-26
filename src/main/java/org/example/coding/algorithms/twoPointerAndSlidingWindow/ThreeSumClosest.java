package org.example.coding.algorithms.twoPointerAndSlidingWindow;

import java.util.Arrays;

public class ThreeSumClosest {
    /**
     * Finds the sum of three integers in the array `nums` such that the sum is closest to the given target.
     *
     * <p>This method first sorts the array and then uses a two-pointer technique to explore
     * possible triplets. It iterates over each element and for each fixed element, tries to find
     * the best pair using the remaining sorted elements with two pointers (left and right).
     * <p>
     * Time Complexity: O(n^2)
     * - Sorting takes O(n log n)
     * - The main loop runs in O(n), and the inner two-pointer loop runs in O(n)
     * => Total: O(n log n + n^2) ≈ O(n^2)
     * <p>
     * Space Complexity: O(1)
     * - No extra space is used beyond variables; sorting is in-place.
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        int dif = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) return target;
                if (Math.abs(target - sum) < dif) {
                    ans = sum;
                    dif = Math.abs(target - sum);
                }
                if (sum > target) k--;
                else j++;
            }
        }
        return ans;
    }
}
