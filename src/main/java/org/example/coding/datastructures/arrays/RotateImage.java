package org.example.coding.datastructures.arrays;

public class RotateImage {

    /**
     * Rotates a given {@code n x n} matrix 90 degrees clockwise in-place.
     *
     * <p><b>Intuition:</b>
     * A 90° clockwise rotation can be achieved without using extra space by
     * first transposing the matrix (swapping rows and columns) and then reversing each row.
     * Transposition moves elements to their rotated positions along the diagonal,
     * and row reversal finalizes the clockwise rotation.
     *
     * <p><b>Approach:</b>
     * <ol>
     *   <li>Transpose the matrix:
     *       <ul>
     *           <li>Swap {@code matrix[i][j]} with {@code matrix[j][i]} for all {@code i > j}.</li>
     *       </ul>
     *   </li>
     *   <li>Reverse each row:
     *       <ul>
     *           <li>Swap elements from both ends of the row, moving toward the center.</li>
     *       </ul>
     *   </li>
     *   <li>This transforms the matrix in-place into its 90° rotated form.</li>
     * </ol>
     *
     * <p><b>Time Complexity:</b> O(n²) — Each element is visited a constant number of times.
     * <b>Space Complexity:</b> O(1) — Rotation is done in-place without extra storage.
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int j = 0, k = n - 1;
            while (j <= k) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][k];
                matrix[i][k] = temp;
                j++;
                k--;
            }
        }
    }

}
