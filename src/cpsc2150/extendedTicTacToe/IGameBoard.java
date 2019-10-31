package cpsc2150.extendedTicTacToe;

/**
 * This is a game board for a tic-tac-toe game that has a
 * user defined size. This is used for the game tic tac toe which
 * will contain characters to represent multiple players.
 *
 * @Define: row_size: z+
 * column_size: z+
 * winning_num: z+
 * @Constraints: row_size >= MIN_ROWS_AND_COLUMNS and row_size <= MAX_ROWS_AND_COLUMNS and
 * column_size >= MIN_ROWS_AND_COLUMNS and column_size <= MAX_ROWS_AND_COLUMNS and
 * winning_num >= MIN_TO_Win and winning_num <= MAX_TO_WIN
 * @Initialization Ensures: IGameBoard filled with character spaces of row_size by
 * column_size.
 */
public interface IGameBoard {
    int MAX_ROWS_AND_COLUMNS = 100;
    int MIN_ROWS_AND_COLUMNS = 3;
    int MIN_TO_WIN = 3;
    int MAX_TO_WIN = 25;
    int SPACE_HANDLER = 9;

    /**
     * @param pos object with row and column location on the board
     * @return true if the space on the board is open
     * @pre pos != null
     * @post checkSpace iff (0 <= pos.getRow < row_size and 0 <= pos.getColumn < column_size and
     * whatsAtPos(pos) = ' '
     */
    default boolean checkSpace(BoardPosition pos) {
        return pos.getRow() < getNumRows() && pos.getColumn() < getNumColumns() &&
                pos.getRow() >= 0 && pos.getColumn() >= 0 &&
                whatsAtPos(pos) == ' ';
    }

    /**
     * @param marker object with row and column location on the board to be occupied
     * @param player character to occupy the space at marker
     * @pre checkSpace = true and marker != null
     * @post [location on the board at marker will contain the character
     * player]
     */
    void placeMarker(BoardPosition marker, char player);

    /**
     * @param lastpos location on the board that was last placed
     * @return whether or not a player won with 5 in a row
     * @pre 0 <= lastpos.getRow < row_size and 0 <= lastpos.getColumn < column_size and
     * lastpos != null
     * @post checkForWinner iff [There are winning_num characters in a row vertically,
     * horizontally, or diagonally from lastpos]
     */
    default boolean checkForWinner(BoardPosition lastpos) {
        char player = whatsAtPos(lastpos);
        if (checkHorizontalWin(lastpos, player))
            return true;
        else if (checkVerticalWin(lastpos, player))
            return true;
        else if (checkDiagonalWin(lastpos, player))
            return true;
        return false;
    }

    /**
     * @return true if all spaces on the board are filled
     * @pre checkForWinner = false
     * @post checkForDraw iff [All characters in the game board are not a space]
     */
    default boolean checkForDraw() {
        BoardPosition pos;
        for (int rows = 0; rows < getNumRows(); rows++) {
            for (int cols = 0; cols < getNumColumns(); cols++) {
                pos = new BoardPosition(rows, cols);
                if (whatsAtPos(pos) == ' ')
                    return false;
            }
        }
        return true;
    }

    /**
     * @param lastpos location on the board that was last placed
     * @param player  character to check if there is 5 in a row
     * @return whether or not a player won with 5 in a row horizontally
     * @pre lastpos != null and MIN_ROWS_AND_COLUMNS <= lastpos.getRow() <= MAX_ROWS_AND_COLUMNS and
     * MIN_ROWS_AND_COLUMNS <= lastpos.getColumns <= lastpos.getColumns
     * @post checkHorizontalWin iff [there are winning_num characters of player in a row
     * horizontally]
     */
    default boolean checkHorizontalWin(BoardPosition lastpos, char player) {
        int col_iterator = lastpos.getColumn() + 1;
        int chars_in_a_row = 1;
        BoardPosition pos = new BoardPosition(lastpos.getRow(), col_iterator);

        // Counts all the characters in a row to the right of
        // the current position
        while (chars_in_a_row != getNumToWin() &&
                col_iterator < getNumColumns() &&
                isPlayerAtPos(pos, player)) {
            chars_in_a_row++;
            col_iterator++;
            pos = new BoardPosition(lastpos.getRow(), col_iterator);
        }

        col_iterator = lastpos.getColumn() - 1;
        pos = new BoardPosition(lastpos.getRow(), col_iterator);

        // Counts all the characters in a row to the left of
        // the current position
        while (chars_in_a_row != getNumToWin() &&
                col_iterator >= 0 &&
                isPlayerAtPos(pos, player)) {
            chars_in_a_row++;
            col_iterator--;
            pos = new BoardPosition(lastpos.getRow(), col_iterator);
        }

        return chars_in_a_row == getNumToWin();
    }

    /**
     * @param lastpos location on the board that was last placed
     * @param player  character to check if there is 5 in a row
     * @return whether or not a player won with 5 in a row vertically
     * @pre lastpos != null and MIN_ROWS_AND_COLUMNS <= lastpos.getRow() <= MAX_ROWS_AND_COLUMNS and
     * MIN_ROWS_AND_COLUMNS <= lastpos.getColumns <= lastpos.getColumns
     * @post checkVerticalWin iff [there are winning_num characters of player in a row
     * vertically]
     */
    default boolean checkVerticalWin(BoardPosition lastpos, char player) {
        int row_iterator = lastpos.getRow() + 1;
        int chars_in_a_row = 1;
        BoardPosition pos = new BoardPosition(row_iterator, lastpos.getColumn());

        // Counts all the characters in a row below
        // the current position
        while (chars_in_a_row != getNumToWin() &&
                row_iterator < getNumRows() &&
                isPlayerAtPos(pos, player)) {
            chars_in_a_row++;
            row_iterator++;
            pos = new BoardPosition(row_iterator, lastpos.getColumn());
        }

        row_iterator = lastpos.getRow() - 1;
        pos = new BoardPosition(row_iterator, lastpos.getColumn());

        // Counts all the characters in a row above
        // the current position
        while (chars_in_a_row != getNumToWin() &&
                row_iterator >= 0 &&
                isPlayerAtPos(pos, player)) {
            chars_in_a_row++;
            row_iterator--;
            pos = new BoardPosition(row_iterator, lastpos.getColumn());
        }

        return chars_in_a_row == getNumToWin();
    }

    /**
     * @param lastpos location on the board that was last placed
     * @param player  character to check if there is 5 in a row
     * @return whether or not a player won with 5 in a row diagonally
     * @pre lastpos != null and MIN_ROWS_AND_COLUMNS <= lastpos.getRow() <= MAX_ROWS_AND_COLUMNS and
     * MIN_ROWS_AND_COLUMNS <= lastpos.getColumns <= lastpos.getColumns
     * @post checkDiagonalWin iff [there are winning_num characters of player in a row
     * diagonally]
     */
    default boolean checkDiagonalWin(BoardPosition lastpos, char player) {
        int row_iterator = lastpos.getRow() + 1;
        int col_iterator = lastpos.getColumn() + 1;
        int chars_in_a_row = 1;
        BoardPosition pos = new BoardPosition(row_iterator, col_iterator);

        // Counts all the characters in a row down and to the right of
        // the current position
        while (chars_in_a_row != getNumToWin() &&
                row_iterator < getNumRows() && col_iterator < getNumColumns() &&
                isPlayerAtPos(pos, player)) {
            row_iterator++;
            col_iterator++;
            chars_in_a_row++;
            pos = new BoardPosition(row_iterator, col_iterator);
        }

        row_iterator = lastpos.getRow() - 1;
        col_iterator = lastpos.getColumn() - 1;
        pos = new BoardPosition(row_iterator, col_iterator);

        // Counts all the characters in a row up and to the left of
        // the current position
        while (chars_in_a_row != getNumToWin() &&
                row_iterator >= 0 && col_iterator >= 0 &&
                isPlayerAtPos(pos, player)) {
            row_iterator--;
            col_iterator--;
            chars_in_a_row++;
            pos = new BoardPosition(row_iterator, col_iterator);
        }

        row_iterator = lastpos.getRow() - 1;
        col_iterator = lastpos.getColumn() + 1;
        pos = new BoardPosition(row_iterator, col_iterator);

        // Counts all the characters in a row up and to the right of
        // the current position
        while (chars_in_a_row != getNumToWin() &&
                row_iterator >= 0 && col_iterator < getNumColumns() &&
                isPlayerAtPos(pos, player)) {
            row_iterator--;
            col_iterator++;
            chars_in_a_row++;
            pos = new BoardPosition(row_iterator, col_iterator);
        }

        row_iterator = lastpos.getRow() + 1;
        col_iterator = lastpos.getColumn() - 1;
        pos = new BoardPosition(row_iterator, col_iterator);

        // Counts all the characters in a row down and to the left of
        // the current position
        while (chars_in_a_row != getNumToWin() &&
                row_iterator < getNumRows() && col_iterator >= 0 &&
                isPlayerAtPos(pos, player)) {
            row_iterator++;
            col_iterator--;
            chars_in_a_row++;
        }

        return chars_in_a_row == getNumToWin();
    }

    /**
     * @param pos row and column location on the board
     * @return the player/character that occupies the space at pos
     * @pre 0 <= pos.getRow < row_size and 0 <= pos.getColumn < column_size and
     * pos != null
     * @post [whatsAtPos returns the character that occupies the space at pos]
     */
    char whatsAtPos(BoardPosition pos);

    /**
     * @param pos    row and column location on the board
     * @param player character to check at location pos
     * @return true if the player is at the location pos, false otherwise
     * @pre 0 <= pos.getRow < row_size and 0 <= pos.getColumn < column_size and
     * pos != null
     * @post isPlayerAtPos iff [The character at pos is equal to player]
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player) {
        return whatsAtPos(pos) == player;
    }

    /**
     * @return the number rows on the game board
     * @pre
     * @post getNumRows = row_size
     */
    int getNumRows();

    /**
     * @return the number column on the game board
     * @pre
     * @post getNumColumns = column_size
     */
    int getNumColumns();

    /**
     * @return the required number of characters in a row to win
     * tic-tac-toe
     * @pre
     * @post getNumToWin = winning_num
     */
    int getNumToWin();
}
