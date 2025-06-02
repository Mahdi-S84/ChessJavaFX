public class Bishop extends Piece {

    Bishop(char color, int i, int j) {
        super(color, i, j, "bishop");
    }

    public boolean canGo(int i, int j, Spaceoccupier[][] board) {
        if ((Math.abs(this.i - i) == Math.abs(this.j - j)) && (i - this.i != 0)) {
            return true;
        }
        return false;
    }

    public boolean isBlocked(int fi, int fj, Spaceoccupier[][] board) {
        if (fi > this.i && fj > this.j) {
            int j = fj--;
            for (int i = fi--; i > this.i; i--, j--) {
                Spaceoccupier piece = board[i][j];
                if (!piece.getName().equals("null")) {
                    return true;
                }
            }
        } else if (fi > this.i && fj < this.j) {
            int j = fj++;
            for (int i = fi--; i > this.i; i--, j++) {
                Spaceoccupier piece = board[i][j];
                if (!piece.getName().equals("null")) {
                    return true;
                }
            }
        } else if (fi < this.i && fj > this.j) {
            int j = fj--;
            for (int i = fi++; i < this.i; i++, j--) {
                Spaceoccupier piece = board[i][j];
                if (!piece.getName().equals("null")) {
                    return true;
                }
            }
        } else if (fi < this.i && fj < this.j) {
            int j = fj++;
            for (int i = fi++; i < this.i; i++, j++) {
                Spaceoccupier piece = board[i][j];
                if (!piece.getName().equals("null")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValidMove(int i, int j, Spaceoccupier[][] board) {
        return canGo(i, j, board) && !isBlocked(i, j, board)&& isValidAboutCheck(i, j, board) && !finalHouseIsBlocked(i,j,board);
    }

    public void move(int fi, int fj, Spaceoccupier[][] board,Board boards) {
        if (isValidMove(fi, fj, board)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            boards.move();
        }
    }

    @Override
    public boolean canAttack(int targetI, int targetJ, Spaceoccupier[][] board) {
        return isValidMove(targetI, targetJ);
    }
}
