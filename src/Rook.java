public class Rook extends Piece {
    public boolean didMove = false;

    Rook(char color, int i, int j) {
        super(color, i, j, "rook");
    }

    public boolean canGo(int i, int j) {
        // Rook moves in straight lines (row or column), not both
        if ((i == this.i || j == this.j) && (i != this.i || j != this.j)) {
            return true;
        }
        return false;
    }

    public boolean isValidMove(int i, int j) {
        return canGo(i, j);
    }

    @Override
    public void move(int fi, int fj, Spaceoccupier[][] board) {
        if (isValidMove(fi, fj)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            didMove = true;
        }
    }

    @Override
    public boolean canAttack(int targetI, int targetJ, Spaceoccupier[][] board) {
        return isValidMove(targetI, targetJ);
    }
}
