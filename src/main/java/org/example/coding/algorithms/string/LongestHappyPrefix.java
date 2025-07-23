package org.example.coding.algorithms.string;

public class LongestHappyPrefix {

    /**
     * Returns the longest proper prefix of the string that is also a suffix.
     * <p>
     * Intuition:
     * This function uses the LPS (Longest Prefix Suffix) array from the KMP algorithm.
     * For each index i in the pattern, we compute the length of the longest prefix that
     * matches a suffix ending at i.
     * <p>
     * At the end, lps[m - 1] gives the length of the longest prefix that is also a suffix
     * for the entire string (excluding the whole string itself).
     * <p>
     * <p>
     * Time Complexity: O(n), where n is the length of the input string.
     * Space Complexity: O(n) for the LPS array.
     */
    public String longestPrefix(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return pattern.substring(0, lps[m - 1]);
    }
}
