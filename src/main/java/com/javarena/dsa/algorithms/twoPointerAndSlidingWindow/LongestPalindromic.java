package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Longest Palindromic Substring
 *
 * <p><b>Problem Statement:</b><br>
 * Given a string s, return the longest palindromic substring in s.
 * A palindrome is a string that reads the same backward as forward.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Try all possible substrings and check if they are palindromes
 * - Start with longer substrings first to optimize (once found, skip shorter ones)
 * - For each starting position, check substrings of increasing length
 * - Check palindrome using two pointers from both ends moving inward
 * - Keep track of maximum length palindrome found
 * - Better approach: Expand around center (not implemented here but O(N²) vs O(N³))
 *
 * <p><b>Time Complexity:</b> O(N³) - O(N²) for trying substrings, O(N) to check palindrome
 * <br><b>Space Complexity:</b> O(1) - Only storing result string
 */
public class LongestPalindromic {
    
    /**
     * Finds the longest palindromic substring.
     *
     * @param s input string
     * @return longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int maxLen = 1;
        String maxStr = s.substring(0, 1);
        int n = s.length();

        for (int i = 0; i < n; i++) {
            for (int j = i + maxLen; j < s.length(); j++) {
                if (j - i + 1 > maxLen && isPalindrome(s, i, j)) {
                    maxLen = j - i;
                    maxStr = s.substring(i, j + 1);
                }
            }
        }
        return maxStr;
    }

    /**
     * Helper to check if substring is palindrome.
     */
    private boolean isPalindrome(String val, int i, int j) {
        while (j >= i) {
            if (val.charAt(i) != val.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
