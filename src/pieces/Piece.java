package pieces;

import logic.Board;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.ImageManager.*;

public abstract class Piece {
    private int col;
    private int row;
    private int xPos, yPos;
    private boolean isWhite;
    private String name;
    private Image sprite;
    private final Board board;
    private boolean isFirstMove = true;

    public Piece(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setSprite(int pieceOrdinal, boolean isWhite) {
        this.sprite = getSheet()
                .getSubimage(pieceOrdinal * getSheetScale(), isWhite ? 0 : getSheetScale(), getSheetScale(), getSheetScale())
                .getScaledInstance(board.getTITLE_SIZE(), board.getTITLE_SIZE(), BufferedImage.SCALE_SMOOTH);
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValidMovement(int col, int row) {
        return true;
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        return false;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(sprite, xPos, yPos, null);
    }
}
