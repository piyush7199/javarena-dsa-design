package org.example.coding.algorithms.miscellaneous;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    /**
     * Returns the k-th permutation sequence of numbers from 1 to n.
     * <p>
     * Intuition:
     * - There are n! total permutations of numbers [1, 2, ..., n].
     * - We can directly build the k-th permutation without generating all permutations.
     * - Each digit in the sequence is determined by "dividing" the problem into blocks.
     * <p>
     * Example:
     * n = 4 → [1, 2, 3, 4]
     * Total permutations = 4! = 24
     * - First 6 (3!) permutations start with '1'
     * - Next 6 permutations start with '2'
     * - Next 6 start with '3'
     * - Last 6 start with '4'
     * Using factorial, we can "skip" blocks and directly select digits.
     * <p>
     * Approach:
     * 1. Precompute factorials up to (n-1)!.
     * 2. Maintain a list of available numbers [1..n].
     * 3. Adjust k → 0-indexed (since easier to work with blocks).
     * 4. For each position:
     * - Find the index of the digit = k / (block size).
     * - Append that digit to result and remove it from the list.
     * - Update k = k % (block size).
     * 5. Continue until all numbers are placed.
     * <p>
     * Time Complexity: O(n^2)
     * - For each of the n positions, we remove from a list (O(n) worst-case).
     * <p>
     * Space Complexity: O(n)
     * - For storing factorials and available numbers.
     *
     * @param n size of the set {1, 2, ..., n}
     * @param k index of the permutation sequence (1-based)
     * @return k-th permutation sequence as a string
     */
    public String getPermutation(int n, int k) {
        k--; // Convert to 0-indexing for easier calculations

        // Initialize numbers 1..n
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }

        StringBuilder ans = new StringBuilder();

        // Precompute factorial values
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // Build result digit by digit
        for (int cnt = 1; cnt < n; cnt++) {
            int pattern = factorial[n - cnt]; // block size = (n - cnt)!
            int ind = k / pattern;            // index of digit to pick
            k -= pattern * ind;               // reduce k for next step

            ans.append(nums.get(ind));        // append chosen number
            nums.remove(ind);                 // remove from available numbers
        }

        // Add the last remaining number
        ans.append(nums.get(0));

        return ans.toString();
    }
}
