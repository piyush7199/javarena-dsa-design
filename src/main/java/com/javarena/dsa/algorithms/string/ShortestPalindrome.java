package com.javarena.dsa.algorithms.string;

/**
 * Shortest Palindrome
 *
 * <p><b>Problem Statement:</b><br>
 * Given string s, find shortest palindrome by adding characters in front of s.
 * Return the shortest palindrome string.
 *
 * <p><b>Intuition & Approach:</b><br>
 * KMP-based approach to find longest palindromic prefix:
 * - Create combined string: s + "#" + reverse(s)
 * - Build LPS array for combined string
 * - LPS[end] gives longest prefix of s that's also suffix of reverse(s)
 * - This means longest palindromic prefix length
 * - Add reverse of remaining suffix to front
 * 
 * Key insight:
 * - If s = "aacecaa", reverse = "aacecaa" (already palindrome)
 * - If s = "abcd", need to add "dcb" in front → "dcbabcd"
 * - LPS tells how much of s is already palindromic from start
 * 
 * Alternative: Two-pointer from ends, but KMP is optimal O(n).
 *
 * <p><b>Time Complexity:</b> O(N) - LPS array construction
 * <br><b>Space Complexity:</b> O(N) - LPS array and combined string
 */
public class ShortestPalindrome {
    
    /**
     * Finds shortest palindrome by adding characters to front.
     */
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        
        // Create combined string: s + "#" + reverse(s)
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;
        
        // Build LPS array
        int[] lps = buildLPS(combined);
        
        // lps[end] = longest palindromic prefix length
        int palindromePrefixLen = lps[combined.length() - 1];
        
        // Add reverse of remaining suffix to front
        String toAdd = rev.substring(0, s.length() - palindromePrefixLen);
        return toAdd + s;
    }
    
    /**
     * Builds LPS array for KMP.
     */
    private int[] buildLPS(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;
        
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
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
        
        return lps;
    }
}
