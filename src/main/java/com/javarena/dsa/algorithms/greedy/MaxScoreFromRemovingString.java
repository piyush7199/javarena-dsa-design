package com.javarena.dsa.algorithms.greedy;

/**
 * Maximum Score From Removing Substrings
 *
 * <p><b>Problem Statement:</b><br>
 * Given string s and integers x, y, perform operations to maximize points:
 * - Remove "ab" and gain x points
 * - Remove "ba" and gain y points
 * Return maximum points achievable.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy priority: Always remove higher-value substring first
 * - If x > y: prioritize "ab" removal, then "ba"
 * - If y > x: prioritize "ba" removal, then "ab"
 * - Use stack/StringBuilder for efficient removal
 * 
 * Two-pass strategy:
 * 1. First pass: Remove higher-value substring
 *    - Build string while removing target pattern
 *    - Add points for each removal
 * 2. Second pass: Remove lower-value substring from result
 *    - Process remaining string for other pattern
 * 
 * Stack approach: Check last character before adding current.
 *
 * <p><b>Time Complexity:</b> O(N) - Two passes through string
 * <br><b>Space Complexity:</b> O(N) - StringBuilder storage
 */
public class MaxScoreFromRemovingString {
    
    /**
     * Maximizes score using greedy two-pass removal.
     */
    public int maximumGain(String s, int x, int y) {
        if (x < y) {
            return maximizeRemoval(s, "ba", y) + maximizeRemoval(result, "ab", x);
        } else {
            return maximizeRemoval(s, "ab", x) + maximizeRemoval(result, "ba", y);
        }
    }
    
    private String result = "";
    
    /**
     * Removes target pattern and returns score.
     */
    private int maximizeRemoval(String s, String target, int points) {
        StringBuilder sb = new StringBuilder();
        int score = 0;
        
        for (char c : s.toCharArray()) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == target.charAt(0) 
                && c == target.charAt(1)) {
                sb.deleteCharAt(sb.length() - 1);
                score += points;
            } else {
                sb.append(c);
            }
        }
        
        result = sb.toString();
        return score;
    }
}
