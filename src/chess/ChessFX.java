package chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;

public class ChessFX extends Application {

    private final int TILE_SIZE = 80;
    private final int WIDTH = 8;
    private final int HEIGHT = 8;

    private Board board;
    private Spaceoccupier[][] boardArray;

    private Piece selectedPiece = null;
    private List<int[]> validMoves = new ArrayList<>();

    private char currentTurn = 'w'; // 'w' = white's turn, 'b' = black's turn

    @Override
    public void start(Stage primaryStage) {
        board = new Board();
        boardArray = board.getBoard();

        GridPane gridPane = new GridPane();
        drawBoard(gridPane);

        Scene scene = new Scene(gridPane, TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
        primaryStage.setTitle("ChessFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawBoard(GridPane gridPane) {
        gridPane.getChildren().clear();

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                StackPane tile = new StackPane();
                Rectangle rect = new Rectangle(TILE_SIZE, TILE_SIZE);
                Color tileColor = (i + j) % 2 == 0 ? Color.BEIGE : Color.BROWN;
                rect.setFill(tileColor);
                tile.getChildren().add(rect);

                Label pieceLabel = new Label(boardArray[i][j].toString());
                pieceLabel.setFont(Font.font("Arial Unicode MS", 40));
                pieceLabel.setPrefSize(TILE_SIZE, TILE_SIZE);
                pieceLabel.setAlignment(Pos.CENTER);

                tile.getChildren().add(pieceLabel);
                tile.setAlignment(Pos.CENTER);

                int finalI = i;
                int finalJ = j;

                tile.setOnMouseClicked(e -> {
                    Spaceoccupier clicked = boardArray[finalI][finalJ];

                    // اگر مهره‌ای انتخاب شده بود
                    if (selectedPiece != null) {
                        // اگر کلیک روی مهره‌ی هم‌تیمی بود => فقط جایگزین کن و خانه‌های جدید رو نشان بده
                        if (clicked instanceof Piece) {
                            Piece clickedPiece = (Piece) clicked;
                            if (clickedPiece.color == selectedPiece.color && clickedPiece.color == currentTurn) {
                                selectedPiece = clickedPiece;
                                validMoves.clear();
                                showValidMoves(gridPane, clickedPiece);
                                return;
                            }
                        }

                        // بررسی حرکت قانونی
                        boolean moved = false;
                        for (int[] move : validMoves) {
                            if (move[0] == finalI && move[1] == finalJ) {
                                selectedPiece.move(finalI, finalJ, boardArray, board);
                                selectedPiece = null;
                                validMoves.clear();
                                moved = true;

                                currentTurn = (currentTurn == 'w') ? 'b' : 'w';
                                break;
                            }
                        }

                        if (!moved) {
                            selectedPiece = null;
                            validMoves.clear();
                            resetBoardColors(gridPane);
                        }

                        redrawBoard(gridPane);
                        return;
                    }

                    // اگر هنوز مهره‌ای انتخاب نشده و کلیک روی مهره‌ی هم‌رنگ با نوبت بود
                    if (clicked instanceof Piece) {
                        Piece piece = (Piece) clicked;
                        if (piece.color == currentTurn) {
                            selectedPiece = piece;
                            validMoves.clear();
                            showValidMoves(gridPane, piece);
                        }
                    }
                });

                gridPane.add(tile, j, i);
            }
        }
    }

    private void showValidMoves(GridPane gridPane, Piece piece) {
        resetBoardColors(gridPane);

        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                if (piece.isValidMoveUniversal(x, y, boardArray, board)) {
                    validMoves.add(new int[]{x, y});
                    StackPane moveTile = getTile(gridPane, x, y);
                    if (moveTile != null) {
                        Rectangle moveRect = (Rectangle) moveTile.getChildren().get(0);
                        moveRect.setFill(Color.LIGHTGREEN);
                    }
                }
            }
        }
    }

    private void redrawBoard(GridPane gridPane) {
        boardArray = board.getBoard();
        drawBoard(gridPane);
    }

    private void resetBoardColors(GridPane gridPane) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                StackPane tile = getTile(gridPane, i, j);
                if (tile != null) {
                    Rectangle rect = (Rectangle) tile.getChildren().get(0);
                    Color tileColor = (i + j) % 2 == 0 ? Color.BEIGE : Color.BROWN;
                    rect.setFill(tileColor);
                }
            }
        }
    }

    private StackPane getTile(GridPane grid, int row, int col) {
        for (javafx.scene.Node node : grid.getChildren()) {
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);
            if (r != null && c != null && r == row && c == col) {
                return (StackPane) node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
