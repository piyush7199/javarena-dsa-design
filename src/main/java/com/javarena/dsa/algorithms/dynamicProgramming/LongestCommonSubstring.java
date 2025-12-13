package com.javarena.dsa.algorithms.dynamicProgramming;

/**
 * Longest Common Substring
 *
 * <p><b>Problem Statement:</b><br>
 * Find length of longest common substring between two strings.
 * Substring must be contiguous (unlike subsequence).
 *
 * <p><b>Intuition & Approach:</b><br>
 * DP with contiguity constraint:
 * - Similar to LCS but substring must be continuous
 * - If characters match: extend current substring length
 * - If don't match: reset to 0 (can't extend non-contiguous)
 * 
 * DP state: dp[i][j] = length of common substring ending at text1[i-1] and text2[j-1]
 * - If text1[i-1] == text2[j-1]: dp[i][j] = 1 + dp[i-1][j-1]
 * - Else: dp[i][j] = 0 (reset)
 * 
 * Answer: maximum value in entire DP table (not just dp[n][m]).
 *
 * <p><b>Time Complexity:</b> O(N × M) - N = len(text1), M = len(text2)
 * <br><b>Space Complexity:</b> O(N × M) for 2D DP, optimizable to O(min(N,M))
 */
public class LongestCommonSubstring {
    
    /**
     * Finds longest common substring using 2D DP.
     */
    public int longestCommonSubstr(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        int maxLength = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        
        return maxLength;
    }
    
    /**
     * Space-optimized version using 1D array.
     */
    public int longestCommonSubstrOptimized(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] prev = new int[m + 1];
        int maxLength = 0;
        
        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                    maxLength = Math.max(maxLength, curr[j]);
                }
            }
            prev = curr;
        }
        
        return maxLength;
    }
}
