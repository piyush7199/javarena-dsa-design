package com.javarena.dsa.datastructures.string;

import java.util.Arrays;

/**
 * Reordered Power of 2
 *
 * <p><b>Problem Statement:</b><br>
 * Check if digits of given number can be reordered to form power of 2.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Digit frequency comparison:
 * - Sort digits of input number
 * - Generate all powers of 2 (2^0 to 2^30 covers all 32-bit integers)
 * - Sort digits of each power of 2
 * - If any matches input's sorted digits, return true
 * 
 * Key: Two numbers are anagrams if sorted digits match.
 * Only 31 powers of 2 to check (efficient).
 *
 * <p><b>Time Complexity:</b> O(31 × log D) - 31 powers, D = digits, sorting
 * <br><b>Space Complexity:</b> O(log D) - Store sorted digit strings
 */
public class ReorderedPowerOfTwo {
    /**
     * Checks if number can be rearranged to power of 2.
     */
    public boolean reorderedPowerOf2(int n) {
        String target = sortDigits(n);

        // Check all 31 possible powers of 2 (2^0 to 2^30)
        for (int i = 0; i < 31; i++) {
            int powerOfTwo = 1 << i; // 2^i
            if (sortDigits(powerOfTwo).equals(target)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Sorts digits of number into string.
     */
    public String sortDigits(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
