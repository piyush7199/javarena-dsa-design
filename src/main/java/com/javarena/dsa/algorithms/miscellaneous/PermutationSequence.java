package com.javarena.dsa.algorithms.miscellaneous;

import java.util.ArrayList;
import java.util.List;

/**
 * Permutation Sequence (Kth Permutation)
 *
 * <p><b>Problem Statement:</b><br>
 * Set [1, 2, 3, ..., n] contains n! unique permutations in order.
 * Given n and k, return the kth permutation sequence.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Direct construction using factorial number system:
 * - Total permutations = n!
 * - Permutations divided into blocks by first digit
 * - Each block has (n-1)! permutations
 * - For n=4: first 6 start with 1, next 6 with 2, etc.
 * - Use k to determine block → which digit to pick
 * - Repeat for remaining positions
 * 
 * Algorithm:
 * 1. Convert k to 0-indexed (k--)
 * 2. Initialize available numbers [1, 2, ..., n]
 * 3. Precompute factorials
 * 4. For each position:
 *    - Block size = (n - currentPosition)!
 *    - Index = k / blockSize
 *    - Pick that digit, remove from available
 *    - Update k = k % blockSize
 * 5. Add last remaining digit
 *
 * <p><b>Time Complexity:</b> O(N²) - N positions, O(N) to remove from list
 * <br><b>Space Complexity:</b> O(N) - Factorial array and available numbers
 */
public class PermutationSequence {
    
    /**
     * Returns the kth permutation sequence of [1, 2, ..., n].
     */
    public String getPermutation(int n, int k) {
        k--;  // Convert to 0-indexed
        
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }
        
        StringBuilder ans = new StringBuilder();
        
        // Precompute factorials
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        
        // Build result digit by digit
        for (int cnt = 1; cnt < n; cnt++) {
            int pattern = factorial[n - cnt];
            int ind = k / pattern;
            k -= pattern * ind;
            
            ans.append(nums.get(ind));
            nums.remove(ind);
        }
        
        ans.append(nums.get(0));
        return ans.toString();
    }
}
