public class Rook extends Piece {
    public boolean didMove = false;
    Rook(char color , int i , int j){
        super(color,i,j,"rook");
    }
    public boolean canGo(int i, int j){
        if(i==this.i ^ j==this.j){
            System.out.println("Rook can go");
            return true;
        }
        return false;
    }
    public boolean isValidMove(int i, int j){
        if(canGo(i,j)){
            System.out.println("slm");
            return true;
        }
        return false;
    }
    @Override
    public void move(int fi,int fj, Spaceoccupier[][] board){
        if(isValidMove(fi,fj)){
            board[fi][fj]=this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            didMove = true;
            Board.moveNumber++;
        }
    }
}
