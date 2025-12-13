package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Longest Repeating Character Replacement
 *
 * <p><b>Problem Statement:</b><br>
 * Given string s and integer k, find length of longest substring that can be obtained
 * by replacing at most k characters with any character to make all characters same.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Sliding window with character frequency tracking:
 * - Maintain frequency of each character in current window
 * - Track most frequent character count in window
 * - Window is valid if: (window_size - max_freq) ≤ k
 *   - This represents characters that need to be replaced
 * - If invalid: shrink from left by moving left pointer
 * - Track maximum valid window size
 * 
 * Key insight:
 * - If we have window of size L with max frequency F
 * - We need to replace (L - F) characters to make all same
 * - Valid if (L - F) ≤ k
 * 
 * Optimization: No need to decrease maxFreq when shrinking.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through string
 * <br><b>Space Complexity:</b> O(1) - Fixed 26-size array for uppercase letters
 */
public class RepeatingChars {
    
    /**
     * Finds longest substring with at most k replacements.
     */
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLen = 0;
        int n = s.length();
        
        for (int right = 0; right < n; right++) {
            // Add character to window
            freq[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);
            
            // Calculate characters to replace
            int replacements = (right - left + 1) - maxFreq;
            
            if (replacements <= k) {
                maxLen = Math.max(maxLen, right - left + 1);
            } else {
                // Shrink window from left
                freq[s.charAt(left) - 'A']--;
                left++;
            }
        }
        
        return maxLen;
    }
}
