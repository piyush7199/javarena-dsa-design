package com.javarena.dsa.algorithms.miscellaneous;

/**
 * 2177. Find Three Consecutive Integers That Sum to a Given Number
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/find-three-consecutive-integers-that-sum-to-a-given-number/">LeetCode - Three Consecutive Integers</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> Math
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer num, return three consecutive integers (as a sorted array) that sum to num. 
 * If num cannot be expressed as the sum of three consecutive integers, return an empty array.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: num = 33
 * Output: [10,11,12]
 * Explanation: 10 + 11 + 12 = 33
 *
 * Input: num = 4
 * Output: []
 * Explanation: Cannot express 4 as sum of three consecutive integers.
 *
 * Input: num = 0
 * Output: [-1,0,1]
 * Explanation: -1 + 0 + 1 = 0
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Mathematical pattern for consecutive integers:
 * - Three consecutive integers: n-1, n, n+1
 * - Sum: (n-1) + n + (n+1) = 3n
 * - Therefore, if num is divisible by 3, we can find such integers
 * - Middle number: n = num / 3
 * - The three numbers are: n-1, n, n+1
 * - If num % 3 != 0, no valid consecutive integers exist
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Check if num is divisible by 3</li>
 *   <li>If not divisible, return empty array (impossible)</li>
 *   <li>Calculate middle number: n = num / 3</li>
 *   <li>Return [n-1, n, n+1]</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(1)<br>
 * Only arithmetic operations, constant time.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only creates a fixed-size array for result.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>num = 0: Returns [-1, 0, 1]</li>
 *   <li>Negative num: Works correctly (e.g., -12 → [-5, -4, -3])</li>
 *   <li>num not divisible by 3: Returns empty array</li>
 *   <li>Large numbers: Handles within long range</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/consecutive-numbers-sum/">Consecutive Numbers Sum</a>
 */
public class ThreeConsecutiveInt {
    
    /**
     * Finds three consecutive integers that sum to num.
     *
     * @param num the target sum
     * @return array of three consecutive integers summing to num, or empty if impossible
     */
    public long[] sumOfThree(long num) {
        // Step 1: Check divisibility by 3
        // Three consecutive: (n-1) + n + (n+1) = 3n
        // So num must be divisible by 3
        if (num % 3 != 0) return new long[]{};
        
        // Step 2: Calculate middle number
        long n = num / 3;
        
        // Step 3: Return the three consecutive integers
        return new long[]{n - 1, n, n + 1};
    }
}
