package com.javarena.dsa.algorithms.miscellaneous;

/**
 * 7. Reverse Integer
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/reverse-integer/">LeetCode - Reverse Integer</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Math
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the 
 * value to go outside the signed 32-bit integer range [-2³¹, 2³¹ - 1], then return 0.
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: x = 123
 * Output: 321
 *
 * Input: x = -123
 * Output: -321
 *
 * Input: x = 120
 * Output: 21
 * Explanation: Trailing zeros are dropped.
 *
 * Input: x = 1534236469
 * Output: 0
 * Explanation: Reversed would be 9646324351, which overflows 32-bit integer.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Digit-by-digit reversal with overflow protection:
 * - Extract digits using modulo: x % 10 gives last digit
 * - Build reversed number: rev = rev × 10 + digit
 * - Must check for overflow BEFORE multiplying to prevent overflow
 * - Integer.MAX_VALUE = 2147483647 (last digit 7)
 * - Integer.MIN_VALUE = -2147483648 (last digit -8)
 * - If rev > MAX/10 or (rev == MAX/10 and digit > 7), will overflow
 * - Similar check for negative overflow
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Initialize reversed number = 0</li>
 *   <li>While x != 0:</li>
 *   <li>- Extract last digit: pop = x % 10</li>
 *   <li>- Remove last digit: x /= 10</li>
 *   <li>- Check for positive overflow: rev > MAX/10 or (rev == MAX/10 and pop > 7)</li>
 *   <li>- Check for negative overflow: rev < MIN/10 or (rev == MIN/10 and pop < -8)</li>
 *   <li>- If overflow detected, return 0</li>
 *   <li>- Build reversed: rev = rev × 10 + pop</li>
 *   <li>Return reversed number</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(log₁₀ x)<br>
 * Process each digit once, which is at most log₁₀(|x|) digits.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses constant extra variables for reversal.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Positive overflow: 1534236469 → would be 9646324351 (> MAX)</li>
 *   <li>Negative overflow: -2147483648 → edge case handled</li>
 *   <li>Trailing zeros: 120 → 21 (zeros dropped naturally)</li>
 *   <li>Single digit: Returns same digit</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/palindrome-number/">Palindrome Number</a>
 */
public class ReverseInteger {
    
    /**
     * Reverses digits of a 32-bit signed integer with overflow protection.
     *
     * @param x the integer to reverse
     * @return reversed integer, or 0 if overflow would occur
     */
    public int reverse(int x) {
        int rev = 0;
        
        // Step 1: Process each digit
        while (x != 0) {
            // Step 2: Extract last digit
            int pop = x % 10;
            x /= 10;
            
            // Step 3: Check for positive overflow
            // MAX_VALUE = 2147483647, so MAX/10 = 214748364
            if (rev > Integer.MAX_VALUE / 10 ||
                    (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            
            // Step 4: Check for negative overflow
            // MIN_VALUE = -2147483648, so MIN/10 = -214748364
            if (rev < Integer.MIN_VALUE / 10 ||
                    (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            
            // Step 5: Build reversed number
            rev = rev * 10 + pop;
        }
        
        return rev;
    }
}
