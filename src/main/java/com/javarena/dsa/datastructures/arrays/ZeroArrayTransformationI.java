package com.javarena.dsa.datastructures.arrays;

/**
 * Zero Array Transformation I
 *
 * <p><b>Problem Statement:</b><br>
 * Given array nums and queries [l, r], for each query we can decrement any indices in range [l, r] by 1.
 * Check if it's possible to make entire array zero after applying all queries.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Optimal Solution using Difference Array:
 * - Each query provides coverage to reduce values in range [l, r]
 * - To make nums[i] = 0, coverage at i must be >= nums[i]
 * - Use difference array to efficiently track coverage:
 *   - For query [l, r]: diff[l]++, diff[r+1]--
 * - Compute prefix sum to get coverage[i]
 * - Check if nums[i] <= coverage[i] for all i
 * 
 * Brute Force: Apply each query directly (O(N*Q))
 *
 * <p><b>Time Complexity:</b> O(N + Q) for optimal, O(N × Q) for brute force
 * <br><b>Space Complexity:</b> O(N) for difference array, O(1) for brute force
 */
public class ZeroArrayTransformationI {
    /**
     * Brute Force Approach
     */
    public boolean isZeroArrayBruteForce(int[] nums, int[][] queries) {
        int n = nums.length;

        // Apply each query directly
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            for (int i = l; i <= r; i++) {
                if (nums[i] > 0) nums[i]--; // decrement if possible
            }
        }

        // Check if all are zero
        for (int num : nums) {
            if (num != 0) return false;
        }
        return true;
    }

    /**
     * Optimal Approach using Difference Array
     */
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1]; // difference array

        // Mark increments and decrements for coverage
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            diff[l]++;
            if (r + 1 < n) diff[r + 1]--;
        }

        // Compute prefix sum to get coverage[i]
        int coverage = 0;
        for (int i = 0; i < n; i++) {
            coverage += diff[i];
            if (nums[i] > coverage) {
                return false; // not enough coverage to reduce nums[i] to 0
            }
        }

        return true;
    }
}
