package com.javarena.dsa.algorithms.miscellaneous;

/**
 * 1323. Maximum 69 Number
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/maximum-69-number/">LeetCode - Maximum 69 Number</a>
 *
 * <p><b>Difficulty:</b> Easy
 *
 * <p><b>Topics:</b> Math, Greedy
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * You are given a positive integer num consisting only of digits 6 and 9.
 * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: num = 9669
 * Output: 9969
 * Explanation: Change the first 6 to 9.
 *
 * Input: num = 9996
 * Output: 9999
 * Explanation: Change the last 6 to 9.
 *
 * Input: num = 9999
 * Output: 9999
 * Explanation: No 6 to change, number is already maximum.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Greedy strategy for maximization:
 * - To maximize, we want to change a 6 to 9 (never 9 to 6, that decreases value)
 * - Leftmost digits have more weight (e.g., 1000s > 100s)
 * - Therefore, change the LEFTMOST 6 to 9 for maximum gain
 * - Find leftmost 6's position, add 3 × 10^position to original number
 * - If no 6 exists, return original number
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Traverse digits from right to left (using modulo and division)</li>
 *   <li>Track position of each digit (0-indexed from right)</li>
 *   <li>If digit is 6, update leftMostSixIndex (last one found will be leftmost)</li>
 *   <li>After traversal, if any 6 was found:</li>
 *   <li>- Add 3 × 10^leftMostSixIndex to num (changes 6→9 at that position)</li>
 *   <li>Else, return num unchanged</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(log n)<br>
 * Process each digit of num once, which is log₁₀(num) digits.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses constant extra variables for tracking position and index.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>All 9s: No change needed, return as is</li>
 *   <li>All 6s: Change leftmost (most significant) 6</li>
 *   <li>Single digit: Either 6→9 or stay as 9</li>
 *   <li>6 at leftmost position: Maximum impact change</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/maximum-swap/">Maximum Swap</a>
 */
public class Maximum69Number {
    
    /**
     * Returns maximum number by changing at most one digit (6→9 or 9→6).
     *
     * @param num positive integer consisting only of 6s and 9s
     * @return maximum possible number after at most one digit change
     */
    public int maximum69Number(int num) {
        int currIndex = 0;
        int leftMostSixIndex = -1;

        // Step 1: Find leftmost 6 (traverse right to left)
        int temp = num;
        while (temp > 0) {
            // Step 2: Check if current digit is 6
            if (temp % 10 == 6) {
                // Update index (last found will be leftmost)
                leftMostSixIndex = currIndex;
            }
            
            // Move to next digit
            temp /= 10;
            currIndex++;
        }

        // Step 3: If any 6 found, change it to 9
        if (leftMostSixIndex != -1) {
            // Add 3 × 10^position (difference between 9 and 6)
            return num + (int) Math.pow(10, leftMostSixIndex) * 3;
        }

        // Step 4: No 6 found, return original
        return num;
    }
}
