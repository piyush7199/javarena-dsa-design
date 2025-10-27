package com.javarena.dsa.algorithms.bitManupulation;

/**
 * 371. Sum of Two Integers
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/sum-of-two-integers/">LeetCode - Sum of Two Integers</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Bit Manipulation, Math
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 * You must implement the addition using only bitwise operations (AND, OR, XOR, shifts).
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: a = 1, b = 2
 * Output: 3
 *
 * Input: a = 2, b = 3
 * Output: 5
 *
 * Input: a = -1, b = 1
 * Output: 0
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Simulate binary addition using bitwise operations:
 * - XOR gives sum without considering carry: 1^1=0, 1^0=1, 0^0=0
 * - AND gives positions where carry is generated: 1&1=1 (carry), others=0
 * - Left shift AND result by 1 to get actual carry positions
 * - Repeat process: add new sum and carry until no carry remains
 * - This mimics how hardware adds numbers at circuit level
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>While there is a carry (b != 0):</li>
 *   <li>Calculate carry = (a & b) << 1</li>
 *   <li>- AND finds positions with both bits set (carry generation)</li>
 *   <li>- Left shift moves carry to next position</li>
 *   <li>Calculate sum without carry: a = a ^ b</li>
 *   <li>- XOR performs addition without carry propagation</li>
 *   <li>Update b = carry for next iteration</li>
 *   <li>When b becomes 0, no more carry, return a</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(log n)<br>
 * Where n is the larger of |a| and |b|. In worst case, we iterate through all bit positions.
 * Typically completes in ~32 iterations for 32-bit integers.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses two variables (a and carry) regardless of input size.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Both positive: Standard binary addition</li>
 *   <li>One negative: Two's complement handles correctly</li>
 *   <li>Both negative: Two's complement addition works</li>
 *   <li>Sum = 0: Loop terminates when carry becomes 0</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/subtract-two-integers/">Subtract Two Integers</a>
 */
public class SumOfTwoIntegers {
    
    /**
     * Computes sum of two integers using only bitwise operations.
     *
     * @param a first integer
     * @param b second integer
     * @return sum of a and b
     */
    public int getSum(int a, int b) {
        // Step 1: Iterate until no carry remains
        while (b != 0) {
            // Step 2: Calculate carry
            // AND finds positions where both bits are 1
            // Left shift moves carry to next significant bit
            int carry = (a & b) << 1;

            // Step 3: Calculate sum without carry
            // XOR performs addition without considering carry
            a = a ^ b;

            // Step 4: Update b with carry for next iteration
            b = carry;
        }
        
        // Step 5: When no carry left, a contains final sum
        return a;
    }
}
