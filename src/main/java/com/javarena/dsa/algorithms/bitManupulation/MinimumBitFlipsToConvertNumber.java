package com.javarena.dsa.algorithms.bitManupulation;

/**
 * 2220. Minimum Bit Flips to Convert Number
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/minimum-bit-flips-to-convert-number/">LeetCode - Minimum Bit Flips</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Bit Manipulation
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * A bit flip of a number x is choosing a bit in the binary representation of x and flipping it 
 * from either 0 to 1 or from 1 to 0. Given two integers start and goal, return the minimum 
 * number of bit flips to convert start to goal.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: start = 10, goal = 7
 * Output: 3
 * Explanation: Binary: 10 = 1010, 7 = 0111. Need to flip 3 bits.
 *
 * Input: start = 3, goal = 4
 * Output: 3
 * Explanation: Binary: 3 = 011, 4 = 100. Need to flip all 3 bits.
 *
 * Input: start = 0, goal = 0
 * Output: 0
 * Explanation: Numbers are already equal.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * XOR operation to find differing bits:
 * - XOR returns 1 where bits differ, 0 where bits are same
 * - start ^ goal gives a number with 1s at positions that need flipping
 * - Problem reduces to counting number of 1s (set bits) in XOR result
 * - This is also known as Hamming distance between two numbers
 * - Use bit manipulation to count set bits efficiently
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Compute diffBit = start ^ goal</li>
 *   <li>Each set bit (1) in diffBit represents a position where bits differ</li>
 *   <li>Count number of set bits using loop:</li>
 *   <li>- Check LSB using (diffBit & 1)</li>
 *   <li>- Right shift diffBit to check next bit</li>
 *   <li>- Continue until diffBit becomes 0</li>
 *   <li>Return count as minimum flips needed</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(log n)<br>
 * Where n = max(start, goal). We iterate through all bits, which is at most log₂(n) iterations.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses a constant number of variables for counting and shifting.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>start = goal: XOR = 0, no flips needed</li>
 *   <li>start = 0: Count set bits in goal</li>
 *   <li>goal = 0: Count set bits in start</li>
 *   <li>Powers of 2: Often results in many bit differences</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/hamming-distance/">Hamming Distance</a>
 */
public class MinimumBitFlipsToConvertNumber {
    
    /**
     * Returns minimum number of bit flips to convert start to goal.
     *
     * @param start the starting integer
     * @param goal the target integer
     * @return minimum number of bit flips required
     */
    public int minBitFlips(int start, int goal) {
        // Step 1: XOR to find differing bits
        int diffBit = start ^ goal;
        int ans = 0;
        
        // Step 2: Count set bits in XOR result
        while (diffBit != 0) {
            // Check if LSB is 1
            ans += (diffBit & 1);
            
            // Right shift to check next bit
            diffBit = diffBit >> 1;
        }
        
        return ans;
    }
}
