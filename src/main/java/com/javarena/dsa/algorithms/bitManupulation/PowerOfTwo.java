package com.javarena.dsa.algorithms.bitManupulation;

/**
 * 231. Power of Two
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/power-of-two/">LeetCode - Power of Two</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Math, Bit Manipulation, Recursion
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer n, return true if it is a power of two. Otherwise, return false.
 * An integer n is a power of two, if there exists an integer x such that n == 2^x.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: n = 1
 * Output: true
 * Explanation: 2^0 = 1
 *
 * Input: n = 16
 * Output: true
 * Explanation: 2^4 = 16
 *
 * Input: n = 3
 * Output: false
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * The key insight for the optimal approach is understanding binary representation:
 * - A power of two has exactly ONE set bit in binary (e.g., 1→0001, 2→0010, 4→0100, 8→1000)
 * - When we subtract 1 from a power of two, all bits after the set bit become 1
 * - AND operation between n and (n-1) removes the rightmost set bit
 * - For powers of two, this results in 0 since there's only one set bit
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Handle edge case: if n <= 0, return false (powers of two are positive)</li>
 *   <li>Use bit manipulation: check if (n & (n-1)) equals 0</li>
 *   <li>This works because powers of two have exactly one set bit</li>
 *   <li>Return the result of the check</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(1)<br>
 * Only performs constant-time bitwise operations regardless of input size.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * No extra space used, only a few variables for computation.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>n = 0: Not a power of two, returns false</li>
 *   <li>n < 0: Negative numbers cannot be powers of two, returns false</li>
 *   <li>n = 1: 2^0 = 1, returns true</li>
 *   <li>Large powers: Works correctly up to Integer.MAX_VALUE</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/power-of-three/">Power of Three</a>
 * @see <a href="https://leetcode.com/problems/power-of-four/">Power of Four</a>
 */
public class PowerOfTwo {
    
    /**
     * Checks if a number is a power of two using optimal bit manipulation.
     *
     * @param n the integer to check
     * @return true if n is a power of two, false otherwise
     */
    public boolean isPowerOfTwo(int n) {
        // Step 1: Handle negative numbers and zero
        if (n <= 0) return false;
        
        // Step 2: Use bit manipulation trick - power of 2 has only one set bit
        // n & (n-1) removes the rightmost set bit
        // For power of 2, this results in 0
        return (n & (n - 1)) == 0;
    }
    
    /**
     * Alternative approach using division - for educational comparison.
     * 
     * <p><b>Intuition:</b> A power of two can be repeatedly divided by 2 until reaching 1.
     * 
     * <p><b>Time:</b> O(log n) - divides number by 2 each iteration
     * <br><b>Space:</b> O(1)
     * <br><b>Trade-off:</b> Easier to understand but slower than bit manipulation O(1)
     *
     * @param n the integer to check
     * @return true if n is a power of two, false otherwise
     */
    public boolean powerOfTwoDivision(int n) {
        // Step 1: Handle edge case
        if (n <= 0) return false;
        
        // Step 2: Keep dividing by 2
        while (n > 1) {
            // If not divisible by 2, not a power of 2
            if (n % 2 != 0) return false;
            n = n / 2;
        }
        
        // Step 3: If we reached 1, it's a power of 2
        return true;
    }
}
