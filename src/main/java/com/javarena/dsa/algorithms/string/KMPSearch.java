package com.javarena.dsa.algorithms.string;

/**
 * 28. Find the Index of the First Occurrence in a String (KMP Algorithm)
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/">LeetCode - Implement strStr()</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> String, Pattern Matching, KMP Algorithm
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given two strings needle and haystack, return the index of the first occurrence of needle in 
 * haystack, or -1 if needle is not part of haystack. This is the implementation using the 
 * Knuth-Morris-Pratt (KMP) algorithm for optimal O(n+m) time complexity.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6. Return first occurrence: 0.
 *
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode".
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * KMP pattern matching algorithm:
 * - Avoids re-examining characters in text after mismatch
 * - Uses LPS (Longest Prefix Suffix) array for intelligent backtracking
 * - When mismatch occurs, LPS tells us where to resume pattern matching
 * - No need to shift pattern one position at a time
 * - Achieves linear time O(n+m) vs naive O(n×m)
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Build LPS array for the pattern (needle)</li>
 *   <li>Use two pointers: i for text, j for pattern</li>
 *   <li>Match characters: if match, advance both pointers</li>
 *   <li>If full pattern matched (j == m), return starting index</li>
 *   <li>On mismatch:</li>
 *   <li>- If j > 0: set j = lps[j-1] (smart backtrack)</li>
 *   <li>- If j == 0: advance i (no partial match to reuse)</li>
 *   <li>If text exhausted without finding pattern, return -1</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n + m)<br>
 * Where n = length of haystack, m = length of needle.
 * LPS preprocessing O(m), pattern matching O(n).
 *
 * <p><b>Space Complexity:</b> O(m)<br>
 * Space for LPS array of size m.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Empty needle: Return 0 (convention)</li>
 *   <li>Needle longer than haystack: Return -1</li>
 *   <li>No match found: Return -1</li>
 *   <li>Multiple occurrences: Return first one</li>
 * </ul>
 *
 * @see <a href="https://www.geeksforgeeks.org/problems/search-pattern0205/1">Pattern Searching</a>
 */
public class KMPSearch {
    
    /**
     * Finds first occurrence of pattern in text using KMP algorithm.
     *
     * @param text the text to search in (haystack)
     * @param pattern the pattern to find (needle)
     * @return index of first occurrence, or -1 if not found
     */
    public int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        
        // Step 1: Build LPS array for pattern
        int[] lps = calculateLPS(pattern, m);

        // Step 2: Initialize pointers
        int i = 0, j = 0;
        
        // Step 3: Scan through text
        while (i < n) {
            // Characters match
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            
            // Step 4: Full pattern matched
            if (j == m) {
                return i - j;  // Return starting index
            } 
            // Mismatch handling
            else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j > 0) {
                    j = lps[j - 1];  // Smart backtrack using LPS
                } else {
                    i++;  // No reusable prefix, move text pointer
                }
            }
        }

        // Pattern not found
        return -1;
    }

    /**
     * Builds the Longest Prefix Suffix (LPS) array for KMP algorithm.
     *
     * @param pattern the pattern string
     * @param m length of pattern
     * @return LPS array where lps[i] is length of longest proper prefix 
     *         that is also suffix for pattern[0...i]
     */
    private int[] calculateLPS(String pattern, int m) {
        int[] lps = new int[m];
        int len = 0;  // Length of previous longest prefix suffix
        int i = 1;    // lps[0] is always 0

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                // Extend current LPS
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    // Mismatch: try previous LPS
                    len = lps[len - 1];
                } else {
                    // No prefix match
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
