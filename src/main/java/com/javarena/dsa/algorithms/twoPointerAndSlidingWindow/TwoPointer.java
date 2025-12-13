package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Two Pointer Technique - Collection
 *
 * <p><b>Problem Statement:</b><br>
 * Collection of problems solved using two-pointer technique:
 * 1. Celebrity Problem - Find celebrity in party
 * 2. Merge Sorted Arrays - Merge nums2 into nums1 in-place
 *
 * <p><b>Intuition & Approach:</b><br>
 * Two-pointer technique patterns:
 * - Opposite ends: Start from both ends, move towards center
 * - Same direction: Both pointers move left-to-right at different speeds
 * - Fast-slow pointers: One moves twice as fast (cycle detection)
 * 
 * Key principles:
 * - Reduces O(N²) brute force to O(N) by eliminating redundant checks
 * - Works best on sorted/organized data
 * - Maintains invariants while moving pointers
 * - Often eliminates need for extra space
 * 
 * Common applications:
 * - Pair/triplet sum problems
 * - Merging sorted arrays
 * - Removing duplicates in-place
 * - Palindrome checking
 * - Container with most water
 *
 * <p><b>Time Complexity:</b> O(N) typical - single pass through data
 * <br><b>Space Complexity:</b> O(1) typical - constant extra space
 */
public class TwoPointer {
    
    /**
     * Celebrity Problem: Find person who knows no one but is known by everyone.
     * 
     * Two-phase elimination:
     * Phase 1: Use two pointers to eliminate n-1 non-celebrities
     * Phase 2: Validate the remaining candidate
     */
    public int celebrity(int[][] mat) {
        int n = mat.length;
        int i = 0, j = n - 1;

        // Phase 1: Eliminate non-celebrities using two pointers
        while (i < j) {
            if (mat[j][i] == 1) {
                j--;  // j knows i → j can't be celebrity
            } else {
                i++;  // j doesn't know i → i can't be celebrity
            }
        }

        // Phase 2: Candidate found at index i, now validate
        int candidate = i;
        for (i = 0; i < n; i++) {
            if (i == candidate) continue;
            // Celebrity must not know anyone AND be known by everyone
            if (mat[candidate][i] != 0 || mat[i][candidate] != 1) {
                return -1;
            }
        }
        
        return candidate;
    }

    /**
     * Merge Sorted Arrays: Merge nums2 into nums1 in-place (nums1 has extra space).
     * 
     * Key insight: Fill from end to avoid overwriting unprocessed elements.
     * Use three pointers: p1 (nums1 last), p2 (nums2 last), p (result last).
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        
        // Fill from end, choosing larger element
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p--] = nums1[p1--];
            } else {
                nums1[p--] = nums2[p2--];
            }
        }
        
        // Copy remaining elements from nums2 (if any)
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }
}
