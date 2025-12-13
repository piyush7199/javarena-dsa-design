package com.javarena.dsa.datastructures.arrays;

/**
 * Find Missing and Repeated Values
 *
 * <p><b>Problem Statement:</b><br>
 * Given an n x n grid containing numbers from 1 to n², where one number is missing and one is repeated,
 * return [repeated, missing].
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use mathematical formulas for sum and sum of squares:
 * - Expected sum = n² × (n² + 1) / 2
 * - Expected square sum = n² × (n² + 1) × (2n² + 1) / 6
 * - Subtract actual grid values from expected:
 *   - sum = y - x (missing - repeated)
 *   - sqSum = y² - x² = (y - x)(y + x)
 * - Solve system of equations:
 *   - eq1 = y - x
 *   - eq2 = y + x = sqSum / sum
 *   - x (repeated) = (eq2 - eq1) / 2
 *   - y (missing) = (eq2 + eq1) / 2
 *
 * <p><b>Time Complexity:</b> O(N²) - Single pass through grid
 * <br><b>Space Complexity:</b> O(1) - Only variables for calculations
 */
public class FindMissingNRepeatedNum {
    /**
     * Finds missing and repeated values using mathematical approach.
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
