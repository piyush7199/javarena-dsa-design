package org.example.lld.practice.tic_tac_toe.stratergies;

import org.example.lld.practice.tic_tac_toe.models.Board;

import java.util.Scanner;

public class DefaultStrategy implements PlayerStrategy {

    private final Scanner scanner;

    public DefaultStrategy() {
        this.scanner = new Scanner(System.in);
    }


    @Override
    public int[] makeMove(Board board) {
        while (true) {
            try {
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (board.isValidMove(row, col)) {
                    return new int[]{row, col};
                }

                System.out.println("Invalid move. Try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter row and column as numbers.");
            }
        }

    }
}
