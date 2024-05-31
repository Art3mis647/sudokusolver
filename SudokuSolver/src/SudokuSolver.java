import java.util.Scanner;

public class SudokuSolver {


    public static void main(String[] args) {
        System.out.println("Sudoku Solver:");
        System.out.println("=".repeat(100) + "\n");
        System.out.println("Supports 9x9 Sudoku Boards");
        System.out.println("Type 'start' to start solving");
        System.out.println("Type 'exit' to terminate the program");
        System.out.println("Type 'help' to display the help manual");
        System.out.println("=".repeat(100) + "\n");
        while (true) {
            //get user input
            Scanner input = new Scanner(System.in);
            System.out.println("Input:");
            String expression = input.nextLine();

            //code to exit the program
            if (expression.toLowerCase().contains("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            //code to display help manual
            if (expression.toLowerCase().contains("help")) {
                displayHelpManual();
                continue;
            }

            if (!(expression.toLowerCase().contains("help") | expression.toLowerCase().contains("start") | expression.toLowerCase().contains("exit"))){
                System.out.println("Erorr: Invalid Input");
                continue;
            }

            //code to start solving
            if (expression.toLowerCase().contains("start")) {
                Scanner boardScanner = new Scanner(System.in);
                int[][] board = new int[9][9];
                System.out.println("Input Puzzle:");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        board[i][j] = boardScanner.nextInt();
                    }
                }

                if (solveSudoku(board, 9))
                {
                    print(board, 9);
                }
                else {
                    System.out.println("No solution");
                }
                continue;
            }
        }
    }

    public static void displayHelpManual() {
        System.out.println("Sudoku Solver");
        System.out.println("=".repeat(100) + "\n");
        System.out.println("Enter the Sudoku puzzle as a 9x9 grid of numbers.\n");
        System.out.println("Use numbers 1 to 9 for filled cells and 0 (zero) for empty cells.\n");
        System.out.println("After processing the puzzle, the solver displays the solution if one exists.");
        System.out.println("The solution is shown as a 9x9 grid of numbers, with 0 representing empty cells.");
        System.out.println("Note: This program only supports 9x9 sudoku puzzles");
    }
    public static boolean isSafe(int[][] board, int row, int col, int num)
    {
        //checks if the row is 'safe' to put a number in
        for (int i = 0; i < board.length; i++)
        {
            if (board[row][i] == num) {
                return false;
            }
        }
        //checks if the col is 'safe' to put a number in

        for (int j = 0; j < board.length; j++)
        {
            if (board[j][col] == num)
            {
                return false;
            }
        }
        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;
        //checks if the box is 'safe' to put a number in

        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++)
        {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++)
            {
                if (board[r][d] == num)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solveSudoku(
            int[][] board, int n)
    {
        int row = -1, column = -1;
        boolean empty = true;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] == 0)
                {
                    row = i;
                    column = j;
                    empty = false;
                    break;
                }
            }
            if (!empty) {
                break;
            }
        }
        if (empty)
        {
            return true;
        }

        // else case to backtrack each row
        for (int num = 1; num <= n; num++)
        {
            if (isSafe(board, row, column, num))
            {
                board[row][column] = num;
                if (solveSudoku(board, n))
                {
                    return true;
                }
                else
                {
                    board[row][column] = 0;
                }
            }
        }
        return false;
    }

    //Prints out the Board
    public static void print(
            int[][] board, int N)
    {

        for (int r = 0; r < N; r++)
        {
            for (int d = 0; d < N; d++)
            {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % (int)Math.sqrt(N) == 0)
            {
                System.out.print("");
            }
        }
    }



}