package com.javarena.dsa.algorithms.recursionAndBacktracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Find Unique Binary String
 *
 * <p><b>Problem Statement:</b><br>
 * Given an array of binary strings nums of length n, return a binary string of length n
 * that does not appear in nums.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Approach 1 (Backtracking):
 * - Store all existing strings in a HashSet for O(1) lookup
 * - Generate binary strings recursively, trying '0' and '1' at each position
 * - Return first string not present in the set
 *
 * Approach 2 (Cantor's Diagonalization - Optimal):
 * - For each index i, flip the i-th character of nums[i]
 * - This guarantees the result differs from each input string at position i
 * - Much faster and elegant solution
 *
 * <p><b>Time Complexity:</b> O(2^N * N) for backtracking, O(N) for diagonalization
 * <br><b>Space Complexity:</b> O(N) for both approaches
 */
public class FindUniqueBinaryString {
    /**
     * Solution using backtracking to find a unique binary string.
     */
    public String findDifferentBinaryString(String[] nums) {
        Set<String> st = new HashSet<>(Arrays.asList(nums));
        int n = nums.length;
        char[] ans = new char[n];

        // Initialize ans with '0'
        for (int i = 0; i < n; i++) ans[i] = '0';

        // Use backtracking to find a valid string
        isValid(st, ans, n - 1);
        return new String(ans);
    }

    /**
     * Recursive helper function to generate binary strings and check if it's valid.
     *
     * @param st  HashSet containing all input strings for O(1) lookup
     * @param ans current candidate binary string as a character array
     * @param ind current index being processed
     * @return true if a valid unique binary string is found, false otherwise
     */
    private boolean isValid(Set<String> st, char[] ans, int ind) {
        if (ind < 0) {
            // Base case: check if current string is not in the set
            return !st.contains(new String(ans));
        }

        char original = ans[ind];

        // Try placing '0'
        ans[ind] = '0';
        if (isValid(st, ans, ind - 1)) return true;

        // Try placing '1'
        ans[ind] = '1';
        if (isValid(st, ans, ind - 1)) return true;

        // Backtrack to original value
        ans[ind] = original;
        return false;
    }

    /**
     * Optimal solution using Cantor's Diagonalization.
     */
    public String findDifferentBinaryStringEffi(String[] nums) {
        StringBuilder ans = new StringBuilder();

        // Loop through each index
        for (int i = 0; i < nums.length; i++) {

            // Look at nums[i]'s ith character
            if (nums[i].charAt(i) == '1') {
                ans.append('0');  // flip 1 -> 0
            } else {
                ans.append('1');  // flip 0 -> 1
            }
        }

        return ans.toString();
    }
}
