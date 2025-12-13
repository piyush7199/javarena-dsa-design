package com.javarena.dsa.algorithms.miscellaneous;

/**
 * Palindrome Number
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer x, return true if x is a palindrome, false otherwise.
 * A palindrome reads the same backward as forward.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Number reversal technique:
 * - Negative numbers: cannot be palindromes (minus sign doesn't reverse)
 * - Numbers ending in 0: not palindromes (except 0 itself)
 * - Reverse entire number digit by digit
 * - Compare reversed with original
 * 
 * Digit extraction process:
 * - Extract last digit: x % 10
 * - Build reversed: reversed = reversed * 10 + digit
 * - Remove last digit: x /= 10
 * - Repeat until x becomes 0
 * 
 * Optimization: Only reverse half the number for comparison.
 *
 * <p><b>Time Complexity:</b> O(log N) - N/10 operations for N-digit number
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class PalindromeNumber {
    
    /**
     * Checks if number is palindrome using full reversal.
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        
        int original = x;
        int reversed = 0;
        
        while (x > 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }
        
        return original == reversed;
    }
    
    /**
     * Optimized: Only reverse half the number.
     */
    public boolean isPalindromeOptimized(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        
        // Even length: x == reversed, Odd length: x == reversed / 10
        return x == reversed || x == reversed / 10;
    }
}
