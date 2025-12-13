package com.javarena.dsa.datastructures.arrays;

/**
 * Integer to Roman
 *
 * <p><b>Problem Statement:</b><br>
 * Convert an integer to its Roman numeral representation using symbols I, V, X, L, C, D, M.
 * Roman numerals include subtractive cases like IV (4), IX (9), XL (40), XC (90), CD (400), CM (900).
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Create arrays of values and corresponding symbols in descending order
 * - Include both standard (M=1000, D=500) and subtractive pairs (CM=900, CD=400)
 * - Greedily subtract largest possible value from num
 * - Append corresponding symbol to result
 * - Repeat until num becomes 0
 * - This ensures we build the shortest valid Roman numeral
 *
 * <p><b>Time Complexity:</b> O(1) - Fixed 13 values, bounded iterations
 * <br><b>Space Complexity:</b> O(1) - Fixed size arrays and string builder
 */
public class IntegerToRoman {

    /**
     * Converts integer to Roman numeral.
     */
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (num == 0) break;

            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }

        return sb.toString();
    }
}
