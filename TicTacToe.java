import java.util.Scanner;

public class TicTacToe {
    private static final int boardSize = 3;
    private enum Status { WIN, DRAW, CONTINUE }; // Game status: win, draw, or continue

    private char[][] board = new char[boardSize][boardSize]; // The game board
    private boolean firstPlayer = true; // True if it's the first player's turn (X), false if second player's (O)
    private boolean gameOver = false; // True if the game is over

    // Constructor to initialize the board
    public TicTacToe() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' '; // Empty spaces initially
            }
        }
    }

    // Starts the game and handles turns
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            printBoard();
            printStatus();
            int row, col;

            // Input for row
            do {
                String player = firstPlayer ? "Player X" : "Player O";
                System.out.print(player + ": ");
                System.out.print("Enter row ( 0, 1, or 2 ): ");
                row = scanner.nextInt();
            } while (row < 0 || row >= boardSize);

            // Input for column
            do {
                String player = firstPlayer ? "Player X" : "Player O";
                System.out.print(player + ": ");
                System.out.print("Enter column ( 0, 1, or 2 ): ");
                col = scanner.nextInt();
            } while (col < 0 || col >= boardSize);

            // Check if the move is valid
            if (validMove(row, col)) {
                char symbol = firstPlayer ? 'X' : 'O';
                printSymbol(row, col, symbol);
                gameOver = checkGameOver();

                // Switch players
                firstPlayer = !firstPlayer;
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        scanner.close();
    }

    // Prints the current player's turn
    private void printStatus() {
        String player = firstPlayer ? "Player X" : "Player O";
        System.out.println(player + "'s turn.");
    }

    // Checks the current game status (win, draw, or continue)
    private boolean checkGameOver() {
        Status status = gameStatus();
        if (status == Status.WIN) {
            printBoard();
            System.out.println((firstPlayer ? "Player X" : "Player O") + " wins!");
            return true;
        } else if (status == Status.DRAW) {
            printBoard();
            System.out.println("The game is a draw.");
            return true;
        }
        return false;
    }

    // Determines the game status (WIN, DRAW, CONTINUE)
    private Status gameStatus() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < boardSize; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return Status.WIN;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return Status.WIN;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return Status.WIN;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return Status.WIN;
        }

        // Check for a draw (no empty spaces)
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return Status.CONTINUE;
                }
            }
        }
        return Status.DRAW;
    }

    // Prints the board
    private void printBoard() {
        System.out.println();
        System.out.println(" _________________ ");
        for (int i = 0; i < boardSize; i++) {
            System.out.println("|     |     |     |");
            for (int j = 0; j < boardSize; j++) {
                System.out.print("|  " + board[i][j] + "  ");
            }
            System.out.println("|");
            System.out.println("|_____|_____|_____|");
        }
        System.out.println();
    }

    // Places the symbol (X or O) on the board
    private void printSymbol(int row, int col, char value) {
        board[row][col] = value;
    }

    // Checks if the selected move is valid (i.e., within bounds and the space is empty)
    private boolean validMove(int row, int col) {
        return board[row][col] == ' ';
    }

    // Main method to start the game
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}