package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class MinimumWindowSubstring {

    /**
     * Finds the minimum window in string `s` which will contain all the characters of string `t`.
     * <p>
     * Intuition:
     * - Use a sliding window to expand the right boundary to include characters until all characters from `t` are included.
     * - Then shrink the left boundary to minimize the window while still containing all characters from `t`.
     * - Track the minimum-length valid window found.
     * <p>
     * Implementation Details:
     * - An integer array of size 256 is used to simulate a frequency map for all ASCII characters.
     * - Each time a required character is included in the window, the `cnt` is increased.
     * - Once all characters from `t` are matched, the window is potentially shrunk from the left.
     * <p>
     * Time Complexity: O(n) — Each character in `s` is processed at most twice (once by right and once by left pointer).
     * Space Complexity: O(1) — Fixed size map of 256 characters (ASCII), regardless of input size.
     */
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[] map = new int[256];
        for (char it : t.toCharArray()) {
            map[it]++;
        }
        int lastIndex = -1;
        int r = 0, l = 0;
        int cnt = 0;
        int maxLen = Integer.MAX_VALUE;
        while (r < n) {
            if (map[s.charAt(r)] > 0) cnt++;
            map[s.charAt(r)]--;
            while (cnt == m) {
                if (r - l + 1 < maxLen) {
                    maxLen = r - l + 1;
                    lastIndex = l;
                }
                map[s.charAt(l)]++;
                if (map[s.charAt(l)] > 0) cnt--;
                l++;
            }
            r++;
        }

        return lastIndex == -1 ? "" : s.substring(lastIndex, lastIndex + maxLen);
    }
}
