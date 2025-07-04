package org.example.coding.miscellaneous;

public class ReverseInteger {

    /**
     * Reverses digits of a 32-bit signed integer.
     *
     * <p>🔍 Intuition:
     * - Extract digits one by one using modulo (`x % 10`).
     * - Build the reversed number using `rev = rev * 10 + pop`.
     * - To prevent overflow beyond 32-bit signed integer limits, we perform checks before multiplying and adding.
     * - Integer.MAX_VALUE =  2147483647 → last digit 7
     * - Integer.MIN_VALUE = -2147483648 → last digit -8
     * - If overflow is about to occur, return 0 immediately.
     * <p>
     * ⏱ Time Complexity: O(log₁₀x) — We process each digit once.
     * 🧠 Space Complexity: O(1) — Constant space usage.
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (
                    rev > Integer.MAX_VALUE / 10 ||
                            (rev == Integer.MAX_VALUE / 10 && pop > 7)
            ) return 0;
            if (
                    rev < Integer.MIN_VALUE / 10 ||
                            (rev == Integer.MIN_VALUE / 10 && pop < -8)
            ) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
