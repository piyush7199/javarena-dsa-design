package com.javarena.dsa.algorithms.binarySearch;

/**
 * Allocate Minimum Pages (Book Allocation Problem)
 *
 * <p><b>Problem Statement:</b><br>
 * Given n books with pages arr[i] and k students, allocate books contiguously such that
 * the maximum pages assigned to any student is minimized. Return minimum possible maximum.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Binary search on maximum pages per student:
 * - Search space: [max(arr), sum(arr)]
 * - Lower bound: someone must read largest book
 * - Upper bound: one student reads all
 * - For candidate X, greedily allocate:
 *   1. If k > n, return -1 (not enough books)
 *   2. Assign books to student until adding next exceeds X
 *   3. Move to next student
 *   4. If allocation possible with ≤ k students: try smaller X
 *   5. Else: need larger X
 * - Greedy allocation ensures optimal distribution
 *
 * <p><b>Time Complexity:</b> O(N log S) - N books, S = sum of pages
 * <br><b>Space Complexity:</b> O(1) - Constant space
 */
public class AllocateMinimumPages {
    
    /**
     * Finds minimum possible maximum pages any student must read.
     */
    public int findPages(int[] arr, int k) {
        int n = arr.length;
        
        if (n < k) return -1;

        int low = arr[0];
        int high = 0;
        for (int ele : arr) {
            low = Math.max(low, ele);
            high += ele;
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            
            if (helper(arr, k, mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    /**
     * Checks if books can be allocated with max pages ≤ maxPages per student.
     */
    private static boolean helper(int[] arr, int k, int maxPages) {
        int noOfStudents = 1;
        int pages = 0;

        for (int i = 0; i < arr.length; i++) {
            if (pages + arr[i] <= maxPages) {
                pages += arr[i];
            } else {
                pages = arr[i];
                noOfStudents++;
            }
            
            if (noOfStudents > k) return false;
        }
        
        return true;
    }
}
