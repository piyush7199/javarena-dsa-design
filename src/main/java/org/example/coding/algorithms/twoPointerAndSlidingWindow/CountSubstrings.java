package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class CountSubstrings {
    /**
     * Counts the number of substrings that contain at least one occurrence of each character: 'a', 'b', and 'c'.
     * <p>
     * Intuition:
     * - Maintain the last seen index of 'a', 'b', and 'c'.
     * - At each index, if all three characters have been seen, the count of substrings ending at that index
     * is equal to (minimum of the last seen indices + 1).
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
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
