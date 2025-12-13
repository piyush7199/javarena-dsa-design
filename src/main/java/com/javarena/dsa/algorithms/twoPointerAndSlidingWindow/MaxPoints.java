package com.javarena.dsa.algorithms.twoPointerAndSlidingWindow;

/**
 * Maximum Points You Can Obtain from Cards
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer array cardPoints and integer k, find maximum score by picking exactly k cards
 * from either end of the array (left or right). Return maximum score possible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Sliding window on picked cards:
 * - Can pick i cards from left and (k-i) cards from right, where 0 ≤ i ≤ k
 * - Try all combinations: k from left, then shift window to include right cards
 * - Start with first k cards (all from left)
 * - Replace leftmost picked card with rightmost unpicked card iteratively
 * 
 * Strategy:
 * 1. Calculate sum of first k cards (all from left)
 * 2. For each position, remove one from left, add one from right
 * 3. Track maximum sum across all configurations
 * 
 * Optimization: Only need to try k+1 combinations instead of all possibilities.
 *
 * <p><b>Time Complexity:</b> O(K) - Calculate initial sum + k iterations
 * <br><b>Space Complexity:</b> O(1) - Constant extra space
 */
public class MaxPoints {
    
    /**
     * Finds maximum score by picking k cards from either end.
     */
    public int maxScore(int[] cardPoints, int k) {
        int leftSum = 0;
        int rightSum = 0;
        int n = cardPoints.length;
        
        // Calculate sum of first k cards (all from left)
        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }
        
        int maxSum = leftSum;
        int leftPtr = k - 1;
        
        // Try replacing left cards with right cards
        for (int rightPtr = n - 1; rightPtr >= n - k; rightPtr--) {
            rightSum += cardPoints[rightPtr];
            leftSum -= cardPoints[leftPtr];
            leftPtr--;
            
            maxSum = Math.max(maxSum, leftSum + rightSum);
        }
        
        return maxSum;
    }
}
