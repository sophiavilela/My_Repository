
/**
 * Write a description of class Soduku here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Soduku
{
    private static final int SIZE = 9; // Sudoku board size

    //0's are empty cells
    private int[][] board = { //example board
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Find an empty cell
                if (board[row][col] == 0) {
                    // Try numbers 1 to 9
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(row, col, num)) {
                            board[row][col] = num; // Place number
                            if (solve()) {
                                return true; // If board is solved, return true
                            }
                            board[row][col] = 0; // Reset cell if it leads to a dead end
                        }
                    }
                    return false; // No valid number was found
                }
            }
        }
        return true; // Solved
    }

    // Check if placing num in board[row][col] is valid
    private boolean isValid(int row, int col, int num) {
        // Check row
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) return false;
        }
        // Check column
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) return false;
        }
        // Check 3x3 
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) return false;
            }
        }
        return true;
    }

    // Print the board
    public void printBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Soduku solver = new Soduku();
        if (solver.solve()) {
            System.out.println("Solved Sudoku:");
            solver.printBoard();
        } else {
            System.out.println("No solution exists.");
        }
    }
}
