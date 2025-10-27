package com.javarena.dsa.algorithms.bitManupulation;

/**
 * 29. Divide Two Integers
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/divide-two-integers/">LeetCode - Divide Two Integers</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Bit Manipulation, Math
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given two integers dividend and divisor, divide two integers without using multiplication, 
 * division, and mod operator. The integer division should truncate toward zero, which means 
 * losing its fractional part. Return the quotient after dividing dividend by divisor.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = 3.33333, truncated to 3.
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = -2.33333, truncated to -2.
 *
 * Input: dividend = -2147483648, divisor = -1
 * Output: 2147483647
 * Explanation: Result would overflow, so clamped to Integer.MAX_VALUE.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Division without operators using bit manipulation:
 * - Direct subtraction (dividend - divisor repeatedly) is too slow for large numbers
 * - Optimization: double the divisor (left shift) until it exceeds dividend
 * - Similar to long division, but using powers of 2
 * - Track how many times we can subtract doubled divisor
 * - Handle sign separately, work with absolute values to avoid overflow
 * - Use long type to prevent overflow during intermediate calculations
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Determine sign of result (negative if signs differ)</li>
 *   <li>Handle base case: if dividend equals divisor, return 1</li>
 *   <li>Convert both to positive long values to avoid overflow</li>
 *   <li>While dividend >= divisor:</li>
 *   <li>- Find max power k such that divisor << (k+1) <= dividend</li>
 *   <li>- Add 2^k to quotient and subtract (divisor << k) from dividend</li>
 *   <li>Handle overflow: if quotient = 2^31, clamp to MAX_VALUE or MIN_VALUE</li>
 *   <li>Apply sign and return result</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(log²n)<br>
 * Outer loop runs log(dividend) times, inner loop runs log(dividend) times per iteration.
 * Each iteration reduces dividend significantly using exponential growth.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses a constant number of variables regardless of input size.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>dividend = divisor: Returns 1</li>
 *   <li>dividend = Integer.MIN_VALUE, divisor = -1: Overflow, return MAX_VALUE</li>
 *   <li>Negative numbers: Handle sign separately</li>
 *   <li>divisor = 1: Returns dividend (or -dividend if negative)</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/power-of-two/">Power of Two</a>
 */
public class DivideTwoIntegers {
    
    /**
     * Divides two integers without using multiplication, division, or modulo.
     *
     * @param dividend the number to be divided
     * @param divisor the number to divide by
     * @return the quotient (truncated toward zero)
     */
    public int divide(int dividend, int divisor) {
        // Step 1: Determine sign of result
        boolean sign = true; // true = positive result
        if (divisor < 0 && dividend >= 0) sign = false;
        if (dividend < 0 && divisor > 0) sign = false;

        // Step 2: Base case - equal numbers
        if (dividend == divisor) return 1;

        // Step 3: Convert to positive long to avoid overflow
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        int qut = 0;

        // Step 4: Repeated subtraction using bit shifts
        while (dvd >= dvs) {
            int cnt = 0;
            
            // Find maximum power of 2 such that (divisor << cnt+1) <= dividend
            while (dvd >= (dvs << (cnt + 1))) {
                cnt++;
            }
            
            // Add 2^cnt to quotient
            qut += 1 << cnt;
            
            // Subtract (divisor << cnt) from dividend
            dvd -= dvs << cnt;
        }

        // Step 5: Handle overflow cases
        if (qut == (1 << 31) && sign) return Integer.MAX_VALUE;
        if (qut == (1 << 31) && !sign) return Integer.MIN_VALUE;

        // Step 6: Apply sign and return
        return sign ? qut : -qut;
    }
}
