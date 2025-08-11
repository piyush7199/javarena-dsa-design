package org.example.lld.practice.tic_tac_toe.models;

import org.example.lld.practice.tic_tac_toe.enums.PieceType;

public class Piece {
    private final PieceType pieceType;

    public Piece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
