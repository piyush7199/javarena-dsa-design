package org.example.lld.practice.tic_tac_toe;

public class Main {
    public static void main(String[] args) {
        TicTacToeGame ticTacToeGame = new TicTacToeGame(3);
        ticTacToeGame.initializeGame();
        String winner = ticTacToeGame.startGame();
        System.out.println("Winner : " + winner);
    }
}
