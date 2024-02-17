package logic;

import pieces.Piece;

public class CheckScanner {
    private final Board board;

    public CheckScanner(Board board) {
        this.board = board;
    }

    public boolean isKingChecked(Move move) {
        Piece king = board.findKing(move.getPiece().isWhite());
        assert king != null;

        int kingCol = king.getCol();
        int kingRow = king.getRow();

        if (board.getSelectedPiece() != null && board.getSelectedPiece().getName().equals("King")) {
            kingCol = move.getNewCol();
            kingRow = move.getNewRow();
        }

        return !hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, 1) &&     //up
                !hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, 0) &&    //right
                !hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, -1) &&   //down
                !hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, 0) &&   //left

                !hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, -1) &&  //up left
                !hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, -1) &&   //up right
                !hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, 1) &&    //down right
                !hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, 1) &&   //down left

                !hitByKnight(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow) &&
                !hitByPawn(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow) &&
                !hitByKing(king, kingCol, kingRow);

    }

    private boolean hitByRook(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row) {
                break;
            }

            Piece piece = board.getPiece(kingCol + (i * colVal), kingRow + (i * rowVal));
            if (piece != null && piece != board.getSelectedPiece()) {
                if (!board.sameTeam(piece, king) && (piece.getName().equals("Rook") || piece.getName().equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row) {
                break;
            }
            Piece piece = board.getPiece(kingCol - (i * colVal), kingRow - (i * rowVal));
            if (piece != null && piece != board.getSelectedPiece()) {
                if (!board.sameTeam(piece, king) && (piece.getName().equals("Bishop") || piece.getName().equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow) {
        return checkKnight(board.getPiece(kingCol - 1, kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1, kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2, kingRow - 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2, kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1, kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 1, kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2, kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2, kingRow - 1), king, col, row);
    }

    private boolean checkKnight(Piece p, Piece k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && p.getName().equals("Knight") && !(p.getCol() == col && p.getRow() == row);
    }

    private boolean hitByKing(Piece king, int kingCol, int kingRow) {
        return checkKing(board.getPiece(kingCol - 1, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol - 1, kingRow), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow), king) ||
                checkKing(board.getPiece(kingCol - 1, kingRow + 1), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow + 1), king) ||
                checkKing(board.getPiece(kingCol, kingRow + 1), king);
    }

    private boolean checkKing(Piece p, Piece k) {
        return p != null && !board.sameTeam(p, k) && p.getName().equals("King");
    }

    private boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow) {
        int colorVal = king.isWhite() ? -1 : 1;
        return checkPawn(board.getPiece(kingCol + 1, kingRow + colorVal), king, col, row) ||
                checkPawn(board.getPiece(kingCol - 1, kingRow + colorVal), king, col, row);
    }

    private boolean checkPawn(Piece p, Piece k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && p.getName().equals("Pawn") && !(p.getCol() == col && p.getRow() == row);
    }
}
