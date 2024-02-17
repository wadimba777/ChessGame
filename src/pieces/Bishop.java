package pieces;

import logic.Board;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {
    public Bishop(Board board, int col, int row, boolean isWhite) {
        super(board);
        setCol(col);
        setRow(row);
        this.setxPos(col * board.getTITLE_SIZE());
        this.setyPos(row * board.getTITLE_SIZE());

        this.setWhite(isWhite);
        this.setName("Bishop");

        this.setSprite(getSheet().getSubimage(2 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.getTITLE_SIZE(), board.getTITLE_SIZE(), BufferedImage.SCALE_SMOOTH));
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return Math.abs(getCol() - col) == Math.abs(getRow() - row);
    }

    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
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
        return false;
    }
}
