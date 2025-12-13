package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Number of Substrings Containing All Three Characters
 *
 * <p><b>Problem Statement:</b><br>
 * Given a string s consisting only of characters 'a', 'b', and 'c', return the number of substrings
 * that contain at least one occurrence of each of these three characters.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Track the last seen index of each character ('a', 'b', 'c')
 * - At each position i, if all three characters have been seen:
 *   - Any substring starting from position 0 to min(last indices) and ending at i is valid
 *   - Count = minimum of last seen indices + 1
 * - Sum up counts for all positions to get total substrings
 * - Key insight: Once all three characters appear, earlier positions also form valid substrings
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through string
 * <br><b>Space Complexity:</b> O(1) - Array of size 3 for tracking indices
 */
public class CountSubstrings {
    /**
     * Counts substrings containing at least one 'a', 'b', and 'c'.
     */
    public int numberOfSubstrings(String s) {
        int[] lastSeen = {-1, -1, -1};
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                ans += (Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2])) + 1);
            }
        }
        return ans;
    }
}
