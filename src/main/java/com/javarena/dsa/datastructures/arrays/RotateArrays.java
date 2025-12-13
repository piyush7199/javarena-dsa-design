package com.javarena.dsa.datastructures.arrays;

/**
 * Rotate Array
 *
 * <p><b>Problem Statement:</b><br>
 * Rotate an array to the right by k steps in-place.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use three-reversal trick:
 * 1. Reverse entire array
 * 2. Reverse first k elements
 * 3. Reverse remaining n-k elements
 * 
 * Example: [1,2,3,4,5,6,7], k=3
 * - After reversing all: [7,6,5,4,3,2,1]
 * - After reversing first 3: [5,6,7,4,3,2,1]
 * - After reversing last 4: [5,6,7,1,2,3,4] ✓
 * 
 * Handle k > n with k = k % n
 *
 * <p><b>Time Complexity:</b> O(N) - Three passes through array
 * <br><b>Space Complexity:</b> O(1) - In-place rotation
 */
public class RotateArrays {

    /**
     * Rotates array to right by k steps.
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;  // Handle cases where k > n
        if (k == 0) return;

        reverseArray(nums, 0, n - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, n - 1);
    }

    /**
     * Reverses a subarray from index i to j in-place.
     */
    public void reverseArray(int[] nums, int i, int j) {
        while (j >= i) {
            int cur = nums[i];
            nums[i] = nums[j];
            nums[j] = cur;
            i++;
            j--;
        }
    }
}
