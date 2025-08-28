package org.example.coding.datastructures.arrays;

import java.util.Arrays;

public class SortMatrixByDiagonals {
    /**
     * Sorts all diagonals of the given square matrix.
     *
     * <p>
     * - Diagonals starting from the first column (left edge) are sorted in
     * descending order.
     * - Diagonals starting from the first row (top edge, except the first element)
     * are sorted in ascending order.
     * </p>
     *
     * <p>
     * <b>Time Complexity:</b> O(m * n * log(min(m, n)))
     * - Each diagonal (up to m+n diagonals) of length ≤ min(m, n) is sorted.
     * - Sorting each diagonal costs O(k log k).
     * </p>
     *
     * <b>Space Complexity:</b> O(min(m, n))
     * - Temporary array used to store each diagonal.
     * </p>
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
