package org.example.coding.datastructures.arrays;

public class MinimumOperationsToMakeArrayElementsZero {
    /**
     * Solves the problem of reducing all numbers in given ranges [l, r] to zero
     * using a defined operation where two numbers a and b are replaced by
     * floor(a/4) and floor(b/4).
     *
     * <p><b>Intuition:</b>
     * Each number can be independently reduced to zero by repeatedly applying
     * floor(x/4). The total steps required for all numbers in a range can be
     * computed using a mathematical approach without explicitly creating the array.
     * Since each operation processes two numbers, the final minimum operations are
     * ceil(totalSteps / 2).
     *
     * <p><b>Approach:</b>
     * <ol>
     *   <li>Create a helper function {@code stepsToZero(x)} to compute how many steps it
     *       takes to reduce a single number x to zero using floor(x/4).</li>
     *   <li>Create a function {@code prefixSteps(n)} to calculate total steps for all numbers
     *       from 1 to n.</li>
     *   <li>For a query [l, r], the total steps required are:
     *       totalSteps = prefixSteps(r) - prefixSteps(l-1).</li>
     *   <li>The minimum operations for this query = ceil(totalSteps / 2).</li>
     *   <li>Sum up the result for all queries.</li>
     * </ol>
     *
     * <p><b>Complexity Analysis:</b>
     * <ul>
     *   <li><b>Brute Force:</b>
     *       <ul>
     *         <li>Time Complexity: O(Σ(r-l+1) * log(max(r)))</li>
     *         <li>Space Complexity: O(r-l+1)</li>
     *       </ul>
     *   </li>
     *   <li><b>Optimized Approach:</b>
     *       <ul>
     *         <li>Time Complexity: O(queries.length * log(max(r)))</li>
     *         <li>Space Complexity: O(1)</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * @param queries 2D array where each query is of the form [l, r].
     * @return The sum of minimum operations required for all queries.
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
