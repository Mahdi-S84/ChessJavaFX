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
        boolean check = isCheck('w', board.board);
        System.out.println("White King in check? " + check);

    }
}
