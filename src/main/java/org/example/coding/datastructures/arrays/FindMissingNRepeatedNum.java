package org.example.coding.datastructures.arrays;

public class FindMissingNRepeatedNum {
    /**
     * Finds the missing and the repeated value in an n x n grid that contains numbers from 1 to n^2.
     *
     * <p><b>Intuition:</b></p>
     * The grid is supposed to contain all numbers from 1 to n^2 exactly once, but:
     * - One number is missing (say `y`)
     * - One number is repeated (say `x`)
     * <p>
     * Using the mathematical formulas for sum and sum of squares of first `n^2` natural numbers:
     * - Actual sum = 1 + 2 + ... + n^2 = n^2 * (n^2 + 1) / 2
     * - Actual square sum = 1² + 2² + ... + n^2² = n^2 * (n^2 + 1) * (2n^2 + 1) / 6
     * <p>
     * If we subtract the sum and square sum of grid values from these formulas:
     * - We get two equations:
     * 1. x - y = diffSum   (difference in total sum)
     * 2. x² - y² = diffSq  (difference in total square sum)
     * <p>
     * Solving these gives us:
     * x = (diffSum + diffSq / diffSum) / 2
     * y = x - diffSum
     *
     * <p><b>Approach:</b></p>
     * <ol>
     *   <li>Calculate expected sum and square sum for numbers 1 to n^2.</li>
     *   <li>Iterate through the grid and subtract actual numbers and their squares from those sums.</li>
     *   <li>Use the resulting `sum` and `sqSum` to derive two equations and solve for missing and repeated values.</li>
     * </ol>
     *
     * <p><b>Time Complexity:</b> O(n²) – A single pass through the entire grid.</p>
     * <p><b>Space Complexity:</b> O(1) – Only constant extra space used for calculations.</p>
     */
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        long m = n * n;

        // Expected total sum and square sum of numbers from 1 to n^2
        long sum = (m * (m + 1)) / 2;
        long sqSum = (m * (m + 1) * (2 * m + 1)) / 6;

        // Subtract actual values from expected sums
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum -= grid[i][j];
                sqSum -= (long) grid[i][j] * grid[i][j];
            }
        }

        // Let sum = y - x and sqSum = y^2 - x^2 = (y - x)(y + x)
        int eq1 = (int) sum;                // y - x
        int eq2 = (int) (sqSum / sum);     // y + x

        int x = (eq1 + eq2) / 2; // repeated
        int y = (eq2 - eq1) / 2; // missing

        return new int[]{y, x};
    }

}
