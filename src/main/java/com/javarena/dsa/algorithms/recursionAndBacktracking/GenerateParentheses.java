package com.javarena.dsa.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate Parentheses
 *
 * <p><b>Problem Statement:</b><br>
 * Given n pairs of parentheses, generate all combinations of well-formed parentheses.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use backtracking to build valid parentheses combinations
 * - At any point, we can add '(' if we haven't used all n open brackets
 * - We can add ')' only if number of open brackets used > close brackets used
 * - This ensures we only generate valid combinations (no need for validation)
 * - Track remaining open and close brackets
 * - When both reach 0, add the combination to result
 * - Use StringBuilder for efficient string building, backtrack by removing last character
 *
 * <p><b>Time Complexity:</b> O(4^N / √N) - Catalan number, generates all valid combinations
 * <br><b>Space Complexity:</b> O(N) - Recursion depth (worst case 2n)
 */
public class GenerateParentheses {
    /**
     * Generates all combinations of well-formed parentheses.
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
