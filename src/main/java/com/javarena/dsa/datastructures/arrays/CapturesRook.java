package com.javarena.dsa.datastructures.arrays;

/**
 * Available Captures for Rook
 *
 * <p><b>Problem Statement:</b><br>
 * Given an 8x8 chessboard, find the number of pawns ('p') that can be captured by a white rook ('R').
 * The rook can move in 4 directions (up, down, left, right) until it hits a bishop ('B') or the edge.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - First, find the position of the rook ('R') on the board
 * - From rook's position, check all 4 directions:
 *   - Up: decrease row, keep column same
 *   - Down: increase row, keep column same
 *   - Left: decrease column, keep row same
 *   - Right: increase column, keep row same
 * - In each direction, stop when hitting bishop ('B') or edge
 * - Count captures when finding pawn ('p')
 *
 * <p><b>Time Complexity:</b> O(1) - Board is fixed 8x8, constant operations
 * <br><b>Space Complexity:</b> O(1) - Only a few variables used
 */
public class CapturesRook {
    /**
     * Counts number of pawns rook can capture.
     */
    public int numRookCaptures(char[][] board) {
        int result = 0;
        int rookRow = 0;
        int rookCol = 0;
        
        // Find rook position
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
