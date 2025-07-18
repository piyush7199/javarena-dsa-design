package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class MaxPoints {

    /**
     * Returns the maximum score by picking `k` elements from either end of the array.
     * <p>
     * Intuition:
     * - Try all possible ways of picking `i` cards from the start and `k - i` cards from the end.
     * - Use a sliding window to efficiently calculate this.
     * <p>
     * Time Complexity: O(k)
     * Space Complexity: O(1)
     */
    public int maxScore(int[] a, int k) {
        int leftsum = 0, rightsum = 0;
        int l = k - 1;

        for (int i = 0; i < k; i++) {
            leftsum += a[i];
        }

        int maxsum = leftsum;

        for (int h = a.length - 1; h >= a.length - k; h--) {
            rightsum += a[h];
            leftsum -= a[l--];
            maxsum = Math.max(maxsum, leftsum + rightsum);
        }

        return maxsum;
    }
}
