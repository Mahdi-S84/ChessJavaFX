public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        
        
        System.out.println("--- WP4 from (1,3) to (3,3) ---");
        board.WP4.move(3, 3, board.board);
        board.printBoard();

       
        System.out.println("--- BP5 from (6,4) to (4,4) ---");
        board.BP5.move(4, 4, board.board);
        board.printBoard();

        System.out.println("--- WP4 en passant BP5 ---");
        board.WP4.move(4, 4, board.board);  
        board.printBoard();
    }
}
