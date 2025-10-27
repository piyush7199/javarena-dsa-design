package com.javarena.dsa.algorithms.binarySearch;

import java.util.Arrays;

/**
 * Aggressive Cows (Binary Search on Answer)
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://www.spoj.com/problems/AGGRCOW/">SPOJ - Aggressive Cows</a>
 *
 * <p><b>Difficulty:</b> Hard
 *
 * <p><b>Topics:</b> Binary Search, Greedy, Array
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given n stalls at different positions and k cows, place the cows such that the minimum distance 
 * between any two cows is maximized. Return this maximum possible minimum distance.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: stalls = [1, 2, 4, 8, 9], k = 3
 * Output: 3
 * Explanation: Place cows at positions 1, 4, and 8. Min distance = 3.
 *
 * Input: stalls = [1, 2, 3, 4, 5], k = 3
 * Output: 2
 * Explanation: Place at 1, 3, 5. Min distance = 2.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Binary search on the answer (minimum distance):
 * - Cannot directly determine best placement, but can verify if distance d is achievable
 * - Search space: [1, max_position - min_position]
 * - For candidate distance, greedily place cows as far apart as possible
 * - If can place all k cows, try larger distance; else try smaller
 * - Greedy placement: put first cow at first stall, next cow at first stall >= d away
 * - This ensures maximum utilization of available space
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Sort stalls array (to place cows in order)</li>
 *   <li>Initialize binary search bounds:</li>
 *   <li>- low = 1 (minimum possible distance)</li>
 *   <li>- high = stalls[n-1] - stalls[0] (maximum possible distance)</li>
 *   <li>Binary search on distance:</li>
 *   <li>- For mid distance, check if can place k cows</li>
 *   <li>- Use greedy helper: place first cow at stalls[0]</li>
 *   <li>- Place next cow at first stall >= distance away</li>
 *   <li>- If all k placed: increase distance (low = mid+1)</li>
 *   <li>- Else: decrease distance (high = mid-1)</li>
 *   <li>Return high (largest feasible distance)</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n log n + n log D)<br>
 * Where n = number of stalls, D = range of distances.
 * Sorting O(n log n), binary search O(log D), each check O(n).
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses constant extra space (excluding sort).
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>k = 2: Return max_position - min_position</li>
 *   <li>k = n: Must place in every stall, return minimum gap</li>
 *   <li>All stalls same position: Return 0</li>
 *   <li>Sparse stalls: Large distances possible</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/magnetic-force-between-two-balls/">Magnetic Force Between Two Balls</a>
 */
public class AggressiveCows {
    
    /**
     * Finds maximum possible minimum distance between cows.
     *
     * @param stalls array of stall positions
     * @param k number of cows to place
     * @return maximum possible minimum distance
     */
    public static int aggressiveCows(int[] stalls, int k) {
        // Step 1: Sort stalls for ordered placement
        Arrays.sort(stalls);
        int n = stalls.length;
        
        // Step 2: Define search space
        int i = 1;                          // Minimum distance
        int j = stalls[n - 1] - stalls[0];  // Maximum distance

        // Step 3: Binary search on distance
        while (i <= j) {
            int mid = (i + j) / 2;
            
            // Step 4: Check if mid distance is achievable
            if (possible(stalls, k, mid)) {
                i = mid + 1;  // Try larger distance
            } else {
                j = mid - 1;  // Reduce distance
            }
        }
        
        // j contains largest feasible distance
        return j;
    }

    /**
     * Checks if k cows can be placed with minimum distance dis.
     * Uses greedy placement strategy.
     *
     * @param stalls sorted array of stall positions
     * @param k number of cows
     * @param dis minimum distance to maintain
     * @return true if placement is possible
     */
    private static boolean possible(int[] stalls, int k, int dis) {
        int noOfCows = 1;  // Place first cow
        int last = stalls[0];  // Position of last placed cow

        // Step 1: Try to place remaining cows
        for (int i = 1; i < stalls.length; i++) {
            // Step 2: If distance >= dis, place cow here
            if (last + dis <= stalls[i]) {
                last = stalls[i];
                noOfCows++;
            }
            
            // Step 3: Early exit if all cows placed
            if (noOfCows >= k) return true;
        }
        
        return false;
    }
}
