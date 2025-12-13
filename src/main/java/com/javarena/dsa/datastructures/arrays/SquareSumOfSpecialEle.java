package com.javarena.dsa.datastructures.arrays;

/**
 * Sum of Squares of Special Elements
 *
 * <p><b>Problem Statement:</b><br>
 * Given an array nums, return the sum of squares of all special elements. An element nums[i] is special 
 * if i+1 divides n (length of array), i.e., n % (i+1) == 0 (1-indexed).
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Iterate through array with 0-indexed positions
 * - Check if n is divisible by (i+1) - converts to 1-indexed
 * - If divisible, element at position i is "special"
 * - Add square of special element to result
 * - Example: n=4, special positions are 1,2,4 (1-indexed) = indices 0,1,3
 * - Simple modulo check determines divisibility
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(1) - Only accumulator variable
 */
public class SquareSumOfSpecialEle {
    /**
     * Calculates sum of squares of special elements.
     */
    public int sumOfSquares(int[] nums) {
        int ans = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) {  // Check if position (i+1) divides n
                ans += (nums[i] * nums[i]);
            }
        }

        return ans;
    }
}
