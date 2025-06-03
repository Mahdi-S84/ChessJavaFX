public class Knight extends Piece {

    Knight(char color, int i, int j) {
        super(color, i, j, "knight");
    }

    public boolean canGo(int fi, int fj, Spaceoccupier[][] board) {
        Spaceoccupier piece = board[fi][fj];
        if (piece.color != this.color && (((fi == i - 2) || (fi == i + 2)) && ((fj == j - 1) || (fj == j + 1))) ||
                (((fi == i - 1) || (fi == i + 1)) && ((fj == j - 2) || (fj == j + 2)))) {
            return true;
        }
        return false;
    }

    public boolean isValidMove(int fi, int fj, Spaceoccupier[][] board) {
        return canGo(fi, fj, board) && !isValidAboutCheck(fi, fj, board) && !finalHouseIsBlocked(fi, fj, board);
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
        return isValidMove(targetI, targetJ, board);
    }

}
