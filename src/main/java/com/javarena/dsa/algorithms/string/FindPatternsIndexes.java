package com.javarena.dsa.algorithms.string;

import java.util.ArrayList;

/**
 * Find All Pattern Occurrences (KMP Application)
 *
 * <p><b>Problem Statement:</b><br>
 * Given text and pattern, find all starting indices where pattern occurs in text.
 * Return list of 1-indexed positions.
 *
 * <p><b>Intuition & Approach:</b><br>
 * KMP algorithm to find all occurrences:
 * - Build LPS array for pattern
 * - Scan through text with two pointers
 * - When full pattern matched, record index
 * - Continue searching from pattern's LPS position (find overlapping matches)
 * - Don't reset to start after match
 * 
 * Difference from single occurrence search:
 * - After finding match, continue with j = lps[j-1]
 * - Allows finding overlapping patterns (e.g., "aa" in "aaaa")
 * 
 * Returns 1-indexed positions for compatibility with problem format.
 *
 * <p><b>Time Complexity:</b> O(N + M) - N=text length, M=pattern length
 * <br><b>Space Complexity:</b> O(M) - LPS array, O(K) for result where K=matches
 */
public class FindPatternsIndexes {
    
    /**
     * Finds all pattern occurrences in text.
     */
    ArrayList<Integer> search(String pattern, String text) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        
        if (m > n) return result;
        
        // Build LPS array
        int[] lps = calculateLPS(pattern);
        
        int i = 0, j = 0;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            
            if (j == m) {
                // Found match - add 1-indexed position
                result.add(i - j + 1);
                j = lps[j - 1];  // Continue searching for more matches
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Builds LPS array for KMP.
     */
    private int[] calculateLPS(String pattern) {
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
        
        return lps;
    }
}
