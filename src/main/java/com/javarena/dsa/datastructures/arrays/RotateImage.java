package com.javarena.dsa.datastructures.arrays;

/**
 * Rotate Image (Matrix) 90 Degrees
 *
 * <p><b>Problem Statement:</b><br>
 * Rotate an n x n matrix 90 degrees clockwise in-place.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use two-step transformation:
 * 1. Transpose the matrix: Swap matrix[i][j] with matrix[j][i] for all i > j
 *    - This converts rows to columns
 * 2. Reverse each row: Swap elements from both ends toward center
 *    - This completes the 90° clockwise rotation
 * 
 * Example: [[1,2,3],[4,5,6],[7,8,9]]
 * - After transpose: [[1,4,7],[2,5,8],[3,6,9]]
 * - After row reverse: [[7,4,1],[8,5,2],[9,6,3]] ✓
 * 
 * In-place transformation without extra space
 *
 * <p><b>Time Complexity:</b> O(N²) - Visit each element constant times
 * <br><b>Space Complexity:</b> O(1) - In-place rotation
 */
public class RotateImage {

    /**
     * Rotates matrix 90 degrees clockwise in-place.
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
