package com.javarena.dsa.algorithms.miscellaneous;

/**
 * 342. Power of Four
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/power-of-four/">LeetCode - Power of Four</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Math, Bit Manipulation, Recursion
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 * An integer n is a power of four if there exists an integer x such that n == 4^x.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: n = 16
 * Output: true
 * Explanation: 4^2 = 16
 *
 * Input: n = 5
 * Output: false
 *
 * Input: n = 1
 * Output: true
 * Explanation: 4^0 = 1
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Iterative division approach:
 * - Powers of 4: 1, 4, 16, 64, 256, 1024, ...
 * - If n is power of 4, repeatedly dividing by 4 will eventually give 1
 * - If at any point n is not divisible by 4, it's not a power of 4
 * - Handle edge case: n < 1 cannot be power of 4
 * - Alternative: bit manipulation (n is power of 2 AND only set bit at even position)
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Quick check: if n < 1, return false</li>
 *   <li>While n is divisible by 4:</li>
 *   <li>- Divide n by 4</li>
 *   <li>- Continue until n is no longer divisible</li>
 *   <li>After loop, check if n == 1</li>
 *   <li>If yes, original n was power of 4; else it wasn't</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(log₄ n)<br>
 * We divide n by 4 each iteration, so at most log₄(n) iterations.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses constant extra space for loop variable.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>n = 1: True (4^0 = 1)</li>
 *   <li>n = 0 or negative: False</li>
 *   <li>n is power of 2 but not 4 (e.g., 2, 8, 32): False</li>
 *   <li>Large powers: Handled correctly by repeated division</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/power-of-two/">Power of Two</a>
 */
public class PowerOfFour {
    
    /**
     * Checks if n is a power of four.
     *
     * @param n the integer to check
     * @return true if n is a power of four, false otherwise
     */
    public boolean isPowerOfFour(int n) {
        // Step 1: Powers of 4 must be positive
        if (n < 1) return false;

        // Step 2: Repeatedly divide by 4 while possible
        while (n % 4 == 0) {
            n /= 4;
        }

        // Step 3: If we reached 1, original n was power of 4
        return n == 1;
    }
}
