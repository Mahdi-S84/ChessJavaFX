//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        // Initialize your game board
        Board gameBoard = new Board();

        // Launch JavaFX
        ChessFX.launchFX(gameBoard);
    }
}