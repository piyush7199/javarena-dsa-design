package com.javarena.dsa.algorithms.string;

/**
 * Longest Happy Prefix
 *
 * <p><b>Problem Statement:</b><br>
 * Given string s, return longest happy prefix - a non-empty string that is both
 * proper prefix and proper suffix of s. Return empty string if no such prefix exists.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Application of KMP's LPS (Longest Prefix Suffix) array:
 * - Happy prefix = proper prefix that's also proper suffix
 * - This is exactly what LPS array computes
 * - Build LPS array for entire string
 * - lps[n-1] gives length of longest happy prefix
 * - Extract substring of that length
 * 
 * LPS array construction:
 * - Two pointers: len (length of current LPS) and i (current position)
 * - Match: increment both, set lps[i] = len
 * - Mismatch: backtrack using lps[len-1] or reset to 0
 * 
 * Direct application of KMP preprocessing.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass to build LPS array
 * <br><b>Space Complexity:</b> O(N) - LPS array storage
 */
public class LongestHappyPrefix {
    
    /**
     * Finds longest happy prefix using LPS array.
     */
    public String longestPrefix(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;
        
        // Build LPS array
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
        
        // Length of longest happy prefix
        int happyLen = lps[n - 1];
        return s.substring(0, happyLen);
    }
}
