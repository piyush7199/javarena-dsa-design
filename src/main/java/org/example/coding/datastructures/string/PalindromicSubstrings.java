package org.example.coding.datastructures.string;

/**
 * This class provides two methods to count the total number of palindromic substrings
 * in a given string: an optimized center-expansion approach and a brute-force approach.
 */
public class PalindromicSubstrings {

    /**
     * Optimized method to count palindromic substrings using the center expansion technique.
     *
     * <p><b>Intuition:</b>
     * A palindrome mirrors around its center.
     * There are two types of centers:
     * - Odd length palindromes (center at a single character)
     * - Even length palindromes (center between two characters)
     * <p>
     * We expand around every possible center and count valid palindromes.
     *
     * <p><b>Approach:</b>
     * 1. Iterate over each character to consider it as the middle of an odd-length palindrome.
     * 2. Expand outward while characters on both sides are equal.
     * 3. Repeat the same for even-length palindromes by considering the gap between two characters as the center.
     * 4. Count each valid palindrome found.
     *
     * <p><b>Time Complexity:</b> O(n²) —
     * For each of the n centers, in the worst case, we expand O(n) times.
     *
     * <p><b>Space Complexity:</b> O(1) —
     * No extra space is used apart from variables.
     *
     * @param s the input string
     * @return the total number of palindromic substrings
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