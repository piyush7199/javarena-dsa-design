package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Power of Two
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer n, return true if it's a power of two, false otherwise.
 * A power of two: n = 2^x for some integer x.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Binary representation insight:
 * - Power of two has exactly ONE set bit (1→0001, 2→0010, 4→0100, 8→1000)
 * - Subtracting 1 flips all bits after the single set bit
 * - n & (n-1) removes rightmost set bit
 * - For powers of two: only one set bit, so result = 0
 * 
 * Example: 8 (1000) & 7 (0111) = 0000 = 0 ✓
 * Non-power: 6 (0110) & 5 (0101) = 0100 ≠ 0 ✗
 *
 * <p><b>Time Complexity:</b> O(1) - Constant time bitwise operations
 * <br><b>Space Complexity:</b> O(1) - No extra space
 */
public class PowerOfTwo {
    
    /**
     * Checks if number is power of two using bit manipulation.
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
