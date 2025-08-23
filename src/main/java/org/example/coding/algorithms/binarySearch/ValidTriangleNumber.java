package org.example.coding.algorithms.binarySearch;

import java.util.Arrays;

public class ValidTriangleNumber {
    /**
     * Brute force approach.
     * <p>
     * Idea:
     * - Try all possible triplets (i, j, k).
     * - Check if they satisfy triangle inequality.
     * <p>
     * Time Complexity: O(n^3)
     * - 3 nested loops.
     * Space Complexity: O(1)
     */
    public int triangleNumberBrute(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int a = nums[i], b = nums[j], c = nums[k];
                    if (a + b > c && a + c > b && b + c > a) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Optimized solution using sorting + two pointers.
     * <p>
     * Intuition:
     * - Sort nums first.
     * - Fix the largest side (c = nums[k]).
     * - Use two pointers (i = 0, j = k-1) to find pairs (a, b) such that:
     * nums[i] + nums[j] > nums[k].
     * - If nums[i] + nums[j] > nums[k], then all elements from i...j-1 with nums[j]
     * will also form valid pairs (since array is sorted).
     * - Count += (j - i), then move j--.
     * - Otherwise, move i++.
     * <p>
     * Approach:
     * 1. Sort nums.
     * 2. Iterate from right to left, fixing the largest side.
     * 3. Use two-pointer technique to count valid pairs.
     * <p>
     * Time Complexity: O(n^2)
     * - Sorting O(n log n), two-pointer loop O(n^2).
     * Space Complexity: O(1)
     * - No extra space beyond input and counters.
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;

        // Fix largest side nums[k]
        for (int k = n - 1; k >= 2; k--) {
            int i = 0, j = k - 1;

            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    // all nums[i...j-1] with nums[j] form valid pairs
                    count += (j - i);
                    j--;
                } else {
                    i++;
                }
            }
        }
        return count;
    }

}
