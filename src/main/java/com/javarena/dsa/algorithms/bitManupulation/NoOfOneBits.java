package com.javarena.dsa.algorithms.bitManupulation;

/**
 * 191. Number of 1 Bits (Hamming Weight)
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/number-of-1-bits/">LeetCode - Number of 1 Bits</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Bit Manipulation, Divide and Conquer
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Write a function that takes an unsigned integer and returns the number of '1' bits 
 * it has (also known as the Hamming weight). The Hamming weight of a number is the 
 * total number of '1' bits in its binary representation.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: n = 11
 * Output: 3
 * Explanation: The binary representation of 11 is 1011, which has three '1' bits.
 *
 * Input: n = 128
 * Output: 1
 * Explanation: The binary representation of 128 is 10000000, which has one '1' bit.
 *
 * Input: n = 2147483645
 * Output: 30
 * Explanation: The binary has thirty '1' bits.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * The key insights for counting set bits:
 * - We can check each bit position by using bitwise AND with 1
 * - Right shift (>>) moves all bits one position to the right, allowing us to check each bit
 * - We continue until all bits have been checked (n becomes 0)
 * - Each time (n & 1) equals 1, we've found a set bit
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Initialize a counter to track the number of set bits</li>
 *   <li>While n is not zero, check the rightmost bit using (n & 1)</li>
 *   <li>Add the result to the counter (0 or 1)</li>
 *   <li>Right shift n by 1 position to check the next bit</li>
 *   <li>Return the total count when n becomes 0</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(log n) or O(32) for 32-bit integers<br>
 * We iterate through each bit position, which is at most log₂(n) iterations,
 * or 32 iterations for a standard integer.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses a constant amount of extra space for the counter variable.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>n = 0: No set bits, returns 0</li>
 *   <li>n = 1: One set bit, returns 1</li>
 *   <li>All bits set (e.g., -1 in two's complement): Returns 32 for 32-bit integers</li>
 *   <li>Power of 2: Returns 1 (only one bit set)</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/counting-bits/">Counting Bits</a>
 * @see <a href="https://leetcode.com/problems/hamming-distance/">Hamming Distance</a>
 */
public class NoOfOneBits {
    
    /**
     * Counts the number of set bits (1s) in the binary representation of n.
     *
     * @param n the integer whose set bits are to be counted
     * @return the number of '1' bits in the binary representation
     */
    static int setBits(int n) {
        // Step 1: Initialize counter
        int ans = 0;
        
        // Step 2: Check each bit until n becomes 0
        while (n != 0) {
            // Step 3: Check if rightmost bit is 1 using AND operation
            ans += (n & 1);
            
            // Step 4: Right shift to check next bit
            n = n >> 1;
        }
        
        // Step 5: Return total count
        return ans;
    }
    
    /**
     * Alternative approach using Brian Kernighan's Algorithm - more efficient.
     * 
     * <p><b>Intuition:</b> n & (n-1) removes the rightmost set bit.
     * We can count how many times we can do this operation until n becomes 0.
     * 
     * <p><b>Time:</b> O(k) where k is the number of set bits (better than O(log n))
     * <br><b>Space:</b> O(1)
     * <br><b>Trade-off:</b> Faster when number has few set bits
     *
     * @param n the integer whose set bits are to be counted
     * @return the number of '1' bits in the binary representation
     */
    static int setBitsOptimized(int n) {
        int count = 0;
        
        // Each iteration removes one set bit
        while (n != 0) {
            n = n & (n - 1);  // Remove rightmost set bit
            count++;
        }
        
        return count;
    }
}
