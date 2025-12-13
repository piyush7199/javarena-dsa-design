package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Check Kth Bit is Set or Not
 *
 * <p><b>Problem Statement:</b><br>
 * Given number n and bit position k, check if kth bit of n is set (equals 1).
 * Position indexed from 0 starting from LSB (Least Significant Bit).
 *
 * <p><b>Intuition & Approach:</b><br>
 * Two efficient methods to check specific bit:
 * 
 * Method 1 - Left Shift:
 * - Create mask = 1 << k (shifts 1 left by k positions)
 * - Perform n & mask
 * - If result ≠ 0, kth bit is set
 * 
 * Method 2 - Right Shift:
 * - Shift n right by k: n >> k
 * - AND with 1 to isolate LSB: (n >> k) & 1
 * - If result = 1, kth bit is set
 * 
 * Both are O(1) operations.
 *
 * <p><b>Time Complexity:</b> O(1) - Constant time bit operations
 * <br><b>Space Complexity:</b> O(1) - No extra space
 */
public class CheckKthBit {
    
    /**
     * Checks if kth bit is set using left shift method.
     */
    static boolean checkKthBit(int n, int k) {
        return (n & (1 << k)) != 0;
    }
    
    /**
     * Alternative: checks using right shift method.
     */
    static boolean checkKthBitRightShift(int n, int k) {
        return ((n >> k) & 1) == 1;
    }
}
