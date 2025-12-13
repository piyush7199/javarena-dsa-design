package com.javarena.dsa.algorithms.miscellaneous;

/**
 * Reverse Integer
 *
 * <p><b>Problem Statement:</b><br>
 * Given signed 32-bit integer x, return x with digits reversed.
 * If reversing causes overflow outside [-2³¹, 2³¹-1], return 0.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Digit-by-digit reversal with overflow protection:
 * - Extract digits using modulo: x % 10 gives last digit
 * - Build reversed: rev = rev × 10 + digit
 * - Check overflow BEFORE multiplying by 10
 * - MAX_VALUE = 2147483647 (last digit 7)
 * - MIN_VALUE = -2147483648 (last digit -8)
 * 
 * Overflow detection:
 * - If rev > MAX/10: next multiplication overflows
 * - If rev == MAX/10 && digit > 7: overflows
 * - Similar check for negative overflow
 * 
 * Handle signs naturally: modulo preserves sign.
 *
 * <p><b>Time Complexity:</b> O(log N) - Process each digit once
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class ReverseInteger {
    
    /**
     * Reverses integer with overflow protection.
     */
    public int reverse(int x) {
        int rev = 0;
        
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            
            // Check overflow before multiplying
            if (rev > Integer.MAX_VALUE / 10 || 
                (rev == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || 
                (rev == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }
            
            rev = rev * 10 + digit;
        }
        
        return rev;
    }
}
