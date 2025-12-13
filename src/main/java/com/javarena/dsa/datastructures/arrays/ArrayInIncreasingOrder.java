package com.javarena.dsa.datastructures.arrays;

/**
 * Check If Array Can Become Strictly Increasing After Removing One Element
 *
 * <p><b>Problem Statement:</b><br>
 * Given an array of integers nums, return true if you can remove exactly one element to make the array strictly increasing.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Optimal O(N) Solution:
 * - Scan array once, count violations where nums[i] <= nums[i-1]
 * - If more than one violation, impossible to fix with single removal
 * - On finding violation, decide which element to remove:
 *   - If nums[i] > nums[i-2], logically remove nums[i-1]
 *   - Otherwise, remove nums[i] by setting nums[i] = nums[i-1]
 * 
 * Brute Force O(N²):
 * - Try removing each element one by one
 * - Check if remaining array is strictly increasing
 *
 * <p><b>Time Complexity:</b> O(N) for optimal, O(N²) for brute force
 * <br><b>Space Complexity:</b> O(1) for both approaches
 */
public class ArrayInIncreasingOrder {
    /**
     * Brute force solution - tries removing each element.
     */
    public boolean canBeIncreasingBruteForce(int[] nums) {
        int n = nums.length;

        // Try removing each element
        for (int i = 0; i < n; i++) {
            int prev = Integer.MIN_VALUE; // Start with the smallest possible value
            boolean valid = true;         // Assume it's valid until proven otherwise

            // Check strictly increasing condition while skipping index i
            for (int j = 0; j < n; j++) {
                if (j == i) continue; // Skip the element we're "removing"

                // If the current element is not strictly greater than previous, it's invalid
                if (nums[j] <= prev) {
                    valid = false;
                    break;
                }

                prev = nums[j]; // Update previous element
            }

            // If valid array found after removing one element, return true
            if (valid) return true;
        }

        // None of the removals worked
        return false;
    }

    /**
     * Optimal solution - scans array once to count violations.
     */
    public boolean canBeIncreasing(int[] nums) {
        int count = 0; // Count how many violations are found

        for (int i = 1; i < nums.length; i++) {
            // If the current number is not greater than the previous one, it's a violation
            if (nums[i] <= nums[i - 1]) {
                count++;

                // If there are more than one violation, it's impossible to fix with one removal
                if (count > 1) return false;

                // Decide which element to "remove" logically:
                // If nums[i] <= nums[i-2], remove nums[i] (simulate by setting nums[i] = nums[i-1])
                if (i > 1 && nums[i] <= nums[i - 2]) {
                    nums[i] = nums[i - 1];
                }
            }
        }

        return true; // At most one violation found, array can be fixed
    }
}
