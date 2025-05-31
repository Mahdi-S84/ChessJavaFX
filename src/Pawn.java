public class Pawn extends Piece {
    public int PawnMoves = 0;

    Pawn(char color, int i, int j) {
        super(color, i, j, "pawn");
    }

    public boolean canGo(int i, int j, Spaceoccupier[][] board){

        char dir = this.color == 'W' ? 'D' : 'U';

        if(dir == 'D'){
            if( this.PawnMoves == 0 && this.j == j && i - this.i == 2 && board[this.i + 1][this.j].getName().equals("null")
            && board[this.i + 2][this.j].getName().equals("null")){
                this.PawnMoves++;
                return true;
            }
            else if(this.j == j && i - this.i == 1 && board[this.j][this.i +1].getName().equals("null")){
                this.PawnMoves++;
                return true;
            }
            else if(board[this.j + 1][this.i + 1].getName() != "null" && board[this.j + 1][this.i + 1].color != board[this.j][this.i].color ||
                    board[this.j - 1][this.i + 1].getName() != "null" && board[this.j - 1][this.i + 1].color != board[this.j][this.i].color){
                        this.PawnMoves++;
                        return true;
                    }
            return false;
        }

        else {
            if( this.PawnMoves == 0 && this.j == j && i - this.i == -2 && board[this.j][this.i -1].getName().equals("null")
            && board[this.j][this.i +2].getName().equals("null")){
                this.PawnMoves++;
                return true;
            }
            else if(this.j == j & i - this.i == -1 && board[this.j][this.i -1].getName().equals("null")){
                this.PawnMoves++;
                return true;
            }
            else if(board[this.j + 1][this.i + 1].getName() != "null" && board[this.j + 1][this.i - 1].color != board[this.j][this.i].color ||
                    board[this.j - 1][this.i + 1].getName() != "null" && board[this.j - 1][this.i - 1].color != board[this.j][this.i].color){
                        this.PawnMoves++;
                        return true;
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

    public void move(int fi, int fj, Spaceoccupier[][] board) {
        if (isValidMove(fi, fj , board)) {
            board[this.i][this.j] = new Empty();
            board[fi][fj] = this;
            this.i = fi;
            this.j = fj;
        }
    }
}
