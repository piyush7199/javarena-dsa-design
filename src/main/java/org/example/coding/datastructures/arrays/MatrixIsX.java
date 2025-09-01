package org.example.coding.datastructures.arrays;

public class MatrixIsX {
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        int i = 0;
        int j = n - 1;
        for (int k = 0; k < n; k++) {
            if (grid[i][k] == 0 || grid[j][k] == 0) return false;
            i++;
            j--;
        }
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                if (k == l) continue;
                if (n - k - 1 == l) continue;
                if (grid[k][l] != 0) return false;
            }
        }

        return true;
    }
}
