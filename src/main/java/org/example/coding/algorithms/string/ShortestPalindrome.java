package org.example.coding.algorithms.string;

public class ShortestPalindrome {
    /**
     * Returns the shortest palindrome that can be formed by adding characters in front of the given string.
     * <p>
     * Intuition:
     * We want to find the longest prefix of the input string `s` that is also a palindrome.
     * To do this efficiently, we reverse the string and append it to the original string with a separator
     * to avoid overlap (e.g., "abc" -> "abc#cba"). Then we compute the Longest Prefix Suffix (LPS)
     * using the KMP algorithm on this combined string.
     * The length of the LPS at the end tells us how many characters from the start of the original string
     * are part of the palindrome. We reverse the remaining suffix and prepend it to the original string.
     * <p>
     * Time Complexity: O(n), where n is the length of the input string.
     * Space Complexity: O(n) for the LPS array and reversed string.
     */
    public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;

        // Create string t = s + "#" + reverse(s)
        String rev = new StringBuilder(s).reverse().toString();
        String t = s + "#" + rev;
        int n = t.length();

        // Compute LPS array for t
        int[] lps = new int[n];
        lps[0] = 0;
        int len = 0; // Length of previous longest prefix-suffix
        int i = 1;

        while (i < n) {
            if (t.charAt(i) == t.charAt(len)) {
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

        // Length of the longest palindromic prefix is lps[n-1]
        int palLen = lps[n - 1];

        // Take the remaining suffix, reverse it, and prepend to s
        String suffix = s.substring(palLen);
        return new StringBuilder(suffix).reverse().append(s).toString();
    }
}
