package com.javarena.dsa.datastructures.arrays;

/**
 * Rearrange Array Elements by Sign
 *
 * <p><b>Problem Statement:</b><br>
 * Rearrange array elements so that positive and negative integers alternate. Start with positive integer.
 * Equal number of positive and negative integers are guaranteed.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use two pointers: i for even indices (0,2,4...), j for odd indices (1,3,5...)
 * - Positive numbers go to even indices
 * - Negative numbers go to odd indices
 * - Iterate through input array once
 * - Place each element in appropriate position based on sign
 * - Increment pointer by 2 to maintain alternating pattern
 * - Result: [pos, neg, pos, neg, pos, neg...]
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through array
 * <br><b>Space Complexity:</b> O(N) - Result array
 */
public class RearrangeArrayEleBySign {
    /**
     * Rearranges elements with positive-negative alternating pattern.
     */
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int i = 0;  // Even indices for positive
        int j = 1;  // Odd indices for negative
        
        for (int ele : nums) {
            if (ele > 0) {
                ans[i] = ele;
                i += 2;
            } else {
                ans[j] = ele;
                j += 2;
            }
        }
        
        return ans;
    }
}
