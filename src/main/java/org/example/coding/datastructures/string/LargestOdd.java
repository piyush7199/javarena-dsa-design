package org.example.coding.datastructures.string;

public class LargestOdd {
    /**
     * Finds the largest odd-numbered substring from the left of the given number.
     * <p>
     * Intuition:
     * - Traverse from end to find the last odd digit.
     * - Return substring from 0 to that index.
     * <p>
     * Time Complexity: O(n), where n is the length of the string.
     * Space Complexity: O(1), no extra space apart from return string.
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
