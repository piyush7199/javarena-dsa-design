package com.javarena.dsa.datastructures.string;

/**
 * Largest Odd Number in String
 *
 * <p><b>Problem Statement:</b><br>
 * Given string representing number, return largest odd-valued substring.
 * Substring must be contiguous from start.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Key insight: Number is odd if last digit is odd.
 * - Traverse from right to left
 * - Find first odd digit (1, 3, 5, 7, 9)
 * - Return substring from start to that position
 * - If no odd digit found, return empty string
 * 
 * Greedy: Rightmost odd digit gives largest odd number.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass from right
 * <br><b>Space Complexity:</b> O(1) - No extra space
 */
public class LargestOdd {
    /**
     * Finds largest odd number in string.
     */
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            char ch = num.charAt(i);
            if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9') {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}
