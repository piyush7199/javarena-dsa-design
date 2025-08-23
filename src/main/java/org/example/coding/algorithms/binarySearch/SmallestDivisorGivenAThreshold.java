package org.example.coding.algorithms.binarySearch;

public class SmallestDivisorGivenAThreshold {
    /**
     * Problem: LeetCode 1283 – Find the Smallest Divisor Given a Threshold
     * <p>
     * We need the smallest integer divisor such that:
     * sum( ceil(nums[i] / divisor) ) <= threshold
     * <p>
     * Intuition:
     * - The divisor affects the sum:
     * - Smaller divisor → larger quotients → larger sum.
     * - Larger divisor → smaller quotients → smaller sum.
     * - This property is monotonic → we can apply Binary Search.
     * <p>
     * Approach:
     * 1. Search range:
     * - Low = 1 (smallest possible divisor).
     * - High = max(nums) (largest divisor needed, since dividing by max(nums) yields at most 1).
     * 2. Binary Search:
     * - Mid = (low + high) / 2 → test this divisor.
     * - Compute sum = Σ ceil(nums[i] / mid).
     * - If sum <= threshold → mid might be valid, but try smaller divisor (move left).
     * - Else → mid is too small, increase divisor (move right).
     * 3. Return low (first valid divisor found).
     * <p>
     * Helper function:
     * - `helper(nums, mid, threshold)` checks if divisor = mid keeps sum ≤ threshold.
     * - Uses integer trick: ceil(a / b) = (a + b - 1) / b.
     * <p>
     * Time Complexity: O(n log(max(nums)))
     * - Binary search range = [1, max(nums)] → log(max(nums)) steps.
     * - Each step computes sum in O(n).
     * <p>
     * Space Complexity: O(1)
     * - Uses constant extra space.
     */
    public int smallestDivisor(int[] nums, int threshold) {
        int n = nums.length;

        // Search space: [1, max(nums)]
        int low = 1;
        int high = 0;
        for (int ele : nums) {
            high = Math.max(high, ele);
        }

        // Binary Search
        while (low <= high) {
            int mid = (low + high) / 2;

            // If mid is valid, try smaller divisor
            if (helper(nums, mid, threshold)) {
                high = mid - 1;
            } else { // Otherwise, need larger divisor
                low = mid + 1;
            }
        }

        return low;
    }

    /**
     * Helper function to check if divisor = mid satisfies threshold.
     * Returns true if sum of ceil(nums[i] / mid) <= threshold.
     */
    private boolean helper(int[] nums, int mid, int threshold) {
        int sum = 0;
        for (int ele : nums) {
            // ceil division: (a + b - 1) / b
            sum += (ele + mid - 1) / mid;

            // Early exit if threshold exceeded
            if (sum > threshold) return false;
        }
        return sum <= threshold;
    }

}
