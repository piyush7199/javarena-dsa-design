package org.example.coding.algorithms.binarySearch;

public class SearchInMatrix {

    /**
     * Searches for a target value in a 2D matrix.
     * The matrix has the following properties:
     * - Integers in each row are sorted in ascending order.
     * - The first integer of each row is greater than the last integer of the previous row.
     * <p>
     * Intuition:
     * - Start from the top-right corner of the matrix.
     * - If the current value is greater than the target, move left (decrease column).
     * - If it's less than the target, move down (increase row).
     * - This works because of the sorted nature of rows and columns.
     * <p>
     * Time Complexity: O(n + m)
     * - Where n is the number of rows and m is the number of columns.
     * - In the worst case, we may traverse at most n rows and m columns.
     * <p>
     * Space Complexity: O(1)
     * - No additional space is used beyond a few variables.
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0;
        int j = m - 1;
        while (i < n && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j -= 1;
            } else {
                i += 1;
            }
        }
        return false;
    }
}
