package logic;

import pieces.Piece;

public class Move {
    private final int newCol;
    private final int newRow;
    private Piece piece;
    private Piece capture;

    public int getNewCol() {
        return newCol;
    }

    public int getNewRow() {
        return newRow;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getCapture() {
        return capture;
    }

    public void setCapture(Piece capture) {
        this.capture = capture;
    }

    public Move(Board board, Piece piece, int newCol, int newRow) {
        this.newCol = newCol;
        this.newRow = newRow;
        this.piece = piece;
        this.capture = board.getPiece(newCol, newRow);
    }
}
