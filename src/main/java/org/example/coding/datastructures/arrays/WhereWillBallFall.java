package org.example.coding.datastructures.arrays;

/**
 * <p><b>Problem Intuition:</b></p>
 * Imagine a grid where each cell has a diagonal board:
 * - `1` means the board redirects the ball to the right.
 * - `-1` means the board redirects the ball to the left.
 * <p>
 * The ball starts at the top of a column and moves down row by row.
 * The ball can get stuck if:
 * 1. It hits the boundary of the grid (moving outside the grid).
 * 2. It forms a "V-shape" with two adjacent boards:
 * - Example: cell `(r, c)` has `1` and cell `(r, c + 1)` has `-1`.
 * <p>
 * The goal is to determine for each starting column where the ball ends up.
 * If the ball gets stuck, return -1 for that column.
 *
 * <p><b>Approach:</b></p>
 * - We simulate the ball's movement recursively or iteratively.
 * - Start from each column at the top and move row by row until:
 * - The ball reaches the bottom successfully (return the final column index).
 * - The ball gets stuck (return -1).
 *
 * <p><b>Time Complexity:</b></p>
 * - For each starting column, we potentially traverse all rows once.
 * - There are `m` starting columns and `n` rows.
 * - Total = O(m * n)
 *
 * <p><b>Space Complexity:</b></p>
 * - Recursive stack space = O(n) in the worst case (if recursion goes all the way down).
 * - Output array of size O(m).
 * - Total = O(n + m)
 */
public class WhereWillBallFall {

    /**
     * Recursive helper function to simulate the movement of a single ball.
     *
     * @param grid The input 2D grid with directions.
     * @param row  Current row position of the ball.
     * @param col  Current column position of the ball.
     * @return The final column where the ball exits, or -1 if stuck.
     */
    private static int simulateBall(int[][] grid, int row, int col) {
        // Base case: Ball has reached the bottom of the grid successfully.
        if (row == grid.length) {
            return col;
        }

        // Ball moves right
        if (grid[row][col] == 1) {
            // Check if right movement is possible and valid
            if (col + 1 < grid[0].length && grid[row][col + 1] == 1) {
                return simulateBall(grid, row + 1, col + 1); // move down-right
            } else {
                return -1; // Stuck due to wall or V-shape
            }
        }

        // Ball moves left
        if (grid[row][col] == -1) {
            // Check if left movement is possible and valid
            if (col - 1 >= 0 && grid[row][col - 1] == -1) {
                return simulateBall(grid, row + 1, col - 1); // move down-left
            } else {
                return -1; // Stuck due to wall or V-shape
            }
        }

        // Invalid case (should not happen for valid input)
        return -1;
    }

    /**
     * Finds the final column for each ball starting at the top of every column.
     *
     * @param grid The input 2D grid with boards directing the ball.
     * @return Array where each index i represents the final column for ball i,
     * or -1 if the ball gets stuck.
     */
    public int[] findBall(int[][] grid) {
        int cols = grid[0].length; // Number of columns
        int[] result = new int[cols];

        // Start the simulation for each column at the top row
        for (int col = 0; col < cols; col++) {
            result[col] = simulateBall(grid, 0, col);
        }

        return result;
    }
}
