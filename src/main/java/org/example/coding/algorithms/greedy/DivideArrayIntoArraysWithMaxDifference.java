package org.example.coding.algorithms.greedy;

import java.util.Arrays;

public class DivideArrayIntoArraysWithMaxDifference {
    /**
     * Divides an array into groups of size 3 such that the difference between
     * the largest and smallest element in each group is at most {@code k}.
     *
     * <p><b>Intuition:</b>
     * To minimize the maximum difference within each group, sorting the array first
     * ensures that potential groups are formed from consecutive elements.
     * After sorting, checking the difference between the smallest and largest
     * in every triplet ensures the constraint is met.
     *
     * <p><b>Approach:</b>
     * <ol>
     *   <li>If the array has fewer than 3 elements, return an empty 2D array.</li>
     *   <li>Sort the array in non-decreasing order.</li>
     *   <li>Iterate through the array in chunks of 3:
     *       <ul>
     *           <li>If the difference between the third and first element in the chunk is ≤ {@code k},
     *               add the triplet to the result.</li>
     *           <li>Otherwise, return an empty 2D array (constraint violation).</li>
     *       </ul>
     *   </li>
     *   <li>Return the constructed 2D array of valid triplets.</li>
     * </ol>
     *
     * <p><b>Time Complexity:</b> O(n log n) — dominated by sorting.
     * <b>Space Complexity:</b> O(n) — output array plus sorting overhead (maybe O(1) if sort is in-place).
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
                i += 3;
            } else {
                return new int[][]{};
            }
        }
        return ans;
    }

}
