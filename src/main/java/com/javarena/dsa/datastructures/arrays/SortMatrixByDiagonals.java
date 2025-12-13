package com.javarena.dsa.datastructures.arrays;

import java.util.Arrays;

/**
 * Sort Matrix Diagonally
 *
 * <p><b>Problem Statement:</b><br>
 * Sort all diagonals of a matrix independently. Each diagonal runs from top-left to bottom-right direction.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Process diagonals starting from first column (left edge)
 * - Process diagonals starting from first row (top edge, except corner)
 * - For each diagonal:
 *   1. Extract elements into temporary array
 *   2. Sort the array
 *   3. Place sorted elements back along diagonal
 * - Diagonals move from (i,j) to (i+k, j+k) direction
 * - Total (m + n - 1) diagonals to process
 *
 * <p><b>Time Complexity:</b> O(M × N × log(min(M,N))) - Sort each diagonal
 * <br><b>Space Complexity:</b> O(min(M,N)) - Temporary array for diagonal
 */
public class SortMatrixByDiagonals {
    /**
     * Sorts all matrix diagonals independently.
     */
    public int[][] sortMatrix(int[][] grid) {

        int m = grid.length;       // number of rows
        int n = grid[0].length;    // number of columns

        // Process diagonals starting from the first column (descending order)
        for (int i = 0; i < m; i++) {
            int[] store = new int[m - i];
            int cnt = 0;

            // Collect diagonal elements starting at (i, 0)
            for (int j = 0; j < n - i; j++) {
                store[cnt] = grid[i + cnt][j];
                cnt++;
            }

            Arrays.sort(store);   // sort ascending
            reverse(store);       // reverse → descending

            cnt = 0;
            // Put back sorted diagonal
            for (int j = 0; j < n - i; j++) {
                grid[i + cnt][j] = store[cnt];
                cnt++;
            }
        }

        // Process diagonals starting from the first row (ascending order)
        for (int i = 0; i < n; i++) {
            int[] store = new int[m - i - 1];
            int cnt = 0;

            // Collect diagonal elements starting at (0, i+1)
            for (int j = i + 1; j < m; j++) {
                store[cnt] = grid[cnt][j];
                cnt++;
            }

            Arrays.sort(store);   // keep ascending
            cnt = 0;

            // Put back sorted diagonal
            for (int j = i + 1; j < m; j++) {
                grid[cnt][j] = store[cnt];
                cnt++;
            }
        }

        return grid;
    }

    /**
     * Helper method to reverse an array in place.
     *
     * @param arr input array to reverse
     */
    private void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
