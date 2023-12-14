/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package jeudedames;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author 23031
 */

    
/**
 * JeuDeDames class represents a game of checkers.
 * It provides methods to initialize the game, make moves, and check for a winner.
 */
public class JeuDeDames {
    
    // Constants to represent the two players
    private static final int PLAYER_ONE = 1;//PLAYER IE FOR PION
    private static final int PLAYER_TWO = 2;
    private static final int QUEEN_ONE = 3;
    private static final int QUEEN_TWO = 4;  
    
    // 2D array to represent the game board
    private int[][] board;
    
    // Current player
    private int currentPlayer;
    
    /**
     * Constructor to initialize the game.
     * It sets up the game board and assigns the first player.
     */
    public JeuDeDames() {
        // Initialize the game board with size 8x8
        board = new int[8][8];
        
        // Set up the initial positions of the pieces for player one
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 1) {
                    board[row][col] = PLAYER_ONE;
                }
            }
        }
        
        // Set up the initial positions of the pieces for player two
        for (int row = 5; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 1) {
                    board[row][col] = PLAYER_TWO;
                }
            }
        }
        
        // Assign the first player
        currentPlayer = PLAYER_ONE;
    }
    
    /**
     * Makes a move on the game board.
     * It checks if the move is valid and updates the board accordingly.
     *
     * @param fromRow The row index of the piece to move.
     * @param fromCol The column index of the piece to move.
     * @param toRow The row index of the destination.
     * @param toCol The column index of the destination.
     * @throws IllegalArgumentException if the move is invalid.
     */
    public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is valid
        if (!isValidMove(fromRow, fromCol, toRow, toCol)) {
            throw new IllegalArgumentException("Invalid move.");
        }
        
        // Update the board with the move
        int playerPiece = board[fromRow][fromCol];
        board[fromRow][fromCol] = 0;
        board[toRow][toCol] = playerPiece;

        // Check for Queen promotion
        if (playerPiece == PLAYER_ONE && toRow == 7) {
            board[toRow][toCol] = QUEEN_ONE;
        } else if (playerPiece == PLAYER_TWO && toRow == 0) {
            board[toRow][toCol] = QUEEN_TWO;
        }
        
        //capture
        int middleRow=(fromRow+toRow)/2;
        int middleCol=(fromCol+toCol)/2;
        if(Math.abs(fromCol-toRow)==2){
            board[middleRow][middleCol]=0;
        }
       
        // Capture logic
        capturePieces(fromRow, fromCol, toRow, toCol, playerPiece);

        // Check if the current player has won
        if (hasWon(currentPlayer)) {
            System.out.println("Player " + currentPlayer + " has won!");
        }

        // Switch to the next player
        currentPlayer = (currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
    }
        /**
     * Captures all opponent pieces along the path from the starting position to the target position.
     *
     * @param fromRow The starting row index.
     * @param fromCol The starting column index.
     * @param toRow The target row index.
     * @param toCol The target column index.
     * @param playerPiece The type of the piece of the current player.
     */
    private void capturePieces(int fromRow, int fromCol, int toRow, int toCol, int playerPiece) {
        int rowDirection = Integer.signum(toRow - fromRow); // Determine the direction of row movement
        int colDirection = Integer.signum(toCol - fromCol); // Determine the direction of column movement

        int currentRow = fromRow + rowDirection;
        int currentCol = fromCol + colDirection;

        // Traverse the path from the starting position to the target position
        while (currentRow != toRow && currentCol != toCol) {
            if (board[currentRow][currentCol] != 0) {
                board[currentRow][currentCol] = 0; // Capture the piece on the path
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }    
    }
      
    
    /**
     * Checks if a move is valid.
     * It checks if the move is within the bounds of the board,
     * if the piece belongs to the current player, and if the destination is empty.
     *
     * @param fromRow The row index of the piece to move.
     * @param fromCol The column index of the piece to move.
     * @param toRow The row index of the destination.
     * @param toCol The column index of the destination.
     * @return Returns true if the move is valid, false otherwise.
     */
    private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is within the bounds of the board
        if (fromRow < 0 || fromRow >= 8 || fromCol < 0 || fromCol >= 8 ||
            toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            return false;
        }
        
        // Check if the piece belongs to the current player
        if (board[fromRow][fromCol] != currentPlayer) {
            return false;
        }
        
        // Check if the destination is empty
        if (board[toRow][toCol] != 0) {
            return false;
        }
        
        // Check if the move is a valid diagonal move
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        if (rowDiff != 1 || colDiff != 1) {
            return false;
        }
        // Check diagonal move for Player1 and queen one
        if (currentPlayer == PLAYER_ONE || board[fromRow][fromCol] == QUEEN_ONE) {
            if (!(rowDiff == 1 && colDiff == 1 || rowDiff == 2 && colDiff == 2)) {
                return false;
            }
        }

        // Check diagonal move for Player2 and queen two
        if (currentPlayer == PLAYER_TWO || board[fromRow][fromCol] == QUEEN_TWO) {
            if (!(-rowDiff == 1 && colDiff == 1 || -rowDiff == 2 && colDiff == 2)) {
                return false;
            }
        }

        // Check Capture
        if (rowDiff == 2 || rowDiff == -2) {
            int middleRow = (fromRow + toRow) / 2;
            int middleCol = (fromCol + toCol) / 2;
            if (board[middleRow][middleCol] == 0 || board[middleRow][middleCol] == currentPlayer || board[middleRow][middleCol] == (currentPlayer == PLAYER_ONE ? QUEEN_ONE : QUEEN_TWO)) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * Checks if a player has won the game.
     * It checks if all the pieces of the opponent player have been captured.
     *
     * @param player The player to check for a win.
     * @return Returns true if the player has won, false otherwise.
     */
    private boolean hasWon(int player) {
        // Get the opponent player
        int opponent = (player == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
        
        // Check if all the pieces of the opponent player have been captured
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == opponent) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean isWithinBoard(int row,int col){
        return row>=0&&row<8&&col>=0&&col<8;
    }
    
    /**
     * Gets the current player.
     *
     * @return Returns the current player.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * Gets the game board.
     *
     * @return Returns the game board.
     */
    public int[][] getBoard() {
        return board;
    }
    
    /**
     * Main method to run the checkers game.
     * It initializes the game, makes some moves, and prints the game board.
     */
    public static void main(String[] args) {
        // Initialize the checkers game
        JeuDeDames game = new JeuDeDames();
        
 
        // Print the initial board state
        System.out.println("Initial Board:");
        printBoard(game.getBoard());

        // Test 1: Move a piece 
        System.out.println("\\nTest 1: Move a piece");
        game.makeMove(2, 1, 3, 0); // Player 1 moves
        game.makeMove(5, 0, 4, 1); // Player 2 moves
        printBoard(game.getBoard());

        // Test 2: Transform a piece into a queen
        System.out.println("\\nTest 2: Transform a piece into a queen");
        game.makeMove(3, 0, 4, 1); // Player 1 captures and should transform into queen
        printBoard(game.getBoard());

        // Test 3: Capture a piece or a queen 
        System.out.println("\\nTest 3: Capture a piece or a queen");
        game.makeMove(4, 1, 5, 0); // Player 1 captures Player 2's piece
        printBoard(game.getBoard());
    }

    // Helper method to print the board
    public static void printBoard(int[][] board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
    }
}

}

    

