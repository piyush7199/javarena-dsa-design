package org.example.coding.algorithms.recursionAndBacktracking;

public class PowerOfThree {
    /**
     * Determines if a given integer is a power of three using an iterative approach.
     *
     * <p><b>Intuition:</b>
     * A number is a power of three if it can be divided by 3 repeatedly until it becomes exactly 1,
     * without leaving any remainder in the process.
     *
     * <p><b>Approach:</b>
     * 1. Immediately return {@code false} if {@code n} is non-positive.
     * 2. While {@code n} is divisible by 3, divide it by 3.
     * 3. After the loop, check if {@code n} is exactly 1.
     *
     * <p><b>Time Complexity:</b> O(log₃ n) — Each division by 3 reduces the problem size by a factor of 3.
     * <b>Space Complexity:</b> O(1) — Constant extra memory is used.
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
