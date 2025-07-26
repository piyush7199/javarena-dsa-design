package org.example.coding.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    /**
     * Generates all combinations of well-formed parentheses given n pairs.
     *
     * <h3>Intuition:</h3>
     * We use backtracking to explore all possible combinations of '(' and ')'.
     * At any point:
     * - We can add an open bracket '(' if we haven't used up all `n` open brackets.
     * - We can add a closing bracket ')' if the number of open brackets used so far
     * is more than the number of close brackets.
     * <p>
     * This ensures that we only build valid combinations without needing to validate them after generation.
     *
     * <h3>Approach:</h3>
     * - Use recursion with backtracking.
     * - Keep track of the number of open and close brackets remaining.
     * - Add '(' if open > 0
     * - Add ')' if close > open (i.e., we can't close more than we opened)
     * - Stop and add to result when both open and close are 0.
     *
     * <h3>Time Complexity:</h3>
     * O(2^2n) in the worst case, but tighter bound is **Catalan number**, which is O(4^n / sqrt(n)).
     * Each valid combination has length 2n.
     *
     * <h3>Space Complexity:</h3>
     * O(2n) for recursion stack and StringBuilder during construction (worst case depth = 2n)
     * O(Catalan(n)) * 2n for storing all valid strings.
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(n, n, new StringBuilder(), ans);
        return ans;
    }

    private void backtrack(int open, int close, StringBuilder sb, List<String> ans) {
        if (open == 0 && close == 0) {
            ans.add(sb.toString());
            return;
        }

        if (open > 0) {
            sb.append('(');
            backtrack(open - 1, close, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (close > open) {
            sb.append(')');
            backtrack(open, close - 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
