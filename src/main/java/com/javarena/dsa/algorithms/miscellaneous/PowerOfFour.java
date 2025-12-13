package com.javarena.dsa.algorithms.miscellaneous;

/**
 * Power of Four
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer n, return true if it is a power of four, false otherwise.
 * A power of four exists if n == 4^x for some integer x.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Multiple approaches possible:
 * 
 * Approach 1 - Iterative division:
 * - Powers of 4: 1, 4, 16, 64, 256, 1024...
 * - Repeatedly divide by 4 until cannot divide evenly
 * - If result is 1, original was power of 4
 * 
 * Approach 2 - Bit manipulation (optimal):
 * - Power of 4 must be power of 2 (only one bit set)
 * - AND bit must be at even position (0, 2, 4, 6...)
 * - Check: (n > 0) && (n & (n-1)) == 0 && (n & 0x55555555) != 0
 * - 0x55555555 = binary 01010101... (bits at even positions)
 * 
 * Approach 3 - Modulo check: (n > 0) && (n & (n-1)) == 0 && n % 3 == 1
 *
 * <p><b>Time Complexity:</b> O(log N) iterative, O(1) bit manipulation
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class PowerOfFour {
    
    /**
     * Iterative division approach.
     */
    public boolean isPowerOfFour(int n) {
        if (n < 1) return false;
        
        while (n % 4 == 0) {
            n /= 4;
        }
        
        return n == 1;
    }
    
    /**
     * Bit manipulation approach (optimal).
     */
    public boolean isPowerOfFourBitwise(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }
}
