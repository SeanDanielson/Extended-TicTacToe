package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameScreen {
    public static void main(String[] args) {
        final int MIN_PLAYERS = 2;
        final int MAX_PLAYERS = 10;
        IGameBoard gameBoard;
        BoardPosition boardPosition;
        List<Character> players;
        Scanner input = new Scanner(System.in);
        int row, column;
        int rowSize, columnSize, winningNum;
        int numPlayers, playerIterator;
        boolean gameOver;
        boolean isValid = false;
        boolean playAgain = true;
        String implChoice;

        // Continue the game cycle while the players still
        // want to play.
        while (playAgain) {
            gameOver = false;
            gameBoard = null;
            boardPosition = null;
            players = new ArrayList<Character>();
            rowSize = 0;
            columnSize = 0;
            winningNum = 0;
            numPlayers = 0;
            playerIterator = 0;
            implChoice = " ";

            // Get the number of players from the users and check if within bounds
            while (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS) {
                System.out.println("How many players?");
                numPlayers = input.nextInt();
                if (numPlayers > MAX_PLAYERS)
                    System.out.println("Must be " + MAX_PLAYERS + " players or fewer");
                else if (numPlayers < MIN_PLAYERS)
                    System.out.println("Must be at least " + MIN_PLAYERS + " players");
            }

            // Get the players that will be playing the game
            for (int playerNumbers = 1; playerNumbers <= numPlayers; playerNumbers++) {
                boolean enterCharacter = true;
                Character inputPlayer = null;
                while (enterCharacter || players.isEmpty()) {
                    System.out.println("Enter the character to represent player " + playerNumbers);
                    inputPlayer = input.next().toUpperCase().charAt(0);
                    if (players.contains(inputPlayer))
                        System.out.println(inputPlayer + " is already taken as a player token!");
                    else {
                        players.add(inputPlayer);
                        enterCharacter = false;
                    }
                }
            }

            // Get the row size from the user and check that it is within bounds.
            while (rowSize < GameBoard.MIN_ROWS_AND_COLUMNS || rowSize > GameBoard.MAX_ROWS_AND_COLUMNS) {
                System.out.println("How many rows?");
                rowSize = input.nextInt();
                if (rowSize < GameBoard.MIN_ROWS_AND_COLUMNS || rowSize > GameBoard.MAX_ROWS_AND_COLUMNS)
                    System.out.println("Rows must be between " + GameBoard.MIN_ROWS_AND_COLUMNS + " and " + GameBoard.MAX_ROWS_AND_COLUMNS);
            }

            // Get the column size from the user and check that it is within bounds.
            while (columnSize < GameBoard.MIN_ROWS_AND_COLUMNS || columnSize > GameBoard.MAX_ROWS_AND_COLUMNS) {
                System.out.println("How many columns?");
                columnSize = input.nextInt();
                if (columnSize < GameBoard.MIN_ROWS_AND_COLUMNS || columnSize > GameBoard.MAX_ROWS_AND_COLUMNS)
                    System.out.println("Columns must be between " + GameBoard.MIN_ROWS_AND_COLUMNS + " and " + GameBoard.MAX_ROWS_AND_COLUMNS);
            }

            // Get the winning num from user and make sure that it is within the
            // max and min bounds. Also check to make sure that it is within the bounds
            // set by the user.
            while (winningNum < GameBoard.MIN_TO_WIN || winningNum > GameBoard.MAX_TO_WIN ||
                    winningNum > rowSize || winningNum > columnSize) {
                System.out.println("How many in a row to win?");
                winningNum = input.nextInt();
                // Re-prompt if the while-condition is false.
                if (winningNum < GameBoard.MIN_TO_WIN || winningNum > GameBoard.MAX_TO_WIN ||
                        winningNum > rowSize || winningNum > columnSize)
                    System.out.println("The number in a row to win must be between " + GameBoard.MIN_TO_WIN + " and " + GameBoard.MAX_TO_WIN +
                            " and within your bounds of " + rowSize + " rows and " + columnSize + " columns");
            }

            // Get implementation choice from user
            while (!implChoice.toUpperCase().equals("F") && !implChoice.toUpperCase().equals("M")) {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                implChoice = input.next().toUpperCase();
                if (implChoice.equals("F"))
                    gameBoard = new GameBoard(rowSize, columnSize, winningNum);
                else if (implChoice.equals("M"))
                    gameBoard = new GameBoardMem(rowSize, columnSize, winningNum);
                else
                    System.out.println("Please enter F or M");
            }

            // If the current game isn't over, keep looping
            while (!gameOver) {
                System.out.println(gameBoard.toString());

                // Checking if the input is valid
                while (!isValid) {
                    System.out.println("Player " + players.get(playerIterator) + " Please enter you ROW");
                    row = input.nextInt();
                    System.out.println("Player " + players.get(playerIterator) + " Please enter you COLUMN");
                    column = input.nextInt();
                    boardPosition = new BoardPosition(row, column);
                    if (gameBoard.checkSpace(boardPosition)) {
                        isValid = true;
                    } else {
                        System.out.println(gameBoard.toString());
                        System.out.println("That space is unavailable, please pick again");
                    }
                }
                isValid = false;

                gameBoard.placeMarker(boardPosition, players.get(playerIterator));

                // Check for winner or draw
                if (gameBoard.checkForWinner(boardPosition)) {
                    System.out.println("Player " + players.get(playerIterator) + " Wins!");
                    System.out.println(gameBoard.toString());
                    gameOver = true;
                } else if (gameBoard.checkForDraw()) {
                    System.out.println("It's a draw!");
                    System.out.println(gameBoard.toString());
                    gameOver = true;
                }

                if (playerIterator < numPlayers - 1)
                    playerIterator++;
                else
                    playerIterator = 0;
            }
            System.out.println("Would you like to play again? Y/N");
            if (input.next().toLowerCase().equals("n"))
                playAgain = false;
        }
    }
}
