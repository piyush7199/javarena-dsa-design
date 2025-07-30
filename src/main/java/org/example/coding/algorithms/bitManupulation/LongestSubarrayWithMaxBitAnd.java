package org.example.coding.algorithms.bitManupulation;

public class LongestSubarrayWithMaxBitAnd {

    /**
     * Problem:
     * Find the length of the longest subarray in the given array `nums` such that
     * the bitwise AND of all elements in the subarray is equal to the maximum value
     * in the entire array.
     * <p>
     * Intuition:
     * - Bitwise AND is a restrictive operation: ANDing two numbers results in a number
     * that is less than or equal to both.
     * - Therefore, for a subarray to have maximum AND, all the elements in it must
     * be equal to the maximum number in the array.
     * - So, the problem reduces to finding the length of the **longest contiguous
     * subarray** consisting only of the maximum number in `nums`.
     * <p>
     * Approach:
     * 1. First, find the maximum number in the array.
     * 2. Traverse the array, and whenever the current number equals the maximum,
     * increment the current count.
     * 3. Reset the current count if the current number is not equal to the maximum.
     * 4. Track the longest such streak (maximum count of contiguous `max` values).
     * <p>
     * Time Complexity: O(n)
     * - We make a single pass through the array to determine the longest streak
     * of the maximum value.
     * <p>
     * Space Complexity: O(1)
     * - We use a constant amount of space (only a few integer variables).
     */
    public int longestSubarray(int[] nums) {
        int max = 0;
        int currentCount = 0;
        int maxLength = 0;

        // First pass to find the maximum element
        for (int x : nums) {
            if (x > max) {
                max = x;
            }
        }

        // Second pass to find the longest subarray of max elements
        for (int x : nums) {
            if (x == max) {
                currentCount++;
                maxLength = Math.max(maxLength, currentCount);
            } else {
                currentCount = 0;
            }
        }

        return maxLength;
    }

}
