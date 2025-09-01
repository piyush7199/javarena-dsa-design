package org.example.coding.algorithms.binarySearch;

public class MinimumDaysMakeBouquets {
    /*
     * Problem: Minimum Number of Days to Make m Bouquets (LeetCode 1482)
     *
     * Intuition:
     * -----------
     * - We need to find the earliest day when it's possible to make `m` bouquets,
     *   each requiring `k` consecutive flowers.
     * - If we imagine a "day X", we can check if enough flowers have bloomed by then
     *   to make `m` bouquets.
     * - This naturally suggests a **binary search on the minimum day**:
     *   - Lower bound = earliest bloom day (min value in bloomDay).
     *   - Upper bound = latest bloom day (max value in bloomDay).
     * - For each mid-day, check feasibility using a helper function.
     *
     * Approach:
     * -----------
     * 1. Edge case: If total flowers < m*k, it's impossible → return -1.
     * 2. Find the min (low) and max (high) bloom days.
     * 3. Apply binary search:
     *      - mid = candidate minimum day.
     *      - If it's possible to make m bouquets by `mid`, try smaller days.
     *      - Otherwise, try larger days.
     * 4. Return the smallest feasible day (stored in low).
     *
     * Time Complexity:
     *  - O(n log(maxBloom))
     *    where n = length of bloomDay, maxBloom = max(bloomDay).
     *    Because for each binary search step (log(maxBloom)), we scan the array (O(n)).
     *
     * Space Complexity:
     *  - O(1) extra space (in-place computation).
     */

    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;

        // If total flowers < required flowers for m bouquets → impossible
        if (n / k < m) return -1;

        // Find min and max bloom days
        int low = bloomDay[0];
        int high = bloomDay[0];
        for (int ele : bloomDay) {
            low = Math.min(low, ele);
            high = Math.max(high, ele);
        }

        // Binary search on the number of days
        while (low <= high) {
            int mid = low + (high - low) / 2; // candidate day (avoid overflow)
            if (helper(bloomDay, mid, m, k)) {
                // If possible by mid, try earlier
                high = mid - 1;
            } else {
                // Otherwise, need more days
                low = mid + 1;
            }
        }
        return low; // Smallest feasible day
    }

    /*
     * Helper function:
     * - Given a day `mid`, check if we can make at least `m` bouquets.
     */
    private boolean helper(int[] bloomDay, int mid, int m, int k) {
        int bouq = 0; // number of bouquets formed
        int adj = 0;  // count of consecutive bloomed flowers

        for (int ele : bloomDay) {
            if (ele <= mid) {
                adj++; // flower bloomed, extend streak
            } else {
                adj = 0; // streak breaks
            }

            // Once we collect k consecutive flowers → form a bouquet
            if (adj == k) {
                bouq++;
                adj = 0; // reset for next bouquet
            }

            // Early exit if we already have enough bouquets
            if (bouq >= m) return true;
        }

        return false;
    }

}
