package org.example.coding.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortedArray {

    /**
     * Problem
     * <p>
     * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
     * <p>
     * Intuition
     * <p></p>
     * We are given a list of intervals. If any intervals overlap, we want to merge them into one.
     * Sorting by start time allows us to scan through and merge overlapping intervals on the go.
     * <p>
     * Complexity
     * <p>
     * Time complexity:{@code O(nlogn)} (for sorting)
     * <p></p>
     * Space complexity:{@code O(n)} (for storing merged intervals)
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (res.getLast()[1] >= intervals[i][0]) {
                res.getLast()[1] = Math.max(intervals[i][1], res.getLast()[1]);
            } else {
                res.add(intervals[i]);
            }
        }

        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }

    /**
     * Problem
     * <p>
     * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
     * </p>
     * Intuition
     * <p>
     * By iterating backwards from the end of the array, we can
     * start populating the result array from the end, ensuring
     * that the squares of larger elements occupy the higher
     * indices of the result array.
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
        int lastInd = n - 1;
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

    /**
     * Problem<p>
     * Given an array of integers arr[] that is first strictly increasing and then maybe strictly decreasing, find the bitonic point, that is the maximum element in the array.
     * Bitonic Point is a point before which elements are strictly increasing and after which elements are strictly decreasing.
     * Note: It is guaranteed that the array contains exactly one bitonic point.
     * </p>
     * Intuituion <p>
     * If the mid element is greater than both of its adjacent elements, then mid is the maximum.
     * If the mid element is smaller than its next element then we should try to search on the right half of the array. So, update low = mid + 1.
     * If the mid element is greater than the next element, then we should try to search on the left half. So, update high = mid - 1.
     * </p>
     * Complexity
     * <p>
     * Time complexity: {@code O(logn)}
     * <p></p>
     * Space complexity: {@code O(1)}
     */
    public static int findMaximum(int[] arr) {
        // code here
        int ans = arr[0];
        int n = arr.length;
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (mid == n - 1 || (arr[mid] > arr[mid - 1]) && arr[mid] > arr[mid + 1]) {
                return arr[mid];
            } else if (arr[mid] < arr[mid + 1]) i = mid + 1;
            else j = mid - 1;
        }
        return ans;
    }

    /**
     * Problem <p>
     * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
     * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
     * The tests are generated such that there is exactly one solution. You may not use the same element twice.
     * Your solution must use only constant extra space.
     * </p>
     * Complexity
     * <p>
     * Time complexity: {@code O(n)}
     * <p></p>
     * Space complexity: {@code O(1)}
     */
    public static int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int[] ans = new int[2];
        int i = 0;
        int j = n - 1;
        while (i <= j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                ans[0] = i + 1;
                ans[1] = j + 1;
                break;
            } else if (sum < target) i++;
            else j--;
        }
        return ans;
    }

    /**
     * Problem <p>
     * Given an array arr[], find all possible triplets i, j, k in the arr[] whose sum of elements is equals to zero.
     * Returned triplet should also be internally sorted i.e. i<j<k.
     * </p>
     * Intuition <p>
     * To find all unique triplets in the array that sum to zero, we fix one number and try to find two other numbers that sum to its negative. Sorting the array helps in efficiently avoiding duplicates and applying a hashmap for lookup.
     * </p>
     * Complexity
     * <p>
     * Time complexity: {@code O(n^2)}
     * <p></p>
     * Space complexity: {@code O(1)}
     */
    public static List<List<Integer>> findTriplets(int[] nums) {
        // Your code here
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[i]);
                    res.add(nums[j]);
                    res.add(nums[k]);
                    ans.add(res);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
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
     * Problem <p>
     * Given an array arr[] and an integer target, the task is to find the sum of three integers in arr[] such that the sum is closest to target.
     * Note: If multiple sums are closest to target, return the maximum one.
     * </p>
     * Intuition <p>
     * Initially, we sort the input array so that we can apply two pointers technique. Then, we iterate over the array fixing the first element of the triplet and then use two pointers technique to find the remaining two elements. Set one pointer at the beginning (left) and another at the end (right) of the remaining array. We then find the absolute difference between the sum of triplet and target and store the triplet having minimum absolute difference.
     * </p>
     * Complexity
     * <p>
     * Time complexity:{@code O(n^2)} (for sorting)
     * <p></p>
     * Space complexity:{@code O(1)} (for storing merged intervals)
     */

    public static int closest3Sum(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        int res = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, r = n - 1;
            while (l < r) {
                int currSum = arr[i] + arr[l] + arr[r];
                if (Math.abs(currSum - target) < minDiff) {
                    minDiff = Math.abs(currSum - target);
                    res = currSum;
                } else if (Math.abs(currSum - target) == minDiff) {
                    res = Math.max(res, currSum);
                }
                if (currSum > target) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }
}
