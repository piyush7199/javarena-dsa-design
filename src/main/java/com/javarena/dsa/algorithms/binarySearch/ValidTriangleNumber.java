package com.javarena.dsa.algorithms.binarySearch;

import java.util.Arrays;

/**
 * Valid Triangle Number
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer array nums, return number of triplets (i,j,k) where i<j<k
 * and nums forms a valid triangle. Triangle inequality: sum of any two sides > third side.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Two-pointer technique after sorting:
 * - Sort array for efficient checking
 * - Fix largest side at position k (iterate right to left)
 * - Use two pointers (i, j) to find valid smaller sides
 * - If nums[i] + nums[j] > nums[k] (sorted):
 *   - All elements between i and j also work with j
 *   - Count (j - i) triangles
 *   - Move j left
 * - Else: move i right (need larger sum)
 * - Sorting enables efficient counting without checking all triplets
 *
 * <p><b>Time Complexity:</b> O(N²) - Sorting O(N log N), two-pointer O(N²)
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class ValidTriangleNumber {
    
    /**
     * Counts number of valid triangles.
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        int n = nums.length;

        for (int k = n - 1; k >= 2; k--) {
            int i = 0;
            int j = k - 1;

            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    count += (j - i);
                    j--;
                } else {
                    i++;
                }
            }
        }
        return count;
    }
}
