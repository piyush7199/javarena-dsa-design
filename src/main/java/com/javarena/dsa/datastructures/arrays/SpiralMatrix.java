package com.javarena.dsa.datastructures.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Spiral Matrix
 *
 * <p><b>Problem Statement:</b><br>
 * Given an m x n matrix, return all elements in spiral order (clockwise from outside to inside).
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Maintain four boundaries: rowStart, rowEnd, colStart, colEnd
 * - Traverse in four directions in order:
 *   1. Left to right along top row (rowStart)
 *   2. Top to bottom along right column (colEnd)
 *   3. Right to left along bottom row (rowEnd) - if rows remain
 *   4. Bottom to top along left column (colStart) - if columns remain
 * - After each direction, shrink corresponding boundary
 * - Continue until boundaries cross
 * - Conditions prevent duplicate traversal of last row/column
 *
 * <p><b>Time Complexity:</b> O(M × N) - Visit each element once
 * <br><b>Space Complexity:</b> O(1) excluding output list
 */
public class SpiralMatrix {
    /**
     * Returns elements in spiral order.
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int colStart = 0;
        int colEnd = matrix[0].length - 1;
        int rowStart = 0;
        int rowEnd = matrix.length - 1;

        while (colEnd >= colStart && rowEnd >= rowStart) {
            // Traverse right
            for (int j = colStart; j <= colEnd; j++) {
                ans.add(matrix[rowStart][j]);
            }
            rowStart++;
            
            // Traverse down
            for (int i = rowStart; i <= rowEnd; i++) {
                ans.add(matrix[i][colEnd]);
            }
            colEnd--;
            
            // Traverse left (if row remains)
            if (rowStart <= rowEnd) {
                for (int j = colEnd; j >= colStart; j--) {
                    ans.add(matrix[rowEnd][j]);
                }
                rowEnd--;
            }
            
            // Traverse up (if column remains)
            if (colEnd >= colStart) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    ans.add(matrix[i][colStart]);
                }
                colStart++;
            }
        }
        return ans;
    }
}
