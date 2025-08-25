package org.example.coding.datastructures.arrays;

public class DiagonalTraverse {
    /**
     * Problem: Diagonal Traverse (LeetCode 498)
     * <p>
     * Intuition:
     * -----------
     * We want to visit all elements of the matrix in a diagonal order:
     * - Start at the top-left (0,0).
     * - Each diagonal is formed by elements where (row + col) is constant.
     * - The tricky part is that the traversal alternates direction:
     * * One diagonal goes from bottom-left to top-right.
     * * The next diagonal goes from top-right to bottom-left.
     * <p>
     * Approach:
     * -----------
     * 1. Start from the top row (row = 0, col = 0 → m-1).
     * - For each starting cell, traverse the diagonal downward (i++, j--).
     * 2. Then continue from the rightmost column (col = m-1, row = 1 → n-1).
     * - Again traverse diagonals downward (i++, j--).
     * 3. Each diagonal is collected in order.
     * 4. To maintain the zig-zag pattern:
     * - Use a boolean `reverse` flag to decide whether to reverse the diagonal’s order.
     * - Reverse the collected segment of `ans` when needed.
     * <p>
     * Complexity:
     * ------------
     * - Time Complexity: O(n * m)
     * * Each element is visited exactly once.
     * * Reversing diagonals costs at most O(n+m) total across the run (still linear).
     * <p>
     * - Space Complexity: O(1) extra
     * * Aside from the output array, we use only constant extra variables.
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        boolean reverse = true;  // toggles after every diagonal
        int ind = 0;             // position in the result array
        int[] ans = new int[n * m];

        // Traverse diagonals starting from the first row
        for (int j = 0; j < m; j++) {
            int len = traverse(0, j, ind, mat, ans);   // collect diagonal
            if (reverse) {
                reverse(ind, ind + len - 1, ans);      // reverse if needed
            }
            ind += len;
            reverse = !reverse;  // toggle direction
        }

        // Traverse diagonals starting from the last column
        for (int i = 1; i < n; i++) {
            int len = traverse(i, m - 1, ind, mat, ans);
            if (reverse) {
                reverse(ind, ind + len - 1, ans);
            }
            ind += len;
            reverse = !reverse;
        }
        return ans;
    }

    /**
     * Reverses the subarray ans[i..j] in place.
     */
    private void reverse(int i, int j, int[] ans) {
        while (i < j) {
            int temp = ans[i];
            ans[i] = ans[j];
            ans[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * Traverses one diagonal starting from (i, j) and
     * fills it into ans[] starting at position ind.
     */
    private int traverse(int i, int j, int ind, int[][] mat, int[] ans) {
        int n = mat.length;
        int m = mat[0].length;
        int cnt = 0;

        // Move diagonally down-left (i++, j--)
        while (i < n && j >= 0) {
            ans[ind + cnt] = mat[i][j];
            cnt++;
            i++;
            j--;
        }
        return cnt;
    }

    /**
     * Problem: Diagonal Traverse (LeetCode 498)
     * <p>
     * Intuition:
     * -----------
     * We want to visit all elements of the matrix in a zig-zag diagonal order.
     * Instead of explicitly grouping diagonals, we can simulate the traversal
     * by maintaining the current position (row, col) and a direction flag (up or down).
     * <p>
     * Approach:
     * -----------
     * 1. Start at (0,0).
     * 2. Maintain a boolean `up`:
     * - If `up == true`, we move up-right (row--, col++).
     * - If `up == false`, we move down-left (row++, col--).
     * 3. When we hit a boundary (first row, last row, first col, last col):
     * - Switch direction.
     * - Adjust row/col to move along the boundary correctly.
     * 4. Continue until all elements are added.
     * <p>
     * Complexity:
     * ------------
     * - Time Complexity: O(m * n)
     * * Each element is visited exactly once.
     * <p>
     * - Space Complexity: O(1) extra
     * * Output array is required, but no extra data structures are used.
     */
    public int[] findDiagonalOrderOptimal(int[][] mat) {
        // Edge case: empty matrix
        if (mat.length == 0 || mat[0].length == 0) {
            return new int[0];
        }

        int m = mat.length, n = mat[0].length; // Rows (m) x Columns (n)
        int row = 0, col = 0; // Current position in matrix
        int index = 0;        // Position in result array
        int[] arr = new int[m * n];
        boolean up = true;    // Direction flag: true = upward, false = downward

        // Traverse all elements
        while (index < m * n) {
            arr[index++] = mat[row][col]; // Store current element

            if (up) { // Moving upward (↗)
                if (col == n - 1) {   // Reached the last column → move down
                    row++;
                    up = false;
                } else if (row == 0) { // Reached the first row → move right
                    col++;
                    up = false;
                } else { // Normal upward move
                    row--;
                    col++;
                }
            } else { // Moving downward (↙)
                if (row == m - 1) {   // Reached the last row → move right
                    col++;
                    up = true;
                } else if (col == 0) { // Reached the first column → move down
                    row++;
                    up = true;
                } else { // Normal downward move
                    row++;
                    col--;
                }
            }
        }
        return arr;
    }


}
