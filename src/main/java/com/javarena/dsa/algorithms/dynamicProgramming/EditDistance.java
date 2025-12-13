package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.Arrays;

/**
 * Edit Distance (Levenshtein Distance)
 *
 * <p><b>Problem Statement:</b><br>
 * Find minimum number of operations (insert, delete, replace) to convert word1 to word2.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Character-by-character comparison with three operations:
 * 
 * - If characters match: no operation needed, move both pointers
 * - If don't match, try all three operations:
 *   1. Insert: stay on i, move j-1 (add char to match word2[j])
 *   2. Delete: move i-1, stay on j (remove char from word1)
 *   3. Replace: move both i-1, j-1 (replace word1[i] with word2[j])
 * - Take minimum of three operations + 1
 * 
 * Base cases:
 * - If word1 empty (i=0): need j insertions
 * - If word2 empty (j=0): need i deletions
 * 
 * DP state: dp[i][j] = min operations to convert first i chars to first j chars
 *
 * <p><b>Time Complexity:</b> O(N × M) - N = len(word1), M = len(word2)
 * <br><b>Space Complexity:</b> O(N × M) for 2D DP table
 */
public class EditDistance {

    /**
     * Brute force recursive solution.
     */
    public int minDistanceBruteForce(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        return helperBruteForce(word1, word2, n, m);
    }

    private int helperBruteForce(String word1, String word2, int i, int j) {
        if (j == 0) return i;
        if (i == 0) return j;

        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            return helperBruteForce(word1, word2, i - 1, j - 1);
        }

        int insert = 1 + helperBruteForce(word1, word2, i, j - 1);
        int delete = 1 + helperBruteForce(word1, word2, i - 1, j);
        int replace = 1 + helperBruteForce(word1, word2, i - 1, j - 1);

        return Math.min(insert, Math.min(delete, replace));
    }

    /**
     * Memoization approach.
     */
    public int minDistanceMemo(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        
        return helperMemo(word1, word2, n, m, dp);
    }

    private int helperMemo(String word1, String word2, int i, int j, int[][] dp) {
        if (j == 0) return i;
        if (i == 0) return j;
        if (dp[i][j] != -1) return dp[i][j];

        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            return dp[i][j] = helperMemo(word1, word2, i - 1, j - 1, dp);
        }

        int insert = 1 + helperMemo(word1, word2, i, j - 1, dp);
        int delete = 1 + helperMemo(word1, word2, i - 1, j, dp);
        int replace = 1 + helperMemo(word1, word2, i - 1, j - 1, dp);

        return dp[i][j] = Math.min(insert, Math.min(delete, replace));
    }

    /**
     * Tabulation approach - most efficient.
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];
                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        return dp[n][m];
    }
}
