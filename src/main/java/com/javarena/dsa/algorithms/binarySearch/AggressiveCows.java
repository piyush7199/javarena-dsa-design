package com.javarena.dsa.algorithms.binarySearch;

import java.util.Arrays;

/**
 * Aggressive Cows
 *
 * <p><b>Problem Statement:</b><br>
 * Given n stalls at different positions and k cows, place the cows such that the minimum distance 
 * between any two cows is maximized. Return this maximum possible minimum distance.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Binary search on answer (minimum distance):
 * - Search space: [1, max_position - min_position]
 * - Cannot directly determine best placement, but can verify if distance d is achievable
 * - For candidate distance, greedily place cows:
 *   1. Sort stalls array
 *   2. Place first cow at first stall
 *   3. Place next cow at first stall >= distance away
 *   4. If all k cows placed: try larger distance
 *   5. Else: try smaller distance
 * - Greedy placement ensures maximum space utilization
 *
 * <p><b>Time Complexity:</b> O(N log N + N log D) - N stalls, D = range
 * <br><b>Space Complexity:</b> O(1) - Constant extra space
 */
public class AggressiveCows {
    
    /**
     * Finds maximum possible minimum distance between cows.
     */
    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        int n = stalls.length;
        
        int i = 1;                          // Minimum distance
        int j = stalls[n - 1] - stalls[0];  // Maximum distance

        while (i <= j) {
            int mid = (i + j) / 2;
            
            if (possible(stalls, k, mid)) {
                i = mid + 1;  // Try larger distance
            } else {
                j = mid - 1;  // Reduce distance
            }
        }
        
        return j;
    }

    /**
     * Checks if k cows can be placed with minimum distance dis.
     */
    private static boolean possible(int[] stalls, int k, int dis) {
        int noOfCows = 1;
        int last = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (last + dis <= stalls[i]) {
                last = stalls[i];
                noOfCows++;
            }
            
            if (noOfCows >= k) return true;
        }
        
        return false;
    }
}
