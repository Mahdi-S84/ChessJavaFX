//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
        //add achmez method
        board.WP1.move(0,3,board.board);
        board.printBoard();
    }
}
