package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import pieces.King;

public class Board extends JPanel {
    public final int TITLE_SIZE = 85;

    final int COLS = 8;
    final int ROWS = 8;

    ArrayList<Piece> pieces = new ArrayList<>();

    Piece selectedPiece;
    Input input = new Input(this);

    CheckScanner checkScanner = new CheckScanner(this);

    private int enPassantTile = -1;

    public int getEnPassantTile() {
        return enPassantTile;
    }

    public Board() {
        this.setPreferredSize(new Dimension(COLS * TITLE_SIZE, ROWS * TITLE_SIZE));

        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addPieces();
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


        if (move.piece.getName().equals("Pawn")) {
            movePawn(move);
        } else {
            move.piece.setCol(move.getNewCol());
            move.piece.setRow(move.getNewRow());
            move.piece.setxPos(move.getNewCol() * TITLE_SIZE);
            move.piece.setyPos(move.getNewRow() * TITLE_SIZE);

            move.piece.isFirstMove = false;

            capture(move.capture);
        }
    }

    private void movePawn(Move move) {
        //en passant
        int colorIndex = move.piece.isWhite() ? 1 : -1;

        if (getTileNum(move.getNewCol(), move.getNewRow()) == enPassantTile) {
            move.capture = getPiece(move.getNewCol(), move.getNewRow() + colorIndex);
        }
        if (Math.abs(move.piece.getRow() - move.getNewRow()) == 2) {
            enPassantTile = getTileNum(move.getNewCol(), move.getNewRow() + colorIndex);
        } else {
            enPassantTile = -1;
        }

        //promotions
        colorIndex = move.piece.isWhite() ? 0 : 7;
        if (move.getNewRow() == colorIndex) {
            promotePawn(move);
        }

        move.piece.setCol(move.getNewCol());
        move.piece.setRow(move.getNewRow());
        move.piece.setxPos(move.getNewCol() * TITLE_SIZE);
        move.piece.setyPos(move.getNewRow() * TITLE_SIZE);

        move.piece.isFirstMove = false;

        capture(move.capture);
    }

    private void promotePawn(Move move) {
        pieces.add(new Queen(this, move.getNewCol(), move.getNewRow(), move.piece.isWhite()));
        capture(move.piece);
    }

    public void capture(Piece piece) {
        pieces.remove(piece);
    }

    public boolean isValidMove(Move move) {
        if (sameTeam(move.piece, move.capture)) {
            return false;
        }
        if (!move.piece.isValidMovement(move.getNewCol(), move.getNewRow())) {
            return false;
        }
        if (move.piece.moveCollidesWithPiece(move.getNewCol(), move.getNewRow())) {
            return false;
        }
        return !checkScanner.isKingChecked(move);
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


    Piece findKing(boolean isWhite) {
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
