package org.example.coding.datastructures.string;

public class AddStrings {
    /**
     * Adds two non-negative integers represented as strings and returns the sum as a string.
     *
     * <p>
     * The input numbers may be very large (beyond the range of standard integer types),
     * so we simulate the addition process digit by digit, similar to how we add numbers by hand.
     * </p>
     *
     * <p><b>Intuition:</b><br>
     * Perform digit-by-digit addition from the least significant digit (rightmost) to
     * the most significant digit (leftmost), keeping track of carry. Append each
     * digit of the result to a StringBuilder and reverse at the end.
     * </p>
     *
     * <p><b>Approach:</b><br>
     * 1. Initialize two pointers `i` and `j` at the end of num1 and num2.<br>
     * 2. Initialize a variable `carry = 0`.<br>
     * 3. Loop while either pointer is valid or carry is nonzero:<br>
     * - Add digit from `num1[i]` if available.<br>
     * - Add digit from `num2[j]` if available.<br>
     * - Add `carry`.<br>
     * - Append `sum % 10` to the result.<br>
     * - Update `carry = sum / 10`.<br>
     * 4. Reverse the StringBuilder since digits were appended from least significant to most significant.<br>
     * </p>
     *
     * <p><b>Time Complexity:</b> O(max(m, n))<br>
     * - Each digit of both strings is processed once.<br>
     * </p>
     *
     * <p><b>Space Complexity:</b> O(max(m, n))<br>
     * - The StringBuilder stores the result, which has at most `max(m, n) + 1` digits.<br>
     * </p>
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
