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
        board.WR1.move(4,0,board.board);
        board.printBoard();
    }
}
