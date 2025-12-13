package com.javarena.dsa.datastructures.string;

/**
 * Longest Common Prefix
 *
 * <p><b>Problem Statement:</b><br>
 * Find longest common prefix string amongst array of strings.
 * Return empty string if no common prefix.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Character-by-character comparison:
 * - Find length of shortest string (max possible prefix)
 * - For each position i from 0 to min length:
 *   - Compare character at position i across all strings
 *   - If all match: add to prefix
 *   - If mismatch: return current prefix
 * - Continue until mismatch or end of shortest string
 *
 * <p><b>Time Complexity:</b> O(S) - S = sum of all characters, worst case compare all
 * <br><b>Space Complexity:</b> O(1) - Excluding output
 */
public class LongestCommonPrefix {

    /**
     * Finds longest common prefix.
     */
    public String longestCommonPrefix(String[] strs) {
        int n = strs[0].length();
        for (String str : strs) {
            n = Math.min(n, str.length());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = strs[0].charAt(i);
            for (String str : strs) {
                if (ch != str.charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}
