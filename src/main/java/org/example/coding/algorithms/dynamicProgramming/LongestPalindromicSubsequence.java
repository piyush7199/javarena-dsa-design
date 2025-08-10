package org.example.coding.algorithms.dynamicProgramming;

public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        StringBuilder res = new StringBuilder();
        res.append(s);
        res.reverse();
        return tabularLongestCommonSubsequence(s, res.toString());
    }

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
