package com.javarena.dsa.algorithms.binarySearch;

/**
 * 1482. Minimum Number of Days to Make m Bouquets
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/">LeetCode - Minimum Days Bouquets</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Binary Search, Array
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given array bloom Day where bloomDay[i] is the day the ith flower blooms, integers m and k,
 * find the minimum days to wait to make m bouquets where each bouquet needs k adjacent flowers.
 * Return -1 if impossible.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
 * Output: 3
 * Explanation: Day 3: flowers at [1,3,2] bloom. Can make 3 bouquets.
 *
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
 * Output: -1
 * Explanation: Need 6 adjacent flowers, only have 5.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Binary search on days. For each day, count consecutive bloomed flowers to form bouquets.
 * Search space: [min(bloomDay), max(bloomDay)]. If can make m bouquets by day X, try earlier days.
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Check if total flowers < m×k (impossible)</li>
 *   <li>Binary search on days: low=min(bloomDay), high=max(bloomDay)</li>
 *   <li>For mid day, count bouquets: scan array, count consecutive bloomed (≤mid)</li>
 *   <li>Every k consecutive forms one bouquet</li>
 *   <li>If ≥m bouquets: try earlier day (high=mid-1)</li>
 *   <li>Else: need more days (low=mid+1)</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n log D)<br>
 * <p><b>Space Complexity:</b> O(1)<br>
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>n < m×k: Impossible, return -1</li>
 *   <li>k = 1: Each flower is a bouquet</li>
 *   <li>All flowers bloom same day: That day is answer</li>
 * </ul>
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
