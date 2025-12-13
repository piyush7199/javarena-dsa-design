package com.javarena.dsa.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate Binary Strings Without Adjacent Zeros
 *
 * <p><b>Problem Statement:</b><br>
 * You are given a positive integer n. A binary string x is valid if all substrings of x of length 2
 * contain at least one "1". Return all valid strings with length n, in any order.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use backtracking to build strings character by character
 * - Always safe to add '1' at any position
 * - Only add '0' if previous character is not '0' (or string is empty)
 * - Use StringBuilder for efficient string building
 * - When string reaches length n, add to result list
 * - Backtrack by removing last character to explore other possibilities
 *
 * <p><b>Time Complexity:</b> O(2^N) - Generate all valid binary strings
 * <br><b>Space Complexity:</b> O(N) - Recursion depth and StringBuilder storage
 */
public class GenerateBinaryString {
    
    /**
     * Generates all valid binary strings of length n without adjacent zeros.
     *
     * @param n length of binary strings to generate
     * @return list of all valid binary strings
     */
    public List<String> validStrings(int n) {
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        helper(sb, n, ans);
        return ans;
    }

    /**
     * Helper method for backtracking to generate valid strings.
     *
     * @param sb current string being built
     * @param n remaining characters to add
     * @param ans result list
     */
    private void helper(StringBuilder sb, int n, List<String> ans) {
        if (n == 0) {
            ans.add(sb.toString());
            return;
        }

        sb.append("1");
        helper(sb, n - 1, ans);
        sb.deleteCharAt(sb.length() - 1);
        if (sb.isEmpty() || sb.charAt(sb.length() - 1) != '0') {
            sb.append("0");
            helper(sb, n - 1, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
