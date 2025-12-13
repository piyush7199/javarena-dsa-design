package com.javarena.dsa.datastructures.arrays;

/**
 * Find the Minimum Area to Cover All Ones
 *
 * <p><b>Problem Statement:</b><br>
 * Given a binary grid (0s and 1s), find the minimum rectangular area that covers all cells containing 1.
 *
 * <p><b>Intuition & Approach:</b><br>
 * The smallest rectangle is defined by boundaries:
 * - Topmost row with a 1
 * - Bottommost row with a 1
 * - Leftmost column with a 1
 * - Rightmost column with a 1
 * 
 * Two approaches:
 * 1. With Extra Space O(N+M): Track which rows/cols have 1s, find boundaries
 * 2. Optimal O(1) Space: Scan from edges to find first/last row and column with 1s
 * 
 * Area = (bottomRow - topRow + 1) × (rightCol - leftCol + 1)
 *
 * <p><b>Time Complexity:</b> O(N × M) - Must scan grid to find all 1s
 * <br><b>Space Complexity:</b> O(1) for optimal approach, O(N+M) for first approach
 */
public class FindTheMinimumAreaToCoverAllOnes {
    /**
     * Finds minimum area using extra space for row/col tracking.
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
     * Finds minimum area with O(1) space by scanning from edges.
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
