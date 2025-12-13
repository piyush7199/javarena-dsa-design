package com.javarena.dsa.algorithms.miscellaneous;

/**
 * Find Three Consecutive Integers That Sum to Given Number
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer num, return three consecutive integers (sorted array) that sum to num.
 * If impossible, return empty array.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Mathematical pattern for consecutive integers:
 * - Three consecutive: n-1, n, n+1
 * - Sum: (n-1) + n + (n+1) = 3n
 * - Therefore, num must be divisible by 3
 * - Middle number: n = num / 3
 * - Result: [n-1, n, n+1]
 * - If num % 3 != 0, impossible
 * 
 * Simple O(1) solution using algebra.
 *
 * <p><b>Time Complexity:</b> O(1) - Constant time arithmetic
 * <br><b>Space Complexity:</b> O(1) - Fixed-size result array
 */
public class ThreeConsecutiveInt {
    
    /**
     * Finds three consecutive integers summing to num.
     */
    public long[] sumOfThree(long num) {
        // Check if divisible by 3
        if (num % 3 != 0) return new long[]{};
        
        // Calculate middle number
        long n = num / 3;
        
        // Return consecutive integers
        return new long[]{n - 1, n, n + 1};
    }
}
