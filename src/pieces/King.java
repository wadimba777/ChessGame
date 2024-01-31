package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class King extends Piece{
    public King(Board board, int col, int row, boolean isWhite){
        super(board);
        this.setCol(col);
        this.setRow(row);
        this.setxPos(col * board.TITLE_SIZE);
        this.setyPos(row * board.TITLE_SIZE);

        this.setWhite(isWhite);
        this.setName("King");

        this.sprite = sheet.getSubimage(0 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.TITLE_SIZE, board.TITLE_SIZE, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMovement(int col, int row) {
        return Math.abs((col - this.getCol()) * (row - this.getRow())) == 1 || Math.abs(col - this.getCol()) + Math.abs(row - this.getRow()) == 1;
    }
}
