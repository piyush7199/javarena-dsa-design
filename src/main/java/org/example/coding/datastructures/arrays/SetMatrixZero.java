package org.example.coding.datastructures.arrays;

public class SetMatrixZero {
    /**
     * Sets entire rows and columns to zero in a given matrix if any element in them is zero.
     *
     * <p><b>Intuition:</b>
     * The problem is: if an element at position (i, j) is zero, then the entire row `i` and
     * the entire column `j` should be set to zero. To avoid modifying the matrix while scanning
     * (which would cause false positives), we first store which rows and columns need to be
     * zeroed, and then update the matrix in a second pass.
     *
     * <p><b>Approach:</b>
     * 1. Create two arrays: `row[]` of size `n` (number of rows) and `col[]` of size `m`
     * (number of columns). These act as markers.
     * 2. First pass:
     * - Iterate through the matrix.
     * - If `matrix[i][j] == 0`, mark `row[i] = -1` and `col[j] = -1`.
     * 3. Second pass:
     * - Iterate through the matrix again.
     * - If the current cell's row or column is marked (-1), set that cell to zero.
     *
     * <p><b>Time Complexity:</b> O(n * m)
     * <ul>
     *   <li>We scan the matrix twice: once for marking and once for setting values.</li>
     *   <li>Both passes are O(n * m), so total time complexity is O(n * m).</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b> O(n + m)
     * <ul>
     *   <li>We use two additional arrays: one of length `n` for rows and one of length `m` for columns.</li>
     *   <li>No extra space proportional to n*m is used.</li>
     * </ul>
     *
     * @param matrix The input 2D array of integers. Modified in-place.
     */
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] row = new int[n];
        int[] col = new int[m];

        // Mark rows and columns that need to be zeroed
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = -1;
                    col[j] = -1;
                }
            }
        }

        // Set cells to zero based on the marked rows and columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (row[i] == -1 || col[j] == -1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
