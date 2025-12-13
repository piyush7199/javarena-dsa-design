package com.javarena.dsa.algorithms.miscellaneous;

/**
 * Maximum 69 Number
 *
 * <p><b>Problem Statement:</b><br>
 * Given positive integer num consisting only of digits 6 and 9,
 * return maximum number by changing at most one digit (6→9 or 9→6).
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy strategy for maximization:
 * - To maximize, change 6→9 (never 9→6, that decreases value)
 * - Leftmost digits have more weight (1000s > 100s)
 * - Change LEFTMOST 6 to 9 for maximum gain
 * - Find leftmost 6 position, add 3 × 10^position
 * - If no 6 exists, return original
 * 
 * Implementation:
 * - Traverse digits right to left (modulo/division)
 * - Track position of each digit
 * - Record last 6 found (will be leftmost)
 * - Add 3 × 10^position to change that 6→9
 *
 * <p><b>Time Complexity:</b> O(log N) - Process each digit once
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class Maximum69Number {
    
    /**
     * Returns maximum number by changing at most one digit.
     */
    public int maximum69Number(int num) {
        int currIndex = 0;
        int leftMostSixIndex = -1;
        int temp = num;
        
        while (temp > 0) {
            if (temp % 10 == 6) {
                leftMostSixIndex = currIndex;
            }
            temp /= 10;
            currIndex++;
        }
        
        if (leftMostSixIndex != -1) {
            return num + (int) Math.pow(10, leftMostSixIndex) * 3;
        }
        
        return num;
    }
}
