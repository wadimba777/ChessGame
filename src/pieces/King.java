package pieces;

import logic.Board;
import logic.Move;


import static utils.PieceNumber.KING;

public class King extends Piece {
    public King(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.setCol(col);
        this.setRow(row);
        this.setxPos(col * board.getTITLE_SIZE());
        this.setyPos(row * board.getTITLE_SIZE());

        this.setWhite(isWhite);
        this.setName("King");

        this.setSprite(KING.ordinal(), isWhite);
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return Math.abs((col - this.getCol()) * (row - this.getRow())) == 1
                || Math.abs(col - this.getCol()) + Math.abs(row - this.getRow()) == 1
                || canCastle(col, row);
    }

    private boolean canCastle(int col, int row) {
        if (this.getRow() == row) {
            if (col == 6) {
                Piece rook = getBoard().getPiece(7, row);
                if (rook != null && rook.isFirstMove() && this.isFirstMove()) {
                    return getBoard().getPiece(5, row) == null &&
                            getBoard().getPiece(6, row) == null &&
                            getBoard().getCheckScanner().isKingChecked(new Move(getBoard(), this, 5, row));
                }
            } else if (col == 2) {
                Piece rook = getBoard().getPiece(0, row);
                if (rook != null && rook.isFirstMove() && this.isFirstMove()) {
                    return getBoard().getPiece(3, row) == null &&
                            getBoard().getPiece(2, row) == null &&
                            getBoard().getPiece(1, row) == null &&
                            getBoard().getCheckScanner().isKingChecked(new Move(getBoard(), this, 3, row));
                }
            }
        }
        return false;
    }
}
