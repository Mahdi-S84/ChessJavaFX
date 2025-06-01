//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
//        board.board[1][0]=new Empty();
//        board.board[1][1]=new Empty();
//        board.board[1][2]=new Empty();
//        board.board[0][1]=new Empty();
//        board.board[0][2]=new Empty();
//        board.board[7][4]=new Empty();
//        board.WK.shortWhiteCastling(0,1, board.board, board.WR1);
//        board.printBoard();
        //board.board[1][0]=new Empty();
        board.WR1.move(3,0,board.board);
        board.printBoard();


    }
}
