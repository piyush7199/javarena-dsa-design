package com.javarena.dsa.algorithms.dynamicProgramming;

/**
 * 516. Longest Palindromic Subsequence
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/longest-palindromic-subsequence/">LeetCode - Longest Palindromic Subsequence</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Dynamic Programming, String
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given a string s, find the longest palindromic subsequence's length in s.
 * A subsequence is a sequence that can be derived from another sequence by deleting some
 * or no elements without changing the order of the remaining elements.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb"
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Key insight: A palindrome reads the same forwards and backwards
 * - If we reverse string s to get rev, the longest common subsequence between s and rev
 *   will be the longest palindromic subsequence
 * - This transforms the problem into finding LCS(s, reverse(s))
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Reverse the input string to get rev</li>
 *   <li>Find longest common subsequence between s and rev</li>
 *   <li>Use standard LCS dynamic programming algorithm</li>
 *   <li>Return the result</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(N²)<br>
 * LCS algorithm takes O(N*M) where N = M = length of string
 *
 * <p><b>Space Complexity:</b> O(N²)<br>
 * For the DP table in LCS algorithm
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Single character: Return 1</li>
 *   <li>Empty string: Return 0</li>
 *   <li>All same characters: Return length of string</li>
 * </ul>
 */
public class LongestPalindromicSubsequence {
    
    /**
     * Finds the length of longest palindromic subsequence.
     *
     * @param s input string
     * @return length of longest palindromic subsequence
     */
    public int longestPalindromeSubseq(String s) {
        StringBuilder res = new StringBuilder();
        res.append(s);
        res.reverse();
        return tabularLongestCommonSubsequence(s, res.toString());
    }

    /**
     * Helper method - Standard LCS tabulation algorithm.
     *
     * @param text1 first string
     * @param text2 second string
     * @return length of longest common subsequence
     */
    public int tabularLongestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (text1.charAt(ind1 - 1) == text2.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                } else {
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }
        }
        return dp[n][m];
    }
}
