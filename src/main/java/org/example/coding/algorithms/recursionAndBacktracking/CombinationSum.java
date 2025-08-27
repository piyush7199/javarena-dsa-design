package org.example.coding.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    /**
     * Problem Intuition:
     * ------------------
     * Given an array of distinct integers (candidates) and a target integer,
     * find all unique combinations of candidates where the chosen numbers sum up to the target.
     * A number may be used multiple times.
     * <p>
     * Example:
     * --------
     * candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]]
     * <p>
     * Approach:
     * ---------
     * 1. Use backtracking (DFS) to explore combinations.
     * 2. At each step, we have two choices:
     * - Include the current number (stay on the same index since repetitions are allowed).
     * - Skip the current number (move to the next index).
     * 3. If the target becomes 0, we found a valid combination → add it to the answer list.
     * 4. If target < 0 or we run out of numbers, backtrack.
     * <p>
     * Time Complexity:
     * ----------------
     * - Worst case: O(2^T) where T = target / min(candidates)
     * (since each recursive call can branch into "include" or "exclude").
     * - Each valid combination requires O(k) time to copy into the result,
     * where k is the length of the combination.
     * - So overall: O(N^(T/minVal)) in the worst case (exponential).
     * <p>
     * Space Complexity:
     * -----------------
     * - O(target/minVal) recursion depth (stack).
     * - O(k) extra space for storing current combination.
     * - Final result storage depends on the number of valid combinations.
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
