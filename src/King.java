public class King extends Piece {
    private boolean didMove=false;
    
    King(char color , int i , int j){
        super(color,i,j,"king");
    }
    public boolean canGo(int i, int j){
        if((((i-this.i>=-1)&&(i-this.i<=1))||((j-this.j>=-1)&&(j-this.j<=1)))&&((i-this.i!=0)||(j-this.j!=0))){
            return true;
        }
        return false;
    }
    public boolean isValidMove(int i, int j){

        return true;
    }
    /*public boolean isValidBigCastling(int i, int j,Rook rook,King king ){
        if()
    }*/

    public void move(int fi,int fj, Spaceoccupier[][] board){
        if(isValidMove(fi,fj)){
            board[fi][fj]=this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            didMove=true;
        }
    }
    /*public void bigCastling(int fi,int fj, Spaceoccupier[][] board){
        if(isValidBigCastling(fi,fj,)){

        }
    }*/
}
