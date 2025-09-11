package org.example.coding.algorithms.recursionAndBacktracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindUniqueBinaryString {
    /**
     * Solution to find a unique binary string of length n
     * that is not present in the given array nums.
     *
     * <p>There are two approaches explained below:
     *
     * <h2>Approach 1: Backtracking (Brute Force)</h2>
     * <ul>
     *   <li>Use a HashSet to store all existing binary strings for O(1) lookups.</li>
     *   <li>Generate all possible binary strings of length n using recursion.</li>
     *   <li>At each index, try placing '0' and '1'.</li>
     *   <li>Once a generated string is not in the set, return it as the answer.</li>
     * </ul>
     *
     * <b>Time Complexity:</b> O(2^n * n)
     * <ul>
     *   <li>There are 2^n possible binary strings of length n.</li>
     *   <li>Each string takes O(n) to generate and check.</li>
     * </ul>
     *
     * <b>Space Complexity:</b> O(2^n + n)
     * <ul>
     *   <li>O(2^n) for the recursion call stack in the worst case.</li>
     *   <li>O(n) for the character array used to build strings.</li>
     *   <li>O(n) extra for the HashSet to store input strings.</li>
     * </ul>
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
     * <h2>Approach 2: Cantor's Diagonalization (Optimal)</h2>
     * <ul>
     *   <li>For each index i, flip the i-th character of nums[i].</li>
     *   <li>This guarantees the constructed string differs from each string at index i.</li>
     *   <li>Thus, the generated string cannot match any string in nums.</li>
     * </ul>
     *
     * <b>Time Complexity:</b> O(n)
     * <ul>
     *   <li>We loop through the array once and build the result string in O(n).</li>
     * </ul>
     *
     * <b>Space Complexity:</b> O(n)
     * <ul>
     *   <li>O(n) for the StringBuilder used to construct the result.</li>
     * </ul>
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
