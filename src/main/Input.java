package main;

import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Input extends MouseAdapter {
    Board board;

    public Input(Board board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.TITLE_SIZE;
        int row = e.getY() / board.TITLE_SIZE;

        Piece pieceXY = board.getPiece(col, row);
        if (pieceXY != null) {
            board.selectedPiece = pieceXY;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.selectedPiece != null) {
            board.selectedPiece.setxPos(e.getX() - board.TITLE_SIZE / 2);
            board.selectedPiece.setyPos(e.getY() - board.TITLE_SIZE / 2);

            board.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.TITLE_SIZE;
        int row = e.getY() / board.TITLE_SIZE;

        if (board.selectedPiece != null) {
            Move move = new Move(board, board.selectedPiece, col, row);

            if (board.isValidMove(move)) {
                board.makeMove(move);
            } else {
                board.selectedPiece.setxPos(board.selectedPiece.getCol() * board.TITLE_SIZE);
                board.selectedPiece.setyPos(board.selectedPiece.getRow() * board.TITLE_SIZE);
            }
            board.selectedPiece = null;
            board.repaint();
        }
    }
}
