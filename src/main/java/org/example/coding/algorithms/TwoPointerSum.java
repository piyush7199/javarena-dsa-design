package org.example.coding.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointerSum {

    /**
     * Finds all unique triplets in the array that sum to zero.
     * <p>
     * Intuition:
     * - Sort the array.
     * - Fix one number and use two-pointer technique to find valid triplets.
     * - Skip duplicates to ensure unique triplets.
     * <p>
     * Time Complexity: O(n^2) – for each element, search a pair in remaining array
     * Space Complexity: O(1) (excluding output list)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    ans.add(temp);
                    j++;
                    k--;
                    while (j < n && nums[j] == nums[j - 1]) {
                        j++;
                    }

                    while (k >= 0 && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return ans;
    }

    /**
     * Finds all unique quadruplets [a, b, c, d] in the array such that:
     * a + b + c + d == target
     * <p>
     * Intuition:
     * - Sort the array.
     * - Use two nested loops to fix the first two elements.
     * - Use two-pointer technique to find the remaining two.
     * - Skip duplicates for all positions to avoid redundant quadruplets.
     * <p>
     * Time Complexity: O(n^3)
     * Space Complexity: O(1) (excluding output list)
     * <p>
     * Note: Uses long type for sum to avoid overflow when inputs are large.`
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int l = i + 1; l < n; l++) {
                if (l != i + 1 && nums[l] == nums[l - 1]) {
                    continue;
                }
                int j = l + 1;
                int k = n - 1;
                while (j < k) {
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];
                    if (sum > target) {
                        k--;
                    } else if (sum < target) {
                        j++;
                    } else {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[l]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        ans.add(temp);
                        j++;
                        k--;
                        while (j < n && nums[j] == nums[j - 1]) {
                            j++;
                        }

                        while (k >= 0 && nums[k] == nums[k + 1]) {
                            k--;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
