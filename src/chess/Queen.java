package chess;
public class Queen extends Piece {

    Queen(char color, int i, int j) {
        super(color, i, j, "queen");
    }

    @Override
public String toString() {
    return (this.color == 'w') ? "♕" : "♛";
}

public boolean isBlocked(int fi, int fj, Spaceoccupier[][] board) {
    int dx = Integer.compare(fi, this.i);
    int dy = Integer.compare(fj, this.j);

    int x = this.i + dx;
    int y = this.j + dy;

    while (x != fi || y != fj) {
        if (!board[x][y].getName().equals("null")) return true;
        x += dx;
        y += dy;
    }

    return false;
}




@Override
public boolean canGo(int fi, int fj, Spaceoccupier[][] board) {
    if (fi == i && fj == j) return false;

    return (fi == i || fj == j || Math.abs(fi - i) == Math.abs(fj - j));
}

    
    public boolean isValidMove(int fi, int fj, Spaceoccupier[][] board) {
        return canGo(fi, fj, board)
            && !isBlocked(fi, fj, board)
            && !finalHouseIsBlocked(fi, fj, board)
            && isValidAboutCheck(fi, fj, board);
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