package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Longest Subarray With Maximum Bitwise AND
 *
 * <p><b>Problem Statement:</b><br>
 * Find length of longest subarray with maximum possible bitwise AND value.
 * Maximum AND = highest value achievable by ANDing any subarray.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Bitwise AND property insight:
 * - AND is restrictive: a & b ≤ min(a, b)
 * - ANDing different numbers produces result ≤ both
 * - Maximum AND = maximum element in array (only subarray of that element)
 * - To maximize AND, subarray must contain only max elements
 * 
 * Strategy:
 * - Find maximum element in array
 * - Find longest consecutive sequence of max elements
 * - That's the longest subarray with maximum AND
 *
 * <p><b>Time Complexity:</b> O(N) - Two passes: find max, find longest sequence
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class LongestSubarrayWithMaxBitAnd {
    
    /**
     * Finds longest subarray with maximum AND value.
     */
    public int longestSubarray(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        
        int maxLength = 0;
        int currentLength = 0;
        
        for (int num : nums) {
            if (num == max) {
                currentLength++;
                maxLength = Math.max(maxLength, currentLength);
            } else {
                currentLength = 0;
            }
        }
        
        return maxLength;
    }
}
