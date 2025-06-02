public class Rook extends Piece {
    public boolean didMove = false;

    Rook(char color, int i, int j) {
        super(color, i, j, "rook");
    }

    public boolean isBlocked(int fi,int fj,Spaceoccupier[][] board){
        if(fi==this.i){
            if(fj>this.j) {
                for (int j = this.j + 1; j < fj; j++) {
                    if (!board[fi][j].getName().equals("null")) {
                        return true;
                    }
                }
            }
            if(fj<this.j){
                for(int j = this.j-1;j>fj;j--){
                    if (!board[fi][j].getName().equals("null")) {
                        return true;
                    }
                }
            }
        }
        if(fj==this.j){
            if(fi>this.i){
                for (int i = this.i + 1; i < fi; i++) {
                    if (!board[i][fj].getName().equals("null")) {
                        return true;
                    }
                }
            }
            if(fi<this.i){
                for(int i = this.i-1;j>fi;i--){
                    if (!board[i][fj].getName().equals("null")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean canGo(int i, int j) {
        if ((i == this.i || j == this.j) && (i != this.i || j != this.j)) {
            return true;
        }
        return false;
    }

    public boolean isValidMove(int i, int j,Spaceoccupier[][] board) {
        return canGo(i, j)&&!isBlocked(i,j,board);
    }

    @Override
    public void move(int fi, int fj, Spaceoccupier[][] board) {
        if (isValidMove(fi, fj,board)) {
            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            didMove = true;
            Board.moveNumber++;
        }
    }

    @Override
    public boolean canAttack(int targetI, int targetJ, Spaceoccupier[][] board) {
        return isValidMove(targetI, targetJ);
    }
}
