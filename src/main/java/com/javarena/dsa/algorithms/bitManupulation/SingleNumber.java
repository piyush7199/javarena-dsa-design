package com.javarena.dsa.algorithms.bitManupulation;

/**
 * Single Number
 *
 * <p><b>Problem Statement:</b><br>
 * Given non-empty array where every element appears twice except one,
 * find that single element. Must use O(N) time and O(1) space.
 *
 * <p><b>Intuition & Approach:</b><br>
 * XOR bitwise operation properties:
 * - XOR of number with itself = 0 (a ⊕ a = 0)
 * - XOR of number with 0 = number (a ⊕ 0 = a)
 * - XOR is commutative and associative
 * - XOR all numbers: duplicates cancel out, single remains
 * 
 * Process:
 * - Initialize result = 0
 * - XOR all numbers with result
 * - Pairs cancel (a ⊕ a = 0), single number remains
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(1) - Only one variable
 */
public class SingleNumber {
    
    /**
     * Finds the single non-duplicate number using XOR.
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
