//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
        board.board[7][1]=new Empty();
        board.printBoard();
        //add achmez method
        board.BK.shortBlackCastling(7,1, board.board, board.BR1);
        board.printBoard();
    }
}
