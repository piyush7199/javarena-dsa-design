package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Set the Rightmost Unset Bit
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://www.geeksforgeeks.org/problems/set-the-rightmost-unset-bit/1">GFG - Set Rightmost Unset Bit</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Bit Manipulation, Math
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given a non-negative number n, the task is to set the rightmost unset bit in the binary 
 * representation of n. If there are no unset bits, then just leave the number as it is.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: n = 6
 * Output: 7
 * Explanation: Binary of 6 is 110. After setting rightmost unset bit it becomes 111 = 7.
 *
 * Input: n = 15
 * Output: 15
 * Explanation: Binary of 15 is 1111. There is no unset bit, so output is 15.
 *
 * Input: n = 10
 * Output: 11
 * Explanation: Binary of 10 is 1010. After setting rightmost unset bit it becomes 1011 = 11.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Two approaches to set the rightmost unset bit:
 * - **Loop Method:** Scan from LSB until finding the first 0 bit, then set it
 * - **Bitwise Trick:** Use n | (n + 1) to set the rightmost unset bit in O(1)
 * - When you add 1 to n, it flips the rightmost sequence of 1s to 0s and first 0 to 1
 * - OR operation (n | (n+1)) preserves all original set bits while setting the first unset bit
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li><b>Optimal Method:</b> Simply compute n | (n + 1)</li>
 *   <li>This works because n+1 flips bits up to first 0 in n</li>
 *   <li>OR operation combines both, setting the target bit</li>
 *   <li><b>Loop Method:</b> Count positions while LSB is 1</li>
 *   <li>Once found first 0 bit, OR with (1 << position) to set it</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(1) for optimal, O(log n) for loop method<br>
 * Optimal method uses only constant-time operations.
 * Loop method may iterate through all bit positions in worst case.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses a constant number of variables for computation.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>n = 0: First bit (LSB) is unset, result is 1</li>
 *   <li>All bits set (e.g., 2^k - 1): No unset bit, returns same number</li>
 *   <li>Single unset bit: That bit gets set</li>
 *   <li>Multiple unset bits: Only rightmost one is set</li>
 * </ul>
 *
 * @see <a href="https://www.geeksforgeeks.org/problems/set-kth-bit/1">Set Kth Bit</a>
 */
public class SetTheRightMostBit {
    
    /**
     * Sets the rightmost unset bit using optimal O(1) approach.
     *
     * @param n the number whose rightmost unset bit should be set
     * @return number with rightmost unset bit set to 1
     */
    static int setBitOptimal(int n) {
        // Step 1: Use the property n | (n + 1)
        // When we add 1, it flips rightmost 1s to 0s and first 0 to 1
        // OR operation preserves original bits and sets the target bit
        return n | (n + 1);
    }
    
    /**
     * Sets the rightmost unset bit using loop method - for comparison.
     *
     * @param n the number whose rightmost unset bit should be set
     * @return number with rightmost unset bit set to 1
     */
    static int setBit(int n) {
        // Step 1: Find position of first unset bit
        int unsetBit = 0;
        int m = n;
        
        // Step 2: Count how many positions until we find a 0 bit
        while ((m & 1) != 0) {
            unsetBit++;
            m = m >> 1;
        }
        
        // Step 3: Set that bit using OR with (1 << position)
        return n | (1 << unsetBit);
    }
}
