package org.example.coding.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecursionWordBreak {

    /**
     * Backtracking approach for the Word Break problem (inefficient for large inputs).
     * <p>
     * Intuition:
     * - Try every possible substring starting from index `i`.
     * - If the substring exists in the dictionary, recursively check the remaining string.
     * - If the end of the string is reached, a valid segmentation is found.
     * <p>
     * Time Complexity: O(2^n) — each index can either be split or not, leading to exponential time.
     * Space Complexity: O(n) — recursion stack depth in the worst case.
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
