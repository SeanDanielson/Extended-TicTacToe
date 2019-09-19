package cpsc2150.extendedTicTacToe;

public class GameBoard {
    /**
     * @Invariant
     * board.length = MAX_COLUMNS and
     * board[].length = MAX_ROWS and
     * [All values in board are either PLAYER_X,
     *  PLAYER_O, or an empty space]
     */

    private char board[][];
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';
    private static final int MAX_ROWS = 8;
    private static final int MAX_COLUMNS = 8;

    /**
     * @post
     * [The board will be initialized with it's size and every
     * index will hold an empty space]
     */
    GameBoard(){
    }

    /**
     * @param pos object with row and column location on the board
     * @return true if the space on the board is taken
     */
    public boolean checkSpace(BoardPosition pos){
        return true;
    }

    /**
     * @param marker object with row and column location on the board to be occupied
     * @param player character to occupy the space at marker
     * @pre
     * [The position pos on the board is available] and
     * player = PLAYER_X or player = PLAYER_O
     * @post
     * [location on the board at marker will contain the character
     * player]
     */
    public void placeMarker(BoardPosition marker, char player){

    }

    /**
     * @param lastpos location on the board that was last placed
     * @return whether or not a player won with 5 in a row
     */
    public boolean checkForWinner(BoardPosition lastpos){
        return true;
    }

    /**
     * @return true if all spaces on the board are filled
     * @pre
     * checkForWinner = false
     */
    public boolean checkForDraw(){
        return true;
    }

    /**
     * @param lastpos location on the board that was last placed
     * @param player character to check if there is 5 in a row
     * @return whether or not a player won with 5 in a row horizontally
     * @pre
     * player = PLAYER_X or player = PLAYER_O
     */
    public boolean checkHorizontalWin(BoardPosition lastpos, char player){
        return true;
    }

    /**
     * @param lastpos location on the board that was last placed
     * @param player character to check if there is 5 in a row
     * @return whether or not a player won with 5 in a row vertically
     * @pre
     * player = PLAYER_X or player = PLAYER_O
     */
    public boolean checkVerticalWin(BoardPosition lastpos, char player){
        return true;
    }

    /**
     * @param lastpos location on the board that was last placed
     * @param player character to check if there is 5 in a row
     * @return whether or not a player won with 5 in a row diagonally
     * @pre
     * player = PLAYER_X or player = PLAYER_O
     */
    public boolean checkDiagonalWin(BoardPosition lastpos, char player){
        return true;
    }

    /**
     * @param lastpos location on the board that was last placed
     * @param player character to check if there is 5 in a row
     * @return whether or not a player won with 5 in a row diagonally to the left
     * @pre
     * player = PLAYER_X or player = PLAYER_O
     */
    private boolean checkLeftDiagonalWin(BoardPosition lastpos, char player){
        return true;
    }

    /**
     * @param lastpos location on the board that was last placed
     * @param player character to check if there is 5 in a row
     * @return whether or not a player won with 5 in a row diagonally to the right
     * @pre
     * player = PLAYER_X or player = PLAYER_O
     */
    private boolean checkRightDiagonalWin(BoardPosition lastpos, char player){
        return true;
    }

    /**
     * @param pos row and column location on the board
     * @return the player/character that occupies the space at pos
     * @post
     * whatsAtPos = PLAYER_O or whatsAtPos = PLAYER_X or
     * [whatsAtPos returns a space character]
     */
    public char whatsAtPos(BoardPosition pos){
        return ' ';
    }

    /**
     * @param pos row and column location on the board
     * @param player character to check at location pos
     * @return true if the player is at the location pos, false otherwise
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        return true;
    }

    /**
     * @return a string representation of the game board
     */
    public String toString() {
        return "";
    }
}
