package org.example.coding.algorithms.recursionAndBacktracking;

public class WordSearch {

    /**
     * Checks if a given word exists in the board by performing DFS-based backtracking.
     * <p>
     * Intuition:
     * - Start from each cell in the grid.
     * - Use DFS to explore all 4 directions (up, down, left, right) from the current cell,
     * matching each character of the word.
     * - Mark the cell as visited temporarily (e.g., by setting it to '.') to avoid revisiting in the same path.
     * - Backtrack if the current path does not lead to a solution.
     * <p>
     * Time Complexity: O(N * M * 4^L)
     * - N = number of rows, M = number of columns, L = length of the word.
     * - For each cell, we may explore up to 4 directions at each step (DFS depth = L).
     * <p>
     * Space Complexity: O(L)
     * - Stack space used by recursion in the worst case (depth of the DFS = length of the word).
     * - In-place marking avoids extra visited matrix.
     */
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (wordSearcher(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean wordSearcher(char[][] board, String word, int i, int j, int wInd) {
        if (wInd == word.length()) {
            return true;
        }
        int n = board.length;
        int m = board[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m) return false;

        if (board[i][j] != word.charAt(wInd)) return false;
        char temp = board[i][j];
        board[i][j] = '.';
        int[] nRow = {1, 0, -1, 0};
        int[] nCol = {0, 1, 0, -1};
        for (int k = 0; k < 4; k++) {
            if (wordSearcher(board, word, i + nRow[k], j + nCol[k], wInd + 1)) {
                return true;
            }
        }
        board[i][j] = temp;
        return false;
    }
}
