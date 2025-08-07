package org.example.coding.datastructures.arrays;

public class CountSubArraysWithFixedBound {
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int minIndex = -1;
        int maxIndex = -1;
        int invalidIndex = -1;

        long res = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                invalidIndex = i;
            }
            if (nums[i] == minK) {
                minIndex = i;
            }
            if (nums[i] == maxK) {
                maxIndex = i;
            }

            int validStart = Math.min(minIndex, maxIndex);
            if (validStart > invalidIndex) {
                res += validStart - invalidIndex;
            }
        }

        return res;
    }
}
