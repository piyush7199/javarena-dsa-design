package com.javarena.dsa.algorithms.miscellaneous;

/**
 * 9. Palindrome Number
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/palindrome-number/">LeetCode - Palindrome Number</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Math
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 * An integer is a palindrome when it reads the same backward as forward.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads same from left to right and right to left.
 *
 * Input: x = -121
 * Output: false
 * Explanation: From left to right: -121. From right to left: 121-.
 *
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Number reversal approach:
 * - Negative numbers cannot be palindromes (minus sign doesn't match)
 * - Reverse the entire number and compare with original
 * - If reversed equals original, it's a palindrome
 * - Handle overflow: if reversal would overflow, it can't be palindrome
 * - Use digit extraction: repeatedly get last digit, build reversed number
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Quick check: if x < 0, return false (negatives not palindromes)</li>
 *   <li>Initialize reversed number = 0 and copy of x</li>
 *   <li>While copy > 0:</li>
 *   <li>- Extract last digit: rem = val % 10</li>
 *   <li>- Check for overflow before multiplying by 10</li>
 *   <li>- Build reversed: ele = ele × 10 + rem</li>
 *   <li>- Remove last digit: val = val / 10</li>
 *   <li>Compare reversed with original x</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(log n)<br>
 * Where n is the value of x. We process each digit once, which is log₁₀(x) digits.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses a constant number of variables for reversal.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Negative numbers: Always false</li>
 *   <li>Single digit (0-9): Always true</li>
 *   <li>Numbers ending in 0: False (except 0 itself)</li>
 *   <li>Overflow during reversal: Return false</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/palindrome-linked-list/">Palindrome Linked List</a>
 */
public class PalindromeNumber {
    
    /**
     * Checks if an integer is a palindrome.
     *
     * @param x the integer to check
     * @return true if x is palindrome, false otherwise
     */
    public boolean isPalindrome(int x) {
        // Step 1: Negative numbers cannot be palindromes
        if (x < 0) return false;
        
        // Step 2: Initialize variables for reversal
        int ele = 0;
        int val = x;
        
        // Step 3: Reverse the number digit by digit
        while (val > 0) {
            // Extract last digit
            int rem = val % 10;
            
            // Step 4: Check for overflow before multiplying
            if (ele >= Integer.MAX_VALUE / 10) {
                return false;
            }
            
            // Build reversed number
            ele = ele * 10 + rem;
            
            // Remove last digit from original
            val = val / 10;
        }
        
        // Step 5: Compare reversed with original
        return x == ele;
    }
}
