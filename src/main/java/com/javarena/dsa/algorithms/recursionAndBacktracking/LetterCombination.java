package com.javarena.dsa.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Letter Combinations of a Phone Number
 *
 * <p><b>Problem Statement:</b><br>
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations
 * that the number could represent. The mapping of digits to letters (similar to telephone buttons) is:
 * 2-abc, 3-def, 4-ghi, 5-jkl, 6-mno, 7-pqrs, 8-tuv, 9-wxyz
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use backtracking to explore all possible combinations character by character
 * - Create a mapping of digits to their corresponding characters (like phone keypad)
 * - For each digit in the input, try all possible characters it maps to
 * - Use StringBuilder for efficient string building
 * - When all digits are processed, add the combination to result
 * - Backtrack by removing last character to explore other possibilities
 *
 * <p><b>Time Complexity:</b> O(4^N) - N is length of digits, each digit maps to at most 4 characters
 * <br><b>Space Complexity:</b> O(N) - Recursion stack depth
 */
public class LetterCombination {
    /**
     * Returns all possible letter combinations that the number could represent.
     * <p>
     * Intuition:
     * - Use a backtracking approach to explore every possible character combination.
     * - For each digit, pick a character from the mapping and recursively build the string.
     * - Use a StringBuilder to efficiently manage character additions/removals during recursion.
     * <p>
     * Time Complexity: O(4^n), where n is the length of `digits`.
     * - Each digit can map to at most 4 characters ('7' and '9').
     * - There are 4^n total combinations in the worst case.
     * <p>
     * Space Complexity: O(n) for the recursion stack (backtracking depth).
     */
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.isEmpty()) return new ArrayList<>();
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
        combinations(digits, 0, new StringBuilder(), ans, map);
        return ans;
    }

    private void combinations(String digit, int ind, StringBuilder sb,
                              List<String> ans, HashMap<Character, char[]> map) {
        if (digit.length() == ind) {
            ans.add(sb.toString());
            return;
        }

        char[] chars = map.get(digit.charAt(ind));
        for (char ch : chars) {
            sb.append(ch);
            combinations(digit, ind + 1, sb, ans, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
