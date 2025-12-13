package com.javarena.dsa.datastructures.arrays;

/**
 * Area of Max Diagonal Rectangle
 *
 * <p><b>Problem Statement:</b><br>
 * Given a 2D array dimensions where dimensions[i] = [length_i, width_i], find the area of the rectangle 
 * with the maximum diagonal. If there are multiple rectangles with the same maximum diagonal, 
 * return the area of the one with maximum area.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Diagonal of rectangle = √(length² + width²) using Pythagorean theorem
 * - Iterate through all dimensions calculating diagonal and area
 * - Track maximum diagonal seen so far
 * - When larger diagonal found, update maxDiagonal and maxArea
 * - When equal diagonal found, update maxArea to max(current, maxArea)
 * - Return the maximum area corresponding to maximum diagonal
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through all dimensions
 * <br><b>Space Complexity:</b> O(1) - Only tracking max values
 */
public class MaxRecArea {
    /**
     * Finds area of rectangle with maximum diagonal.
     */
    public int areaOfMaxDiagonal(int[][] dimensions) {
        double maxDiagonal = 0;
        int maxArea = 0;
        
        for (int[] dimension : dimensions) {
            int len = dimension[0];
            int width = dimension[1];
            double diagonal = Math.sqrt(len * len + width * width);
            int area = len * width;
            
            if (diagonal > maxDiagonal) {
                maxDiagonal = diagonal;
                maxArea = area;
            } else if (diagonal == maxDiagonal) {
                maxArea = Math.max(area, maxArea);
            }
        }
        
        return maxArea;
    }
}
