package com.javarena.dsa.datastructures.arrays;

/**
 * Distribute Elements Into Two Arrays
 *
 * <p><b>Problem Statement:</b><br>
 * Given an array nums, distribute elements into two arrays based on comparison rule: if last element 
 * of first array > last element of second array, add to first; otherwise add to second. Concatenate results.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use single result array with two pointers: i (left) and j (right)
 * - Place first element at start (index 0), second at end (index n-1)
 * - For remaining elements, compare ans[i] vs ans[j]:
 *   - If ans[i] > ans[j], add to left side (increment i)
 *   - Otherwise, add to right side (decrement j)
 * - After distribution, reverse the right portion to correct order
 * - This simulates two arrays using single array with clever indexing
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass for distribution, O(N) for reversal
 * <br><b>Space Complexity:</b> O(N) - Result array
 */
public class DistributeElements {
    /**
     * Distributes elements into result array based on comparison rule.
     */
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = nums[0];
        ans[n - 1] = nums[1];
        int i = 0;
        int j = n - 1;
        
        for (int k = 2; k < n; k++) {
            if (ans[i] > ans[j]) {
                ans[++i] = nums[k];
            } else {
                ans[--j] = nums[k];
            }
        }

        // Reverse right portion
        int k = n - 1;
        while (j <= k) {
            int temp = ans[j];
            ans[j] = ans[k];
            ans[k] = temp;
            j++;
            k--;
        }
        return ans;
    }
}
