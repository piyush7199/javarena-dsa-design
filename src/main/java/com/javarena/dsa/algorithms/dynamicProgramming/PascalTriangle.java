package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Pascal's Triangle
 *
 * <p><b>Problem Statement:</b><br>
 * Given integer numRows, return first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is sum of two numbers directly above it.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Build triangle row by row:
 * - First and last element of each row = 1
 * - Middle elements: triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j]
 * 
 * Properties:
 * - Row i has i+1 elements
 * - Symmetrical: triangle[i][j] = triangle[i][i-j]
 * - Can be computed using combinations: C(n, k)
 * 
 * Simple DP where each row depends only on previous row.
 *
 * <p><b>Time Complexity:</b> O(N²) - Generate N rows, row i has i elements
 * <br><b>Space Complexity:</b> O(N²) - Store entire triangle
 */
public class PascalTriangle {
    
    /**
     * Generates Pascal's triangle with numRows rows.
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    int sum = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                    row.add(sum);
                }
            }
            triangle.add(row);
        }
        
        return triangle;
    }
    
    /**
     * Returns the kth row of Pascal's triangle (0-indexed).
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = row.size() - 1; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1);
        }
        
        return row;
    }
}
