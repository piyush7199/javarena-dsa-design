package com.javarena.dsa.datastructures.arrays;

/**
 * Build Array from Permutation
 *
 * <p><b>Problem Statement:</b><br>
 * Given a zero-based permutation nums (0-indexed), build an array ans where ans[i] = nums[nums[i]] for each 0 <= i < nums.length.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Create result array of same size
 * - For each index i, set ans[i] = nums[nums[i]]
 * - This is a direct permutation mapping problem
 * - Simply follow the permutation indices to build result
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(N) - Result array of size N
 */
public class ArrayPermutation {
    /**
     * Builds array by applying permutation indices.
     */
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }
}
