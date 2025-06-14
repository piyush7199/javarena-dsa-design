package org.example.coding.algorithms;

public class SlidingWindow {

    /**
     * Finds the shortest lexicographically smallest substring in a binary string `s` that contains exactly `k` ones.
     *
     * <p><b>Intuition:</b></p>
     * We use the sliding window technique with two pointers (`left` and `right`) to explore all substrings of `s`.
     * As we move the right pointer, we count the number of '1's in the window.
     * If the count exceeds `k`, we move the left pointer to shrink the window until we have at most `k` '1's.
     * Whenever the window has exactly `k` '1's:
     * - We check if the window is shorter than the previous shortest (update if yes).
     * - If lengths are equal, we keep the lexicographically smaller one.
     * This approach ensures we find the shortest valid substring efficiently.
     *
     * <p><b>Time Complexity:</b> O(n)</p>
     * - Each character is visited at most twice (once by `right`, once by `left`).
     *
     * <p><b>Space Complexity:</b> O(1)</p>
     * - Only constant extra space is used (a few variables), excluding the result string.
     */
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int ones = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Shrink from left if too many 1s
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Try to shrink while still maintaining exactly k 1s
            while (ones == k) {
                int len = right - left + 1;
                String candidate = s.substring(left, right + 1);

                if (len < minLen || (len == minLen && candidate.compareTo(result) < 0)) {
                    minLen = len;
                    result = candidate;
                }

                // Try to shrink the window from the left
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }
        }

        return result;
    }

}
