package com.javarena.dsa.algorithms.dynamicProgramming;

/**
 * Longest Palindromic Subsequence
 *
 * <p><b>Problem Statement:</b><br>
 * Given string, find length of longest palindromic subsequence.
 * Subsequence maintains order but need not be contiguous.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Reduce to LCS problem:
 * - LPS of string s = LCS(s, reverse(s))
 * - Characters matching in both directions form palindrome
 * 
 * Alternative direct DP:
 * - dp[i][j] = LPS length in substring s[i...j]
 * - If s[i] == s[j]: dp[i][j] = 2 + dp[i+1][j-1]
 * - Else: dp[i][j] = max(dp[i+1][j], dp[i][j-1])
 * 
 * Build DP table diagonally or in reverse order.
 *
 * <p><b>Time Complexity:</b> O(N²) - N = string length
 * <br><b>Space Complexity:</b> O(N²) for 2D DP
 */
public class LongestPalindromicSubsequence {
    
    /**
     * Finds LPS using LCS approach.
     */
    public int longestPalindromeSubseq(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequence(s, reversed);
    }
    
    /**
     * Helper: Computes LCS of two strings.
     */
    private int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[n][m];
    }
    
    /**
     * Direct DP approach without LCS.
     */
    public int longestPalindromeSubseqDirect(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + (len > 2 ? dp[i + 1][j - 1] : 0);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}
