package com.javarena.dsa.algorithms.recursionAndBacktracking;

/**
 * Word Search
 *
 * <p><b>Problem Statement:</b><br>
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use backtracking with DFS to explore all possible paths from each cell
 * - Start from each cell and try to match the word character by character
 * - Explore all 4 directions (up, down, left, right) from current cell
 * - Mark visited cells temporarily (set to '.') to avoid reuse in same path
 * - Backtrack by restoring the cell value when returning from recursion
 * - If any path successfully matches entire word, return true
 *
 * <p><b>Time Complexity:</b> O(N * M * 4^L) - N=rows, M=columns, L=word length
 * <br><b>Space Complexity:</b> O(L) - Recursion stack depth equals word length
 */
public class WordSearch {

    /**
     * Main method to check if word exists in board.
     *
     * @param board 2D character grid
     * @param word word to search for
     * @return true if word exists in board
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

    /**
     * Helper method for DFS backtracking.
     *
     * @param board character grid
     * @param word target word
     * @param i current row
     * @param j current column
     * @param wInd current word index
     * @return true if word can be formed from this position
     */
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
