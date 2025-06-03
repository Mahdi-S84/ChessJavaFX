public abstract class Piece extends Spaceoccupier {
    protected int i;
    protected int j;
    protected String name;
    protected boolean isValidAboutCheck(int i, int j , Spaceoccupier[][] board) {
        Spaceoccupier original = board[i][j];
        Spaceoccupier original1 = board[this.i][this.j];
        board[i][j] = board[this.i][this.j];
        board[this.i][this.j] = new Empty();
        boolean result = !King.isCheck(this.color, board);
        board[i][j] = original;
        board[this.i][this.j] = original1;
        return result;
    }
    protected boolean finalHouseIsBlocked(int i, int j, Spaceoccupier[][] board) {
        if(board[i][j].color == this.color) {
            return true;
        }
        return false;
    }
    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public char getColor() {
        return color;
    }

    Piece(char color, int i, int j, String name) {
        this.color = color;
        this.i = i;
        this.j = j;
        this.name = name;
    }

    @Override
    public String getName() {
        return color + name;
    }

    public abstract boolean isCapture(int fi, int fj, Spaceoccupier[][] board);

    public abstract void move(int fi, int fj, Spaceoccupier[][] board,Board boards);

    public abstract boolean canAttack(int targetI, int targetJ, Spaceoccupier[][] board);
}

