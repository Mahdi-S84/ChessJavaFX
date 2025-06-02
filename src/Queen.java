public class Queen extends Piece {

    Queen(char color, int i, int j) {
        super(color, i, j, "queen");
    }

    public boolean isBlocked(int fi, int fj, Spaceoccupier[][] board) {
        if (fi > this.i && fj > this.j) {
            int j = fj;
            for (int i = fi; i > this.i; i--, j--) {
                Spaceoccupier piece = board[i][j];
                if (!piece.getName().equals("null")) {
                    return true;
                }
            }
        } else if (fi > this.i && fj < this.j) {
            int j = fj;
            for (int i = fi; i > this.i; i--, j++) {
                Spaceoccupier piece = board[i][j];
                if (!piece.getName().equals("null")) {
                    return true;
                }
            }
        } else if (fi < this.i && fj > this.j) {
            int j = fj;
            for (int i = fi; i < this.i; i++, j--) {
                Spaceoccupier piece = board[i][j];
                if (!piece.getName().equals("null")) {
                    return true;
                }
            }
        } else if (fi < this.i && fj < this.j) {
            int j = fj;
            for (int i = fi; i < this.i; i++, j++) {
                Spaceoccupier piece = board[i][j];
                if (!piece.getName().equals("null")) {
                    return true;
                }
            }
        } else if (fi == this.i) {
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
                for (int i = this.i - 1; j > fi; i--) {
                    if (!board[i][fj].getName().equals("null")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean canGo(int fi, int fj, Spaceoccupier[][] board) {
        if (fi == i || fj == j || Math.abs(fi - i) == Math.abs(fj - j)) { return true; }
        return false;
    }

    public boolean isValidMove(int fi, int fj, Spaceoccupier[][] board) {
        if (canGo(fi, fj, board)) {
            return true;
        }
        return false;
    }

    public void move(int fi, int fj, Spaceoccupier[][] board) {
        if (isValidMove(fi, fj, board)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            Board.moveNumber++;
        }
    }

    @Override
    public boolean canAttack(int targetI, int targetJ, Spaceoccupier[][] board) {
        return isValidMove(targetI, targetJ, board);
    }

}
