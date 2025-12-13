package com.javarena.dsa.datastructures.string;

/**
 * Valid Palindrome
 *
 * <p><b>Problem Statement:</b><br>
 * Check if string is palindrome considering only alphanumeric characters and ignoring case.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Two-pointer technique:
 * - Convert to lowercase
 * - Use left and right pointers
 * - Skip non-alphanumeric characters
 * - Compare valid characters from both ends
 * - If mismatch found, not palindrome
 * - If pointers meet/cross, is palindrome
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass with two pointers
 * <br><b>Space Complexity:</b> O(N) - Lowercase conversion (can optimize to O(1))
 */
public class ValidPalindrome {
    /**
     * Checks if string is valid palindrome.
     */
    public boolean isPalindrome(String s1) {
        int i = 0;
        int j = s1.length() - 1;
        String s = s1.toLowerCase();
        
        while (i <= j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            
            if (!isValidAlph(ch1)) {
                i++;
            } else if (!isValidAlph(ch2)) {
                j--;
            } else {
                if (ch1 != ch2) return false;
                i++;
                j--;
            }
        }
        return true;
    }

    /**
     * Checks if character is alphanumeric.
     */
    private boolean isValidAlph(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }
}
