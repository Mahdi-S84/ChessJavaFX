package chess;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;

public class ChessFX extends Application {

    private int selectedPromotionCode = -1;

    private final int TILE_SIZE = 80;
    private final int WIDTH = 8;
    private final int HEIGHT = 8;

    private Board board;
    private Spaceoccupier[][] boardArray;

    private Piece selectedPiece = null;
    private List<int[]> validMoves = new ArrayList<>();

    private char currentTurn = 'w';

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
        resetBoardColors(gridPane);
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

                    if (selectedPiece != null) {
                        if (clicked instanceof Piece) {
                            Piece clickedPiece = (Piece) clicked;
                            if (clickedPiece.color == selectedPiece.color && clickedPiece.color == currentTurn) {
                                selectedPiece = clickedPiece;
                                validMoves.clear();
                                showValidMoves(gridPane, clickedPiece);
                                return;
                            }
                        }

                        boolean moved = false;
                        for (int[] move : validMoves) {
                            if (move[0] == finalI && move[1] == finalJ) {


                                if (selectedPiece instanceof King king) {
                                    if (finalI == 0 || finalI == 7) {
                                        if (finalJ == 2 || finalJ == 6) {
                                            Rook rook = (finalJ == 2) ? (Rook) boardArray[finalI][0] : (Rook) boardArray[finalI][7];
                                            if (rook != null) {
                                                if (king.color == 'w') {
                                                    if (finalJ == 2 && king.isValidBigWhiteCastling(finalI, finalJ, rook, king, boardArray)) {
                                                        king.bigWhiteCastling(finalI, finalJ, boardArray, board, rook);
                                                        moved = true;
                                                    } else if (finalJ == 6 && king.isValidShortWhiteCastling(finalI, finalJ, rook, king, boardArray)) {
                                                        king.shortWhiteCastling(finalI, finalJ, boardArray, board, rook);
                                                        moved = true;
                                                    }
                                                } else {
                                                    if (finalJ == 2 && king.isValidBigBlackCastling(finalI, finalJ, rook, king, boardArray)) {
                                                        king.bigBlackCastling(finalI, finalJ, boardArray, board, rook);
                                                        moved = true;
                                                    } else if (finalJ == 6 && king.isValidShortBlackCastling(finalI, finalJ, rook, king, boardArray)) {
                                                        king.shortBlackCastling(finalI, finalJ, boardArray, board, rook);
                                                        moved = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }


                                if (!moved) {
                                    selectedPiece.move(finalI, finalJ, boardArray, board);
                                    moved = true;

                                    // --- Pawn Promotion ---
                                    if (selectedPiece instanceof Pawn) {
                                        if ((selectedPiece.color == 'w' && finalI == 7) || (selectedPiece.color == 'b' && finalI == 0)) {
                                            int choice = showPromotionDialog(selectedPiece.color);
                                            ((Pawn) selectedPiece).upgradePawn(finalI, finalJ, boardArray, choice);
                                        }
                                    }
                                }

                                selectedPiece = null;
                                validMoves.clear();
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

    private void highlightCheckedKing(GridPane gridPane) {
        King whiteKing = null;
        King blackKing = null;

        // Find both kings
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (boardArray[i][j] instanceof King king) {
                    if (king.color == 'w') whiteKing = king;
                    if (king.color == 'b') blackKing = king;
                }
            }
        }

        if (whiteKing != null && whiteKing.isCheck('w', whiteKing.getI(), whiteKing.getJ(), boardArray)) {
            StackPane tile = getTile(gridPane, whiteKing.getI(), whiteKing.getJ());
            if (tile != null) {
                Rectangle rect = (Rectangle) tile.getChildren().get(0);
                rect.setFill(Color.GRAY);
            }
        }

        if (blackKing != null && blackKing.isCheck('b', blackKing.getI(), blackKing.getJ(), boardArray)) {
            StackPane tile = getTile(gridPane, blackKing.getI(), blackKing.getJ());
            if (tile != null) {
                Rectangle rect = (Rectangle) tile.getChildren().get(0);
                rect.setFill(Color.GRAY);
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

        if (piece instanceof King king) {
            int row = king.getI();
            if (king.color == 'w') {

                Rook leftRook = (boardArray[0][0] instanceof Rook) ? (Rook) boardArray[0][0] : null;
                Rook rightRook = (boardArray[0][7] instanceof Rook) ? (Rook) boardArray[0][7] : null;

                if (leftRook != null && king.isValidBigWhiteCastling(0, 2, leftRook, king, boardArray)) {
                    validMoves.add(new int[]{0, 2});
                    highlight(gridPane, 0, 2);
                }
                if (rightRook != null && king.isValidShortWhiteCastling(0, 6, rightRook, king, boardArray)) {
                    validMoves.add(new int[]{0, 6});
                    highlight(gridPane, 0, 6);
                }

            } else {
                Rook leftRook = (boardArray[7][0] instanceof Rook) ? (Rook) boardArray[7][0] : null;
                Rook rightRook = (boardArray[7][7] instanceof Rook) ? (Rook) boardArray[7][7] : null;

                if (leftRook != null && king.isValidBigBlackCastling(7, 2, leftRook, king, boardArray)) {
                    validMoves.add(new int[]{7, 2});
                    highlight(gridPane, 7, 2);
                }
                if (rightRook != null && king.isValidShortBlackCastling(7, 6, rightRook, king, boardArray)) {
                    validMoves.add(new int[]{7, 6});
                    highlight(gridPane, 7, 6);
                }
            }
        }
    }

    private void highlight(GridPane gridPane, int row, int col) {
        StackPane moveTile = getTile(gridPane, row, col);
        if (moveTile != null) {
            Rectangle moveRect = (Rectangle) moveTile.getChildren().get(0);
            moveRect.setFill(Color.LIGHTBLUE);
        }
    }



    private void redrawBoard(GridPane gridPane) {
        boardArray = board.getBoard();
        drawBoard(gridPane);
        highlightCheckedKing(gridPane);
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

    private int showPromotionDialog(char color) {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Pawn Promotion");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 20;");

        Label title = new Label((color == 'w' ? "White" : "Black") + " Pawn Promotion");
        title.setFont(Font.font("Arial", 20));

        Label queen = createPromoOption("♕", color == 'w' ? 7 : 0, dialogStage);
        Label rook = createPromoOption("♖", color == 'w' ? 6 : 1, dialogStage);
        Label bishop = createPromoOption("♗", color == 'w' ? 5 : 2, dialogStage);
        Label knight = createPromoOption("♘", color == 'w' ? 4 : 3, dialogStage);

        vbox.getChildren().addAll(title, queen, rook, bishop, knight);

        Scene scene = new Scene(vbox, 260, 420);
        dialogStage.setScene(scene);

        vbox.setScaleX(0.1);
        vbox.setScaleY(0.1);
        javafx.animation.ScaleTransition scale = new javafx.animation.ScaleTransition(javafx.util.Duration.millis(300), vbox);
        scale.setToX(1);
        scale.setToY(1);
        scale.play();

        dialogStage.showAndWait();

        return selectedPromotionCode;
    }

    private Label createPromoOption(String pieceSymbol, int code, Stage dialogStage) {
        Label label = new Label(pieceSymbol);
        label.setFont(Font.font("Arial Unicode MS", 40));
        label.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10;");
        label.setPrefWidth(150);
        label.setAlignment(Pos.CENTER);

        label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: lightgray; -fx-border-color: black; -fx-padding: 10;"));
        label.setOnMouseExited(e -> label.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10;"));

        label.setOnMouseClicked(e -> {
            selectedPromotionCode = code;
            dialogStage.close();
        });

        return label;
    }




    public static void main(String[] args) {
        launch(args);
    }
}
