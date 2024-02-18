package pieces;

import logic.Board;

import static utils.PieceNumber.QUEEN;

public class Queen extends Piece {
    public Queen(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.setCol(col);
        this.setRow(row);
        this.setxPos(col * board.getTITLE_SIZE());
        this.setyPos(row * board.getTITLE_SIZE());

        this.setWhite(isWhite);
        this.setName("Queen");

        this.setSprite(QUEEN.ordinal(), isWhite);
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return (Math.abs(this.getCol() - col) == Math.abs(this.getRow() - row))
                || (this.getCol() == col
                || this.getRow() == row);
    }

    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        if (this.getCol() == col || this.getRow() == row) {
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
        } else {
            //up left
            if (this.getCol() > col && this.getRow() > row)
                for (int i = 1; i < Math.abs(this.getCol() - col); i++)
                    if (getBoard().getPiece(this.getCol() - i, this.getRow() - i) != null)
                        return true;
            //up right
            if (this.getCol() < col && this.getRow() > row)
                for (int i = 1; i < Math.abs(this.getCol() - col); i++)
                    if (getBoard().getPiece(this.getCol() + i, this.getRow() - i) != null)
                        return true;
            //down left
            if (this.getCol() > col && this.getRow() < row)
                for (int i = 1; i < Math.abs(this.getCol() - col); i++)
                    if (getBoard().getPiece(this.getCol() - i, this.getRow() + i) != null)
                        return true;
            //down right
            if (this.getCol() < col && this.getRow() < row)
                for (int i = 1; i < Math.abs(this.getCol() - col); i++)
                    if (getBoard().getPiece(this.getCol() + i, this.getRow() + i) != null)
                        return true;
        }
        return false;
    }
}