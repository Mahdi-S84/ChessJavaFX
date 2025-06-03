public class  Board {
    private int fiftyMoves = 0;
    static int rows=8;
    static int columns=8;
    public int moveNumber=0;
    private Spaceoccupier[][][] save= new Spaceoccupier[10][rows][columns];

    public void plusFiftyMoves() {
        fiftyMoves++;
    }

    public void resetFiftyMoves() {
        fiftyMoves = 0;
    }

    public int getFiftyMoves() {
        return fiftyMoves;
    }

    public void initialSave() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < columns; k++) {
                    save[i][j][k] = board[j][k];
                }
            }
        }
    }
    public void move(){
        moveNumber++;
        save(board);
    }
    Rook WR1 = new Rook('w',0,0);
    Rook WR2 = new Rook('w',0,7);
    Knight WN1 = new Knight('w', 0, 1);
    Knight WN2 = new Knight('w', 0, 6);
    Bishop WB1 = new Bishop('w', 0, 2);
    Bishop WB2 = new Bishop('w', 0, 5);
    Queen WQ = new Queen('w', 0, 4);
    King WK = new King('w', 0, 3);
    Pawn WP1 = new Pawn('w', 1, 0);
    Pawn WP2 = new Pawn('w', 1, 1);
    Pawn WP3 = new Pawn('w', 1, 2);
    Pawn WP4 = new Pawn('w', 1, 3);
    Pawn WP5 = new Pawn('w', 1, 4);
    Pawn WP6 = new Pawn('w', 1, 5);
    Pawn WP7 = new Pawn('w', 1, 6);
    Pawn WP8 = new Pawn('w', 1, 7);
    Rook BR1 = new Rook('b', 7, 0);
    Rook BR2 = new Rook('b', 7, 7);
    Knight BN1 = new Knight('b', 7, 1);
    Knight BN2 = new Knight('b', 7, 6);
    Bishop BB1 = new Bishop('b', 7, 2);
    Bishop BB2 = new Bishop('b', 7, 5);
    King BK = new King('b', 7, 3);
    Queen BQ = new Queen('b', 7, 4);
    Pawn BP1 = new Pawn('b', 6, 0);
    Pawn BP2 = new Pawn('b', 6, 1);
    Pawn BP3 = new Pawn('b', 6, 2);
    Pawn BP4 = new Pawn('b', 6, 3);
    Pawn BP5 = new Pawn('b', 6, 4);
    Pawn BP6 = new Pawn('b', 6, 5);
    Pawn BP7 = new Pawn('b', 6, 6);
    Pawn BP8 = new Pawn('b', 6, 7);
    public Spaceoccupier[][] board = new Spaceoccupier[rows][columns];

    Board() {
        board[0][0] = WR1;
        board[0][7] = WR2;
        board[0][1] = WN1;
        board[0][6] = WN2;
        board[0][2] = WB1;
        board[0][5] = WB2;
        board[0][3] = WK;
        board[0][4] = WQ;
        board[1][0] = WP1;
        board[1][1] = WP2;
        board[1][2] = WP3;
        board[1][3] = WP4;
        board[1][4] = WP5;
        board[1][5] = WP6;
        board[1][6] = WP7;
        board[1][7] = WP8;
        board[7][0] = BR1;
        board[7][7] = BR2;
        board[7][1] = BN1;
        board[7][6] = BN2;
        board[7][2] = BB1;
        board[7][5] = BB2;
        board[7][3] = BK;
        board[7][4] = BQ;
        board[6][0] = BP1;
        board[6][1] = BP2;
        board[6][2] = BP3;
        board[6][3] = BP4;
        board[6][4] = BP5;
        board[6][5] = BP6;
        board[6][6] = BP7;
        board[6][7] = BP8;
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Empty();
            }

        }
    }
    public void save(Spaceoccupier[][] board) {
        for(int i = 0; i<9; i++){
            for(int j = 0; j<8; j++){
                for(int k = 0; k<8; k++){
                    save[i][j][k]=save[i+1][j][k];
                }
            }
        }
        for(int j = 0; j<8; j++){
            for(int k = 0; k<8; k++){
                save[10][j][k]=board[j][k];
            }
        }
    }
    public void printBoard() {
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j].getName() + " ");
            }
            System.out.println("*");
        }
    }
    private boolean insufficientPieces() {
        int nbknight = 0;
        int nwknight = 0;
        int nbbishop = 0;
        int nwbishop = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] instanceof Pawn) {
                    return false;
                } else if (board[i][j] instanceof Queen) {
                    return false;
                } else if (board[i][j] instanceof Rook) {
                    return false;
                } else if (board[i][j] instanceof Knight) {
                    if (board[i][j].color == 'b') {
                        nbknight++;
                    } else if (board[i][j].color == 'w') {
                        nwknight++;
                    }
                } else if (board[i][j] instanceof Bishop) {
                    if (board[i][j].color == 'b') {
                        nbbishop++;
                    } else if (board[i][j].color == 'w') {
                        nwbishop++;
                    }
                }
            }
            if (nbbishop + nwbishop + nbknight + nwknight == 0) {
                return true;
            } else if (nwknight + nbknight == 0 && nwbishop + nwbishop == 1) {
                return true;
            } else if (nbbishop + nwbishop == 0 && nwknight + nbknight == 1) {
                return true;
            } else if (nwknight + nbknight == 0 && nwbishop == 1 && nwbishop == 1) {
                return true;
            }
        }
        return false;
    }
    private boolean threefoldRepetition() {
        int repeatCount = 0;

        for (int i = 0; i < 9; i++) {
            boolean isSame = true;
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < columns; k++) {
                    Spaceoccupier a = save[i][j][k];
                    Spaceoccupier b = save[9][j][k];
                    if ((a.getName().equals("null") && !b.getName().equals("null")) || (!a.getName().equals("null") && b.getName().equals("null"))) {
                        isSame = false;
                        break;
                    }

                    else{
                        if(a.getClass() != b.getClass()) {
                            isSame = false;
                            break;
                        }
                        if (a.color != b.color) {
                            isSame = false;
                            break;
                        }
                    }
                }
                if (!isSame){
                    break;
                }
            }

            if (isSame){
                repeatCount++;
            }
        }

        return repeatCount >= 2;
    }
    public static boolean isCheckMate(King king, Spaceoccupier[][] board) {
        if (!king.isCheck(king.color, board)) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spaceoccupier s = board[i][j];
                if (s instanceof Piece && ((Piece) s).color == king.color) {
                    for (int ti = 0; ti < 8; ti++) {
                        for (int tj = 0; tj < 8; tj++) {
                            if(s.getName()=="knight"){
                                Knight knight = (Knight) s;
                                if (knight.isValidMove(ti, tj, board)) {
                                    return false;
                                }
                            }
                            if(s.getName()=="bishop"){
                                Bishop bishop = (Bishop) s;
                                if (bishop.isValidMove(ti, tj, board)) {
                                    return false;
                                }
                            }
                            if (s.getName() == "rook") {
                                Rook rook = (Rook) s;
                                if (rook.isValidMove(ti, tj, board)) {
                                    return false;
                                }
                            }
                            if(s.getName() == "queen"){
                                Queen queen = (Queen) s;
                                if (queen.isValidMove(ti, tj, board)) {
                                    return false;
                                }
                            }
                            if(s.getName() == "pawn"){
                                Pawn pawn = (Pawn) s;
                                if (pawn.canCapture(ti, tj, board)) {
                                    return false;
                                }
                            }

                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean isStaleMate(King king, Spaceoccupier[][] board) {
        if (king.isCheck(king.color, board)) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spaceoccupier s = board[i][j];
                if (s instanceof Piece && ((Piece) s).color == king.color) {
                    for (int ti = 0; ti < 8; ti++) {
                        for (int tj = 0; tj < 8; tj++) {
                            if(s.getName()=="knight"){
                                Knight knight = (Knight) s;
                                if (knight.isValidMove(ti, tj, board)) {
                                    return false;
                                }
                            }
                            if(s.getName()=="bishop"){
                                Bishop bishop = (Bishop) s;
                                if (bishop.isValidMove(ti, tj, board)) {
                                    return false;
                                }
                            }
                            if (s.getName() == "rook") {
                                Rook rook = (Rook) s;
                                if (rook.isValidMove(ti, tj, board)) {
                                    return false;
                                }
                            }
                            if(s.getName() == "queen"){
                                Queen queen = (Queen) s;
                                if (queen.isValidMove(ti, tj, board)) {
                                    return false;
                                }
                            }
                            if(s.getName() == "pawn"){
                                Pawn pawn = (Pawn) s;
                                if (pawn.canCapture(ti, tj, board)) {
                                    return false;
                                }
                            }

                        }
                    }
                }
            }
        }

        return true;
    }


    public boolean end(){
        return insufficientPieces()&&threefoldRepetition()&&isFiftyRule();
    }

    public boolean isFiftyRule() {
        if (fiftyMoves == 50) {
            return true;
        }
        return false;
    }

}