package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Minimum Bit Flips to Convert N to K
 *
 * <p><b>Problem Statement:</b><br>
 * Given two integers n and k, find minimum number of bit changes required to convert n to k.
 * You can only change bits from 1 to 0, not from 0 to 1. Return -1 if impossible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Bit-by-bit comparison with restrictions:
 * - Can only flip bits 1→0, never 0→1
 * - If k > n: impossible (would need 0→1 flips)
 * - Compare bits from LSB to MSB:
 *   - If n=1 and k=0: flip needed (count++)
 *   - If n=0 and k=1: impossible (return -1)
 *   - Otherwise: no change needed
 * - Continue until all bits processed
 *
 * <p><b>Time Complexity:</b> O(log N) - Iterate through all bits
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class BitDifferent {
    
    /**
     * Calculates minimum bit flips from n to k with restrictions.
     */
    public int minBitFlips(int n, int k) {
        if (k > n) return -1;
        
        int count = 0;
        while (n > 0) {
            int bitN = n & 1;
            int bitK = k & 1;
            
            if (bitN == 1 && bitK == 0) {
                count++;
            } else if (bitN == 0 && bitK == 1) {
                return -1;
            }
            
            n >>= 1;
            k >>= 1;
        }
        
        return count;
    }
}
