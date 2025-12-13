package com.javarena.dsa.algorithms.binarySearch;

/**
 * Smallest Divisor Given a Threshold
 *
 * <p><b>Problem Statement:</b><br>
 * Given array nums and threshold, find smallest divisor such that result of
 * dividing all elements by it (rounded up) and summing them is ≤ threshold.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Binary search on divisor value:
 * - Monotonic property: smaller divisor → larger sum, larger divisor → smaller sum
 * - Search space: [1, max(nums)]
 * - For candidate divisor:
 *   1. Calculate sum of ceil(nums[i]/divisor) for all elements
 *   2. Use ceil trick: ceil(a/b) = (a+b-1)/b
 *   3. If sum ≤ threshold: try smaller divisor
 *   4. Else: need larger divisor
 * - Return smallest divisor that satisfies condition
 *
 * <p><b>Time Complexity:</b> O(N log M) - N elements, M = max(nums)
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class SmallestDivisorGivenAThreshold {
    
    /**
     * Finds smallest divisor that keeps sum ≤ threshold.
     */
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = findMax(nums);

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sumByDivisor(nums, mid) <= threshold) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Calculates sum of ceiling division for all elements.
     */
    private int sumByDivisor(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor;  // Ceiling trick
        }
        return sum;
    }

    /**
     * Finds maximum value in array.
     */
    private int findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }
}
