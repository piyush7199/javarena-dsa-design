package org.example.lld.practice.tic_tac_toe;

import org.example.lld.practice.tic_tac_toe.models.Board;
import org.example.lld.practice.tic_tac_toe.models.PieceO;
import org.example.lld.practice.tic_tac_toe.models.PieceX;
import org.example.lld.practice.tic_tac_toe.models.Player;
import org.example.lld.practice.tic_tac_toe.stratergies.DefaultStrategy;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TicTacToeGame {

    private final Deque<Player> players;
    private final Board board;

    public TicTacToeGame(int size) {
        players = new LinkedList<>();
        board = new Board(size);
    }

    public void initializeGame() {
        Player player1 = new Player("player1", new PieceO(), new DefaultStrategy());
        Player player2 = new Player("player2", new PieceX(), new DefaultStrategy());

        players.offer(player1);
        players.offer(player2);
    }

    public String startGame() {
        boolean noWinner = true;

        while (noWinner) {
            Player playerTurn = players.removeFirst();

            board.printBoard();

            List<int[]> freeShells = board.getFreeCells();
            if (freeShells.isEmpty()) {
                noWinner = false;
                continue;
            }

            int[] moves = playerTurn.playerStrategy().makeMove(board);

            boolean placeSuccessfully = board.addPiece(moves[0], moves[1], playerTurn.piece());
            if (!placeSuccessfully) {
                System.out.println("Incorrect position chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);
            boolean isWinner = board.isThereWinner(moves[0], moves[1], playerTurn.piece().getPieceType());
            if (isWinner) {
                return playerTurn.username();
            }
        }
        return "tie";
    }


}
