package org.example.coding.arrays;

public class SquareSortedArray {
    /**
     * Problem
     * <p>
     * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
     * </p>
     * Intuition
     * <p>
     *     By iterating backwards from the end of the array, we can
     *     start populating the result array from the end, ensuring
     *     that the squares of larger elements occupy the higher
     *     indices of the result array.
     * </p>
     * Complexity
     * <p>
     * Time complexity:{@code O(n)}
     * <p></p>
     * Space  complexity:{@code O(n)}
     */
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int[] ans = new int[n];
        int lastInd = n-1;
        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            if (leftSquare > rightSquare) {
                ans[lastInd--] = leftSquare;
                left++;
            } else {
                ans[lastInd--] = rightSquare;
                right--;
            }
        }
        return ans;
    }
}
