package com.javarena.dsa.algorithms.binarySearch;

/**
 * Search in 2D Matrix
 *
 * <p><b>Problem Statement:</b><br>
 * Search for target in m×n matrix where:
 * - Each row is sorted ascending
 * - First integer of each row > last integer of previous row
 * Return true if target exists.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Start from top-right corner:
 * - If current > target: move left (smaller values)
 * - If current < target: move down (larger values)
 * - If current == target: found
 * 
 * This works because:
 * - Moving left decreases values (row sorted)
 * - Moving down increases values (column sorted)
 * - At most m+n moves before out of bounds
 *
 * <p><b>Time Complexity:</b> O(M + N) - M rows, N columns
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class SearchInMatrix {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0, j = m - 1;
        
        while (i < n && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}
