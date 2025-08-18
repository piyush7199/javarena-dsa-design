package org.example.coding.algorithms.binarySearch;

public class AllocateMinimumPages {
    /**
     * Problem: Book Allocation (Find Minimum Pages)
     * ---------------------------------------------
     * We are given 'n' books with pages in arr[i], and 'k' students.
     * The task is to allocate books to students such that:
     * - Each student gets at least one book.
     * - Books are allocated in a contiguous manner.
     * - The maximum number of pages assigned to any student is minimized.
     * <p>
     * Intuition:
     * ----------
     * - If we fix a limit 'X' (max pages any student can be assigned),
     * we can greedily check if it's possible to allocate books
     * such that no student gets more than X pages.
     * - The minimum possible 'X' is the max(arr[i]) (since at least one student
     * must take the book with the highest pages).
     * - The maximum possible 'X' is the sum(arr[i]) (one student takes all books).
     * - Using **Binary Search** over this range [max(arr), sum(arr)],
     * we can find the minimum feasible 'X'.
     * <p>
     * Approach:
     * ---------
     * 1. Edge case: If k > n, return -1 (not enough books).
     * 2. Initialize:
     * low  = max(arr[i])   (a student must take at least the largest book)
     * high = sum(arr)      (a student could take all books)
     * 3. Binary Search:
     * - mid = (low + high) / 2
     * - Check feasibility with `helper(arr, k, mid)`:
     * - Allocate books greedily to students until adding another
     * book would exceed 'mid'.
     * - Start a new student allocation when exceeded.
     * - If number of students needed > k → not feasible.
     * - If feasible, try smaller value → high = mid - 1
     * - Else, increase limit → low = mid + 1
     * 4. Final answer = low (smallest feasible max pages).
     * <p>
     * Time Complexity:
     * ----------------
     * - Finding low & high: O(n)
     * - Binary search on answer space: O(log(sum(arr) - max(arr)))
     * - Each feasibility check (`helper`): O(n)
     * - Overall: O(n log(sum of pages))
     * <p>
     * Space Complexity:
     * -----------------
     * - O(1) extra space (apart from input array).
     */
    public int findPages(int[] arr, int k) {
        int n = arr.length;
        if (n < k) return -1;

        int low = arr[0];
        int high = 0;

        for (int ele : arr) {
            low = Math.max(low, ele); // At least the largest book
            high += ele;              // At most all books
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            if (helper(arr, k, mid)) {
                high = mid - 1; // Try smaller maximum
            } else {
                low = mid + 1;  // Increase limit
            }
        }

        return low;
    }

    /**
     * Helper function to check feasibility.
     * Can we allocate books such that no student gets more than 'maxPages'?
     */
    private static boolean helper(int[] arr, int k, int maxPages) {
        int noOfStudents = 1;
        int pages = 0;

        for (int i = 0; i < arr.length; i++) {
            if (pages + arr[i] <= maxPages) {
                pages += arr[i]; // Allocate to current student
            } else {
                pages = arr[i];  // Start new student
                noOfStudents++;
            }
            if (noOfStudents > k) return false;
        }
        return true;
    }

}
