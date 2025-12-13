package com.javarena.dsa.datastructures.string;

/**
 * Add Strings
 *
 * <p><b>Problem Statement:</b><br>
 * Add two non-negative integers represented as strings. Return sum as string.
 * Numbers may be very large (beyond standard integer range).
 *
 * <p><b>Intuition & Approach:</b><br>
 * Simulate manual addition digit by digit:
 * - Start from rightmost digits (least significant)
 * - Add corresponding digits + carry
 * - Result digit = sum % 10
 * - New carry = sum / 10
 * - Continue until both strings exhausted and carry = 0
 * - Reverse result (built from right to left)
 * 
 * Similar to adding numbers by hand on paper.
 *
 * <p><b>Time Complexity:</b> O(max(M, N)) - Process each digit once
 * <br><b>Space Complexity:</b> O(max(M, N)) - Result string length
 */
public class AddStrings {
    /**
     * Adds two number strings digit by digit.
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += num1.charAt(i--) - '0';
            if (j >= 0) sum += num2.charAt(j--) - '0';

            sb.append(sum % 10);
            carry = sum / 10;
        }

        return sb.reverse().toString();
    }
}
