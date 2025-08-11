package org.example.lld.practice.tic_tac_toe.models;

import org.example.lld.practice.tic_tac_toe.stratergies.PlayerStrategy;

public class Player {
    private final String username;
    private final Piece piece;
    private final PlayerStrategy playerStrategy;

    public Player(String username, Piece piece, PlayerStrategy playerStrategy) {
        this.username = username;
        this.piece = piece;
        this.playerStrategy = playerStrategy;
    }

    public String getUsername() {
        return username;
    }

    public Piece getPiece() {
        return piece;
    }

    public PlayerStrategy getPlayerStrategy() {
        return playerStrategy;
    }
}
