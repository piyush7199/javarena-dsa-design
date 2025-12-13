package com.javarena.dsa.algorithms.recursionAndBacktracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Word Break
 *
 * <p><b>Problem Statement:</b><br>
 * Given a string s and a dictionary of strings wordDict, determine if s can be segmented into
 * a space-separated sequence of one or more dictionary words.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use backtracking to try all possible ways to split the string
 * - At each index, try all substrings starting from that index
 * - If substring exists in dictionary, recursively check remaining string
 * - If we reach end of string, we found a valid segmentation
 * - Optimization: Use memoization (DP) to avoid recomputing same subproblems
 * - dp[i] stores whether substring from index i to end can be segmented
 * - Convert wordDict to HashSet for O(1) lookup
 *
 * <p><b>Time Complexity:</b> O(N²) with memoization, O(2^N) without
 * <br><b>Space Complexity:</b> O(N) - DP array and recursion stack
 */
public class WordBreak {

    /**
     * Backtracking approach (inefficient for large inputs).
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        return solve(s, wordSet, 0);
    }

    /**
     * Recursive helper for backtracking solution.
     */
    public boolean solve(String s, Set<String> wordSet, int i) {
        if (i == s.length()) {
            return true;
        }
        for (int k = i; k < s.length(); k++) {
            String word = s.substring(i, k + 1);
            if (wordSet.contains(word)) {
                if (solve(s, wordSet, k + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Optimized DP (Memoization) approach to solve Word Break problem.
     * <p>
     * Intuition:
     * - Use top-down dynamic programming to avoid re-computing results for the same index.
     * - At each index `i`, check all substrings `s[i...j]`.
     * - If a substring is in the dictionary and the remaining string from `j+1` is segmentable,
     * memoize and return true.
     * <p>
     * Time Complexity: O(n²) — for each index, checking up to n substrings.
     * Space Complexity: O(n) — for the DP array and recursion stack.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return helper(s, set, 0, n, dp);
    }

    /**
     * Recursive DP helper method for word break with memoization.
     */
    private boolean helper(String s, Set<String> set, int i, int n, int[] dp) {
        if (i == n) return true;
        if (dp[i] != -1) return dp[i] == 1;
        for (int j = i; j < n; j++) {
            String s2 = s.substring(i, j + 1);
            if (set.contains(s2)) {
                if (helper(s, set, j + 1, n, dp)) {
                    dp[i] = 1;
                    return true;
                }
            }
        }
        dp[i] = 2;
        return false;
    }

}
