public class Pawn extends Piece {
    public int PawnMoves = 0;
    boolean CrookenMove = false;

    Pawn(char color, int i, int j) {
        super(color, i, j, "pawn");
    }

    public boolean canGo(int i, int j, Spaceoccupier[][] board) {
        int dir = (this.color == 'w') ? -1 : 1;

        // حرکت یک خانه به جلو
        if (this.i == i && j - this.j == dir && board[i][j] instanceof Empty) {
            CrookenMove = false;
            PawnMoves++;
            return true;
        }

        // حرکت دو خانه به جلو از خانه اولیه
        if (PawnMoves == 0 && this.i == i && j - this.j == 2 * dir &&
                board[i][this.j + dir] instanceof Empty &&
                board[i][this.j + 2 * dir] instanceof Empty) {
            CrookenMove = false;
            PawnMoves++;
            return true;
        }

        // زدن مهره حریف به صورت ضربدری
        if (Math.abs(i - this.i) == 1 && j - this.j == dir &&
                board[i][j] instanceof Piece &&
                board[i][j].color != this.color) {
            CrookenMove = true;
            PawnMoves++;
            return true;
        }

        return false;
    }

    public boolean isValidMove(int i, int j) {
        return true;
    }

    public void move(int fi, int fj, Spaceoccupier[][] board) {
        if (isValidMove(fi, fj)) {
            board[this.i][this.j] = new Empty();
            board[fi][fj] = this;
            this.i = fi;
            this.j = fj;
        }
    }
}
