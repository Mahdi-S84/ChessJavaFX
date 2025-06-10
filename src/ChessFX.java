import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChessFX extends Application {
    private static final int TILE_SIZE = 80;
    private static Board sharedBoard;

    private final GridPane chessBoard = new GridPane();
    private final Rectangle[][] tiles = new Rectangle[8][8];
    private final ImageView[][] pieceViews = new ImageView[8][8];
    private Piece selectedPiece = null;
    private int[] selectedPos = {-1, -1}; // [row, col]

    public ChessFX() {
        if (sharedBoard == null) {
            throw new IllegalStateException("Initialize with launchFX()");
        }
    }

    public static void launchFX(Board board) {
        sharedBoard = board;
        launch(ChessFX.class);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeBoard();
        updateBoardDisplay();

        Scene scene = new Scene(chessBoard, 8 * TILE_SIZE, 8 * TILE_SIZE);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Create tile
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill(getTileColor(row, col));

                final int r = row;
                final int c = col;
                tile.setOnMouseClicked(e -> handleTileClick(r, c));

                tiles[row][col] = tile;
                chessBoard.add(tile, col, row);

                // Piece image placeholder
                ImageView pieceView = new ImageView();
                pieceView.setFitWidth(TILE_SIZE - 10);
                pieceView.setFitHeight(TILE_SIZE - 10);
                pieceViews[row][col] = pieceView;
                chessBoard.add(pieceView, col, row);
            }
        }
    }

    private void handleTileClick(int row, int col) {
        Spaceoccupier target = sharedBoard.board[row][col];

        if (selectedPiece == null) {
            // SELECTION PHASE
            if (target instanceof Piece) {
                selectedPiece = (Piece) target;
                selectedPos = new int[]{row, col};
                tiles[row][col].setFill(Color.YELLOW);
                System.out.println("Selected " + selectedPiece.getName() +
                        " at [" + row + "," + col + "]");
            }
        } else {
            // MOVEMENT PHASE
            System.out.println("Attempting to move to [" + row + "," + col + "]");

            // Store original position
            int fromRow = selectedPos[0];
            int fromCol = selectedPos[1];

            try {
                // Execute move
                selectedPiece.move(row, col, sharedBoard.board, sharedBoard);
                updateBoardDisplay();
            } catch (Exception e) {
                System.err.println("Move failed: " + e.getMessage());
            } finally {
                resetSelection();
            }
        }
    }

    private void resetSelection() {
        if (selectedPos[0] != -1 && selectedPos[1] != -1) {
            tiles[selectedPos[0]][selectedPos[1]].setFill(
                    getTileColor(selectedPos[0], selectedPos[1]));
        }
        selectedPiece = null;
        selectedPos = new int[]{-1, -1};
    }

    private Color getTileColor(int row, int col) {
        return (row + col) % 2 == 0 ? Color.WHITE : Color.DARKGRAY;
    }

    private void updateBoardDisplay() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Spaceoccupier piece = sharedBoard.board[row][col];
                pieceViews[row][col].setImage(getPieceImage(piece));
            }
        }
    }

    private Image getPieceImage(Spaceoccupier piece) {
        if (!(piece instanceof Piece)) return null;

        String imagePath = "/chess_pieces/" + ((Piece) piece).getName() + ".png";
        try {
            return new Image(getClass().getResourceAsStream(imagePath));
        } catch (Exception e) {
            System.err.println("Missing image: " + imagePath);
            return null;
        }
    }
}