package org.example.coding.datastructures.arrays;

public class RotateArrays {

    /**
     * Rotates the given array to the right by k steps using array reversal.
     *
     * <p>Intuition:
     * - To rotate the array right by k positions, we can:
     * 1. Reverse the entire array.
     * 2. Reverse the first k elements.
     * 3. Reverse the remaining n-k elements.
     * - This results in the rotated version with O(1) space and O(n) time.
     * <p>
     * Time Complexity: O(n) — Three passes over the array
     * Space Complexity: O(1) — In-place rotation
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
