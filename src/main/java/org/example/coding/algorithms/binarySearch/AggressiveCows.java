package org.example.coding.algorithms.binarySearch;

import java.util.Arrays;

public class AggressiveCows {
    /**
     * Problem: Aggressive Cows
     * --------------------------------
     * We are given 'n' stalls (with positions) and 'k' cows.
     * The task is to place the cows in stalls such that the
     * minimum distance between any two cows is maximized.
     * <p>
     * Intuition:
     * ----------
     * - If we try placing cows greedily at the farthest possible stalls,
     * we can check whether it is possible to place all 'k' cows
     * with at least 'd' minimum distance apart.
     * - Since the maximum possible minimum distance can range between
     * 1 (smallest possible distance) and (max(stall) - min(stall)),
     * we can apply **Binary Search** on this "answer space".
     * - For a given distance 'mid', we use a helper function `possible()`
     * to greedily check if all cows can be placed with at least that spacing.
     * - If it is possible, we try for a larger distance (move right in binary search),
     * otherwise, we reduce the distance (move left).
     * <p>
     * Approach:
     * ---------
     * 1. Sort the stalls array (so we can place cows in increasing order of positions).
     * 2. Apply binary search on the minimum distance:
     * - low = 1
     * - high = stalls[n-1] - stalls[0] (maximum possible spacing).
     * 3. For each mid, call `possible(stalls, k, mid)`:
     * - Place the first cow at the first stall.
     * - Iteratively try placing the next cows whenever the gap is >= mid.
     * - If we can place all 'k' cows, return true, else false.
     * 4. Adjust search space based on feasibility.
     * 5. The maximum feasible distance will be the answer.
     * <p>
     * Time Complexity:
     * ----------------
     * - Sorting: O(n log n)
     * - Binary search over distance range: O(log(max(stall) - min(stall)))
     * - Each feasibility check (`possible`): O(n)
     * - Overall: O(n log n + n log(max distance)) ≈ O(n log n + n log(10^9))
     * → effectively O(n log n).
     * <p>
     * Space Complexity:
     * -----------------
     * - O(1) extra space (apart from input array).
     */
    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        int n = stalls.length;
        int i = 1;
        int j = stalls[n - 1] - stalls[0];

        while (i <= j) {
            int mid = (i + j) / 2;
            if (possible(stalls, k, mid)) {
                i = mid + 1; // Try for larger distance
            } else {
                j = mid - 1; // Reduce distance
            }
        }
        return j;
    }

    /**
     * Helper function to check feasibility.
     */
    private static boolean possible(int[] stalls, int k, int dis) {
        int noOfCows = 1; // Place the first cow at the first stall
        int last = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (last + dis <= stalls[i]) {
                last = stalls[i]; // Place next cow
                noOfCows++;
            }
            if (noOfCows >= k) return true;
        }
        return false;
    }

}
