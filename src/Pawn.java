public class Pawn extends Piece {

    Pawn(char color , int i , int j){
        super(color,i,j,"pawn");
    }

    @Override
    public boolean isValidMove(int fi, int fj){
        if (color == 'w') {
            return (fi == i + 1 && fj == j);
        } else {
            return (fi == i - 1 && fj == j);
        }
    }

    @Override
    public boolean canAttack(int fi, int fj, Spaceoccupier[][] board) {
        if (color == 'w') {
            return fi == i + 1 && (fj == j + 1 || fj == j - 1);
        } else {
            return fi == i - 1 && (fj == j + 1 || fj == j - 1);
        }
    }

    public void move(int fi,int fj, Spaceoccupier[][] board){
        if(isValidMove(fi,fj)){
            board[fi][fj]=this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
        }
    }
}
