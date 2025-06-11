package chess;

public class Bishop extends Piece {

    Bishop(char color, int i, int j) {
        super(color, i, j, "bishop");
    }

    @Override
    public String toString() {
        return (this.color == 'w') ? "♗" : "♝";
    }

    @Override
    public boolean canGo(int fi, int fj, Spaceoccupier[][] board) {
        int dx = fi - this.i;
        int dy = fj - this.j;

        if (Math.abs(dx) != Math.abs(dy)) return false;

        int stepX = dx > 0 ? 1 : -1;
        int stepY = dy > 0 ? 1 : -1;

        int x = this.i + stepX;
        int y = this.j + stepY;

        while (x != fi && y != fj) {
            if (!(board[x][y] instanceof Empty)) return false;
            x += stepX;
            y += stepY;
        }

        return true;
    }

    @Override
    public boolean isValidMove(int fi, int fj, Spaceoccupier[][] board) {
        return canGo(fi, fj, board)
            && !finalHouseIsBlocked(fi, fj, board)
            && isValidAboutCheck(fi, fj, board);
    }

    @Override
    public void move(int fi, int fj, Spaceoccupier[][] board, Board boards) {
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
        return canGo(targetI, targetJ, board)
            && !finalHouseIsBlocked(targetI, targetJ, board);
    }
}
