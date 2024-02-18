package pieces;

import logic.Board;

import static utils.PieceNumber.PAWN;

public class Pawn extends Piece {
    public Pawn(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.setCol(col);
        this.setRow(row);
        this.setxPos(col * board.getTITLE_SIZE());
        this.setyPos(row * board.getTITLE_SIZE());

        this.setWhite(isWhite);
        this.setName("Pawn");

        this.setSprite(PAWN.ordinal(), isWhite);
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        int colorIndex = isWhite() ? 1 : -1;

        //push pawn 1
        if (this.getCol() == col && row == this.getRow() - colorIndex && getBoard().getPiece(col, row) == null)
            return true;
        //push pawn 2
        if (isFirstMove() && this.getCol() == col && row == this.getRow() - colorIndex * 2 && getBoard().getPiece(col, row) == null && getBoard().getPiece(col, row + colorIndex) == null)
            return true;
        //capture left
        if (col == this.getCol() - 1 && row == this.getRow() - colorIndex && getBoard().getPiece(col, row) != null)
            return true;
        //capture right
        if (col == this.getCol() + 1 && row == this.getRow() - colorIndex && getBoard().getPiece(col, row) != null)
            return true;

        //en passant left
        if (getBoard().getTileNum(col, row) == getBoard().getEnPassantTile() && col == this.getCol() - 1 && row == this.getRow() - colorIndex && getBoard().getPiece(col, row + colorIndex) != null) {
            return true;
        }
        //en passant right
        if (getBoard().getTileNum(col, row) == getBoard().getEnPassantTile() && col == this.getCol() + 1 && row == this.getRow() - colorIndex && getBoard().getPiece(col, row + colorIndex) != null) {
            return true;
        }
        return false;
    }
}
