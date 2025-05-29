public class King extends Piece {
    
    King(char color , int i , int j){
        super(color,i,j,"king");
    }

    public boolean isCheck(Spaceoccupier[][] board) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Spaceoccupier piece = board[x][y];

                if (piece != null && !piece.getName().equals("empty") && piece.color != this.color) {
                    if (piece.isValidMove(this.i, this.j)) {
                        // for see can our piece kill our king?
                        return true;
                    }
                }
            }
        }
        return false;
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
