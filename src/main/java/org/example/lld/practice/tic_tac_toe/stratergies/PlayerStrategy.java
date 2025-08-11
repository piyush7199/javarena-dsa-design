package org.example.lld.practice.tic_tac_toe.stratergies;

import org.example.lld.practice.tic_tac_toe.models.Board;

public interface PlayerStrategy {
    int[] makeMove(Board board);
}
