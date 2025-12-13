package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Longest Binary Subsequence Less Than or Equal to K
 *
 * <p><b>Problem Statement:</b><br>
 * Given binary string s and integer k, find length of longest subsequence
 * whose decimal value ≤ k.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy strategy with bit value optimization:
 * - All '0's have value 0, so include ALL zeros (they never increase value)
 * - For '1's, include greedily from right (lower bit positions first)
 * - Build number from LSB: each '1' at position i contributes 2^i
 * - Count zeros first (guaranteed inclusions)
 * - Then try including '1's from right while staying ≤ k
 * 
 * Process:
 * - Count all '0's in string
 * - Traverse from right, build value by including '1's
 * - Stop when adding next '1' would exceed k
 * - Return count of zeros + count of included ones
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through string
 * <br><b>Space Complexity:</b> O(1) - Constant extra space
 */
public class LongestBinarySeq {
    
    /**
     * Finds longest subsequence with value ≤ k.
     */
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int zeros = 0;
        int ones = 0;
        int value = 0;
        int power = 1;
        
        // Count zeros
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
        }
        
        // Try including ones from right
        for (int i = n - 1; i >= 0 && power <= k; i--) {
            if (s.charAt(i) == '1') {
                if (value + power <= k) {
                    value += power;
                    ones++;
                }
            }
            power *= 2;
        }
        
        return zeros + ones;
    }
}
