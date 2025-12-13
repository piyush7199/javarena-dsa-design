package com.javarena.dsa.algorithms.recursionAndBacktracking;

/**
 * Power of Three
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 * An integer n is a power of three if there exists an integer x such that n == 3^x.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - A number is a power of three if dividing by 3 repeatedly results in exactly 1
 * - Iterative: Keep dividing by 3 while divisible, check if final result is 1
 * - Recursive: Base cases - if n==1 return true, if n<=0 return false, if n%3!=0 return false
 * - Otherwise recursively check n/3
 * - Handle edge cases: negative numbers and 0 are not powers of three
 *
 * <p><b>Time Complexity:</b> O(log₃ N) - Divide by 3 at each step
 * <br><b>Space Complexity:</b> O(1) for iterative, O(log₃ N) for recursive (stack)
 */
public class PowerOfThree {
    /**
     * Iterative approach to check if number is power of three.
     */
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * Determines if a given integer is a power of three using a recursive approach.
     *
     * <p><b>Intuition:</b>
     * Similar to the iterative version — a number is a power of three if repeatedly dividing by 3
     * eventually leads to exactly 1, without leaving a remainder.
     *
     * <p><b>Approach:</b>
     * 1. Immediately return {@code false} if {@code n} is non-positive.
     * 2. If {@code n} is 1, return {@code true} (base case).
     * 3. If {@code n} is divisible by 3, recursively check {@code n / 3}.
     * 4. If none of the above apply, return {@code false}.
     *
     * <p><b>Time Complexity:</b> O(log₃ n) — One recursive call per division by 3.
     * <b>Space Complexity:</b> O(log₃ n) — Due to recursion stack frames.
     */
    public boolean isPowerOfThreeUsingRecur(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 3 == 0) {
            return isPowerOfThreeUsingRecur(n / 3);
        }
        return false;
    }

}
