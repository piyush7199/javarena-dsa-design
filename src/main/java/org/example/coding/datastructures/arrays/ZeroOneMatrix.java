package org.example.coding.datastructures.arrays;

import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {
    /**
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     * The distance between two adjacent cells is 1.
     * <p>
     * -------------------------
     * Intuition:
     * - Cells with value 0 are "sources" (distance = 0).
     * - From each 0, we expand outward using BFS to update the distances of neighboring 1s.
     * - This is a classic "multi-source BFS" problem.
     * <p>
     * Approach:
     * 1. Initialize an answer matrix `ans` of the same size as mat.
     * 2. Push all 0-cells into a queue (multi-source BFS).
     * - Mark their distance as 0 in `ans`.
     * - Mark 1-cells as initially "infinity" (Integer.MAX_VALUE).
     * 3. Perform BFS:
     * - Pop a cell (i, j).
     * - For each of its 4 neighbors (up, down, left, right):
     * - If the neighbor's distance can be improved (greater than current + 1),
     * update it and push it into the queue.
     * 4. Return the filled `ans` matrix.
     * <p>
     * Time Complexity:  O(n * m)
     * - Each cell is enqueued and dequeued at most once.
     * - Each edge (neighbor check) is processed at most once.
     * <p>
     * Space Complexity: O(n * m)
     * - We use an extra matrix `ans` of size n * m.
     * - The queue in the worst case can store all cells.
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
