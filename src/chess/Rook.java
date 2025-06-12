package chess;
public class Rook extends Piece {
    public boolean didMove = false;

    Rook(char color, int i, int j) {
        super(color, i, j, "rook");
    }

    @Override
public String toString() {
    return (this.color == 'w') ? "♖" : "♜";
}

    public boolean isBlocked(int fi, int fj, Spaceoccupier[][] board) {
        if (fi == this.i) {
            if (fj > this.j) {
                for (int j = this.j + 1; j < fj; j++) {
                    if (!board[fi][j].getName().equals("null")) {
                        return true;
                    }
                }
            }
            if (fj < this.j) {
                for (int j = this.j - 1; j > fj; j--) {
                    if (!board[fi][j].getName().equals("null")) {
                        return true;
                    }
                }
            }
        }
        if (fj == this.j) {
            if (fi > this.i) {
                for (int i = this.i + 1; i < fi; i++) {
                    if (!board[i][fj].getName().equals("null")) {
                        return true;
                    }
                }
            }
            if (fi < this.i) {
                for (int i = this.i - 1; i > fi; i--) {
                    if (!board[i][fj].getName().equals("null")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean canGo(int fi, int fj, Spaceoccupier[][] board) {
        if ((fi == this.i || fj == this.j) && (fi != this.i || fj != this.j)) {
            return !isBlocked(fi, fj, board) && !finalHouseIsBlocked(fi, fj, board);
        }
        return false;
    }
    public boolean isValidMove(int i, int j, Spaceoccupier[][] board) {
        return canGo(i, j, board) && !isBlocked(i, j, board) && isValidAboutCheck(i, j, board) && !finalHouseIsBlocked(i, j, board);

    }

    @Override
    public void move(int fi, int fj, Spaceoccupier[][] board,Board boards) {
        if (isValidMove(fi, fj, board)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            didMove = true;
            boards.move();
        }
    }

    @Override
    public boolean canAttack(int targetI, int targetJ, Spaceoccupier[][] board) {
        return canGo(targetI, targetJ, board)
                && !isBlocked(targetI, targetJ, board)
                && !finalHouseIsBlocked(targetI, targetJ, board);
    }
}