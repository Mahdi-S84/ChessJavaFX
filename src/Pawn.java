public class Pawn extends Piece {
    public int PawnMoves = 0;
    public int perPawnMoves = 0;
    public boolean doubleMove = false;

    Pawn(char color, int i, int j) {
        super(color, i, j, "pawn");
    }

    public boolean canGo(int fi, int fj, Spaceoccupier[][] board) {
        if (this.color == 'w') {
            if (this.PawnMoves == 0 && this.j == fj && fi == this.i + 2 &&
                    board[this.i + 1][this.j].getName().equals("null") &&
                    board[this.i + 2][this.j].getName().equals("null")) {
                return true;
            }
            if (this.j == fj && fi == this.i + 1 &&
                    board[this.i + 1][this.j].getName().equals("null")) {
                return true;
            }
            if (fi == this.i + 1 && Math.abs(fj - this.j) == 1 &&
                    !board[fi][fj].getName().equals("null") &&
                    board[fi][fj].color == 'b') {
                return true;
            }
        } 
        else {
            if (this.PawnMoves == 0 && this.j == fj && fi == this.i - 2 &&
                    board[this.i - 1][this.j].getName().equals("null") &&
                    board[this.i - 2][this.j].getName().equals("null")) {
                return true;
            }
            if (this.j == fj && fi == this.i - 1 &&
                    board[this.i - 1][this.j].getName().equals("null")) {
                return true;
            }
            if (fi == this.i - 1 && Math.abs(fj - this.j) == 1 &&
                    !board[fi][fj].getName().equals("null") &&
                    board[fi][fj].color == 'w') {
                return true;
            }
        }
        return false;
    }

    public boolean isValidMove(int fi, int fj, Spaceoccupier[][] board) {
        return canGo(fi, fj, board)||canEnPassant(fi, fj, board);
    }

    private boolean canEnPassant(int i, int j, Spaceoccupier[][] board) {
        if (j < 0 || j > 7)
            return false;
        Spaceoccupier sp = board[i][j];
        if(this.color=='w'){
            if(board[4][j] instanceof Pawn){
            Pawn pawn =(Pawn) board[4][j];
                if (this.i == 4 && Math.abs(j - this.j) == 1 && i == 5 && pawn.color=='b' && pawn.doubleMove && Board.moveNumber==pawn.perPawnMoves) {
                    
                    return true;
                }
            }
        }
        else{
            if(board[3][j] instanceof Pawn){
                Pawn pawn =(Pawn) board[3][j];
                    if (this.i == 3 && Math.abs(j - this.j) == 1 && i == 2 && pawn.color=='w' && pawn.doubleMove && Board.moveNumber==pawn.perPawnMoves) {
                        
                        return true;
                    }
                }

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

    
    


    public void move(int fi, int fj, Spaceoccupier[][] board) {
        if (isValidMove(fi, fj, board)) {
            if (this.color == 'w' && Math.abs(fj - this.j) == 1 && board[fi][fj].getName().equals("null")) {
                board[this.i][fj] = new Empty();
            }

            else if (this.color == 'b'&& Math.abs(fj - this.j) == 1&& board[fi][fj].getName().equals("null")) {
                board[this.i][fj] = new Empty();
            }

            if (Math.abs(fi - this.i) == 2) {
                this.doubleMove = true;
                perPawnMoves=Board.moveNumber;
            } 
            else {
                this.doubleMove = false;
            }

            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            Board.moveNumber++;
            

        }


    }

}
