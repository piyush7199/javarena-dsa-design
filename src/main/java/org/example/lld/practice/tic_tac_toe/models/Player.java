package org.example.lld.practice.tic_tac_toe.models;

import org.example.lld.practice.tic_tac_toe.stratergies.PlayerStrategy;

public record Player(String username, Piece piece, PlayerStrategy playerStrategy) {
}
