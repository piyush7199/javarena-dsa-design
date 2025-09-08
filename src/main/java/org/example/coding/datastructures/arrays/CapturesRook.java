package org.example.coding.datastructures.arrays;

public class CapturesRook {
    public int numRookCaptures(char[][] board) {
        int result = 0;
        int rookRow = 0;
        int rookCol = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == 'R') {
                    rookRow = row;
                    rookCol = col;
                }
            }
        }
        // Up: col, row--
        for (int i = rookRow; i >= 0; i--) {
            if (board[i][rookCol] == 'B') {
                break;
            } else if (board[i][rookCol] == 'p') {
                result++;
                break;
            }
        }
        // Down: col; row++
        for (int i = rookRow; i < 8; i++) {
            if (board[i][rookCol] == 'B') {
                break;
            } else if (board[i][rookCol] == 'p') {
                result++;
                break;
            }
        }
        // Left: col--; row
        for (int i = rookCol; i >= 0; i--) {
            if (board[rookRow][i] == 'B') {
                break;
            } else if (board[rookRow][i] == 'p') {
                result++;
                break;
            }
        }
        // Right: col++; row
        for (int i = rookRow; i < 8; i++) {
            if (board[rookRow][i] == 'B') {
                break;
            } else if (board[rookRow][i] == 'p') {
                result++;
                break;
            }
        }
        return result;
    }
}
