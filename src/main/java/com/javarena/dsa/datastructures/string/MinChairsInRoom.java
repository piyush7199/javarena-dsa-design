package com.javarena.dsa.datastructures.string;

/**
 * Minimum Chairs in Waiting Room
 *
 * <p><b>Problem Statement:</b><br>
 * Given string where 'E' = person enters, 'L' = person leaves.
 * Find minimum chairs needed so no one has to stand.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Track current occupancy:
 * - 'E': increment count (person enters)
 * - 'L': decrement count (person leaves)
 * - Track maximum count reached
 * - Maximum = minimum chairs needed
 * 
 * Similar to maximum overlapping intervals problem.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through string
 * <br><b>Space Complexity:</b> O(1) - Two variables only
 */
public class MinChairsInRoom {
    /**
     * Finds minimum chairs needed.
     */
    public int minimumChairs(String s) {
        int ans = 0;
        int cnt = 0;
        
        for (char ch : s.toCharArray()) {
            if (ch == 'E') {
                cnt++; // Person enters
            } else {
                cnt--; // Person leaves
            }
            ans = Math.max(ans, cnt); // Track peak occupancy
        }
        return ans;
    }
}
