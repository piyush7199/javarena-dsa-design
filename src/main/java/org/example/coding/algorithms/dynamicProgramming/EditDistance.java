package org.example.coding.algorithms.dynamicProgramming;

import java.util.Arrays;

public class EditDistance {

    /**
     * Brute force recursive solution to compute the minimum edit distance
     * (Levenshtein distance) between two strings.
     * <p>
     * Intuition:
     * - At each step, we try all three operations (insert, delete, replace).
     * - If characters match, we just move both pointers.
     * - Otherwise, we recursively compute costs of:
     * 1. Insert → stay on i, move j-1
     * 2. Delete → move i-1, stay on j
     * 3. Replace → move both i-1 and j-1
     * - The result is the minimum of these operations.
     * <p>
     * Time Complexity: O(3^(min(n, m)))
     * - Exponential, since at each step we branch into 3 recursive calls.
     * <p>
     * Space Complexity: O(n + m)
     * - Due to recursion stack depth.
     */
    public int minDistanceBruteForce(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        return helperBruteForce(word1, word2, n, m);
    }

    private int helperBruteForce(String word1, String word2, int i, int j) {
        // If second string is empty, we must delete all i characters
        if (j == 0) return i;
        // If first string is empty, we must insert all j characters
        if (i == 0) return j;

        // If last characters match, move both pointers
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            return helperBruteForce(word1, word2, i - 1, j - 1);
        }

        // Try insert, delete, and replace
        int insert = 1 + helperBruteForce(word1, word2, i, j - 1);
        int delete = 1 + helperBruteForce(word1, word2, i - 1, j);
        int replace = 1 + helperBruteForce(word1, word2, i - 1, j - 1);

        return Math.min(insert, Math.min(delete, replace));
    }

    /**
     * Memoized recursive solution (Top-down DP).
     * <p>
     * Intuition:
     * - Same as brute force, but we cache results to avoid recomputation.
     * - This significantly reduces exponential recursion.
     * <p>
     * Time Complexity: O(n * m)
     * - Each subproblem (i, j) is computed once.
     * <p>
     * Space Complexity: O(n * m)
     * - For the DP table.
     * - O(n + m) recursion stack depth.
     */
    public int minDistanceMemoization(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        // Initialize dp array with -1 (uncomputed states)
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helperMemoization(word1, word2, n, m, dp);
    }

    private int helperMemoization(String word1, String word2, int i, int j, int[][] dp) {
        if (j == 0) return i; // Need i deletions
        if (i == 0) return j; // Need j insertions

        // Already computed
        if (dp[i][j] != -1) return dp[i][j];

        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            return dp[i][j] = helperMemoization(word1, word2, i - 1, j - 1, dp);
        }

        int insert = 1 + helperMemoization(word1, word2, i, j - 1, dp);
        int delete = 1 + helperMemoization(word1, word2, i - 1, j, dp);
        int replace = 1 + helperMemoization(word1, word2, i - 1, j - 1, dp);

        return dp[i][j] = Math.min(insert, Math.min(delete, replace));
    }

    /**
     * Bottom-up dynamic programming solution.
     * <p>
     * Intuition:
     * - Build a DP table where dp[i][j] represents minimum edits
     * to convert word1[0..i-1] → word2[0..j-1].
     * - Base cases:
     * - dp[0][j] = j (need j insertions)
     * - dp[i][0] = i (need i deletions)
     * - Transition:
     * - If characters match → dp[i][j] = dp[i-1][j-1]
     * - Else → dp[i][j] = 1 + min(insert, delete, replace)
     * <p>
     * Time Complexity: O(n * m)
     * - Fill entire DP table once.
     * <p>
     * Space Complexity: O(n * m)
     * - For the DP table.
     * - Can be optimized to O(min(n, m)) using rolling arrays.
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        // Fill DP table
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    dp[i][j] = j; // Need j insertions
                } else if (j == 0) {
                    dp[i][j] = i; // Need i deletions
                } else {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1]; // No operation
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i][j - 1], // Insert
                                Math.min(dp[i - 1][j],   // Delete
                                        dp[i - 1][j - 1])); // Replace
                    }
                }
            }
        }

        return dp[n][m];
    }

    /**
     * Space optimized DP solution using rolling array.
     * <p>
     * Intuition:
     * - We only need the previous row to compute the current row.
     * - Use two arrays: prev[] and curr[] of size m+1.
     * - After processing one row, move curr → prev.
     * <p>
     * Time Complexity: O(n * m)
     * Space Complexity: O(m)   (where m = length of smaller string)
     */
    public int minDistanceRollingArray(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // Always use smaller string for columns to save space
        if (m > n) return minDistanceRollingArray(word2, word1);

        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        // Base case: converting empty word1 to word2 (insertions)
        for (int j = 0; j <= m; j++) {
            prev[j] = j;
        }

        // Fill DP row by row
        for (int i = 1; i <= n; i++) {
            curr[0] = i; // converting word1[0..i-1] to empty → i deletions
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1]; // characters match, no edit
                } else {
                    curr[j] = 1 + Math.min(
                            curr[j - 1],            // Insert
                            Math.min(prev[j],       // Delete
                                    prev[j - 1])   // Replace
                    );
                }
            }
            // Move current row to previous row
            int[] temp = prev;
            prev = curr;
            curr = temp; // Reuse array (avoid reallocation)
        }

        return prev[m];
    }

}
