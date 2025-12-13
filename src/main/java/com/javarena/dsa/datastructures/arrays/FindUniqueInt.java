package com.javarena.dsa.datastructures.arrays;

/**
 * Find N Unique Integers Sum up to Zero
 *
 * <p><b>Problem Statement:</b><br>
 * Given an integer n, return any array containing n unique integers such that they add up to 0.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Create pairs of opposite numbers: [1, -1], [2, -2], [3, -3]...
 * - Use two pointers: i at start, j at end
 * - Place ele at ans[i] and -ele at ans[j]
 * - Move both pointers inward: i++, j--
 * - If n is odd, middle element will be 0 (sum remains zero)
 * - This ensures all elements are unique and sum to exactly 0
 *
 * <p><b>Time Complexity:</b> O(N) - Fill array once
 * <br><b>Space Complexity:</b> O(N) - Result array
 */
public class FindUniqueInt {
    /**
     * Returns array of n unique integers that sum to zero.
     */
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        int i = 0;
        int j = n - 1;
        int ele = 1;
        
        while (i <= j) {
            ans[i] = ele;
            ans[j] = -ele;
            i++;
            j--;
            ele++;
        }
        
        if (n % 2 == 1) ans[n / 2] = 0;
        return ans;
    }
}
