package com.javarena.dsa.algorithms.miscellaneous;

/**
 * Divisible and Non-Divisible Sums Difference
 *
 * <p><b>Problem Statement:</b><br>
 * Given positive integers n and m:
 * - num1 = sum of all integers in [1, n] NOT divisible by m
 * - num2 = sum of all integers in [1, n] that ARE divisible by m
 * Return difference num1 - num2.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Mathematical approach using arithmetic series:
 * - Total sum of [1, n] = n × (n+1) / 2
 * - Multiples of m form arithmetic progression: m, 2m, 3m, ..., km
 * - Count of multiples: k = n / m
 * - Sum of multiples = m × (1+2+...+k) = m × k × (k+1) / 2
 * - num1 = totalSum - sumMultiples
 * - num2 = sumMultiples
 * - Difference = totalSum - 2 × sumMultiples
 * 
 * Avoids O(n) iteration by using formulas for O(1) solution.
 *
 * <p><b>Time Complexity:</b> O(1) - Only arithmetic operations
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class DivisibleAndNonDivisibleSumsDifference {
    
    /**
     * Computes difference between non-divisible sum and divisible sum.
     */
    public int differenceOfSums(int n, int m) {
        // Total sum of [1, n]
        int totalSum = n * (n + 1) / 2;
        
        // If m > n, no multiples exist
        if (m > n) return totalSum;
        
        // Count multiples of m in [1, n]
        int count = n / m;
        
        // Sum of multiples: m × (1+2+...+count)
        int sumMultiples = m * count * (count + 1) / 2;
        
        // num1 - num2 = (totalSum - sumMultiples) - sumMultiples
        return totalSum - 2 * sumMultiples;
    }
}
