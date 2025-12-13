package com.javarena.dsa.datastructures.arrays;

/**
 * Remove Outermost Parentheses
 *
 * <p><b>Problem Statement:</b><br>
 * Given a valid parentheses string, remove the outermost parentheses of every primitive decomposition.
 * A primitive string is a valid parentheses string that cannot be split into two non-empty valid strings.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Track nesting depth with counter
 * - When encountering '(':
 *   - If depth > 0 (not outermost), add to result
 *   - Increment depth
 * - When encountering ')':
 *   - Decrement depth
 *   - If depth > 0 (not outermost), add to result
 * - Outermost parentheses are those at depth 0 (before increment) or depth 0 (after decrement)
 * - This automatically separates primitive strings and removes their outer layer
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through string
 * <br><b>Space Complexity:</b> O(N) - StringBuilder for result
 */
public class RemoveOutermostParentheses {
    /**
     * Removes outermost parentheses from primitive decompositions.
     */
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (depth > 0) {
                    result.append(c);
                }
                depth++;
            } else {
                depth--;
                if (depth > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
