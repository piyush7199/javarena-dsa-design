package com.javarena.dsa.datastructures.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Maximum Erasure Value
 *
 * <p><b>Problem Statement:</b><br>
 * Find the maximum possible sum of a subarray that contains only unique elements.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use sliding window with two pointers (i, j)
 * - Use HashSet to track elements in current window
 * - Expand window: If nums[j] not in set, add it, update sum, move j forward
 * - Shrink window: If nums[j] is duplicate, remove nums[i] from set, update sum, move i forward
 * - Track maximum sum found during expansion
 * - This ensures we only consider subarrays with unique elements
 *
 * <p><b>Time Complexity:</b> O(N) - Each element added/removed from set at most once
 * <br><b>Space Complexity:</b> O(N) - HashSet storing current window elements
 */
public class MaximumErasureValue {
    /**
     * Finds maximum sum of subarray with unique elements.
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
