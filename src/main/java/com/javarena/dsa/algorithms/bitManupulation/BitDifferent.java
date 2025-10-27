package com.javarena.dsa.algorithms.bitManupulation;

/**
 * 2997. Minimum Number of Operations to Make Array XOR Equal to K
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/minimum-number-of-operations-to-make-array-xor-equal-to-k/">LeetCode - Min Operations XOR K</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Bit Manipulation, Array
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given two integers n and k, you need to find the minimum number of bit changes required 
 * to convert n into k. You can only change a bit from 1 to 0, not from 0 to 1. 
 * Return -1 if it's impossible to convert n to k.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: n = 13, k = 4
 * Output: 2
 * Explanation: 13 = 1101, 4 = 0100. We need to change 2 bits (positions 0 and 3).
 *
 * Input: n = 4, k = 13
 * Output: -1
 * Explanation: Cannot change 0 to 1, so conversion impossible.
 *
 * Input: n = 14, k = 13
 * Output: 1
 * Explanation: 14 = 1110, 13 = 1101. Change 1 bit at position 1.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Key observations for bit conversion with restrictions:
 * - We can only flip bits from 1→0, never from 0→1
 * - Compare bits position by position from LSB to MSB
 * - If k has 1 where n has 0, conversion is impossible (return -1)
 * - If n has 1 where k has 0, we need to flip that bit (increment count)
 * - Continue until all bits of n are processed
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>If k > n, immediately return -1 (would require setting bits from 0→1)</li>
 *   <li>Iterate through all bits by right-shifting both n and k</li>
 *   <li>For each bit position, check four cases using (n&1) and (k&1)</li>
 *   <li>If n has 1 and k has 0: increment flip count</li>
 *   <li>If n has 0 and k has 1: return -1 (impossible)</li>
 *   <li>Continue until n becomes 0</li>
 *   <li>Return total flip count</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(log n)<br>
 * We iterate through all bits of n, which is at most log₂(n) iterations.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses a constant number of variables regardless of input size.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>n = k: No changes needed, return 0</li>
 *   <li>k > n: Impossible conversion, return -1</li>
 *   <li>n = 0: Only possible if k = 0</li>
 *   <li>All bits different: Count all positions where n=1 and k=0</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/minimum-bit-flips-to-convert-number/">Minimum Bit Flips</a>
 */
public class BitDifferent {
    
    /**
     * Calculates minimum bit flips to convert n to k (only 1→0 flips allowed).
     *
     * @param n the number to convert from
     * @param k the target number to convert to
     * @return minimum number of bit flips needed, or -1 if impossible
     */
    public int minChanges(int n, int k) {
        // Step 1: Quick check - if k > n, impossible (would need 0→1 flips)
        if (k > n) return -1;
        
        int count = 0;
        
        // Step 2: Compare bits position by position
        while (n != 0) {
            // Step 3: Check LSB of both numbers
            if ((n & 1) == 1 && (k & 1) == 0) {
                // n has 1, k has 0: need to flip (1→0)
                count++;
            }
            
            if ((n & 1) == 0 && (k & 1) == 1) {
                // n has 0, k has 1: impossible (would need 0→1 flip)
                return -1;
            }
            
            // Step 4: Move to next bit position
            n = n >> 1;
            k = k >> 1;
        }
        
        return count;
    }
}
