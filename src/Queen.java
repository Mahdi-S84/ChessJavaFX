public class Queen extends Piece {

    Queen(char color, int i, int j) {
        super(color, i, j, "queen");
    }

    public boolean isValidMove(int i, int j) {
        return true;
    }

    public void move(int fi, int fj, Spaceoccupier[][] board) {
        if (isValidMove(fi, fj)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
        }
    }

    @Override
    public boolean canAttack(int targetI, int targetJ, Spaceoccupier[][] board) {
        return isValidMove(targetI, targetJ);
    }

}
