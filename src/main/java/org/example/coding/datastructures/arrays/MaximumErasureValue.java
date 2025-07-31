package org.example.coding.datastructures.arrays;

import java.util.HashSet;
import java.util.Set;

public class MaximumErasureValue {
    /**
     * Finds the maximum possible sum of a subarray that contains only unique elements.
     *
     * <p><b>Simple Intuition:</b></p>
     * We want the largest sum of any subarray (contiguous part of the array)
     * where no number repeats. So, as soon as we see a duplicate, we shrink
     * the window from the left to remove it.
     *
     * <p><b>Approach:</b></p>
     * - Use the sliding window technique with two pointers (i and j).
     * - Use a HashSet to keep track of which numbers are in the current window.
     * - If `nums[j]` is not in the set, add it and move `j` forward.
     * - If it is a duplicate, remove `nums[i]` from the set and move `i` forward to shrink the window.
     * - Keep updating the sum and track the maximum found so far.
     *
     * @param nums An array of integers.
     * @return The maximum sum of any subarray with all unique elements.
     *
     * <p><b>Time Complexity:</b> O(n) – Each element is added and removed from the set at most once.</p>
     * <p><b>Space Complexity:</b> O(n) – For the HashSet storing current subarray elements.</p>
     */
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int max = 0;
        int suma = 0;
        Set<Integer> set = new HashSet<>();
        int i = 0, j = 0;

        while (j < n) {
            if (!set.contains(nums[j])) {
                set.add(nums[j]);
                suma += nums[j];
                max = Math.max(max, suma);
                j++;
            } else {
                set.remove(nums[i]);
                suma -= nums[i];
                i++;
            }
        }

        return max;
    }

}
