package org.example.coding.datastructures.arrays;

public class FindTheMinimumAreaToCoverAllOnes {
    /**
     * Finds the minimum rectangular area in a binary grid (0s and 1s)
     * that covers all the cells containing 1.
     * <p>
     * Intuition:
     * - We want the smallest rectangle that contains all the 1s in the grid.
     * - This rectangle can be defined by the minimum and maximum rows/columns that have at least one `1`.
     * - Once we know the topmost row, bottommost row, leftmost column, and rightmost column that contain a `1`,
     * we can calculate the rectangle’s dimensions and area.
     * <p>
     * Approach:
     * 1. Traverse the grid once to mark which rows and columns contain at least one `1`.
     * 2. From the `row[]` array, find the first and last row that contains a `1` → gives the height of the rectangle.
     * 3. From the `col[]` array, find the first and last column that contains a `1` → gives the width of the rectangle.
     * 4. Multiply height × width to get the minimum area.
     * <p>
     * Time Complexity:
     * - O(n * m) for scanning the grid.
     * - O(n) to find top/bottom rows containing `1`.
     * - O(m) to find left/right columns containing `1`.
     * - Total = O(n * m), dominated by the grid traversal.
     * <p>
     * Space Complexity:
     * - O(n + m) for `row[]` and `col[]` arrays.
     */
    public int minimumAreaWithSpace(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] row = new int[n]; // track if each row has a 1
        int[] col = new int[m]; // track if each col has a 1

        // Step 1: Record rows and columns containing 1s
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        // Step 2: Find topmost and bottommost rows with 1s
        int i = 0;
        while (i < n && row[i] == 0) i++;
        int j = n - 1;
        while (j >= i && row[j] == 0) j--;
        int height = j - i + 1;

        // Step 3: Find leftmost and rightmost columns with 1s
        i = 0;
        while (i < m && col[i] == 0) i++;
        j = m - 1;
        while (j >= i && col[j] == 0) j--;
        int width = j - i + 1;

        // Step 4: Return area
        return height * width;
    }

    /**
     * Finds the minimum rectangular area in a binary grid (0s and 1s)
     * that covers all the cells containing 1.
     * <p>
     * Intuition:
     * - The smallest rectangle containing all 1s is defined by:
     * - the topmost row with a 1
     * - the bottommost row with a 1
     * - the leftmost column with a 1
     * - the rightmost column with a 1
     * - Once these boundaries are known, the rectangle area = (height × width).
     * <p>
     * Approach:
     * 1. Scan rows from top → find first row containing a `1` (ri).
     * 2. Scan rows from bottom → find last row containing a `1` (rl).
     * 3. Scan columns from left → find first column containing a `1` (ci).
     * 4. Scan columns from right → find last column containing a `1` (cl).
     * 5. Compute area = (rl - ri + 1) × (cl - ci + 1).
     * <p>
     * Time Complexity:
     * - O(n * m) in the worst case (we may scan almost the entire grid
     * while checking for top/bottom rows and left/right columns).
     * - Still optimal, since we must at least inspect all 1s.
     * <p>
     * Space Complexity:
     * - O(1), since we only use a few variables to store boundaries.
     */
    public int minimumArea(int[][] arr) {
        int rows = arr.length, cols = arr[0].length;
        int ri = 0, rl = 0, ci = 0, cl = 0;
        int flag = 0;

        // Step 1: Find topmost row (ri)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == 1) {
                    ri = i;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) break;
        }

        flag = 0;
        // Step 2: Find bottommost row (rl)
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == 1) {
                    rl = i;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) break;
        }

        flag = 0;
        // Step 3: Find leftmost column (ci)
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (arr[j][i] == 1) {
                    ci = i;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) break;
        }

        flag = 0;
        // Step 4: Find rightmost column (cl)
        for (int i = cols - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                if (arr[j][i] == 1) {
                    cl = i;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) break;
        }

        // Step 5: Compute area
        return (cl - ci + 1) * (rl - ri + 1);
    }

}
