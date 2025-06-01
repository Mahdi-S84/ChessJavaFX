public class Pawn extends Piece {
    public int PawnMoves = 0;

    Pawn(char color , int i , int j){
        super(color,i,j,"pawn");
    }
    public boolean canGo(int i, int j, Spaceoccupier[][] board){
        if(this.color == 'w'){
            if((this.PawnMoves == 0) && (this.j == j) && (i==3) && (board[2][this.j].getName()=="null")
                    && (board[3][this.j].getName()=="null")){
                return true;
            }
            else if((this.j == j) &&(i - this.i == 1) && (board[this.i+1][this.j].getName()=="null")){
                return true;
            }
            else if((j>0)&&(j<7)) {
                if (((board[this.i + 1][this.j + 1].getName() != "null") && (board[this.i + 1][this.j + 1].color != board[this.j][this.i].color)) ||
                        ((board[this.i + 1][this.j - 1].getName() != "null") && (board[this.i + 1][this.j - 1].color != board[this.j][this.i].color))) {
                    return true;
                }
            }
            else if(j==0){
                if((board[this.i + 1][this.j + 1].getName() != "null") && (board[this.i + 1][this.j + 1].color != board[this.j][this.i].color)){
                    return true;
                }
            }
            else if(j==7){
                if((board[this.i + 1][this.j - 1].getName() != "null") && (board[this.i + 1][this.j - 1].color != board[this.j][this.i].color)){
                    return true;
                }
            }
            return false;
        }

        else {
            System.out.println("slm"+this.PawnMoves);
            if((this.PawnMoves == 0) && (this.j == j) && (i==4) && (board[4][this.j].getName().equals("null"))
                    && (board[5][this.j].getName().equals("null"))){
                return true;
            }
            else if((this.j == j) && (i - this.i == -1) && (board[this.i-1][this.j].getName()=="null")){
                return true;
            }
            else if((j>0)&&(j<7)) {
                System.out.println("slm"+this.PawnMoves);
                System.out.println(this.i-1);
                System.out.println(this.j-1);
                System.out.println(board[this.i-1][this.j-1].getName());
                System.out.println(board[this.i-1][this.j-1].color);
                if (((!board[this.i - 1][this.j + 1].getName().equals("null")) && (board[this.i - 1][this.j + 1].color != board[this.i][this.j].color)) ||
                        ((!board[this.i - 1][this.j - 1].getName().equals("null")) && (board[this.i - 1][this.j - 1].color =='w'))) {
                    System.out.println("slm"+this.PawnMoves);
                    return true;
                }
            }
            else if(j==0){
                if((board[this.i - 1][this.j + 1].getName() != "null") && (board[this.i - 1][this.j + 1].color != board[this.i][this.j].color)){
                    return true;
                }
            }
            else if(j==7){
                if((board[this.i - 1][this.j - 1].getName() != "null") && (board[this.i - 1][this.j - 1].color != board[this.i][this.j].color)){
                    return true;
                }
            }
            return false;
        }

    }
    public boolean isValidMove(int i, int j , Spaceoccupier[][] board) {
        if(canGo(i, j, board)){
            return true;
        }
        return false;
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
        if(isValidMove(fi,fj,board)){
            board[fi][fj]=this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            PawnMoves++;
        }
    }
}

