package com.javarena.dsa.algorithms.binarySearch;

/**
 * 74. Search a 2D Matrix
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/search-a-2d-matrix/">LeetCode - Search 2D Matrix</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Binary Search, Matrix, Array
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Search for a target in an m×n matrix with properties:
 * - Each row is sorted in ascending order
 * - First integer of each row is greater than last integer of previous row
 * Return true if target exists, false otherwise.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Start from top-right corner: if current > target move left, if current < target move down.
 * This works because rows and columns are sorted.
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Start at top-right: i=0, j=m-1</li>
 *   <li>While in bounds:</li>
 *   <li>- If matrix[i][j] == target: return true</li>
 *   <li>- If matrix[i][j] > target: move left (j--)</li>
 *   <li>- If matrix[i][j] < target: move down (i++)</li>
 *   <li>Return false if not found</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(m + n)<br>
 * <p><b>Space Complexity:</b> O(1)<br>
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Single element matrix</li>
 *   <li>Target at corners</li>
 *   <li>Target smaller/larger than all elements</li>
 * </ul>
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
