package com.javarena.dsa.algorithms.searching;

/**
 * Searching Algorithms Collection
 *
 * <p><b>Problem Statement:</b><br>
 * Collection of fundamental searching algorithms and search-based problems:
 * 1. Linear Search - Sequential search
 * 2. Binary Search - Divide and conquer on sorted array
 * 3. Find Median of Two Sorted Arrays
 * 4. Search 2D Matrix
 * 5. Find First and Last Position
 * 6. Find Peak Element
 * 7. Single Element in Sorted Array
 * 8. Find Minimum in Rotated Sorted Array
 * 9. Search in Rotated Sorted Array (with/without duplicates)
 * 10. Search Insert Position
 *
 * <p><b>Intuition & Approach:</b><br>
 * Core searching techniques:
 * 
 * Linear Search (O(N)):
 * - Check each element sequentially
 * - Works on unsorted data
 * - Simple but slow for large datasets
 * 
 * Binary Search (O(log N)):
 * - Requires sorted array
 * - Divide search space in half each iteration
 * - Compare middle element with target
 * - Eliminate half of remaining elements
 * 
 * Binary Search Variations:
 * - Find first/last occurrence: Modify condition to continue searching
 * - Peak element: Compare with neighbors
 * - Rotated array: Identify sorted half, search accordingly
 * - 2D matrix: Treat as 1D sorted array or search row then column
 * 
 * Advanced Applications:
 * - Median of two sorted arrays: Binary search on partition
 * - Single element: Binary search on even/odd indices
 * - Insert position: Binary search with lower bound
 *
 * <p><b>Time Complexity:</b> O(N) linear, O(log N) binary search
 * <br><b>Space Complexity:</b> O(1) iterative, O(log N) recursive
 */
public class SearchingAlgorithms {

    /**
     * Linear Search: Sequential search through array.
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
     * Binary Search: Divide and conquer on sorted array.
     */
    public int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }

    /**
     * Find Median of Two Sorted Arrays using binary search on partition.
     */
    public double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) {
            return findMedianSortedArrays(b, a);
        }
        
        int m = a.length, n = b.length;
        int low = 0, high = m;
        
        while (low <= high) {
            int partitionA = (low + high) / 2;
            int partitionB = (m + n + 1) / 2 - partitionA;
            
            int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : a[partitionA - 1];
            int minRightA = (partitionA == m) ? Integer.MAX_VALUE : a[partitionA];
            
            int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : b[partitionB - 1];
            int minRightB = (partitionB == n) ? Integer.MAX_VALUE : b[partitionB];
            
            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                } else {
                    return Math.max(maxLeftA, maxLeftB);
                }
            } else if (maxLeftA > minRightB) {
                high = partitionA - 1;
            } else {
                low = partitionA + 1;
            }
        }
        
        return 0.0;
    }

    /**
     * Search 2D Matrix: Binary search treating as 1D array.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }

    /**
     * Find First and Last Position: Modified binary search.
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        
        // Find first occurrence
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result[0] = mid;
                right = mid - 1;  // Continue searching left
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // Find last occurrence
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result[1] = mid;
                left = mid + 1;  // Continue searching right
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }

    /**
     * Find Peak Element: Binary search comparing with neighbors.
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                right = mid;  // Peak is on left side
            } else {
                left = mid + 1;  // Peak is on right side
            }
        }
        
        return left;
    }

    /**
     * Single Element in Sorted Array: Binary search on even/odd indices.
     */
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Ensure mid is even
            if (mid % 2 == 1) mid--;
            
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }
        
        return nums[left];
    }

    /**
     * Find Minimum in Rotated Sorted Array.
     */
    public int findMin(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return arr[left];
    }

    /**
     * Search in Rotated Sorted Array (no duplicates).
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Identify which half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }

    /**
     * Search in Rotated Sorted Array (with duplicates).
     */
    public boolean search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            // Handle duplicates
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return false;
    }

    /**
     * Search Insert Position: Binary search for lower bound.
     */
    public int searchInsert(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
}
