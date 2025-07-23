package org.example.coding.datastructures.string;

public class StringToInteger {
    /**
     * Converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
     *
     * <p>This function handles the following:
     * <ul>
     *   <li>Ignores leading whitespace characters</li>
     *   <li>Handles optional '+' or '-' sign before the digits</li>
     *   <li>Parses digit characters and stops on encountering any non-digit character</li>
     *   <li>Clamps the result to {@link Integer#MAX_VALUE} or {@link Integer#MIN_VALUE} in case of overflow</li>
     * </ul>
     *
     * <p><b>Intuition:</b><br>
     * 1. Skip all leading whitespaces.<br>
     * 2. Handle optional sign.<br>
     * 3. Process each digit character and build the integer result.<br>
     * 4. Before each update, check for potential overflow and return clamped values accordingly.<br>
     * 5. Multiply the final result by the sign.
     *
     * <p><b>Edge Cases Handled:</b>
     * <ul>
     *   <li>Strings with only whitespace</li>
     *   <li>Overflow/underflow beyond 32-bit range</li>
     *   <li>Strings with non-digit characters after initial digits</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(n), where n is the length of the input string (we may scan all characters).<br>
     * <b>Space Complexity:</b> O(1), since we use only constant extra space.
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
