package org.example.coding.algorithms.binarySearch;

import java.util.Arrays;

public class MagneticForce {
    /*
     * Problem: Place m balls in positions such that the minimum distance
     *          between any two balls is maximized.
     *
     * Intuition:
     * -----------
     * - This is a "maximize the minimum distance" problem.
     * - If we fix a minimum distance `d`, we can greedily try to place balls
     *   left to right, ensuring each ball is at least `d` away from the last placed ball.
     * - If it’s possible to place all m balls with distance ≥ d, then maybe we can
     *   increase d (binary search).
     * - Otherwise, we need to reduce d.
     *
     * Approach:
     * -----------
     * 1. Sort the `position` array (so we place balls in increasing order of stalls).
     * 2. Set search bounds:
     *      - low = 1 (minimum possible distance).
     *      - high = position[n-1] - position[0] (max possible distance).
     * 3. Binary search:
     *      - mid = candidate minimum distance.
     *      - If we can place all m balls with distance ≥ mid → try larger distance.
     *      - Else → try smaller distance.
     * 4. Return `high` (the largest feasible distance).
     *
     * Time Complexity:
     *  - O(n log(maxDistance))
     *    Sorting: O(n log n),
     *    Binary search: O(log(maxDist)),
     *    Feasibility check each step: O(n).
     *
     * Space Complexity:
     *  - O(1) (in-place, no extra structures).
     */
    public int maxDistance(int[] position, int m) {
        // Sort stall positions
        Arrays.sort(position);
        int n = position.length;

        // Search space: min = 1, max = farthest two stalls
        int low = 1;
        int high = position[n - 1] - position[0];

        // Binary search for the largest minimum distance
        while (low <= high) {
            int mid = low + (high - low) / 2; // candidate distance
            if (isPossible(position, mid, m)) {
                // If possible with distance mid → try for a larger one
                low = mid + 1;
            } else {
                // Otherwise, reduce distance
                high = mid - 1;
            }
        }

        return high; // largest valid distance
    }

    /*
     * Helper function:
     * - Returns true if we can place `m` balls with at least `mid` distance apart.
     */
    private boolean isPossible(int[] position, int mid, int m) {
        int last = position[0]; // place first ball at the first stall
        int cnt = 1;            // number of balls placed

        // Try to place the rest
        for (int i = 1; i < position.length; i++) {
            if (position[i] - last >= mid) {
                last = position[i]; // place ball here
                cnt++;
            }
            if (cnt >= m) return true; // placed all balls
        }
        return false; // couldn't place all m balls
    }
}
