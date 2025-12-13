package com.javarena.dsa.datastructures.string;

/**
 * String to Integer (atoi)
 *
 * <p><b>Problem Statement:</b><br>
 * Convert string to 32-bit signed integer. Handle whitespace, signs, overflow.
 * Stop parsing on first non-digit character.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Simulate C/C++ atoi function:
 * 1. Skip leading whitespaces
 * 2. Handle optional '+' or '-' sign
 * 3. Process digit characters sequentially
 * 4. Check overflow before each multiplication/addition
 * 5. Clamp to [Integer.MIN_VALUE, Integer.MAX_VALUE]
 * 6. Stop on first non-digit
 * 
 * Overflow check: if result > MAX/10 or (result == MAX/10 && digit > 7)
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through string
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class StringToInteger {
    /**
     * Converts string to integer with overflow handling.
     */
    public int myAtoi(String s) {
        int sign = 1, res = 0, idx = 0;
        int n = s.length();

        // Step 1: Skip leading whitespaces
        while (idx < n && s.charAt(idx) == ' ') {
            idx++;
        }

        // Step 2: Handle sign
        if (idx < n && (s.charAt(idx) == '-' || s.charAt(idx) == '+')) {
            if (s.charAt(idx++) == '-') {
                sign = -1;
            }
        }

        // Step 3: Convert digits and check for overflow
        while (idx < n && Character.isDigit(s.charAt(idx))) {
            int digit = s.charAt(idx++) - '0';

            // Check for overflow before multiplying or adding
            if (res > Integer.MAX_VALUE / 10 ||
                    (res == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + digit;
        }

        return res * sign;
    }

}
