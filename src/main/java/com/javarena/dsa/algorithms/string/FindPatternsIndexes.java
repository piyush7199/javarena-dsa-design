package com.javarena.dsa.algorithms.string;

import java.util.ArrayList;

/**
 * Pattern Searching - Find All Occurrences Using KMP Algorithm
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://www.geeksforgeeks.org/problems/search-pattern0205/1">GFG - Search Pattern (KMP)</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> String, Pattern Matching, KMP Algorithm
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given a text txt and a pattern pat, find all the start indices of pat in txt using the 
 * Knuth-Morris-Pratt (KMP) algorithm. Return the list of starting indices (0-indexed) where 
 * pat occurs as a substring in txt.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: txt = "AABAACAADAABAABA", pat = "AABA"
 * Output: [0, 9, 12]
 * Explanation: Pattern "AABA" occurs at indices 0, 9, and 12.
 *
 * Input: txt = "ABABABC", pat = "ABA"
 * Output: [0, 2]
 * Explanation: Pattern "ABA" occurs at indices 0 and 2 (overlapping allowed).
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * KMP algorithm avoids redundant comparisons:
 * - Naive approach: Check pattern at every position in text O(n×m)
 * - KMP optimization: Use LPS (Longest Prefix which is also Suffix) array
 * - LPS helps skip unnecessary comparisons after mismatch
 * - When mismatch occurs, jump to lps[j-1] instead of starting from beginning
 * - This works because LPS tells us how much of the pattern we can reuse
 * - Preprocessing pattern takes O(m), searching takes O(n), total O(n+m)
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Preprocess pattern to build LPS array</li>
 *   <li>LPS[i] = length of longest proper prefix of pat[0...i] that is also suffix</li>
 *   <li>Scan through text with two pointers: i (text) and j (pattern)</li>
 *   <li>If characters match: increment both i and j</li>
 *   <li>If full pattern matched (j == m): record index, reset j using LPS</li>
 *   <li>On mismatch: if j > 0, jump to lps[j-1]; else increment i</li>
 *   <li>Return all found indices</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n + m)<br>
 * Where n = length of text, m = length of pattern. LPS construction O(m), matching O(n).
 *
 * <p><b>Space Complexity:</b> O(m)<br>
 * Space for LPS array of size m (pattern length).
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Pattern longer than text: Returns empty list</li>
 *   <li>Empty pattern: Convention-dependent (typically all indices or empty)</li>
 *   <li>Overlapping matches: KMP handles correctly</li>
 *   <li>No matches: Returns empty list</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/">Find First Occurrence</a>
 */
public class FindPatternsIndexes {
    
    /**
     * Finds all occurrences of pattern in text using KMP algorithm.
     *
     * @param pat the pattern to search for
     * @param txt the text to search in
     * @return list of starting indices where pattern occurs
     */
    public ArrayList<Integer> search(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();
        
        // Step 1: Build LPS array for pattern
        int[] lps = calculateLPS(pat, m);
        
        // Step 2: Initialize pointers and result list
        int i = 0, j = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        
        // Step 3: Scan through text
        while (i < n) {
            // Characters match
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;

                // Step 4: Full pattern matched
                if (j == m) {
                    ans.add(i - m);  // Record starting index
                    j = lps[j - 1];  // Continue searching for overlapping matches
                }
            } 
            // Mismatch after some matches
            else if (j > 0) {
                j = lps[j - 1];  // Use LPS to skip unnecessary comparisons
            } 
            // Mismatch at start of pattern
            else {
                i++;  // Move to next character in text
            }
        }
        
        return ans;
    }

    /**
     * Calculates the Longest Prefix Suffix (LPS) array for KMP algorithm.
     * LPS[i] = length of longest proper prefix of pat[0...i] that is also a suffix.
     *
     * @param pat the pattern string
     * @param n length of pattern
     * @return LPS array
     */
    private int[] calculateLPS(String pat, int n) {
        int[] lps = new int[n];
        int i = 1;      // Current position in pattern
        int len = 0;    // Length of previous longest prefix suffix

        // Step 1: LPS[0] is always 0
        // Step 2: Build LPS array
        while (i < n) {
            if (pat.charAt(i) == pat.charAt(len)) {
                // Extend current LPS
                len++;
                lps[i] = len;
                i++;
            } else if (len > 0) {
                // Mismatch: try previous LPS value
                len = lps[len - 1];
            } else {
                // No match at all
                lps[i] = 0;
                i++;
            }
        }
        
        return lps;
    }
}
