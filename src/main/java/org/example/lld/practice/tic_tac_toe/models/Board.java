package org.example.lld.practice.tic_tac_toe.models;

import org.example.lld.practice.tic_tac_toe.enums.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int size;
    private final Piece[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new Piece[size][size];
    }

    public int getSize() {
        return size;
    }


    public boolean isValidMove(int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0) {
            System.out.println("Please provide a valid row & column");
            return false;
        }
        if (board[row][col] == null) {
            return true;
        }
        System.out.println("Cell is already occupied");
        return false;
    }

    public boolean addPiece(int row, int col, Piece piece) {
        if (isValidMove(row, col)) {
            board[row][col] = piece;
            return true;
        }
        return false;
    }

    public List<int[]> getFreeCells() {
        List<int[]> freeCells = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    freeCells.add(new int[]{i, j});
                }
            }
        }

        return freeCells;
    }


    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getPieceType().name() + "   ");
                } else {
                    System.out.print("    ");

                }
                System.out.print(" | ");
            }
            System.out.println();

        }
    }

    public boolean isThereWinner(int row, int column, PieceType pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for (int i = 0; i < size; i++) {

            if (board[row][i] == null || board[row][i].getPieceType() != pieceType) {
                rowMatch = false;
                break;
            }
        }

        //need to check in column
        for (int i = 0; i < size; i++) {

            if (board[i][column] == null || board[i][column].getPieceType() != pieceType) {
                columnMatch = false;
                break;
            }
        }

        //need to check diagonals
        for (int i = 0, j = 0; i < size; i++, j++) {
            if (board[i][j] == null || board[i][j].getPieceType() != pieceType) {
                diagonalMatch = false;
                break;
            }
        }

        //need to check anti-diagonals
        for (int i = 0, j = size - 1; i < size; i++, j--) {
            if (board[i][j] == null || board[i][j].getPieceType() != pieceType) {
                antiDiagonalMatch = false;
                break;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }

}
