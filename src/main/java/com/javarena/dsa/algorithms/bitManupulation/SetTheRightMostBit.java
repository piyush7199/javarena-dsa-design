package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Set the Rightmost Unset Bit
 *
 * <p><b>Problem Statement:</b><br>
 * Given non-negative number n, set the rightmost unset bit (0 → 1).
 * If no unset bits exist (all 1s), return number as is.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Bitwise trick for O(1) solution:
 * - Use n | (n + 1) to set rightmost unset bit
 * - When adding 1: rightmost 1s flip to 0s, first 0 flips to 1
 * - OR operation preserves all existing 1s, sets the flipped 0
 * 
 * Example: n = 10 (1010)
 * - n + 1 = 11 (1011)
 * - n | (n+1) = 1010 | 1011 = 1011 = 11
 * 
 * Alternative: Loop from LSB, find first 0, set it
 *
 * <p><b>Time Complexity:</b> O(1) - Single bitwise operation
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class SetTheRightMostBit {
    
    /**
     * Sets rightmost unset bit using bitwise trick.
     */
    public static int setBit(int n) {
        return n | (n + 1);
    }
    
    /**
     * Alternative: Loop method for clarity.
     */
    public static int setBitLoop(int n) {
        int pos = 0;
        int temp = n;
        
        // Find first unset bit
        while ((temp & 1) == 1) {
            temp >>= 1;
            pos++;
        }
        
        // Set that bit
        return n | (1 << pos);
    }
}
