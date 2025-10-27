package com.javarena.dsa.algorithms.miscellaneous;

/**
 * 2894. Divisible and Non-divisible Sums Difference
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/">LeetCode - Divisible Sums Difference</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Math, Number Theory
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * You are given positive integers n and m. Define two integers num1 and num2 as follows:
 * - num1: Sum of all integers in [1, n] that are NOT divisible by m
 * - num2: Sum of all integers in [1, n] that ARE divisible by m
 * Return the difference num1 - num2.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: n = 10, m = 3
 * Output: 19
 * Explanation: 
 * Divisible by 3: {3, 6, 9} → sum = 18
 * Not divisible: {1, 2, 4, 5, 7, 8, 10} → sum = 37
 * Difference: 37 - 18 = 19
 *
 * Input: n = 5, m = 6
 * Output: 15
 * Explanation: No numbers in [1,5] are divisible by 6, so num2 = 0, num1 = 15.
 *
 * Input: n = 5, m = 1
 * Output: -15
 * Explanation: All numbers divisible by 1, so num1 = 0, num2 = 15.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Mathematical approach using arithmetic series formulas:
 * - Total sum of [1, n] = n × (n + 1) / 2 (standard formula)
 * - Numbers divisible by m form arithmetic progression: m, 2m, 3m, ..., km (where k = n/m)
 * - Sum of multiples = m × (1 + 2 + ... + k) = m × k × (k + 1) / 2
 * - num1 = totalSum - sumOfMultiples, num2 = sumOfMultiples
 * - Difference = num1 - num2 = totalSum - 2 × sumOfMultiples
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Calculate total sum of [1, n] using formula: n × (n + 1) / 2</li>
 *   <li>If m > n, no multiples exist, return totalSum</li>
 *   <li>Calculate number of multiples: noOfEle = n / m</li>
 *   <li>Calculate sum of multiples: m × noOfEle × (noOfEle + 1) / 2</li>
 *   <li>Return totalSum - 2 × sumOfMultiples (or equivalently, return as shown)</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(1)<br>
 * Only arithmetic operations, no loops needed.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses a few variables for calculations.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>m > n: No divisible numbers, return total sum</li>
 *   <li>m = 1: All numbers divisible, num1 = 0</li>
 *   <li>n = m: Only one divisible number (m itself)</li>
 *   <li>Large n, small m: Many multiples to subtract</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/sum-multiples/">Sum Multiples</a>
 */
public class DivisibleAndNonDivisibleSumsDifference {
    
    /**
     * Computes difference between sum of non-divisible and divisible numbers.
     *
     * @param n the upper limit of range [1, n]
     * @param m the divisor to check divisibility
     * @return num1 - num2 where num1 is sum of non-divisibles, num2 is sum of divisibles
     */
    public int differenceOfSums(int n, int m) {
        // Step 1: Calculate total sum of [1, n]
        int totalSum = n * (n + 1) / 2;
        
        // Step 2: Quick check - if m > n, no multiples exist
        if (m > n) return totalSum;

        // Step 3: Count multiples of m in [1, n]
        int noOfEle = n / m;
        
        // Step 4: Calculate sum of multiples using arithmetic series formula
        // Multiples: m, 2m, 3m, ..., noOfEle×m
        // Sum = m × (1 + 2 + ... + noOfEle) = m × noOfEle × (noOfEle + 1) / 2
        int sumMultiples = m * noOfEle * (noOfEle + 1) / 2;

        // Step 5: Return difference
        // num1 (non-divisible) = totalSum - sumMultiples
        // num2 (divisible) = sumMultiples
        // Difference = num1 - num2 = totalSum - 2×sumMultiples
        return totalSum - 2 * sumMultiples;
    }
}
