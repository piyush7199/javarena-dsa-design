package org.example.coding.datastructures.arrays;

import java.util.Map;

public class RomanToInt {

    /**
     * Converts a Roman numeral string to its integer value.
     *
     * <p>This method traverses the input string and uses subtraction logic:
     * if a smaller-value symbol precedes a larger-value one (like "IV" or "IX"),
     * we subtract the smaller value; otherwise, we add it.</p>
     * <p>
     * Time Complexity: O(n), where n is the length of the string.
     * Each character is processed once.
     * <p>
     * Space Complexity: O(1)
     * Only a constant-size map and a few integer variables are used.
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
