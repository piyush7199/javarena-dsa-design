package com.javarena.dsa.algorithms.bitManupulation;

/**
 * 2311. Longest Binary Subsequence Less Than or Equal to K
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/longest-binary-subsequence-less-than-or-equal-to-k/">LeetCode - Longest Binary Subsequence</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> String, Bit Manipulation, Greedy, Dynamic Programming
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given a binary string s and an integer k, find the length of the longest subsequence of s 
 * whose decimal value (when treated as a binary number) is less than or equal to k. 
 * A subsequence is a string that can be derived from another string by deleting some or 
 * no characters without changing the order.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: s = "1001010", k = 5
 * Output: 5
 * Explanation: Longest valid subsequence is "00010" = 2 in decimal.
 *
 * Input: s = "00101001", k = 1
 * Output: 6
 * Explanation: "000001" = 1, or any subsequence of only zeros.
 *
 * Input: s = "1111", k = 5
 * Output: 2
 * Explanation: "11" = 3, which is ≤ 5.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Greedy approach with key observations:
 * - Any subsequence of only '0's has value 0, so we can always include ALL zeros
 * - '1's contribute to numeric value based on position (powers of 2)
 * - Rightmost '1's contribute smallest powers, leftmost contribute largest
 * - To maximize length, include all zeros + as many '1's as possible from right
 * - Add '1's from right to left while keeping total value ≤ k
 * - Stop when adding another '1' would exceed k
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Count all zeros in string (they contribute 0 value, free length)</li>
 *   <li>Traverse string from right to left (LSB to MSB)</li>
 *   <li>For each '1', calculate its power of 2 contribution</li>
 *   <li>If adding this '1' keeps total ≤ k, include it</li>
 *   <li>Track current power (multiply by 2 each position)</li>
 *   <li>Stop if power exceeds k (remaining bits will be too large)</li>
 *   <li>Return count of zeros + count of included ones</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n)<br>
 * Two passes through string: one to count zeros, one to greedily select ones.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses a constant number of variables regardless of string length.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>All zeros: Include all, value = 0</li>
 *   <li>All ones: Include from right while value ≤ k</li>
 *   <li>k = 0: Only zeros can be included</li>
 *   <li>Very large k: May include all characters</li>
 *   <li>Overflow prevention: Stop when power > k</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/">Valid Words for Puzzle</a>
 */
public class LongestBinarySeq {
    
    /**
     * Finds longest subsequence with decimal value ≤ k.
     *
     * @param s binary string consisting of '0' and '1'
     * @param k maximum allowed decimal value
     * @return length of longest valid subsequence
     */
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int countZeros = 0;

        // Step 1: Count all zeros (free subsequence length)
        for (char c : s.toCharArray()) {
            if (c == '0') countZeros++;
        }

        // Step 2: Try to include ones from right to left
        int value = 0;      // Current decimal value
        int pow = 1;        // Current power of 2
        int countOnes = 0;  // Count of ones included

        // Step 3: Traverse from right (LSB) to left (MSB)
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                // Check if adding this '1' keeps value ≤ k
                if (value + pow <= k) {
                    value += pow;
                    countOnes++;
                }
            }
            
            // Step 4: Update power for next position (avoid overflow)
            if (pow <= k) {
                pow <<= 1; // Multiply by 2 (left shift)
            } else {
                // Remaining bits will be too large, can stop
                break;
            }
        }

        // Step 5: Return total length (all zeros + selected ones)
        return countZeros + countOnes;
    }
}
