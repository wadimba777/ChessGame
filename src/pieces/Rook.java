package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Rook extends Piece{
    public Rook(Board board, int col, int row, boolean isWhite){
        super(board);
        this.setCol(col);
        this.setRow(row);
        this.setxPos(col * board.TITLE_SIZE);
        this.setyPos(row * board.TITLE_SIZE);

        this.setWhite(isWhite);
        this.setName("Rook");

        this.sprite = sheet.getSubimage(4 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.TITLE_SIZE, board.TITLE_SIZE, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMovement(int col, int row) {
        return this.getCol() == col || this.getRow() == row;
    }
    public boolean moveCollidesWithPiece(int col, int row) {

        //left
        if (this.getCol() > col)
            for (int c = this.getCol() - 1; c > col; c--)
                if (board.getPiece(c, this.getRow()) != null)
                    return true;

        //right
        if (this.getCol() < col)
            for (int c = this.getCol() + 1; c < col; c++)
                if (board.getPiece(c, this.getRow()) != null)
                    return true;

        //up
        if (this.getRow() > row)
            for (int r = this.getRow() - 1; r > row; r--)
                if (board.getPiece(this.getCol(), r) != null)
                    return true;

        //down
        if (this.getRow() < row)
            for (int r = this.getRow() + 1; r < row; r++)
                if (board.getPiece(this.getCol(), r) != null)
                    return true;

        return false;
    }
}
