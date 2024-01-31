package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {
    public Bishop(Board board, int col, int row, boolean isWhite) {
        super(board);
        setCol(col);
        setRow(row);
        this.setxPos(col * board.TITLE_SIZE);
        this.setyPos(row * board.TITLE_SIZE);

        this.setWhite(isWhite);
        this.setName("Bishop");

        this.sprite = sheet.getSubimage(2 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.TITLE_SIZE, board.TITLE_SIZE, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row) {
        return Math.abs(getCol() - col) == Math.abs(getRow() - row);
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        //up left
        if (this.getCol() > col && this.getRow() > row)
            for (int i = 1; i < Math.abs(this.getCol() - col); i++)
                if (board.getPiece(this.getCol() - i, this.getRow() - i) != null)
                    return true;
        //up right
        if (this.getCol() < col && this.getRow() > row)
            for (int i = 1; i < Math.abs(this.getCol() - col); i++)
                if (board.getPiece(this.getCol() + i, this.getRow() - i) != null)
                    return true;
        //down left
        if (this.getCol() > col && this.getRow() < row)
            for (int i = 1; i < Math.abs(this.getCol() - col); i++)
                if (board.getPiece(this.getCol() - i, this.getRow() + i) != null)
                    return true;
        //down right
        if (this.getCol() < col && this.getRow() < row)
            for (int i = 1; i < Math.abs(this.getCol() - col); i++)
                if (board.getPiece(this.getCol() + i, this.getRow() + i) != null)
                    return true;
        return false;
    }
}
