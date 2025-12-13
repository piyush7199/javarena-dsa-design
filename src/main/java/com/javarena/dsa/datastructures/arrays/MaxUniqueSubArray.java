package com.javarena.dsa.datastructures.arrays;

/**
 * Maximum Unique Subarray Sum
 *
 * <p><b>Problem Statement:</b><br>
 * Calculate the maximum possible sum by including only distinct positive elements from the array.
 * If all elements are negative, return the maximum (least negative) number.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Only consider positive numbers (negatives reduce sum)
 * - Use frequency array of size 101 (values 0-100) to mark distinct positives
 * - Track if all numbers are negative and keep max element
 * - If all negative, return max element
 * - Otherwise, sum all distinct positive numbers using presence array
 * - This ensures we count each positive value only once
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass + fixed 101 iterations = O(N)
 * <br><b>Space Complexity:</b> O(1) - Fixed size array of 101 elements
 */
public class MaxUniqueSubArray {
    /**
     * Calculates maximum sum from distinct positive elements.
     */
    public int maxSum(int[] nums) {
        int[] arr = new int[101]; // to mark presence of numbers from 0 to 100
        boolean allNeg = true;
        int ans = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num >= 0) {
                arr[num] = 1;
                allNeg = false;
            }
            ans = Math.max(ans, num); // keep track of max element (even if all are negative)
        }

        if (allNeg) return ans; // if all numbers are negative, return the max among them

        int result = 0;
        for (int i = 1; i < 101; i++) {
            if (arr[i] > 0) {
                result += i; // add all distinct positive numbers
            }
        }

        return result;
    }

}
