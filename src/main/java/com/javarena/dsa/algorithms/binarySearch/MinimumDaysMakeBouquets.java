package com.javarena.dsa.algorithms.binarySearch;

/**
 * Minimum Days to Make m Bouquets
 *
 * <p><b>Problem Statement:</b><br>
 * Given bloomDay array where bloomDay[i] is day flower i blooms, and integers m and k,
 * find minimum days to wait to make m bouquets where each needs k adjacent flowers.
 * Return -1 if impossible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Binary search on days:
 * - Search space: [min(bloomDay), max(bloomDay)]
 * - If total flowers < m×k: impossible, return -1
 * - For candidate day:
 *   1. Count consecutive bloomed flowers (≤ day)
 *   2. Every k consecutive = one bouquet
 *   3. If ≥ m bouquets: try earlier day
 *   4. Else: need more days
 * - Return minimum day where m bouquets achievable
 *
 * <p><b>Time Complexity:</b> O(N log D) - N flowers, D = max bloom day
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class MinimumDaysMakeBouquets {
    
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (n / k < m) return -1;

        int low = bloomDay[0], high = bloomDay[0];
        for (int ele : bloomDay) {
            low = Math.min(low, ele);
            high = Math.max(high, ele);
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (helper(bloomDay, mid, m, k)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean helper(int[] bloomDay, int mid, int m, int k) {
        int bouq = 0, adj = 0;
        for (int ele : bloomDay) {
            if (ele <= mid) {
                adj++;
            } else {
                adj = 0;
            }
            if (adj == k) {
                bouq++;
                adj = 0;
            }
            if (bouq >= m) return true;
        }
        return false;
    }
}
