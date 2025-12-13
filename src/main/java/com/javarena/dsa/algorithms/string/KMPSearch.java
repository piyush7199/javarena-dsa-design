package com.javarena.dsa.algorithms.string;

/**
 * KMP (Knuth-Morris-Pratt) Pattern Matching
 *
 * <p><b>Problem Statement:</b><br>
 * Given strings haystack and needle, return index of first occurrence of needle in haystack.
 * Return -1 if needle is not part of haystack.
 *
 * <p><b>Intuition & Approach:</b><br>
 * KMP algorithm avoids re-examining characters after mismatch:
 * - Uses LPS (Longest Prefix Suffix) array for intelligent backtracking
 * - When mismatch occurs, LPS tells where to resume pattern matching
 * - No need to shift pattern one position at a time
 * - Achieves linear O(n+m) vs naive O(n×m)
 * 
 * Two-phase algorithm:
 * Phase 1 - Build LPS array:
 * - lps[i] = length of longest proper prefix that's also suffix
 * - Helps determine how much pattern can be reused after mismatch
 * 
 * Phase 2 - Pattern matching:
 * - Two pointers: i for text, j for pattern
 * - Match: advance both pointers
 * - Mismatch: use lps[j-1] to skip redundant comparisons
 * - Return index when full pattern matched
 *
 * <p><b>Time Complexity:</b> O(N + M) - N=haystack, M=needle (LPS O(M) + matching O(N))
 * <br><b>Space Complexity:</b> O(M) - LPS array storage
 */
public class KMPSearch {
    
    /**
     * Finds first occurrence using KMP algorithm.
     */
    public int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        
        if (m == 0) return 0;
        
        int[] lps = calculateLPS(pattern, m);
        int i = 0, j = 0;
        
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            
            if (j == m) {
                return i - j;
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Builds LPS (Longest Prefix Suffix) array.
     */
    private int[] calculateLPS(String pattern, int m) {
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
        
        return lps;
    }
}
