package com.javarena.dsa.datastructures.string;

/**
 * Palindromic Substrings
 *
 * <p><b>Problem Statement:</b><br>
 * Count number of palindromic substrings in given string.
 * Every character counts as palindrome of length 1.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Center expansion technique:
 * - Palindrome mirrors around center
 * - Two types: odd length (single char center), even length (between chars)
 * - For each possible center, expand outward
 * - Count valid palindromes while characters match
 * 
 * Two approaches:
 * 1. Optimized O(N²): Expand from each center
 * 2. Brute force O(N³): Check every substring
 *
 * <p><b>Time Complexity:</b> O(N²) for optimized, O(N³) for brute force
 * <br><b>Space Complexity:</b> O(1) - No extra space
 */
public class PalindromicSubstrings {

    /**
     * Optimized center expansion approach.
     */
    public int countSubstrings(String s) {
        int n = s.length();
        int count = n; // Each character is a palindrome of length 1

        // Case 1: Odd length palindromes
        for (int i = 0; i < n; i++) {
            int left = i - 1;
            int right = i + 1;

            // Expand around the center while valid
            while (left >= 0 && right < n) {
                if (s.charAt(left) == s.charAt(right)) {
                    count++; // Found a valid palindrome
                } else {
                    break; // Stop expanding when characters don't match
                }
                left--;
                right++;
            }
        }

        // Case 2: Even length palindromes
        for (int i = 0; i < n; i++) {
            int left = i;
            int right = i + 1;

            // Expand around the center between two characters
            while (left >= 0 && right < n) {
                if (s.charAt(left) == s.charAt(right)) {
                    count++;
                } else {
                    break;
                }
                left--;
                right++;
            }
        }

        return count;
    }

    /**
     * Brute force method to count palindromic substrings.
     *
     * <p><b>Intuition:</b>
     * Generate every possible substring and check if each one is a palindrome.
     *
     * <p><b>Approach:</b>
     * 1. Iterate over all possible start and end indices.
     * 2. For each substring, check if it's a palindrome by comparing characters.
     * 3. Count it if valid.
     *
     * <p><b>Time Complexity:</b> O(n³) —
     * - Generating all substrings: O(n²)
     * - Checking if each substring is a palindrome: O(n)
     *
     * <p><b>Space Complexity:</b> O(1) —
     * No extra space is used apart from variables.
     *
     * @param s the input string
     * @return the total number of palindromic substrings
     */
    public int countSubstringsBrute(String s) {
        int n = s.length();
        int ans = 0;

        // Iterate over all possible substrings
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // Check if the substring s[i..j] is a palindrome
                if (isPalindrome(s, i, j)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    /**
     * Helper method to check if a substring is a palindrome.
     *
     * @param s the input string
     * @param i starting index
     * @param j ending index
     * @return true if s[i..j] is a palindrome, false otherwise
     */
    private boolean isPalindrome(String s, int i, int j) {
        // Expand from both ends and compare characters
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}