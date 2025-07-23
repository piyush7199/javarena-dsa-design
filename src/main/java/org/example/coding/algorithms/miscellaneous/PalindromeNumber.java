package org.example.coding.algorithms.miscellaneous;

public class PalindromeNumber {
    /**
     * If the integer is negative then i cant be palindrome
     * Now we will reverse the Integer and then check
     * Time Complexity - O(k) k - no. of Digits
     * Space Complexity - O(1);
     * <p>
     * Problem Link - <a href="https://leetcode.com/problems/palindrome-number">Palindrome Number</a>
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int ele = 0;
        int val = x;
        while (val > 0) {
            int rem = val % 10;
            if (ele >= Integer.MAX_VALUE / 10) {
                return false;
            }
            ele = ele * 10 + rem;
            val = val / 10;
        }
        return x == ele;
    }
}
