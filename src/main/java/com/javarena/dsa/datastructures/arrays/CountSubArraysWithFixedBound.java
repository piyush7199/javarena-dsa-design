package com.javarena.dsa.datastructures.arrays;

/**
 * Count Subarrays With Fixed Bounds
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer array nums and two integers minK and maxK, return the number of fixed-bound subarrays.
 * A fixed-bound subarray is one where both minK and maxK appear at least once, and all elements are within [minK, maxK].
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use sliding window with three pointers: minIndex, maxIndex, invalidIndex
 * - Track positions of minK and maxK occurrences
 * - Track position of last invalid element (outside [minK, maxK])
 * - For each position, count valid subarrays ending at current position:
 *   - Valid start = min(minIndex, maxIndex) (need both bounds)
 *   - If validStart > invalidIndex, add (validStart - invalidIndex) subarrays
 * - This counts all subarrays from invalidIndex+1 to validStart that end at i
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(1) - Only tracking three indices
 */
public class CountSubArraysWithFixedBound {
    /**
     * Counts subarrays containing both minK and maxK within bounds.
     */
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int minIndex = -1;
        int maxIndex = -1;
        int invalidIndex = -1;

        long res = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                invalidIndex = i;
            }
            if (nums[i] == minK) {
                minIndex = i;
            }
            if (nums[i] == maxK) {
                maxIndex = i;
            }

            int validStart = Math.min(minIndex, maxIndex);
            if (validStart > invalidIndex) {
                res += validStart - invalidIndex;
            }
        }

        return res;
    }
}
