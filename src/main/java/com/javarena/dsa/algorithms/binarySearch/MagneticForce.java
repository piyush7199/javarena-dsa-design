package com.javarena.dsa.algorithms.binarySearch;

import java.util.Arrays;

/**
 * Magnetic Force Between Two Balls
 *
 * <p><b>Problem Statement:</b><br>
 * Given basket positions and m balls, place balls such that minimum magnetic force 
 * (distance) between any two balls is maximized. Return maximum possible minimum force.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Binary search on minimum distance (same as Aggressive Cows):
 * - Search space: [1, max_position - min_position]
 * - Sort positions for greedy placement
 * - For candidate distance:
 *   1. Place first ball at first position
 *   2. Place next ball at first position >= distance away
 *   3. If all m balls placed: try larger distance
 *   4. Else: try smaller distance
 * - Return largest feasible distance
 *
 * <p><b>Time Complexity:</b> O(N log N + N log D) - N positions, D = range
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class MagneticForce {
    
    /**
     * Finds maximum possible minimum magnetic force.
     */
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

    /**
     * Checks if m balls can be placed with minimum distance mid.
     */
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
