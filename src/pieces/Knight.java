package pieces;

import logic.Board;

import java.awt.image.BufferedImage;

public class Knight extends Piece {
    public Knight(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.setCol(col);
        this.setRow(row);
        this.setxPos(col * board.getTITLE_SIZE());
        this.setyPos(row * board.getTITLE_SIZE());

        this.setWhite(isWhite);
        this.setName("Knight");

        this.setSprite(getSheet().getSubimage(3 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.getTITLE_SIZE(), board.getTITLE_SIZE(), BufferedImage.SCALE_SMOOTH));
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return Math.abs(col - this.getCol()) * Math.abs(row - this.getRow()) == 2;
    }
}
