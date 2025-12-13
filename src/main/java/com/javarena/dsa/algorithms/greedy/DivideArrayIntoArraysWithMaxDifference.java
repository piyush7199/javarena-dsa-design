package com.javarena.dsa.algorithms.greedy;

import java.util.Arrays;

/**
 * Divide Array Into Arrays With Max Difference
 *
 * <p><b>Problem Statement:</b><br>
 * Divide array into groups of size 3 where max difference (max - min) in each group ≤ k.
 * Return 2D array of groups, or empty array if impossible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy with sorting:
 * - Sort array to group similar values together
 * - Process consecutive triplets (i, i+1, i+2)
 * - Check if nums[i+2] - nums[i] ≤ k
 * - If all triplets valid: return groups
 * - If any invalid: return empty (impossible)
 * 
 * Why sorting works:
 * - Grouping consecutive elements minimizes within-group difference
 * - Any other grouping would have larger differences
 * 
 * Simple greedy: sorted consecutive triplets optimal.
 *
 * <p><b>Time Complexity:</b> O(N log N) - Dominated by sorting
 * <br><b>Space Complexity:</b> O(N) - Output array
 */
public class DivideArrayIntoArraysWithMaxDifference {
    
    /**
     * Divides array into valid triplets using greedy sorting.
     */
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        if (n < 3) return new int[][]{};
        
        int[][] ans = new int[n / 3][3];
        Arrays.sort(nums);

        int i = 2;
        int index = 0;
        
        while (i < n) {
            if (nums[i] - nums[i - 2] <= k) {
                ans[index][0] = nums[i - 2];
                ans[index][1] = nums[i - 1];
                ans[index][2] = nums[i];
                index++;
            } else {
                return new int[][]{};
            }
            i += 3;
        }
        
        return ans;
    }
}
