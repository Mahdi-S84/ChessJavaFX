public class Pawn extends Piece {
    public int PawnMoves = 0;

    //slm
    Pawn(char color, int i, int j) {
        super(color, i, j, "pawn");
    }

    public boolean canGo(int i, int j, Spaceoccupier[][] board) {
        if (this.color == 'w') {
            if ((this.PawnMoves == 0) && (this.j == j) && (i == 3) && (board[2][this.j].getName().equals("null")) && (board[3][this.j].getName().equals("null"))) {
                return true;
            } else if ((this.j == j) && (i - this.i == 1) && (board[this.i + 1][this.j].getName().equals("null"))) {
                return true;
            } else if ((j > 0) && (j < 7)) {
                if (((!board[this.i + 1][this.j + 1].getName().equals("null")) && (board[this.i + 1][this.j + 1].color == 'b')) || ((!board[this.i + 1][this.j - 1].getName().equals("null")) && (board[this.i + 1][this.j - 1].color == 'b'))) {
                    return true;
                }
            } else if (j == 0) {
                if ((!board[this.i + 1][this.j + 1].getName().equals("null")) && (board[this.i + 1][this.j + 1].color == 'b')) {

                    return true;
                }
            } else if (j == 7) {
                if ((!board[this.i + 1][this.j - 1].getName().equals("null")) && (board[this.i + 1][this.j - 1].color == 'b')) {
                    return true;
                }
            }
            return false;
        } else {
            if ((this.PawnMoves == 0) && (this.j == j) && (i == 4) && (board[4][this.j].getName().equals("null")) && (board[5][this.j].getName().equals("null"))) {
                return true;
            } else if ((this.j == j) && (i - this.i == -1) && (board[this.i - 1][this.j].getName().equals("null"))) {
                return true;
            } else if ((j > 0) && (j < 7)) {
                if (((!board[this.i - 1][this.j + 1].getName().equals("null")) && (board[this.i - 1][this.j + 1].color == 'w')) || ((!board[this.i - 1][this.j - 1].getName().equals("null")) && (board[this.i - 1][this.j - 1].color == 'w'))) {
                    return true;
                }
            } else if (j == 0) {
                if ((!board[this.i - 1][this.j + 1].getName().equals("null")) && (board[this.i - 1][this.j + 1].color == 'w')) {
                    return true;
                }
            } else if (j == 7) {
                if ((!board[this.i - 1][this.j - 1].getName().equals("null")) && (board[this.i - 1][this.j - 1].color == 'w')) {
                    return true;
                }
            }
            return false;
        }

    }

    public boolean isValidMove(int i, int j, Spaceoccupier[][] board) {
        if (canGo(i, j, board)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canAttack(int fi, int fj, Spaceoccupier[][] board) {
        if (color == 'w') {
            return fi == i + 1 && (fj == j + 1 || fj == j - 1);
        } else {
            return fi == i - 1 && (fj == j + 1 || fj == j - 1);
        }
    }

    public void move(int fi, int fj, Spaceoccupier[][] board) {
        if (isValidMove(fi, fj, board)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            PawnMoves++;
        }
    }
}

