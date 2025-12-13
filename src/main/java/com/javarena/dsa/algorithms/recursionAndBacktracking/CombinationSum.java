package com.javarena.dsa.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Combination Sum
 *
 * <p><b>Problem Statement:</b><br>
 * Given an array of distinct integers candidates and a target integer, find all unique combinations
 * of candidates where the chosen numbers sum up to the target. The same number may be used unlimited times.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use backtracking (DFS) to explore all possible combinations
 * - At each step, two choices: include current number (stay at same index) or skip it (move to next)
 * - If target becomes 0, found a valid combination - add to result
 * - If target becomes negative or no more candidates, backtrack
 * - Since we can reuse numbers, stay at same index when including a number
 * - Move to next index only when excluding current number
 *
 * <p><b>Time Complexity:</b> O(N^(T/M)) - N=candidates, T=target, M=min(candidates)
 * <br><b>Space Complexity:</b> O(T/M) - Recursion depth and temp combination storage
 */
public class CombinationSum {
    /**
     * Finds all unique combinations that sum to target.
     */
    private List<List<Integer>> ans;

    /**
     * Finds all unique combinations of candidates that sum to the target.
     *
     * @param candidates array of distinct positive integers
     * @param target     target sum
     * @return list of unique combinations
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        helper(candidates, target, 0, new ArrayList<>());
        return ans;
    }

    /**
     * Backtracking helper function to explore combinations.
     *
     * @param candidates input numbers
     * @param target     remaining sum to achieve
     * @param ind        current index in candidates
     * @param res        current combination being built
     */
    private void helper(int[] candidates, int target, int ind, List<Integer> res) {
        // Base case: exact target achieved → add combination to result
        if (target == 0) {
            ans.add(new ArrayList<>(res));
            return;
        }

        // Base case: out of numbers OR target exceeded
        if (ind == candidates.length || target < 0) {
            return;
        }

        // Choice 1: include current candidate (if valid)
        if (candidates[ind] <= target) {
            res.add(candidates[ind]); // choose
            helper(candidates, target - candidates[ind], ind, res); // stay at same index
            res.remove(res.size() - 1); // backtrack
        }

        // Choice 2: skip current candidate, move to next
        helper(candidates, target, ind + 1, res);
    }
}
