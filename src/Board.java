public class  Board {
    static int  rows=8;
    static int columns=8;
    public static int moveNumber=0;
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

    public void printBoard() {
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j].getName() + " ");
            }
            System.out.println("*");
        }
    }
//i want cry man man man


}