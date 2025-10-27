package com.javarena.dsa.algorithms.string;

/**
 * 1392. Longest Happy Prefix
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/longest-happy-prefix/">LeetCode - Longest Happy Prefix</a>
 *
 * <p><b>Difficulty:</b> Hard
 *
 * <p><b>Topics:</b> String, KMP Algorithm, String Matching
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * A string is called a happy prefix if it is a non-empty prefix which is also a suffix 
 * (excluding itself). Given a string s, return the longest happy prefix of s. 
 * Return an empty string "" if no such prefix exists.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: s = "level"
 * Output: "l"
 * Explanation: "l" is both prefix and suffix.
 *
 * Input: s = "ababab"
 * Output: "abab"
 * Explanation: "abab" is both prefix and suffix (excluding whole string).
 *
 * Input: s = "leetcodeleet"
 * Output: "leet"
 *
 * Input: s = "a"
 * Output: ""
 * Explanation: No proper prefix-suffix match.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Use KMP's LPS (Longest Prefix Suffix) array:
 * - LPS array is built for KMP pattern matching algorithm
 * - LPS[i] = length of longest proper prefix that is also suffix for s[0...i]
 * - "Proper prefix" means excluding the entire string itself
 * - The last value lps[n-1] gives us the longest happy prefix
 * - This is exactly what KMP preprocessing computes
 * - Extract substring of that length from beginning
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Build LPS array for the input string</li>
 *   <li>Initialize lps[0] = 0 (no proper prefix for single char)</li>
 *   <li>For each position i from 1 to n-1:</li>
 *   <li>- If s[i] matches s[len], extend LPS: lps[i] = ++len</li>
 *   <li>- Else if len > 0, backtrack: len = lps[len-1]</li>
 *   <li>- Else lps[i] = 0</li>
 *   <li>Return substring s[0...lps[n-1]-1]</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n)<br>
 * Single pass to build LPS array, where n is length of string.
 *
 * <p><b>Space Complexity:</b> O(n)<br>
 * Space for LPS array of size n.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Single character: No happy prefix, return ""</li>
 *   <li>No prefix-suffix match: lps[n-1] = 0, return ""</li>
 *   <li>Entire string minus one char: Valid happy prefix</li>
 *   <li>Repeating patterns: LPS captures longest overlap</li>
 * </ul>
 *
 * @see <a href="https://www.geeksforgeeks.org/problems/search-pattern0205/1">KMP Pattern Searching</a>
 */
public class LongestHappyPrefix {
    
    /**
     * Returns the longest proper prefix that is also a suffix.
     *
     * @param pattern the input string
     * @return longest happy prefix, or empty string if none exists
     */
    public String longestPrefix(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        // Step 1: Build LPS array
        int len = 0;  // Length of previous longest prefix suffix
        int i = 1;    // Start from second character

        // Step 2: Compute LPS for each position
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                // Extend current LPS
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    // Mismatch: try previous LPS value
                    len = lps[len - 1];
                } else {
                    // No match: LPS is 0
                    lps[i] = 0;
                    i++;
                }
            }
        }

        // Step 3: Extract happy prefix using last LPS value
        return pattern.substring(0, lps[m - 1]);
    }
}
