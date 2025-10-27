package com.javarena.dsa.algorithms.string;

/**
 * 214. Shortest Palindrome
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/shortest-palindrome/">LeetCode - Shortest Palindrome</a>
 *
 * <p><b>Difficulty:</b> Hard
 *
 * <p><b>Topics:</b> String, KMP Algorithm, Palindrome
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * You are given a string s. You can convert s to a palindrome by adding characters in front of it.
 * Return the shortest palindrome you can find by performing this transformation.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: s = "aacecaaa"
 * Output: "aaacecaaa"
 * Explanation: Add "aa" in front to make it palindrome.
 *
 * Input: s = "abcd"
 * Output: "dcbabcd"
 * Explanation: Add "dcb" (reverse of "bcd") in front.
 *
 * Input: s = "a"
 * Output: "a"
 * Explanation: Already a palindrome.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Find longest palindromic prefix using KMP:
 * - We need to find longest prefix of s that is already palindrome
 * - Remaining suffix needs to be reversed and prepended
 * - Use KMP's LPS concept: create string t = s + "#" + reverse(s)
 * - Separator "#" prevents overlap between s and reversed s
 * - Compute LPS array for t
 * - lps[length-1] tells us length of longest palindromic prefix
 * - Reverse the remaining suffix and prepend to original string
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Handle edge case: null or single character (already shortest)</li>
 *   <li>Create string t = s + "#" + reverse(s)</li>
 *   <li>Build LPS array for string t</li>
 *   <li>Value lps[t.length()-1] = length of longest palindromic prefix</li>
 *   <li>Extract remaining suffix: s.substring(palindromicLength)</li>
 *   <li>Reverse suffix and prepend to original string</li>
 *   <li>Return result</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n)<br>
 * Where n is length of input string. Building LPS is O(n), string operations O(n).
 *
 * <p><b>Space Complexity:</b> O(n)<br>
 * Space for LPS array and temporary concatenated string.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Null or empty string: Return as is</li>
 *   <li>Single character: Already palindrome</li>
 *   <li>Already palindrome: LPS equals length, no chars added</li>
 *   <li>No palindromic prefix: Must reverse entire string except first char</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/longest-palindromic-substring/">Longest Palindromic Substring</a>
 */
public class ShortestPalindrome {
    
    /**
     * Returns shortest palindrome by adding characters in front.
     *
     * @param s the input string
     * @return shortest palindrome string
     */
    public String shortestPalindrome(String s) {
        // Step 1: Handle edge cases
        if (s == null || s.length() <= 1) return s;

        // Step 2: Create combined string: s + "#" + reverse(s)
        String rev = new StringBuilder(s).reverse().toString();
        String t = s + "#" + rev;
        int n = t.length();

        // Step 3: Compute LPS array for combined string
        int[] lps = new int[n];
        lps[0] = 0;
        int len = 0;  // Length of previous longest prefix-suffix
        int i = 1;

        while (i < n) {
            if (t.charAt(i) == t.charAt(len)) {
                // Extend LPS
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    // Backtrack to previous LPS
                    len = lps[len - 1];
                } else {
                    // No match
                    lps[i] = 0;
                    i++;
                }
            }
        }

        // Step 4: Length of longest palindromic prefix
        int palLen = lps[n - 1];

        // Step 5: Extract remaining suffix and reverse it
        String suffix = s.substring(palLen);
        
        // Step 6: Prepend reversed suffix to original string
        return new StringBuilder(suffix).reverse().append(s).toString();
    }
}
