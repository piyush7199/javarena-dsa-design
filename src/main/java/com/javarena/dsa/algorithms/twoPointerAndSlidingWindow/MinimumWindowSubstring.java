package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Minimum Window Substring
 *
 * <p><b>Problem Statement:</b><br>
 * Given strings s and t, find minimum window substring in s that contains all characters
 * from t (including duplicates). Return empty string if no such window exists.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Sliding window with character frequency tracking:
 * - Use frequency map to track required characters from t
 * - Expand window (right pointer) until all characters matched
 * - Shrink window (left pointer) while maintaining validity
 * - Track minimum valid window found
 * 
 * Two-phase approach:
 * Phase 1 (Expand): Move right, include characters, increment match count
 * Phase 2 (Shrink): Move left, remove characters while window still valid
 * 
 * Implementation:
 * - Use ASCII array (256) as frequency map
 * - Positive values: needed characters, negative: extra characters
 * - cnt tracks how many required characters matched
 * - When cnt == t.length(): window is valid
 *
 * <p><b>Time Complexity:</b> O(N + M) - N=s.length(), M=t.length()
 * <br><b>Space Complexity:</b> O(1) - Fixed 256-size array
 */
public class MinimumWindowSubstring {
    
    /**
     * Finds minimum window in s containing all characters of t.
     */
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        
        if (n < m) return "";
        
        // Build frequency map for t
        int[] map = new int[256];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        
        int left = 0, right = 0;
        int cnt = 0;  // Matched required characters
        int minLen = Integer.MAX_VALUE;
        int startIndex = -1;
        
        // Expand window with right pointer
        while (right < n) {
            char c = s.charAt(right);
            
            // If required character found
            if (map[c] > 0) {
                cnt++;
            }
            map[c]--;
            
            // Shrink window while valid
            while (cnt == m) {
                // Update minimum window
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIndex = left;
                }
                
                // Try to shrink from left
                char leftChar = s.charAt(left);
                map[leftChar]++;
                if (map[leftChar] > 0) {
                    cnt--;
                }
                left++;
            }
            
            right++;
        }
        
        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + minLen);
    }
}
