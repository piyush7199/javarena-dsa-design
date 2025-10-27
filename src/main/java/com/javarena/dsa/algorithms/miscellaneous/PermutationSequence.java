package com.javarena.dsa.algorithms.miscellaneous;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. Permutation Sequence
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/permutation-sequence/">LeetCode - Permutation Sequence</a>
 *
 * <p><b>Difficulty:</b> Hard
 *
 * <p><b>Topics:</b> Math, Recursion, Backtracking
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations. By listing and labeling 
 * all of the permutations in order, we get the following sequence for n = 3:
 * "123", "132", "213", "231", "312", "321"
 * Given n and k, return the kth permutation sequence.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: n = 3, k = 3
 * Output: "213"
 *
 * Input: n = 4, k = 9
 * Output: "2314"
 *
 * Input: n = 3, k = 1
 * Output: "123"
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Direct construction using factorial number system:
 * - Total permutations = n!
 * - Permutations are divided into blocks based on first digit
 * - Each block has (n-1)! permutations
 * - For n=4: first 6 (3!) start with 1, next 6 with 2, etc.
 * - Use k to determine which block → which digit to pick
 * - Repeat process for remaining positions
 * - Convert k to 0-indexed for easier math
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Convert k to 0-indexed (k--)</li>
 *   <li>Initialize list of available numbers [1, 2, ..., n]</li>
 *   <li>Precompute factorials: factorial[i] = i!</li>
 *   <li>For each position from left to right:</li>
 *   <li>- Calculate block size = (n - currentPosition)!</li>
 *   <li>- Find index of digit to pick = k / blockSize</li>
 *   <li>- Append that digit to result</li>
 *   <li>- Remove digit from available list</li>
 *   <li>- Update k = k % blockSize for next iteration</li>
 *   <li>Add last remaining digit</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n²)<br>
 * For each of n positions, we remove from ArrayList which is O(n) in worst case.
 *
 * <p><b>Space Complexity:</b> O(n)<br>
 * Space for factorial array and available numbers list.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>k = 1: First permutation (123...n)</li>
 *   <li>k = n!: Last permutation (n...321)</li>
 *   <li>n = 1: Only one permutation "1"</li>
 *   <li>Small k: Early positions have smaller digits</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/next-permutation/">Next Permutation</a>
 */
public class PermutationSequence {
    
    /**
     * Returns the kth permutation sequence of numbers from 1 to n.
     *
     * @param n size of the set {1, 2, ..., n}
     * @param k index of permutation (1-indexed)
     * @return kth permutation sequence as a string
     */
    public String getPermutation(int n, int k) {
        // Step 1: Convert to 0-indexing for easier calculations
        k--;

        // Step 2: Initialize available numbers [1, 2, ..., n]
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }

        StringBuilder ans = new StringBuilder();

        // Step 3: Precompute factorial values
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // Step 4: Build result digit by digit
        for (int cnt = 1; cnt < n; cnt++) {
            // Block size for current position
            int pattern = factorial[n - cnt]; // (n - cnt)!
            
            // Index of digit to pick from available numbers
            int ind = k / pattern;
            
            // Reduce k for next iteration
            k -= pattern * ind;

            // Append chosen digit
            ans.append(nums.get(ind));
            
            // Remove used digit from available numbers
            nums.remove(ind);
        }

        // Step 5: Add the last remaining number
        ans.append(nums.get(0));

        return ans.toString();
    }
}
