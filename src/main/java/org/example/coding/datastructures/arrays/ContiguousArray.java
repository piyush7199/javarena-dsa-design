package org.example.coding.datastructures.arrays;

import java.util.HashMap;

public class ContiguousArray {
    /**
     * Brute Force Solution
     * <p>
     * Intuition:
     * - Check every possible subarray and count the number of 0s and 1s.
     * - If they are equal, update the maximum length.
     * <p>
     * Time Complexity: O(n^2) -> Two nested loops to consider all subarrays.
     * Space Complexity: O(1) -> Only variables used for counting.
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
     * Optimal Solution: Prefix Sum + HashMap
     * <p>
     * Intuition:
     * - Treat 0 as -1 and 1 as +1.
     * - Now the problem becomes finding the longest subarray with sum = 0.
     * - Use a HashMap to store the first occurrence of each running sum.
     * - If the same sum appears again, it means the subarray between them has equal 0s and 1s.
     * <p>
     * Time Complexity: O(n)
     * -> Single pass through the array, each lookup in HashMap is O(1).
     * <p>
     * Space Complexity: O(n)
     * -> HashMap stores at most n unique sums.
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
