package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Sliding Window Technique - Collection
 *
 * <p><b>Problem Statement:</b><br>
 * Collection of problems solved using sliding window technique:
 * 1. Shortest Beautiful Substring - shortest substring with k ones
 * 2. Longest Substring Without Repeating Characters
 * 3. Max Consecutive Ones III - with k flips allowed
 * 4. Fruit Into Baskets - longest subarray with at most 2 types
 *
 * <p><b>Intuition & Approach:</b><br>
 * Sliding window patterns:
 * - Fixed-size window: Move both pointers together, maintain k elements
 * - Variable-size window: Expand right, shrink left when invalid
 * - Two pointers: left/right define window boundaries
 * 
 * Key principles:
 * - Expands window by moving right pointer
 * - Shrinks window by moving left pointer when constraint violated
 * - Maintains window invariant (sum, count, distinct elements, etc.)
 * - Avoids recalculating from scratch for each window
 * 
 * Common applications:
 * - Maximum/minimum subarray with constraints
 * - Longest substring with k distinct characters
 * - Maximum sum of k consecutive elements
 * - Smallest window containing all characters
 * - Count of subarrays meeting criteria
 *
 * <p><b>Time Complexity:</b> O(N) - Each element visited at most twice
 * <br><b>Space Complexity:</b> O(K) - K is distinct elements or window size
 */
public class SlidingWindow {

    /**
     * Shortest Beautiful Substring: Find shortest substring with exactly k ones,
     * lexicographically smallest if tie.
     */
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int ones = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Shrink from left if too many 1s
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Try to shrink while maintaining exactly k 1s
            while (ones == k) {
                int len = right - left + 1;
                String cur = s.substring(left, right + 1);
                
                if (len < minLen || (len == minLen && cur.compareTo(result) < 0)) {
                    minLen = len;
                    result = cur;
                }
                
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }
        }

        return result;
    }

    /**
     * Longest Substring Without Repeating Characters using sliding window.
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // Shrink window until no duplicate
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            
            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }

    /**
     * Max Consecutive Ones III: Longest subarray of 1s with at most k flips.
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeros = 0;
        int maxLen = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            
            // Shrink window if too many zeros
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }

    /**
     * Fruit Into Baskets: Longest subarray with at most 2 distinct types.
     */
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0;
        int maxFruits = 0;
        
        for (int right = 0; right < fruits.length; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);
            
            // Shrink window if more than 2 types
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }
            
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
}
