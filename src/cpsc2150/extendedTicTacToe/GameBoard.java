package cpsc2150.extendedTicTacToe;

/**
 * Correspondence row_size = rows
 * Correspondence column_size = columns
 * Correspondence winning_num = winningNumber
 *
 * @Invariant board.length = rows and
 * board[].length = columns and
 * MIN_ROWS_AND_COLUMNS <= rows <= MAX_ROWS_AND_COLUMNS and
 * MIN_ROWS_AND_COLUMNS <= colums <= MAX_ROWS_AND_COLUMNS and
 * MIN_TO_WIN <= winningNumber <= MAX_TO_WIN
 */
public class GameBoard extends AbsGameBoard {

    private int rows, columns;
    private int winningNumber;
    private char board[][];

    /**
     * @param rowSize       Number of rows for the game board
     * @param columnSize    Number of columns for the game board
     * @param winningNumber Number of characters in a row to win
     * @pre MIN_ROWS_AND_COLUMNS <= rowSize <= MAX_ROWS_AND_COLUMNS and
     * MIN_ROWS_AND_COLUMNS <= columnSize <= MAX_ROWS_AND_COLUMNS and
     * MIN_TO_WIN <= winningNumber <= MAX_TO_WIN
     * @post rows = rowSize and columns = columnSize and winningNumber = winningNumber and
     * [board is initialized with a row size of row and a column size of column] and
     * [all values in board are set to a space character]
     */
    GameBoard(int rowSize, int columnSize, int winningNumber) {
        this.rows = rowSize;
        this.columns = columnSize;
        this.winningNumber = winningNumber;

        board = new char[rows][columns];
        for (int rows = 0; rows < this.rows; rows++) {
            for (int cols = 0; cols < this.columns; cols++) {
                board[rows][cols] = ' ';
            }
        }
    }

    public void placeMarker(BoardPosition marker, char player) {
        board[marker.getRow()][marker.getColumn()] = player;
    }

    public char whatsAtPos(BoardPosition pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumColumns() {
        return columns;
    }

    public int getNumToWin() {
        return winningNumber;
    }
}
