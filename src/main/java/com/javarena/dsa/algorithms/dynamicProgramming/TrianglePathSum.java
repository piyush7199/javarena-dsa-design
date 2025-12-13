package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.List;

/**
 * Triangle Minimum Path Sum
 *
 * <p><b>Problem Statement:</b><br>
 * Given triangle array, return minimum path sum from top to bottom.
 * For each step, can move to adjacent number in row below (i → i or i+1).
 *
 * <p><b>Intuition & Approach:</b><br>
 * DP from bottom to top:
 * - Start from bottom row (already known values)
 * - For each cell in row above, choose minimum path from two adjacent cells below
 * - dp[i][j] = triangle[i][j] + min(dp[i+1][j], dp[i+1][j+1])
 * 
 * Can modify triangle in-place or use separate DP array.
 * 
 * Space optimization: Only need one row at a time.
 * Bottom-up approach avoids recursion overhead.
 *
 * <p><b>Time Complexity:</b> O(N²) - N = number of rows, total cells = N(N+1)/2
 * <br><b>Space Complexity:</b> O(N) for 1D DP array or O(1) if modifying in-place
 */
public class TrianglePathSum {
    
    /**
     * Finds minimum path sum using bottom-up DP with 1D array.
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        
        for (int row = n - 1; row >= 0; row--) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col + 1]);
            }
        }
        
        return dp[0];
    }
    
    /**
     * Alternative: Modify triangle in-place to save space.
     */
    public int minimumTotalInPlace(List<List<Integer>> triangle) {
        int n = triangle.size();
        
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
                int current = triangle.get(row).get(col);
                int below = triangle.get(row + 1).get(col);
                int belowRight = triangle.get(row + 1).get(col + 1);
                triangle.get(row).set(col, current + Math.min(below, belowRight));
            }
        }
        
        return triangle.get(0).get(0);
    }
}
