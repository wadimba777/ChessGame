package logic;

import utils.Input;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import pieces.King;

public class Board extends JPanel {
    private final int TITLE_SIZE = 85;
    private final int COLS = 8;
    private final int ROWS = 8;
    private static final ArrayList<Piece> pieces = new ArrayList<>(32);
    private Piece selectedPiece;
    private final CheckScanner checkScanner = new CheckScanner(this);
    private int enPassantTile = -1;

    public Board() {
        this.setPreferredSize(new Dimension(COLS * TITLE_SIZE, ROWS * TITLE_SIZE));

        Input input = new Input(this);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addPieces();
    }

    public int getEnPassantTile() {
        return enPassantTile;
    }

    public int getTITLE_SIZE() {
        return TITLE_SIZE;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public CheckScanner getCheckScanner() {
        return checkScanner;
    }

    public Piece getPiece(int col, int row) {
        for (Piece piece : pieces) {
            if (piece.getCol() == col && piece.getRow() == row) {
                return piece;
            }
        }
        return null;
    }

    public void makeMove(Move move) {
        if (move.getPiece().getName().equals("Pawn")) {
            movePawn(move);
        } else if (move.getPiece().getName().equals("King")) {
            moveKing(move);
        }
        move.getPiece().setCol(move.getNewCol());
        move.getPiece().setRow(move.getNewRow());
        move.getPiece().setxPos(move.getNewCol() * TITLE_SIZE);
        move.getPiece().setyPos(move.getNewRow() * TITLE_SIZE);

        move.getPiece().setFirstMove(false);

        capture(move.getCapture());
    }

    private void moveKing(Move move) {
        if (Math.abs(move.getPiece().getCol() - move.getNewCol()) == 2) {
            Piece rook;
            if (move.getPiece().getCol() < move.getNewCol()) {
                rook = getPiece(7, move.getPiece().getRow());
                rook.setCol(5);
            } else {
                rook = getPiece(0, move.getPiece().getRow());
                rook.setCol(3);
            }
            rook.setxPos(rook.getCol() * getTITLE_SIZE());
        }
    }

    private void movePawn(Move move) {
        //en passant
        int colorIndex = move.getPiece().isWhite() ? 1 : -1;

        if (getTileNum(move.getNewCol(), move.getNewRow()) == enPassantTile) {
            move.setCapture(getPiece(move.getNewCol(), move.getNewRow() + colorIndex));
        }
        if (Math.abs(move.getPiece().getRow() - move.getNewRow()) == 2) {
            enPassantTile = getTileNum(move.getNewCol(), move.getNewRow() + colorIndex);
        } else {
            enPassantTile = -1;
        }

        //promotions
        colorIndex = move.getPiece().isWhite() ? 0 : 7;
        if (move.getNewRow() == colorIndex) {
            promotePawn(move);
        }
    }

    private void promotePawn(Move move) {
        pieces.add(new Queen(this, move.getNewCol(), move.getNewRow(), move.getPiece().isWhite()));
        capture(move.getPiece());
    }

    public void capture(Piece piece) {
        pieces.remove(piece);
    }

    public boolean isValidMove(Move move) {
        if (sameTeam(move.getPiece(), move.getCapture())) {
            return false;
        }
        if (!move.getPiece().isValidMovement(move.getNewCol(), move.getNewRow())) {
            return false;
        }
        if (move.getPiece().moveCollidesWithPiece(move.getNewCol(), move.getNewRow())) {
            return false;
        }
        return checkScanner.isKingChecked(move);
    }

    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.isWhite() == p2.isWhite();
    }


    public int getTileNum(int col, int row) {
        return row * ROWS + col;
    }


    public Piece findKing(boolean isWhite) {
        for (Piece piece : pieces) {
            if (isWhite == piece.isWhite() && piece.getName().equals("King")) {
                return piece;
            }
        }
        return null;
    }

    public void addPieces() {
        pieces.add(new King(this, 4, 0, false));
        pieces.add(new Queen(this, 3, 0, false));
        pieces.add(new Knight(this, 1, 0, false));
        pieces.add(new Knight(this, 6, 0, false));
        pieces.add(new Rook(this, 7, 0, false));
        pieces.add(new Rook(this, 0, 0, false));
        pieces.add(new Bishop(this, 2, 0, false));
        pieces.add(new Bishop(this, 5, 0, false));

        pieces.add(new Pawn(this, 0, 1, false));
        pieces.add(new Pawn(this, 1, 1, false));
        pieces.add(new Pawn(this, 2, 1, false));
        pieces.add(new Pawn(this, 3, 1, false));
        pieces.add(new Pawn(this, 4, 1, false));
        pieces.add(new Pawn(this, 5, 1, false));
        pieces.add(new Pawn(this, 6, 1, false));
        pieces.add(new Pawn(this, 7, 1, false));

        pieces.add(new King(this, 4, 7, true));
        pieces.add(new Queen(this, 3, 7, true));
        pieces.add(new Knight(this, 1, 7, true));
        pieces.add(new Knight(this, 6, 7, true));
        pieces.add(new Rook(this, 7, 7, true));
        pieces.add(new Rook(this, 0, 7, true));
        pieces.add(new Bishop(this, 2, 7, true));
        pieces.add(new Bishop(this, 5, 7, true));

        pieces.add(new Pawn(this, 0, 6, true));
        pieces.add(new Pawn(this, 1, 6, true));
        pieces.add(new Pawn(this, 2, 6, true));
        pieces.add(new Pawn(this, 3, 6, true));
        pieces.add(new Pawn(this, 4, 6, true));
        pieces.add(new Pawn(this, 5, 6, true));
        pieces.add(new Pawn(this, 6, 6, true));
        pieces.add(new Pawn(this, 7, 6, true));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // paint the board
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(232, 209, 135) : new Color(89, 64, 36));
                g2d.fillRect(c * TITLE_SIZE, r * TITLE_SIZE, TITLE_SIZE, TITLE_SIZE);
            }
        //paint the highlights
        if (selectedPiece != null) {
            for (int r = 0; r < ROWS; r++)
                for (int c = 0; c < COLS; c++)
                    if (isValidMove(new Move(this, selectedPiece, c, r))) {
                        g2d.setColor(new Color(77, 225, 40, 147));
                        g2d.fillRect(c * TITLE_SIZE, r * TITLE_SIZE, TITLE_SIZE, TITLE_SIZE);
                    }
        }
        //paint the pieces
        for (Piece piece : pieces) {
            piece.paint(g2d);
        }
    }
}
