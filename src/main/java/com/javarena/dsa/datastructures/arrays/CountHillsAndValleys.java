package com.javarena.dsa.datastructures.arrays;

/**
 * Count Hills and Valleys in an Array
 *
 * <p><b>Problem Statement:</b><br>
 * Count the number of hills and valleys in an array. A hill is an index where the element is strictly greater 
 * than its neighbors, and a valley is where it's strictly less than its neighbors. Consecutive equal elements 
 * should be treated as a single entity.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Iterate through array from index 1 to n-2 (exclude endpoints)
 * - Skip consecutive equal elements by checking if nums[i] != nums[i+1]
 * - Use 'left' pointer to track last different element
 * - Check if current element forms hill: nums[i] > nums[left] && nums[i] > nums[i+1]
 * - Check if current element forms valley: nums[i] < nums[left] && nums[i] < nums[i+1]
 * - Update left pointer when moving to next different element
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(1) - Only counter and pointer variables
 */
public class CountHillsAndValleys {
    /**
     * Counts hills and valleys in the array.
     */
    public int countHillValley(int[] nums) {
        int count = 0;
        int left = 0;

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                if ((nums[i] > nums[left] && nums[i] > nums[i + 1]) ||
                        (nums[i] < nums[left] && nums[i] < nums[i + 1])) {
                    count++;
                }
                left = i;
            }
        }
        return count;
    }
}
