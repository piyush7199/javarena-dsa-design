package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Check Kth Bit is Set or Not
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://www.geeksforgeeks.org/problems/check-whether-k-th-bit-is-set-or-not-1587115620/1">GFG - Check Kth Bit</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Bit Manipulation, Math
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given a number n and a bit number k, check if the kth bit of n is set or not. 
 * A bit is called set if it is 1. Position of set bit '1' should be indexed starting 
 * with 0 from the LSB (Least Significant Bit) side in the binary representation of the number.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: n = 4, k = 0
 * Output: false
 * Explanation: Binary representation of 4 is 100, in which 0th bit from LSB is not set.
 *
 * Input: n = 4, k = 2
 * Output: true
 * Explanation: Binary representation of 4 is 100, in which 2nd bit from LSB is set.
 *
 * Input: n = 500, k = 3
 * Output: false
 * Explanation: Binary representation of 500 is 111110100, in which 3rd bit is not set.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Two efficient approaches to check if kth bit is set:
 * - **Left Shift:** Create a mask by shifting 1 left by k positions, then AND with n
 * - **Right Shift:** Shift n right by k positions to bring kth bit to position 0, then check if it's 1
 * - Both approaches use properties of bitwise operations to check a specific bit position
 * - Time complexity is O(1) as bit operations are constant time
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li><b>Left Shift Method:</b> Create mask = 1 << k (shifts 1 left by k positions)</li>
 *   <li>Perform AND operation: n & mask</li>
 *   <li>If result != 0, the kth bit is set; otherwise it's not set</li>
 *   <li><b>Right Shift Method:</b> Shift n right by k positions: n >> k</li>
 *   <li>AND with 1 to isolate the least significant bit</li>
 *   <li>Check if the result equals 1</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(1)<br>
 * Bitwise shift and AND operations are performed in constant time regardless of number size.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses a few variables for computation, no extra space dependent on input.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>k = 0: Checking the least significant bit (LSB)</li>
 *   <li>k >= 31 for 32-bit integers: May access bits beyond the number's representation</li>
 *   <li>n = 0: All bits are 0, returns false for any k</li>
 *   <li>Negative numbers: Works correctly due to two's complement representation</li>
 * </ul>
 *
 * @see <a href="https://www.geeksforgeeks.org/problems/set-kth-bit/1">Set Kth Bit</a>
 */
public class CheckKthBit {
    
    /**
     * Checks if kth bit is set using left shift approach.
     *
     * @param n the number to check
     * @param k the bit position (0-indexed from LSB)
     * @return true if kth bit is set (1), false otherwise
     */
    static boolean checkKthBitUsingLeftShift(int n, int k) {
        // Step 1: Create a mask with only kth bit set
        int ele = 1 << k;  // Shift 1 left by k positions
        
        // Step 2: AND operation with n to check if kth bit is set
        return (n & ele) != 0;
    }

    /**
     * Checks if kth bit is set using right shift approach.
     *
     * @param n the number to check
     * @param k the bit position (0-indexed from LSB)
     * @return true if kth bit is set (1), false otherwise
     */
    static boolean checkKthBitUsingRightShift(int n, int k) {
        // Step 1: Shift n right by k positions to bring kth bit to position 0
        // Step 2: AND with 1 to isolate the least significant bit
        // Step 3: Check if it equals 1
        return 1 == ((n >> k) & 1);
    }
}
