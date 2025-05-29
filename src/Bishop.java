public class Bishop extends Piece {

    Bishop(char color , int i , int j){
        super(color,i,j,"bishop");

    }

    public boolean isValidMove(int i, int j){
        return true;
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
