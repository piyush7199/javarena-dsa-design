package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Sum of Two Integers
 *
 * <p><b>Problem Statement:</b><br>
 * Add two integers without using + or - operators.
 * Must use only bitwise operations.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Simulate binary addition using bitwise operations:
 * - XOR gives sum without carry: 1^1=0, 1^0=1, 0^0=0
 * - AND gives carry positions: 1&1=1 (carry), others=0
 * - Left shift AND by 1 to get actual carry value
 * - Repeat: add sum and carry until no carry remains
 * 
 * Process:
 * 1. sum = a ^ b (add without carry)
 * 2. carry = (a & b) << 1 (find and shift carry)
 * 3. Repeat with a=sum, b=carry until carry=0
 * 
 * This mimics hardware binary adder circuits.
 *
 * <p><b>Time Complexity:</b> O(log N) - At most log N iterations (bit length)
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class SumOfTwoIntegers {
    
    /**
     * Adds two integers using bitwise operations.
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;  // Find carry
            a = a ^ b;                  // Sum without carry
            b = carry;                  // Next iteration carry
        }
        return a;
    }
}
