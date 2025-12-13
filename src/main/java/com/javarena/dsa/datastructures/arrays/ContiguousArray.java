package com.javarena.dsa.datastructures.arrays;

import java.util.HashMap;

/**
 * Contiguous Array
 *
 * <p><b>Problem Statement:</b><br>
 * Given a binary array nums, find the maximum length of a contiguous subarray with an equal number of 0 and 1.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Optimal Solution (Prefix Sum + HashMap):
 * - Transform problem: treat 0 as -1 and 1 as +1
 * - Now find longest subarray with sum = 0
 * - Use HashMap to store first occurrence of each running sum
 * - If same sum appears again, subarray between them has equal 0s and 1s
 * - Track maximum length found
 * 
 * Brute Force: Check all subarrays, count 0s and 1s (O(N²))
 *
 * <p><b>Time Complexity:</b> O(N) for optimal, O(N²) for brute force
 * <br><b>Space Complexity:</b> O(N) for HashMap, O(1) for brute force
 */
public class ContiguousArray {
    /**
     * Brute Force Solution - checks all subarrays.
     */
    public int findMaxLengthBruteForce(int[] nums) {
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            int zeroCount = 0;
            int oneCount = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) zeroCount++;
                else oneCount++;

                if (zeroCount == oneCount) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    /**
     * Optimal Solution using prefix sum and HashMap.
     */
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // base case

        int maxLength = 0;
        int runningSum = 0;

        for (int i = 0; i < nums.length; i++) {
            // Treat 0 as -1
            runningSum += (nums[i] == 0 ? -1 : 1);

            if (map.containsKey(runningSum)) {
                // Found a subarray with sum = 0
                maxLength = Math.max(maxLength, i - map.get(runningSum));
            } else {
                // Store first occurrence of this sum
                map.put(runningSum, i);
            }
        }
        return maxLength;
    }
}
