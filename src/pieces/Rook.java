package pieces;

import logic.Board;

import static utils.PieceNumber.ROOK;

public class Rook extends Piece {
    public Rook(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.setCol(col);
        this.setRow(row);
        this.setxPos(col * board.getTITLE_SIZE());
        this.setyPos(row * board.getTITLE_SIZE());

        this.setWhite(isWhite);
        this.setName("Rook");

        this.setSprite(ROOK.ordinal(), isWhite);
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return this.getCol() == col || this.getRow() == row;
    }

    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        //left
        if (this.getCol() > col)
            for (int c = this.getCol() - 1; c > col; c--)
                if (getBoard().getPiece(c, this.getRow()) != null)
                    return true;

        //right
        if (this.getCol() < col)
            for (int c = this.getCol() + 1; c < col; c++)
                if (getBoard().getPiece(c, this.getRow()) != null)
                    return true;

        //up
        if (this.getRow() > row)
            for (int r = this.getRow() - 1; r > row; r--)
                if (getBoard().getPiece(this.getCol(), r) != null)
                    return true;

        //down
        if (this.getRow() < row)
            for (int r = this.getRow() + 1; r < row; r++)
                if (getBoard().getPiece(this.getCol(), r) != null)
                    return true;

        return false;
    }
}
