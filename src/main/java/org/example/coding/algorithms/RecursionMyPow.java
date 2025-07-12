package org.example.coding.algorithms;

public class RecursionMyPow {


    /**
     * Brute-force approach to compute x^n by multiplying x repeatedly.
     * <p>
     * Intuition:
     * - Multiply x, n times.
     * - Handle negative exponents by inverting x and converting n to positive.
     *
     * <p>
     * Time Complexity: O(N) — linear iterations for exponent
     * Space Complexity: O(1) — constant extra space
     */
    public double myPow1(double x, int n) {
        if (n == 0) return 1;
        long N = n > 0 ? n : n * -1;
        x = n > 0 ? x : 1 / x;
        double ans = 1;
        for (int i = 0; i < N; i++) {
            ans *= x;
        }
        return ans;
    }

    /**
     * Recursive fast exponentiation to compute x^n.
     * <p>
     * Intuition:
     * - Use divide-and-conquer:
     * - x^n = (x^2)^(n/2) if n is even
     * - x^n = x * x^(n-1) if n is odd
     * - Handles negative exponents and overflow cases by converting to long.
     * <p>
     * <p>
     * Time Complexity: O(log N) — reduces problem size by half at each step
     * Space Complexity: O(log N) — due to recursive stack
     */

    public double myPow2(double x, int n) {
        if (n == 0) return 1;
        long N = n;
        if (N < 0) {
            N = -N;
            x = 1 / x;
        }
        if (N % 2 == 0) return myPow2(x * x, (int) (N / 2));
        else return x * myPow2(x, (int) (N - 1));
    }

    /**
     * Iterative fast exponentiation to compute x^n.
     * <p>
     * Intuition:
     * - Same logic as recursive version, but avoids recursion.
     * - For every bit in exponent: if bit is 1, multiply result by current x.
     * - Square x and shift exponent to right.
     * - Handles edge case of n = Integer.MIN_VALUE safely.
     * <p>
     * Time Complexity: O(log N) — number of bits in exponent
     * Space Complexity: O(1) — no recursion stack used
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        long exp = n;
        if (n < 0) {
            x = 1 / x;
            exp = -exp;
        }

        double result = 1.0;
        while (exp > 0) {
            if ((exp % 2) == 1) {
                result *= x;
            }
            x *= x;
            exp /= 2;
        }
        return result;
    }
}
