//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
        board.WP1.move(3, 0, board.board);
        board.WP1.move(4, 0, board.board);
        board.board[5][1] = board.BB1;
        board.WP1.move(5, 1, board.board);
        board.printBoard();


    }
}