package org.example.coding.algorithms;

public class TwoPointer {
    /**
     * Finds the celebrity in a party using two-pointer elimination method.
     *
     * <p>🔍 Intuition:
     * - A celebrity is someone who:
     * 1. Knows no one: row should have all 0s.
     * 2. Is known by everyone: column should have all 1s.
     * <p>
     * - Start with two pointers `i = 0` and `j = n - 1`.
     * - If j knows i → j can't be celeb, move j--.
     * - Else i can't be celeb, move i++.
     * - At the end, i is the only possible candidate.
     * - Validate that this person satisfies both celebrity conditions.
     * <p>
     * <p>
     * ⏱ Time Complexity: O(n)
     * - One pass to eliminate (n-1 comparisons)
     * - One pass to validate candidate
     * <p>
     * 🧠 Space Complexity: O(1)
     * - No extra data structures used
     */
    public int celebrity(int[][] mat) {
        int n = mat.length;
        int i = 0, j = n - 1;

        // Step 1: Eliminate non-celebrities
        while (i < j) {
            if (mat[j][i] == 1) {
                // j knows i → j can't be celebrity
                j--;
            } else {
                // j doesn't know i → i can't be celebrity
                i++;
            }
        }

        // Step 2: Candidate found at index i
        int c = i;

        // Step 3: Verify if c is actually a celebrity
        for (i = 0; i < n; i++) {
            if (i == c) continue;

            // If c knows anyone or someone doesn't know c → not a celeb
            if (mat[c][i] != 0 || mat[i][c] != 1) {
                return -1;
            }
        }

        return c;
    }

    /**
     * Merges two sorted arrays `nums1` and `nums2` into `nums1` in-place.
     * `nums1` has a size of m + n with the first `m` elements initialized.
     * <p>
     * Intuition:
     * - We fill `nums1` from the back (largest index) to avoid overwriting.
     * - Compare the elements from end of `nums1` and `nums2`, place the larger at the end.
     * <p>
     * Time Complexity: O(m + n)
     * Space Complexity: O(1) – in-place
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

    }
}
