public class Pawn extends Piece {
    public int PawnMoves = 0;
    public int perPawnMoves = 0;
    public boolean doubleMove = false;

    Pawn(char color, int i, int j) {
        super(color, i, j, "pawn");
    }

    @Override
    public boolean isCapture(int fi, int fj, Spaceoccupier[][] board) {
        if (this.color != board[fi][fj].color) {
            return true;
        }
        return false;
    }

    public void upgradePawn(int fi, int fj, Spaceoccupier[][] board, int iChoice) {
        if (this.color == 'b' && fi == 0) {
            board[fi][fj] = new Empty();
            switch (iChoice){
                case 0 -> {
                    board[fi][fj] = new Queen('b', fi, fj);
                }
                case 1 -> {
                    board[fi][fj] = new Rook('b', fi, fj);
                }
                case 2 -> {
                    board[fi][fj] = new Bishop('b', fi, fj);
                }
                case 3 -> {
                    board[fi][fj] = new Knight('b', fi, fj);
                }
            }
        }
        if (this.color == 'w' && fi == 7) {
            board[fi][fj] = new Empty();
            switch (iChoice){
                case 7 -> {
                    board[fi][fj] = new Queen('w', fi, fj);
                }
                case 6 -> {
                    board[fi][fj] = new Rook('w', fi, fj);
                }
                case 5 -> {
                    board[fi][fj] = new Bishop('w', fi, fj);
                }
                case 4 -> {
                    board[fi][fj] = new Knight('w', fi, fj);
                }
            }
        }
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

    public boolean isValidMove(int fi, int fj, Spaceoccupier[][] board,Board boards) {
        return (canGo(fi, fj, board)||canEnPassant(fi, fj,board,boards))&& isValidAboutCheck(i, j, board);
    }

    private boolean canEnPassant(int i, int j, Spaceoccupier[][] board,Board boards) {
        if (j < 0 || j > 7)
            return false;
        Spaceoccupier sp = board[i][j];
        if(this.color=='w'){
            if(board[4][j] instanceof Pawn){
            Pawn pawn =(Pawn) board[4][j];
                if (this.i == 4 && Math.abs(j - this.j) == 1 && i == 5 && pawn.color=='b' && pawn.doubleMove && boards.moveNumber==pawn.perPawnMoves) {

                    return true;
                }
            }
        }
        else{
            if(board[3][j] instanceof Pawn){
                Pawn pawn =(Pawn) board[3][j];
                    if (this.i == 3 && Math.abs(j - this.j) == 1 && i == 2 && pawn.color=='w' && pawn.doubleMove && boards.moveNumber==pawn.perPawnMoves) {

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





    public void move(int fi, int fj, Spaceoccupier[][] board, Board boards) {
        if (isValidMove(fi, fj, board,boards)) {
            if (this.color == 'w' && Math.abs(fj - this.j) == 1 && board[fi][fj].getName().equals("null")) {
                board[this.i][fj] = new Empty();
            }

            else if (this.color == 'b'&& Math.abs(fj - this.j) == 1&& board[fi][fj].getName().equals("null")) {
                board[this.i][fj] = new Empty();
            }

            if (Math.abs(fi - this.i) == 2) {
                this.doubleMove = true;
                perPawnMoves=boards.moveNumber;
            }
            else {
                this.doubleMove = false;
            }

            board[fi][fj] = this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            boards.move();
            if (isCapture(fi, fj, board)) {
                boards.resetFiftyMoves();
            } else {
                boards.plusFiftyMoves();
            }


        }


    }

}
