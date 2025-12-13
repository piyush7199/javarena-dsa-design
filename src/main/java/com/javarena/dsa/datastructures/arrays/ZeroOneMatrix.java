package com.javarena.dsa.datastructures.arrays;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 01 Matrix - Distance to Nearest Zero
 *
 * <p><b>Problem Statement:</b><br>
 * Given an m x n binary matrix, return the distance of the nearest 0 for each cell.
 * Distance between adjacent cells is 1.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Classic multi-source BFS problem:
 * - Cells with 0 are "sources" (distance = 0)
 * - From all 0s simultaneously, expand outward using BFS
 * - Update distances of neighboring 1s layer by layer
 * 
 * Steps:
 * 1. Initialize result matrix, mark 0-cells with distance 0
 * 2. Mark 1-cells as infinity (unvisited)
 * 3. Add all 0-cells to queue for multi-source BFS
 * 4. For each cell in queue, check 4 neighbors (up/down/left/right)
 * 5. If neighbor distance can be improved (current + 1 < neighbor), update and enqueue
 * 6. Continue until queue empty
 *
 * <p><b>Time Complexity:</b> O(M × N) - Each cell processed once
 * <br><b>Space Complexity:</b> O(M × N) - Result matrix + queue
 */
public class ZeroOneMatrix {

    /**
     * Finds distance to nearest 0 for each cell using multi-source BFS.
     */
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> q = new LinkedList<>();
        int n = mat.length;
        int m = mat[0].length;
        int[][] ans = new int[n][m];

        // Step 1: Initialize queue with all zero-cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[]{i, j}); // distance 0 sources
                } else {
                    ans[i][j] = Integer.MAX_VALUE; // unvisited "infinity"
                }
            }
        }

        // Directions for BFS traversal (up, right, down, left)
        int[] nRow = {-1, 0, 1, 0};
        int[] nCol = {0, 1, 0, -1};

        // Step 2: Multi-source BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int i = cur[0], j = cur[1];

            // Explore all 4 neighbors
            for (int k = 0; k < 4; k++) {
                int row = i + nRow[k];
                int col = j + nCol[k];

                // Check bounds and if we can relax the distance
                if (row >= 0 && col >= 0 && row < n && col < m &&
                        ans[row][col] > ans[i][j] + 1) {

                    ans[row][col] = ans[i][j] + 1; // update distance
                    q.offer(new int[]{row, col}); // push updated neighbor
                }
            }
        }

        return ans;
    }


}
