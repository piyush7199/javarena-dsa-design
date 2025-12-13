package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Divide Two Integers
 *
 * <p><b>Problem Statement:</b><br>
 * Divide two integers without using multiplication, division, or mod operator.
 * Integer division truncates toward zero. Return quotient.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Division using bit manipulation (binary search on powers of 2):
 * - Repeated subtraction is too slow
 * - Optimization: double divisor (left shift) until it exceeds dividend
 * - Similar to long division with powers of 2
 * - For each power k where (divisor << k) ≤ dividend:
 *   - Add 2^k to quotient
 *   - Subtract (divisor << k) from dividend
 * 
 * Handle overflow:
 * - Use long to prevent intermediate overflow
 * - Special case: -2^31 / -1 = 2^31 (overflow to MAX_VALUE)
 * - Work with absolute values, apply sign at end
 *
 * <p><b>Time Complexity:</b> O(log²N) - Log iterations, each finds log bits
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class DivideTwoIntegers {
    
    /**
     * Divides two integers using bit manipulation.
     */
    public int divide(int dividend, int divisor) {
        if (dividend == divisor) return 1;
        
        boolean isPositive = (dividend > 0) == (divisor > 0);
        
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        long ans = 0;
        
        while (a >= b) {
            int q = 0;
            while (a >= (b << (q + 1))) {
                q++;
            }
            ans += (1L << q);
            a -= (b << q);
        }
        
        if (ans == (1L << 31) && isPositive) {
            return Integer.MAX_VALUE;
        }
        
        return isPositive ? (int) ans : (int) -ans;
    }
}
