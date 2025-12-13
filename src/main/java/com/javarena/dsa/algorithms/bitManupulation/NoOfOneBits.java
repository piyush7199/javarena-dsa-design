package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Number of 1 Bits (Hamming Weight)
 *
 * <p><b>Problem Statement:</b><br>
 * Given unsigned integer, return number of '1' bits in its binary representation.
 * Also known as Hamming weight.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Count set bits by checking each bit position:
 * - Use (n & 1) to check rightmost bit
 * - Right shift (n >> 1) to check next bit
 * - Continue until n becomes 0
 * - Each time (n & 1) = 1, increment counter
 * 
 * Alternative optimization: n & (n-1) removes rightmost set bit.
 * Can count how many times this operation needed until n=0.
 *
 * <p><b>Time Complexity:</b> O(log N) or O(32) for 32-bit integers
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class NoOfOneBits {
    
    /**
     * Counts number of set bits in integer.
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1;  // Unsigned right shift
        }
        return count;
    }
}
