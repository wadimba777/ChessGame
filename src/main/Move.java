package main;

import pieces.Piece;

public class Move {
    private final int newCol;
    private final int newRow;


    public int getNewCol() {
        return newCol;
    }

    public int getNewRow() {
        return newRow;
    }

    Piece piece;
    Piece capture;

    public Move(Board board, Piece piece, int newCol, int newRow) {
        this.newCol = newCol;
        this.newRow = newRow;

        this.piece = piece;
        this.capture = board.getPiece(newCol, newRow);
    }
}
