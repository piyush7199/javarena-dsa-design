package org.example.coding.datastructures.arrays;

/**
 * Problem:
 * We are given an array nums[] and queries[][], where each query = [l, r].
 * For each query, we can select some indices in [l, r] and decrement nums[i] by 1.
 * We must check if it's possible to make nums a Zero Array after applying all queries.
 * <p>
 * -----------------------------------------
 * Intuition:
 * Each query provides a chance to reduce values in a subarray range [l, r].
 * To make nums[i] = 0, the total number of opportunities (times index i is covered by queries)
 * must be at least nums[i]. If any nums[i] > coverage(i), it's impossible.
 * <p>
 * -----------------------------------------
 * Brute Force Approach:
 * - For each query, decrement nums[l..r] directly (O(n) per query).
 * - After all queries, check if array is zero.
 * - Very slow for large input (O(n*q)).
 * <p>
 * Optimal Approach (Prefix Difference / Range Coverage):
 * - Instead of applying queries directly, calculate how many times each index is covered.
 * - Use a difference array: For query [l, r], do diff[l]++, diff[r+1]--.
 * - Take prefix sum to compute coverage[i] = number of times index i is covered.
 * - Finally, check if nums[i] <= coverage[i] for all i.
 * <p>
 * -----------------------------------------
 * Time Complexity:
 * - Brute Force: O(n*q) → inefficient for large inputs.
 * - Optimal: O(n + q) → much faster.
 * Space Complexity:
 * - Brute Force: O(1).
 * - Optimal: O(n) for difference array.
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
