public class Knight extends Piece {
    
    Knight(char color , int i , int j){
        super(color,i,j,"knight");
    }

    public boolean canGo(int fi, int fj, Spaceoccupier[][] board) {
        Spaceoccupier piece = board[fi][fj];
        if (piece.color != this.color) {
            return true;
        }
        return false;
    }
    public boolean isValidMove(int fi, int fj, Spaceoccupier[][] board){
        boolean state = false;
        if ((((fi == i - 2) || (fi == i + 2)) && ((fj == j - 1) || (fj == j + 1))) ||
            (((fi == i - 1) || (fi == i + 1)) && ((fj == j - 2) || (fj == j + 2)))) {

            state = canGo(fi, fj, board);
        }
        return state;
    }

    public void move(int fi,int fj, Spaceoccupier[][] board){
        if(isValidMove(fi, fj, board)){
            board[fi][fj]=this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
        }
    }
}
