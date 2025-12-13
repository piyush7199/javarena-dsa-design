package com.javarena.dsa.algorithms.dynamicProgramming;

/**
 * Longest Common Substring
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://www.geeksforgeeks.org/problems/longest-common-substring">GFG - Longest Common Substring</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Dynamic Programming, String
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given two strings text1 and text2, return the length of their longest common substring.
 * A substring is a contiguous sequence of characters within a string.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: text1 = "abcde", text2 = "abfce"
 * Output: 2
 * Explanation: The longest common substring is "ab" with length 2
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Unlike longest common subsequence, substring requires consecutive characters
 * - Use DP where dp[i][j] represents length of common substring ending at i and j
 * - If characters match, extend from previous diagonal: dp[i][j] = 1 + dp[i-1][j-1]
 * - If they don't match, reset to 0 (streak breaks)
 * - Track maximum length found during the process
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Create 2D DP table of size (n+1) x (m+1)</li>
 *   <li>Initialize maxLen to track the longest substring found</li>
 *   <li>For each cell, if characters match: dp[i][j] = 1 + dp[i-1][j-1]</li>
 *   <li>If they don't match: dp[i][j] = 0 (reset streak)</li>
 *   <li>Update maxLen whenever a longer substring is found</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(N*M)<br>
 * We fill an N x M table once
 *
 * <p><b>Space Complexity:</b> O(N*M)<br>
 * For the DP table (can be optimized to O(min(N,M)) using rolling array)
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Empty strings: Return 0</li>
 *   <li>No common substring: Return 0</li>
 *   <li>Entire string is common: Return min(n, m)</li>
 * </ul>
 */
public class LongestCommonSubstring {

    /**
     * Finds the length of longest common substring using dynamic programming.
     *
     * @param text1 first string
     * @param text2 second string
     * @return length of longest common substring
     */
    public int longestCommonSubstr(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0; // reset streak
                }
            }
        }

        return maxLen;
    }
}
