package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

import java.util.HashMap;

/**
 * Longest Substring With K Distinct Characters
 *
 * <p><b>Problem Statement:</b><br>
 * Given string s and integer k, find length of longest substring with exactly k distinct characters.
 * Return -1 if no such substring exists.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Variable-size sliding window with distinct character tracking:
 * - Expand window by adding characters from right
 * - Track character frequencies using HashMap
 * - When distinct count > k: shrink from left
 * - When distinct count == k: update maximum length
 * 
 * Key insight:
 * - Only count substrings with exactly k distinct characters
 * - HashMap size gives distinct character count
 * - Remove character from map when frequency becomes 0
 * 
 * Similar to "Longest Substring Without Repeating Characters" but with k constraint.
 *
 * <p><b>Time Complexity:</b> O(N) - Each character processed at most twice
 * <br><b>Space Complexity:</b> O(K) - HashMap stores at most k characters
 */
public class LongestSubstring {
    
    /**
     * Finds length of longest substring with exactly k distinct characters.
     */
    public int longestKSubstr(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int n = s.length();
        int maxLen = -1;
        
        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            
            // Shrink window if too many distinct characters
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }
            
            // Update max when exactly k distinct
            if (map.size() == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        
        return maxLen;
    }
}

