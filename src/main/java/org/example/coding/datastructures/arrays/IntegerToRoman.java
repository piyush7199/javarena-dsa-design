package org.example.coding.datastructures.arrays;

public class IntegerToRoman {

    /**
     * Converts an integer to its Roman numeral representation.
     *
     * <p>Roman numerals are based on seven symbols:
     * <pre>
     * Symbol    Value
     *   I         1
     *   V         5
     *   X         10
     *   L         50
     *   C         100
     *   D         500
     *   M         1000
     * </pre>
     * This method subtracts the largest possible values successively
     * from the given integer while appending the corresponding Roman symbols.
     * <p>
     * Time Complexity: O(1)
     * The number of iterations is bounded by the number of Roman numeral values (13),
     * which does not change based on the input size.
     * <p>
     * Space Complexity: O(1)
     * The space used is constant (string builder and fixed arrays).
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
