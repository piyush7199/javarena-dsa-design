package com.javarena.dsa.datastructures.arrays;

/**
 * Where Will the Ball Fall
 *
 * <p><b>Problem Statement:</b><br>
 * Given a grid where each cell has diagonal board (1 = right, -1 = left), determine where balls starting 
 * from top of each column end up. Ball gets stuck if it hits boundary or forms V-shape with adjacent boards.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Simulate ball movement recursively row by row
 * - For each starting column, trace path down:
 *   - If grid[row][col] == 1: try move right
 *     - Check if grid[row][col+1] also == 1 (valid path)
 *     - If valid, move to (row+1, col+1)
 *     - Otherwise stuck (V-shape or boundary)
 *   - If grid[row][col] == -1: try move left
 *     - Check if grid[row][col-1] also == -1 (valid path)
 *     - If valid, move to (row+1, col-1)
 *     - Otherwise stuck
 * - Base case: reached bottom, return final column
 * - Return -1 if stuck anywhere
 *
 * <p><b>Time Complexity:</b> O(M × N) - M columns, each traces N rows
 * <br><b>Space Complexity:</b> O(N) - Recursive stack depth
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
