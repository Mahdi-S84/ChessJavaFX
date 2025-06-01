//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;

public class Main {

    public static boolean isCheck(char kingColor, Spaceoccupier[][] board) {
        int kingI = -1, kingJ = -1;

        // 1. Find the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spaceoccupier s = board[i][j];
                if (s instanceof King && ((King) s).getColor() == kingColor) {
                    kingI = i;
                    kingJ = j;
                    break;
                }
            }
        }

        if (kingI == -1) return false; // king not found

        // 2. Check if any enemy piece can attack the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spaceoccupier piece = board[i][j];
                if (piece instanceof Piece && ((Piece) piece).getColor() != kingColor) {
                    if (((Piece) piece).canAttack(kingI, kingJ, board)) {
                        return true; // king is in check
                    }
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
        board.WP1.move(2,0, board.board);
        board.BP6.move(5,5, board.board);
        board.BP8.move(4,7,board.board);
        board.board[0][6]=new Empty();
        board.board[4][4]=board.WN2;
        System.out.println(board.WN2.color);
        System.out.println(board.board[0][1].color);
        board.BP6.move(4,4, board.board);
        board.board[7][2]=new Empty();
        board.board[3][1]=board.BB1;
        board.WP1.move(3,1, board.board);
        board.board[0][7]=new Empty();
        board.board[3][6]=board.WR2;
        board.BP8.move(3,6, board.board);

        board.printBoard();


    }
}
