package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Minimum Bit Flips to Convert Number
 *
 * <p><b>Problem Statement:</b><br>
 * Given integers start and goal, return minimum number of bit flips
 * to convert start to goal. Can flip any bit (0→1 or 1→0).
 *
 * <p><b>Intuition & Approach:</b><br>
 * XOR operation to identify differing bits:
 * - XOR returns 1 where bits differ, 0 where same
 * - start ^ goal = number with 1s at positions needing flips
 * - Count number of 1s in XOR result = number of flips needed
 * 
 * Two methods to count 1s:
 * 1. Brian Kernighan's: n & (n-1) removes rightmost set bit, count iterations
 * 2. Standard: Check each bit with (n & 1), right shift, repeat
 *
 * <p><b>Time Complexity:</b> O(log N) - Check all bits in larger number
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class MinimumBitFlipsToConvertNumber {
    
    /**
     * Counts minimum bit flips using XOR and bit counting.
     */
    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;
        int count = 0;
        
        // Count set bits in XOR
        while (xor != 0) {
            count += (xor & 1);
            xor >>= 1;
        }
        
        return count;
    }
    
    /**
     * Alternative: Using Brian Kernighan's algorithm.
     */
    public int minBitFlipsBK(int start, int goal) {
        int xor = start ^ goal;
        int count = 0;
        
        while (xor != 0) {
            xor &= (xor - 1);  // Remove rightmost set bit
            count++;
        }
        
        return count;
    }
}
