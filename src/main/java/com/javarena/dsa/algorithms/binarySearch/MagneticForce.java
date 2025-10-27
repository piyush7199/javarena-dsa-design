package com.javarena.dsa.algorithms.binarySearch;

import java.util.Arrays;

/**
 * 1552. Magnetic Force Between Two Balls
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/magnetic-force-between-two-balls/">LeetCode - Magnetic Force</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Binary Search, Array, Greedy
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given an array position representing basket positions and integer m representing number of balls,
 * place m balls in baskets such that the minimum magnetic force between any two balls is maximized.
 * The magnetic force between two balls at positions x and y is |x - y|.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: position = [1,2,3,4,7], m = 3
 * Output: 3
 * Explanation: Place balls at positions 1, 4, 7. Min distance = 3.
 *
 * Input: position = [5,4,3,2,1,1000000000], m = 2
 * Output: 999999999
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Binary search to maximize minimum distance - same pattern as Aggressive Cows.
 * Sort positions, search on possible distances [1, max-min], greedily verify if distance achievable.
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Sort position array</li>
 *   <li>Binary search on distance: low=1, high=position[n-1]-position[0]</li>
 *   <li>For each mid, greedily place balls with distance ≥ mid</li>
 *   <li>If m balls placed: try larger distance</li>
 *   <li>Else: try smaller distance</li>
 *   <li>Return high (largest feasible distance)</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n log n + n log D)<br>
 * <p><b>Space Complexity:</b> O(1)<br>
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>m = 2: Maximum distance between any two positions</li>
 *   <li>All positions same: Return 0</li>
 *   <li>Large position values: Use proper mid calculation</li>
 * </ul>
 */
public class MagneticForce {
    
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int low = 1;
        int high = position[n - 1] - position[0];

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(position, mid, m)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    private boolean isPossible(int[] position, int mid, int m) {
        int last = position[0];
        int cnt = 1;
        
        for (int i = 1; i < position.length; i++) {
            if (position[i] - last >= mid) {
                last = position[i];
                cnt++;
            }
            if (cnt >= m) return true;
        }
        return false;
    }
}
