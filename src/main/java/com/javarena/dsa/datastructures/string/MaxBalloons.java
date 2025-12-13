package com.javarena.dsa.datastructures.string;

/**
 * Maximum Number of Balloons
 *
 * <p><b>Problem Statement:</b><br>
 * Given string text, return maximum number of times word "balloon" can be formed
 * using characters from text.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Frequency counting problem:
 * - Word "balloon" has: b(1), a(1), l(2), o(2), n(1)
 * - Count frequency of each character in text
 * - For 'l' and 'o': divide by 2 (need 2 of each)
 * - For 'b', 'a', 'n': use directly
 * - Answer = minimum of all these counts
 * 
 * Bottleneck character determines maximum balloons.
 *
 * <p><b>Time Complexity:</b> O(N) - Count frequencies
 * <br><b>Space Complexity:</b> O(1) - Fixed array size (26)
 */
public class MaxBalloons {
    /**
     * Counts maximum "balloon" instances.
     */
    public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];
        for (char ch : text.toCharArray()) {
            freq[ch - 'a']++;
        }
        
        String b = "balon"; // Unique chars in "balloon"
        int ans = Integer.MAX_VALUE;
        
        for (char ch : b.toCharArray()) {
            if (ch == 'l' || ch == 'o') {
                ans = Math.min(ans, freq[ch - 'a'] / 2); // Need 2 each
            } else {
                ans = Math.min(ans, freq[ch - 'a']); // Need 1 each
            }
        }
        return ans;
    }
}
