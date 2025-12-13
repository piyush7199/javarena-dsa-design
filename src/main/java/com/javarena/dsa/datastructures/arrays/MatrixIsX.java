package com.javarena.dsa.datastructures.arrays;

/**
 * Check if Matrix Is X-Matrix
 *
 * <p><b>Problem Statement:</b><br>
 * A square matrix is an X-Matrix if both of the following are true:
 * 1. All elements on diagonals are non-zero
 * 2. All other elements are zero
 * The diagonals are main diagonal (i == j) and anti-diagonal (i + j == n - 1).
 *
 * <p><b>Intuition & Approach:</b><br>
 * - First, validate all diagonal elements are non-zero:
 *   - Main diagonal: grid[i][i]
 *   - Anti-diagonal: grid[i][n-1-i]
 *   - Use two pointers moving from top-left and top-right
 * - Second, validate all non-diagonal elements are zero:
 *   - Skip cells where k == l (main diagonal)
 *   - Skip cells where n - k - 1 == l (anti-diagonal)
 *   - All others must be 0
 *
 * <p><b>Time Complexity:</b> O(N²) - Check all matrix elements
 * <br><b>Space Complexity:</b> O(1) - Only loop variables
 */
public class MatrixIsX {
    /**
     * Checks if matrix is a valid X-Matrix.
     */
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        int i = 0;
        int j = n - 1;
        
        // Check diagonal elements are non-zero
        for (int k = 0; k < n; k++) {
            if (grid[i][k] == 0 || grid[j][k] == 0) return false;
            i++;
            j--;
        }
        
        // Check non-diagonal elements are zero
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                if (k == l) continue;  // Main diagonal
                if (n - k - 1 == l) continue;  // Anti-diagonal
                if (grid[k][l] != 0) return false;
            }
        }

        return true;
    }
}
