package org.example.coding.algorithms.dynamicProgramming;

public class CountSquareSubmatricesWithAllOnes {

    /**
     * Counts the total number of square submatrices with all 1s in the given binary matrix.
     *
     * <p><b>Intuition:</b><br>
     * A square submatrix of size k x k ending at cell (i, j) exists if:
     * - The cell (i, j) itself is 1, and
     * - The cells directly above (i-1, j), left (i, j-1), and top-left diagonal (i-1, j-1)
     * can form smaller squares.
     * <p>
     * Essentially, the size of the largest square ending at (i, j) is determined by the
     * minimum of these three neighbors plus 1. This ensures that the square is valid and
     * fully filled with 1s.
     *
     * <p><b>Approach:</b><br>
     * - Use dynamic programming (DP) where dp[i][j] represents the size of the largest
     * square submatrix ending at (i, j).
     * - Initialization:
     * - For the first row and first column, dp[i][j] = mat[i][j], since they cannot
     * form larger squares beyond size 1.
     * - Transition:
     * - If mat[i][j] == 1 and i > 0 and j > 0, then:
     * dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
     * - Otherwise, dp[i][j] = mat[i][j]
     * - Accumulate the values of dp[i][j] into a running sum `sm` since each dp[i][j]
     * represents the number of squares ending at (i, j).
     *
     * <p><b>Time Complexity:</b> O(N * M)<br>
     * - Each cell is visited once, and DP computation per cell takes O(1).
     *
     * <p><b>Space Complexity:</b> O(N * M)<br>
     * - A 2D DP table of the same size as the input matrix is used.
     * - Can be optimized to O(M) if only the previous row is stored.
     */
    public int countSquares(int[][] mat) {
        int N = mat.length;
        int M = mat[0].length;
        int[][] dp = new int[N][M];
        int sm = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = mat[i][j];
                } else if (mat[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
                sm += dp[i][j];
            }
        }
        return sm;
    }

}

