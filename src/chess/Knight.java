package chess;

public class Knight extends Piece {

    Knight(char color, int i, int j) {
        super(color, i, j, "knight");
    }

    @Override
    public boolean canGo(int fi, int fj, Spaceoccupier[][] board) {
        int dx = Math.abs(fi - this.i);
        int dy = Math.abs(fj - this.j);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    @Override
    public boolean isValidMove(int fi, int fj, Spaceoccupier[][] board) {
        
        if (board[fi][fj] instanceof King) {
            return false;
        }
    
        return canGo(fi, fj, board)
            && !finalHouseIsBlocked(fi, fj, board)
            && isValidAboutCheck(fi, fj, board);
    }
    

    
    @Override
    public void move(int fi, int fj, Spaceoccupier[][] board, Board boards) {
        if (isValidMove(fi, fj, board)) {
            if (!(board[fi][fj] instanceof King)) {
                board[fi][fj] = this;
                board[this.i][this.j] = new Empty();
                this.i = fi;
                this.j = fj;
                boards.move();
            } else {
                System.out.println("invalid move, you can't attack king!");
            }
        }
    }
    

    @Override
    public boolean canAttack(int targetI, int targetJ, Spaceoccupier[][] board) {
        return canGo(targetI, targetJ, board)
            && !finalHouseIsBlocked(targetI, targetJ, board);
    }

    @Override
    public String toString() {
        return (this.color == 'w') ? "♘" : "♞";
    }
}
