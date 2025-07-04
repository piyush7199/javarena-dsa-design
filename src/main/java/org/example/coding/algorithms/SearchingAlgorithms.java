package org.example.coding.algorithms;

public class SearchingAlgorithms {

    /**
     * Linear Search is the simplest search algorithm that checks every element
     * of the array sequentially until the desired element is found or the end
     * of the array is reached.
     *
     * <p>
     * This algorithm is useful for unsorted or small datasets.
     * </p>
     *
     * <h3>Time Complexity:</h3>
     * <ul>
     *   <li>Best Case: O(1) – when the target is the first element</li>
     *   <li>Average Case: O(n)</li>
     *   <li>Worst Case: O(n) – when the target is not present or is the last element</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b> O(1)</p>
     *
     * @param arr the array to search
     * @param key the value to search for
     * @return the index of the key if found, otherwise -1
     */
    public int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Binary Search is an efficient algorithm for finding an element's position
     * in a sorted array by repeatedly dividing the search interval in half.
     *
     * <p>
     * It uses the fact that the array is sorted to eliminate half of the remaining
     * elements from consideration in each step.
     * </p>
     *
     * <h3>Preconditions:</h3>
     * <ul>
     *   <li>The array must be sorted in ascending order</li>
     *   <li>Random (constant-time) access to elements is available</li>
     * </ul>
     *
     * <h3>Algorithm Steps:</h3>
     * <ol>
     *   <li>Calculate the middle index: <code>mid = (low + high) / 2</code></li>
     *   <li>If <code>arr[mid] == key</code>, return mid</li>
     *   <li>If <code>arr[mid] > key</code>, repeat the process on the left half</li>
     *   <li>If <code>arr[mid] < key</code>, repeat the process on the right half</li>
     *   <li>Repeat until the element is found or the search space is empty</li>
     * </ol>
     *
     * <h3>Time Complexity:</h3>
     * <ul>
     *   <li>Best Case: O(1)</li>
     *   <li>Average and Worst Case: O(log n)</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b> O(1) for iterative version, O(log n) for recursive version due to stack calls</p>
     *
     * @param arr the sorted array to search
     * @param key the value to search for
     * @return the index of the key if found, otherwise -1
     */
    public int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }
}

class Problems {
    /**
     * Finds the median of two sorted arrays in O(log(min(m, n))) time.
     *
     * <p><b>Problem:</b></p>
     * Given two sorted arrays nums1 and nums2, return the median of the combined sorted array
     * without actually merging them. The required time complexity is O(log(min(m, n))).
     *
     * <p><b>Intuition:</b></p>
     * - This is a binary search problem on the smaller array.
     * - We partition both arrays such that:
     * - The left half contains exactly half (or one more) elements of the combined array.
     * - The max of the left parts <= min of the right parts.
     * - We binary search to find the correct partition point.
     * - Once found:
     * - If total length is odd: return max(left parts)
     * - If even: return avg(max(left), min(right))
     *
     * <p><b>Time Complexity:</b> O(log(min(m, n)))</p>
     * - Binary search on the smaller of the two arrays.
     *
     * <p><b>Space Complexity:</b> O(1)</p>
     * - No extra space used except variables.
     */
    public double findMedianSortedArrays(int[] a, int[] b) {
        int n1 = a.length;
        int n2 = b.length;
        if (n1 > n2) return findMedianSortedArrays(b, a);

        int low = 0;
        int high = n1;
        int left = (n1 + n2 + 1) / 2;
        int n = n1 + n2;

        while (low <= high) {
            int mid1 = (low + high) >> 1;
            int mid2 = left - mid1;

            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;

            if (mid1 < n1) r1 = a[mid1];
            if (mid2 < n2) r2 = b[mid2];

            if (mid1 - 1 >= 0) l1 = a[mid1 - 1];
            if (mid2 - 1 >= 0) l2 = b[mid2 - 1];

            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 == 1) return Math.max(l1, l2);
                return (double) (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;

            }
        }
        return 0.0;
    }

    /**
     * Searches for a target value in a 2D matrix.
     *
     * <p><b>Problem:</b> Each row is sorted left to right, and the first element of each row
     * is greater than the last element of the previous row.
     * You must search for the target in O(log(m * n)) time.
     *
     * <p><b>Intuition:</b> Treat the 2D matrix as a flattened sorted array.
     * Perform binary search on indices [0, m*n - 1], and map the 1D index back to 2D:
     * - row = index / n
     * - col = index % n
     *
     * <p><b>Time Complexity:</b> O(log(m * n))</p>
     * <p><b>Space Complexity:</b> O(1)</p>
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0, high = m * n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int row = mid / n;
            int col = mid % n;
            int midVal = matrix[row][col];

            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    /**
     * Finds the starting and ending positions of a given target value in a sorted array.
     *
     * <p><b>Problem:</b>
     * Given an array of integers nums sorted in non-decreasing order, find the first and last position
     * of a given target. Return [-1, -1] if the target is not present. The algorithm must run in O(log n) time.
     *
     * <p><b>Intuition:</b>
     * - Use two binary searches:
     * - One to find the first occurrence of the target.
     * - One to find the last occurrence, starting from the first.
     * - This avoids linear scans and keeps the runtime logarithmic.
     *
     * <p><b>Time Complexity:</b> O(log n)</p>
     * <p><b>Space Complexity:</b> O(1)</p>
     */
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int first = findFirst(nums, target, n);
        int[] result = {-1, -1};

        if (first == -1) {
            return result; // target not found
        }

        int last = findLast(nums, target, first, n);
        result[0] = first;
        result[1] = last;
        return result;
    }

    /**
     * Binary search to find the first occurrence of the target.
     */
    private int findFirst(int[] nums, int target, int n) {
        int low = 0, high = n - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                ans = mid;
                high = mid - 1; // move left to find earlier occurrence
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    /**
     * Binary search to find the last occurrence of the target.
     */
    private int findLast(int[] nums, int target, int start, int n) {
        int low = start, high = n - 1;
        int ans = start;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                ans = mid;
                low = mid + 1; // move right to find later occurrence
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

}
