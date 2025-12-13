package com.javarena.dsa.datastructures.graph;

/**
 * Maximum Length of Valid Diagonal Path
 *
 * <p><b>Problem Statement:</b><br>
 * Find maximum length diagonal path in binary grid starting from cell with value 1.
 * Path moves diagonally (↖, ↗, ↘, ↙) following alternating sequence {1 → 2 → 0 → 2 → 0 ...}.
 * At most one direction change allowed.
 *
 * <p><b>Intuition & Approach:</b><br>
 * DFS exploration with constraints:
 * - Start from every cell with value 1
 * - Explore 4 diagonal directions
 * - Must alternate between values: 1 → 2 → 0 → 2 → 0...
 * - Track if already turned (max 1 turn allowed)
 * - For each cell, try:
 *   1. Continue straight in same direction
 *   2. Turn once (if not already turned)
 * - Return maximum path length found
 * 
 * Key: DFS with state (position, direction, target, turned).
 *
 * <p><b>Time Complexity:</b> O(M×N×min(M,N)) - Each cell, 4 directions, diagonal length
 * <br><b>Space Complexity:</b> O(min(M,N)) - Recursion depth
 */
public class DiagonalPathFinder {

    // Directions: ↖, ↗, ↘, ↙
    private final int[][] dirs = new int[][]{{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};
    private int[][] grid;
    private int m, n;

    /**
     * Finds maximum length of valid diagonal path.
     */
    public int lenOfVDiagonal(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;

        int res = 0;

        // Try every cell as starting point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, 1);
                    // Explore all 4 diagonal directions
                    for (int d = 0; d < 4; d++) {
                        res = Math.max(res, dfs(i, j, d, 2, false));
                    }
                }
            }
        }
        return res;
    }

    /**
     * DFS to explore diagonal paths with alternating values.
     *
     * @param i current row
     * @param j current column
     * @param dir current direction (0-3)
     * @param target expected next value (alternates 2 ↔ 0)
     * @param turned whether already turned once
     * @return maximum path length from this point
     */
    private int dfs(int i, int j, int dir, int target, boolean turned) {
        int x = i + dirs[dir][0];
        int y = j + dirs[dir][1];

        // Out of bounds or wrong value
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target) {
            return 1;
        }

        // Continue straight
        int straight = 1 + dfs(x, y, dir, target == 2 ? 0 : 2, turned);

        // Try turning (if not already turned)
        int turn = 0;
        if (!turned) {
            turn = 1 + dfs(x, y, (dir + 1) % 4, target == 2 ? 0 : 2, true);
        }

        return Math.max(straight, turn);
    }
}
