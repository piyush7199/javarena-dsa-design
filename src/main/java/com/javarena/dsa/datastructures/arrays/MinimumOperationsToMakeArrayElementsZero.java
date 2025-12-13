package com.javarena.dsa.datastructures.arrays;

/**
 * Minimum Operations to Make Array Elements Zero
 *
 * <p><b>Problem Statement:</b><br>
 * Given queries [l, r], reduce all numbers in range to zero using operation: replace two numbers a, b 
 * with floor(a/4) and floor(b/4). Find total minimum operations for all queries.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Each number x can be reduced to 0 by repeatedly applying floor(x/4)
 * - Calculate steps needed for single number: divide by 4 until 0
 * - Use prefix sum approach: prefixSteps(n) = total steps for [1, n]
 * - For query [l, r]: totalSteps = prefixSteps(r) - prefixSteps(l-1)
 * - Since each operation handles 2 numbers: minOps = ceil(totalSteps/2)
 * - Sum results for all queries
 * - Avoids creating arrays, uses mathematical computation
 *
 * <p><b>Time Complexity:</b> O(Q × log(max(R))) where Q = queries, R = max range value
 * <br><b>Space Complexity:</b> O(1) - No extra arrays needed
 */
public class MinimumOperationsToMakeArrayElementsZero {
    /**
     * Calculates minimum operations for all queries.
     */
    public long minOperations(int[][] queries) {
        long totalOperations = 0;
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            // total steps for this query
            long totalSteps = prefixSteps(r) - prefixSteps(l - 1);

            // Each operation handles 2 numbers, so ceil(totalSteps/2)
            totalOperations += (totalSteps + 1) / 2;
        }
        return totalOperations;
    }

    /**
     * Helper method to compute total steps required to reduce all numbers
     * from 1 to n to zero.
     *
     * @param n the upper bound of the range
     * @return total steps for range [1, n]
     */
    private long prefixSteps(long n) {
        if (n <= 0) return 0;
        long total = 0;
        for (long i = 1; i <= n; i++) {
            total += stepsToZero(i);
        }
        return total;
    }

    /**
     * Computes the number of steps to reduce a single number x to zero
     * by repeatedly applying floor(x/4).
     *
     * @param x the number to reduce
     * @return steps required to reduce x to zero
     */
    private int stepsToZero(long x) {
        int steps = 0;
        while (x > 0) {
            x /= 4;
            steps++;
        }
        return steps;
    }
}
