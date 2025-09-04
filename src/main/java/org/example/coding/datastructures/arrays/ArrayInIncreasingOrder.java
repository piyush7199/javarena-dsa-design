package org.example.coding.datastructures.arrays;

public class ArrayInIncreasingOrder {
    /**
     * Brute Force O(n²) Solution
     * <p>
     * Intuition:
     * ----------
     * Try removing each element one by one and check if the resulting array is strictly increasing.
     * <p>
     * - For each index i:
     * - Pretend to remove nums[i].
     * - Scan the remaining array to verify if it is strictly increasing.
     * - If at least one removal works, return true.
     * - Otherwise, return false.
     * <p>
     * Time Complexity:
     * ----------------
     * - O(n²) → For each element (O(n)), we scan the array again (O(n)).
     * <p>
     * Space Complexity:
     * -----------------
     * - O(1) → We don't create additional data structures except a few variables.
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
     * Intuition:
     * ------------
     * We need to check if it's possible to remove exactly one element so that the array
     * becomes strictly increasing. A strictly increasing array means:
     * nums[i] < nums[i+1] for all i.
     * <p>
     * Instead of trying to remove each element one by one (O(n²) approach),
     * we can solve this in O(n) by scanning the array once:
     * <p>
     * - Traverse the array and count the number of violations where nums[i] <= nums[i-1].
     * - If there is more than one violation, return false immediately.
     * - When a violation is found, we decide which element to "remove":
     * Case 1: Remove nums[i-1] if nums[i] > nums[i-2].
     * Case 2: Otherwise, remove nums[i] (by treating nums[i] = nums[i-1]).
     * <p>
     * This way, we logically simulate removing one element without actually creating new arrays.
     * <p>
     * Time Complexity:
     * ----------------
     * - O(n) → We scan the array once.
     * <p>
     * Space Complexity:
     * -----------------
     * - O(1) → Constant extra space.
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
