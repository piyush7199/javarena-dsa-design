package org.example.coding.algorithms.bitManupulation;

public class DivideTwoIntegers {
    /**
     * This method performs integer division between two integers (dividend / divisor)
     * without using multiplication (*), division (/), or modulo (%) operators.
     * <p>
     * Intuition:
     * - Direct subtraction (dividend - divisor repeatedly) would be too slow
     * when numbers are large. Instead, we use **bit manipulation** to speed things up.
     * - We repeatedly double (shift left) the divisor until it is just smaller than the dividend,
     * then subtract it and add the corresponding power of two to the quotient.
     * - This is similar to how long division works, but optimized with bit shifts.
     * <p>
     * Approach:
     * 1. Handle the sign of the result separately. Keep track if the final result
     * should be positive or negative.
     * 2. Convert dividend and divisor to positive values (long type is used to avoid overflow).
     * 3. Use repeated subtraction with bit-shifting:
     * - Find the maximum power of two such that (divisor << k) ≤ dividend.
     * - Subtract (divisor << k) from dividend and add (1 << k) to quotient.
     * - Repeat until dividend < divisor.
     * 4. Handle overflow cases:
     * - If the quotient reaches 2^31, clamp result to Integer.MAX_VALUE or Integer.MIN_VALUE.
     * 5. Return the quotient with the correct sign.
     * <p>
     * Time Complexity:
     * - O(log(N)), where N = dividend.
     * - In each step, we reduce the dividend significantly by subtracting large multiples of divisor.
     * <p>
     * Space Complexity:
     * - O(1), since only a few variables are used regardless of input size.
     */

    public int divide(int dividend, int divisor) {
        boolean sign = true; // true means result is positive
        if (divisor < 0 && dividend >= 0) sign = false;
        if (dividend < 0 && divisor > 0) sign = false;

        // base case
        if (dividend == divisor) return 1;

        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        int qut = 0;

        while (dvd >= dvs) {
            int cnt = 0;
            while (dvd >= (dvs << (cnt + 1))) {
                cnt++;
            }
            qut += 1 << cnt;
            dvd -= dvs << cnt;
        }

        if (qut == (1 << 31) && sign) return Integer.MAX_VALUE;
        if (qut == (1 << 31) && !sign) return Integer.MIN_VALUE;

        return sign ? qut : -qut;
    }
}
