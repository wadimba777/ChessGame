package utils;

import logic.Board;
import logic.Move;
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
        int col = e.getX() / board.getTITLE_SIZE();
        int row = e.getY() / board.getTITLE_SIZE();

        Piece pieceXY = board.getPiece(col, row);
        if (pieceXY != null) {
            board.setSelectedPiece(pieceXY);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.getSelectedPiece() != null) {
            board.getSelectedPiece().setxPos(e.getX() - board.getTITLE_SIZE() / 2);
            board.getSelectedPiece().setyPos(e.getY() - board.getTITLE_SIZE() / 2);

            board.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.getTITLE_SIZE();
        int row = e.getY() / board.getTITLE_SIZE();

        if (board.getSelectedPiece() != null) {
            Move move = new Move(board, board.getSelectedPiece(), col, row);

            if (board.isValidMove(move)) {
                board.makeMove(move);
            } else {
                board.getSelectedPiece().setxPos(board.getSelectedPiece().getCol() * board.getTITLE_SIZE());
                board.getSelectedPiece().setyPos(board.getSelectedPiece().getRow() * board.getTITLE_SIZE());
            }
            board.setSelectedPiece(null);
            board.repaint();
        }
    }
}
