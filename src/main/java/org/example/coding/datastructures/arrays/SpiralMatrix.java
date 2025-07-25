package org.example.coding.datastructures.arrays;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int colStart = 0;
        int colEnd = matrix[0].length - 1;
        int rowStart = 0;
        int rowEnd = matrix.length - 1;

        while (colEnd >= colStart && rowEnd >= rowStart) {
            for (int j = colStart; j <= colEnd; j++) {
                ans.add(matrix[rowStart][j]);
            }
            rowStart++;
            for (int i = rowStart; i <= rowEnd; i++) {
                ans.add(matrix[i][colEnd]);
            }
            colEnd--;
            if (rowStart <= rowEnd) {
                for (int j = colEnd; j >= colStart; j--) {
                    ans.add(matrix[rowEnd][j]);
                }
                rowEnd--;
            }
            if (colEnd >= colStart) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    ans.add(matrix[i][colStart]);
                }
                colStart++;
            }
        }
        return ans;
    }
}
