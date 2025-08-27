package org.example.coding.datastructures.graph;

/**
 * Problem Intuition:
 * ------------------
 * Given a binary grid, we want to find the maximum length of a diagonal path
 * that starts from a cell with value 1. The path can move in diagonal directions
 * (↖, ↗, ↘, ↙), and it must follow an alternating sequence of values {1 → 2 → 0 → 2 → 0 ...}.
 * Additionally, at most one direction change (turn) is allowed along the path.
 * <p>
 * Approach:
 * ---------
 * 1. Iterate through every cell in the grid.
 * 2. If the current cell has value 1, it is a potential starting point.
 * 3. For each of the four diagonal directions, run a DFS to:
 * - Continue straight if the next cell matches the required alternating target value.
 * - Optionally turn once (change direction) and continue.
 * 4. Track the maximum diagonal length found across all starting cells.
 * <p>
 * Time Complexity:
 * ----------------
 * - For each cell (m * n), we may explore in 4 directions using DFS.
 * - DFS in the worst case can traverse O(min(m, n)) cells along a diagonal.
 * - Hence, worst case: O(m * n * min(m, n) * 4).
 * <p>
 * Space Complexity:
 * -----------------
 * - O(min(m, n)) recursion depth (DFS stack).
 * - O(1) additional memory for variables.
 * <p>
 * Overall: O(m * n * min(m, n)) time, O(min(m, n)) space.
 */
public class DiagonalPathFinder {

    // Directions representing 4 diagonals: ↖, ↗, ↘, ↙
    private final int[][] dirs = new int[][]{{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};
    private int[][] grid;
    private int m, n;

    /**
     * Finds the maximum length of a valid diagonal path in the grid.
     *
     * @param grid input binary grid
     * @return maximum length of valid diagonal path
     */
    public int lenOfVDiagonal(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;

        int res = 0;

        // Iterate over every cell as a possible starting point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Only start if cell value is 1
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
     * Depth-first search to explore diagonal paths.
     *
     * @param i      current row
     * @param j      current column
     * @param dir    current direction index (0-3)
     * @param target expected value in the next cell (alternates between 2 and 0)
     * @param turned whether we have already turned once
     * @return maximum diagonal path length from this point
     */
    private int dfs(int i, int j, int dir, int target, boolean turned) {
        int x = i + dirs[dir][0];
        int y = j + dirs[dir][1];

        // Out of bounds or mismatch in target value
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target) {
            return 1;
        }

        // Continue straight in the same direction
        int straight = 1 + dfs(x, y, dir, target == 2 ? 0 : 2, turned);

        // Optionally take a turn (only once allowed)
        int turn = 0;
        if (!turned) {
            turn = 1 + dfs(x, y, (dir + 1) % 4, target == 2 ? 0 : 2, true);
        }

        // Return the max of straight path vs turned path
        return Math.max(straight, turn);
    }
}
