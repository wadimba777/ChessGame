package pieces;

import logic.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Piece {
    private int col;
    private int row;
    private int xPos, yPos;
    private boolean isWhite;
    private String name;
    private BufferedImage sheet;
    private Image sprite;
    private final Board board;
    private boolean isFirstMove = true;

    public Piece(Board board) {
        this.board = board;
    }

    {
        try {
            this.sheet = ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("pieces.png")));
        } catch (IOException e) {
            System.out.println("No such a file " + e);
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setSprite(int pieceOrdinal, boolean isWhite) {
        this.sprite = getSheet()
                .getSubimage(pieceOrdinal * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(board.getTITLE_SIZE(), board.getTITLE_SIZE(), BufferedImage.SCALE_SMOOTH);
    }

    public BufferedImage getSheet() {
        return sheet;
    }

    protected int sheetScale = sheet.getWidth() / 6;

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
