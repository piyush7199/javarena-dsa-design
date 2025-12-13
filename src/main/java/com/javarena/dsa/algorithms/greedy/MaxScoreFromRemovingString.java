package com.javarena.dsa.algorithms.greedy;

/**
 * 1717. Maximum Score From Removing Substrings
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/maximum-score-from-removing-substrings/">LeetCode - Maximum Score From Removing Substrings</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Greedy, String, Stack
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * You are given a string s and two integers x and y. You can perform two types of operations any number of times:
 * - Remove substring "ab" and gain x points
 * - Remove substring "ba" and gain y points
 * Return the maximum points you can gain after applying the above operations on s.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: s = "cdbcbbaaabab", x = 4, y = 5
 * Output: 19
 * Explanation: Remove "ba" earning 5 points, then remove "ab" earning 4 points, etc.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Greedy approach: Always remove the higher-value substring first
 * - If x > y, prioritize removing "ab" first, then "ba"
 * - If y > x, prioritize removing "ba" first, then "ab"
 * - Use stack-like approach with StringBuilder to efficiently remove substrings
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Determine which substring has higher value</li>
 *   <li>Remove higher-value substring first using stack approach</li>
 *   <li>Remove lower-value substring from remaining string</li>
 *   <li>Return total score</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(N)<br>
 * Two passes through the string
 *
 * <p><b>Space Complexity:</b> O(N)<br>
 * For StringBuilder storage
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>No "ab" or "ba" substrings: Return 0</li>
 *   <li>Equal scores (x == y): Order doesn't matter</li>
 *   <li>String with only 'a' or only 'b': Return 0</li>
 * </ul>
 */
public class MaxScoreFromRemovingString {
    
    /**
     * Calculates maximum score by removing "ab" and "ba" substrings.
     *
     * @param s input string
     * @param x points for removing "ab"
     * @param y points for removing "ba"
     * @return maximum points achievable
     */
    public int maximumGain(String s, int x, int y) {
        if (x > y) {
            return remove(s, "ab", x) + removeRemaining(s, "ba", y, "ab", x);
        } else {
            return remove(s, "ba", y) + removeRemaining(s, "ab", x, "ba", y);
        }
    }

    /**
     * Helper to remove target substring and calculate score.
     *
     * @param s input string
     * @param target substring to remove
     * @param score points per removal
     * @return total score from removals
     */
    private int remove(String s, String target, int score) {
        StringBuilder sb = new StringBuilder();
        int total = 0;

        for (char c : s.toCharArray()) {
            sb.append(c);
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 2) == target.charAt(0) && sb.charAt(len - 1) == target.charAt(1)) {
                sb.delete(sb.length() - 2, sb.length());
                total += score;
            }
        }
        return total;
    }

    /**
     * Helper to remove remaining substrings after first pass.
     *
     * @param s input string
     * @param second second substring to remove
     * @param score2 points for second substring
     * @param first first substring (already removed)
     * @param score1 points for first substring
     * @return score from second removal
     */
    private int removeRemaining(String s, String second, int score2, String first, int score1) {
        // Remove first type greedily
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c);
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 2) == first.charAt(0) && sb.charAt(len - 1) == first.charAt(1)) {
                sb.delete(sb.length() - 2, sb.length());
            }
        }

        // Now remove second type
        int total = 0;
        StringBuilder sb2 = new StringBuilder();
        for (char c : sb.toString().toCharArray()) {
            sb2.append(c);
            int len = sb2.length();
            if (len >= 2 && sb2.charAt(len - 2) == second.charAt(0) && sb2.charAt(len - 1) == second.charAt(1)) {
                sb2.delete(sb2.length() - 2, sb2.length());
                total += score2;
            }
        }
        return total;
    }
}
