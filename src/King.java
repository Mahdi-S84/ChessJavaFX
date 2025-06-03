public class King extends Piece {
    private boolean didMove = false;

    King(char color, int i, int j) {
        super(color, i, j, "king");
    }

    @Override
    public boolean isCapture(int fi, int fj, Spaceoccupier[][] board) {
        if (this.color != board[fi][fj].color) {
            return true;
        }
        return false;
    }

    public boolean canGo(int i, int j) {
        if ((((i - this.i <= 1) && (i - this.i >= -1)) && ((j - this.j <= 1) && (j - this.j >= -1))) && ((i - this.i != 0) || (j - this.j != 0))) {
            return true;
        }
        return false;
    }

    static public boolean isCheck(char kingColor, Spaceoccupier[][] board) {
        int kingI = -1, kingJ = -1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spaceoccupier s = board[i][j];
                if (s instanceof King && ((King) s).getColor() == kingColor) {
                    kingI = i;
                    kingJ = j;
                    break;
                }
            }
        }

        if (kingI == -1) return false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spaceoccupier piece = board[i][j];
                if (piece instanceof Piece && ((Piece) piece).getColor() != kingColor) {
                    if (((Piece) piece).canAttack(kingI, kingJ, board)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isCheck(char kingColor, int si, int sj, Spaceoccupier[][] board) {
        int kingI = si, kingJ = sj;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spaceoccupier piece = board[i][j];
                if (piece instanceof Piece && ((Piece) piece).getColor() != kingColor) {
                    if (((Piece) piece).canAttack(kingI, kingJ, board)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isValidMove(int i, int j, Spaceoccupier[][] board) {
        return canGo(i , j ) && isValidAboutCheck(i,j,board) && !finalHouseIsBlocked(i,j,board);
    }

    public void move(int fi, int fj, Spaceoccupier[][] board,Board boards) {
        if (isValidMove(fi, fj,board)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            didMove=true;
            boards.move();
            if (isCapture(fi, fj, board)) {
                boards.resetFiftyMoves();
            } else {
                boards.plusFiftyMoves();
            }
        }
    }

    public boolean isValidBigBlackCastling(int i, int j, Rook rook, King king, Spaceoccupier[][] board) {
        if (!king.didMove && !rook.didMove && i == 7 && j == 5 && board[7][4].getName() == "null" && board[7][5].getName() == "null" && board[7][6].getName() == "null" && !isCheck('b', 7, 5, board) && !isCheck('b', 7, 4, board) && !isCheck('b', 7, 3, board)) {
            return true;
        }
        return false;
    }

    public boolean isValidBigWhiteCastling(int i, int j, Rook rook, King king, Spaceoccupier[][] board) {
        if (!king.didMove && !rook.didMove && i == 0 && j == 5 && board[0][4].getName() == "null" && board[0][5].getName() == "null" && board[0][6].getName() == "null" && !isCheck('w', 0, 5, board) && !isCheck('w', 0, 4, board) && !isCheck('w', 0, 3, board)) {
            return true;
        }
        return false;
    }

    public boolean isValidShortBlackCastling(int i, int j, Rook rook, King king, Spaceoccupier[][] board) {
        if (!king.didMove && !rook.didMove && i == 7 && j == 1 && board[7][1].getName() == "null" && board[7][2].getName() == "null" && !isCheck('b', 7, 2, board) && !isCheck('b', 7, 3, board)) {
            return true;
        }
        return false;
    }

    public boolean isValidShortWhiteCastling(int i, int j, Rook rook, King king, Spaceoccupier[][] board) {
        //System.out.println(king.didMove);
        //System.out.println(rook.didMove);
        if (!king.didMove && !rook.didMove && i == 0 && j == 1 && board[0][1].getName().equals("null") && board[0][2].getName().equals("null") && !isCheck('w', 0, 3, board) && !isCheck('w', 0, 2, board) && !isCheck('w', 0, 1, board)) {
            return true;

        }
        return false;
    }

    public void bigBlackCastling(int fi, int fj, Spaceoccupier[][] board,Board boards, Rook rook) {
        if (isValidBigBlackCastling(fi, fj, rook, this, board)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            board[7][4]=rook;
            board[7][7]=new Empty();
            rook.j=4;
            didMove=true;
            rook.didMove=true;
            boards.move();
            boards.plusFiftyMoves();
        }
    }

    public void bigWhiteCastling(int fi, int fj, Spaceoccupier[][] board,Board boards, Rook rook) {
        if (isValidBigWhiteCastling(fi, fj, rook, this, board)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            board[0][4]=rook;
            board[0][7]=new Empty();
            rook.j=4;
            didMove=true;
            rook.didMove=true;
            boards.move();
            boards.plusFiftyMoves();
        }
    }

    public void shortBlackCastling(int fi, int fj, Spaceoccupier[][] board,Board boards, Rook rook) {
        if (isValidShortBlackCastling(fi, fj, rook, this, board)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            board[7][2]=rook;
            board[7][0]=new Empty();
            rook.j=2;
            didMove=true;
            rook.didMove=true;
            boards.move();
            boards.plusFiftyMoves();
        }
    }

    public void shortWhiteCastling(int fi, int fj, Spaceoccupier[][] board,Board boards, Rook rook) {
        if (isValidShortWhiteCastling(fi, fj, rook, this, board)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            board[0][2]=rook;
            board[0][0]=new Empty();
            rook.j=2;
            didMove=true;
            rook.didMove=true;
            boards.move();
            boards.plusFiftyMoves();
        }
    }

    @Override
    public boolean canAttack(int targetI, int targetJ, Spaceoccupier[][] board) {
        return isValidMove(targetI, targetJ);
    }

}
