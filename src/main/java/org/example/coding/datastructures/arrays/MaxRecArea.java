package org.example.coding.datastructures.arrays;

public class MaxRecArea {
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
