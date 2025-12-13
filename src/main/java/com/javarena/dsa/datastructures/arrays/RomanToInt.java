package com.javarena.dsa.datastructures.arrays;

import java.util.Map;

/**
 * Roman to Integer
 *
 * <p><b>Problem Statement:</b><br>
 * Convert a Roman numeral string to its integer value. Roman numerals use symbols I, V, X, L, C, D, M
 * with subtractive notation (IV=4, IX=9, XL=40, XC=90, CD=400, CM=900).
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Create map of Roman symbols to values
 * - Traverse string left to right
 * - For each character, compare with next:
 *   - If current < next: subtract current (subtractive case like IV)
 *   - Otherwise: add current (normal case)
 * - Example: "XIV" = X(10) + I(-1) + V(5) = 14
 * - Example: "IX" = I(-1) + X(10) = 9
 * - This handles all subtractive pairs automatically
 *
 * <p><b>Time Complexity:</b> O(N) where N = length of string
 * <br><b>Space Complexity:</b> O(1) - Fixed size map
 */
public class RomanToInt {

    /**
     * Converts Roman numeral to integer.
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = Map.of(
                'I', 1, 'V', 5, 'X', 10,
                'L', 50, 'C', 100, 'D', 500, 'M', 1000
        );
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = map.get(s.charAt(i));
            int next = (i + 1 < s.length()) ? map.get(s.charAt(i + 1)) : 0;

            if (curr < next) {
                result -= curr;
            } else {
                result += curr;
            }
        }
        return result;
    }
}
