package com.javarena.dsa.algorithms.binarySearch;

/**
 * Book Allocation Problem (Binary Search on Answer)
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1">GFG - Allocate Minimum Pages</a>
 *
 * <p><b>Difficulty:</b> Hard
 *
 * <p><b>Topics:</b> Binary Search, Array, Greedy
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given n books with pages arr[i] and k students, allocate books to students such that:
 * - Each student gets at least one book (contiguous allocation)
 * - Maximum pages assigned to any student is minimized
 * Return the minimum possible value of maximum pages.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: arr = [12, 34, 67, 90], k = 2
 * Output: 113
 * Explanation: [12, 34, 67] to student1 (113 pages), [90] to student2 (90 pages). Max = 113.
 *
 * Input: arr = [10, 20, 30, 40], k = 2
 * Output: 60
 * Explanation: [10, 20, 30] and [40]. Max = 60.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Binary search on maximum pages per student:
 * - Cannot enumerate all allocations (exponential)
 * - Can verify if max_pages limit X is achievable
 * - Search space: [max(arr), sum(arr)]
 * - Lower bound: at least max element (someone must read largest book)
 * - Upper bound: one student reads all books
 * - For candidate X, greedily allocate books keeping each student's total ≤ X
 * - If allocation possible with ≤ k students, try smaller X
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Edge case: if k > n, return -1 (not enough books)</li>
 *   <li>Initialize search bounds:</li>
 *   <li>- low = max(arr) - minimum possible max pages</li>
 *   <li>- high = sum(arr) - maximum possible max pages</li>
 *   <li>Binary search on max pages:</li>
 *   <li>- For mid, check if allocation possible with helper function</li>
 *   <li>- Greedy helper: assign books to current student until adding next exceeds mid</li>
 *   <li>- If possible with ≤ k students: decrease max (high = mid-1)</li>
 *   <li>- Else: increase max (low = mid+1)</li>
 *   <li>Return low (minimum feasible max pages)</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n log S)<br>
 * Where n = number of books, S = sum of all pages.
 * Binary search O(log S), each check O(n).
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only constant extra space used.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>k > n: Impossible, return -1</li>
 *   <li>k = n: Each student gets one book, return max(arr)</li>
 *   <li>k = 1: One student gets all, return sum(arr)</li>
 *   <li>All books same pages: Balanced distribution</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/split-array-largest-sum/">Split Array Largest Sum</a>
 */
public class AllocateMinimumPages {
    
    /**
     * Finds minimum possible maximum pages any student must read.
     *
     * @param arr array where arr[i] = pages in book i
     * @param k number of students
     * @return minimum of maximum pages, or -1 if impossible
     */
    public int findPages(int[] arr, int k) {
        int n = arr.length;
        
        // Step 1: Not enough books
        if (n < k) return -1;

        // Step 2: Calculate search bounds
        int low = arr[0];
        int high = 0;
        for (int ele : arr) {
            low = Math.max(low, ele);  // At least max book
            high += ele;                // At most all books
        }

        // Step 3: Binary search on max pages
        while (low <= high) {
            int mid = (low + high) / 2;
            
            // Step 4: Check if mid is feasible
            if (helper(arr, k, mid)) {
                high = mid - 1;  // Try smaller max
            } else {
                low = mid + 1;   // Increase max
            }
        }

        return low;
    }

    /**
     * Checks if books can be allocated with max pages ≤ maxPages per student.
     *
     * @param arr book pages array
     * @param k number of students
     * @param maxPages maximum pages any student can read
     * @return true if allocation is possible
     */
    private static boolean helper(int[] arr, int k, int maxPages) {
        int noOfStudents = 1;
        int pages = 0;

        for (int i = 0; i < arr.length; i++) {
            if (pages + arr[i] <= maxPages) {
                pages += arr[i];  // Allocate to current student
            } else {
                pages = arr[i];   // Start new student
                noOfStudents++;
            }
            
            // Too many students needed
            if (noOfStudents > k) return false;
        }
        
        return true;
    }
}
