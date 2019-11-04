package cpsc2150.extendedTicTacToe;

import java.util.*;

/**
 * Correspondence row_size = rows
 * Correspondence column_size = columns
 * Correspondence winning_num = winningNumber
 *
 * @Invariant MIN_ROWS_AND_COLUMNS <= rows <= MAX_ROWS_AND_COLUMNS and
 * MIN_ROWS_AND_COLUMNS <= colums <= MAX_ROWS_AND_COLUMNS and
 * MIN_TO_WIN <= winningNumber <= MAX_TO_WIN and
 * [All BoardPositions in the Linked List in board will be within the
 * bounds of row and column]
 */
public class GameBoardMem extends AbsGameBoard {
    private int rows, columns;
    private int winningNumber;
    private Map<Character, LinkedList<BoardPosition>> board;

    /**
     * @param rowSize       number of rows for the game board
     * @param columnSize    number of columns for the game board
     * @param winningNumber number in a row it takes to win
     * @pre MIN_ROWS_AND_COLUMNS <= rowSize <= MAX_ROWS_AND_COLUMNS and
     * MIN_ROWS_AND_COLUMNS <= columnSize <= MAX_ROWS_AND_COLUMNS and
     * MIN_TO_WIN <= winningNumber <= MAX_TO_WIN
     * @post rows = rowSize and columns = columnSize and winningNumber = winningNumber and
     * [board is initialized as a HashMap with a Character as the key and a List of BoardPositions
     * as the value]
     */
    GameBoardMem(int rowSize, int columnSize, int winningNumber) {
        this.rows = rowSize;
        this.columns = columnSize;
        this.winningNumber = winningNumber;
        board = new HashMap<Character, LinkedList<BoardPosition>>();
    }

    @Override
    public void placeMarker(BoardPosition marker, char player) {
        List<BoardPosition> temp;
        if (board.containsKey(player)) {
            temp = board.get(player);
            temp.add(marker);
            board.put(player, (LinkedList<BoardPosition>) temp);
        } else {
            temp = new LinkedList<BoardPosition>();
            temp.add(marker);
            board.put(player, (LinkedList<BoardPosition>) temp);
        }
    }

    @Override
    public char whatsAtPos(BoardPosition pos) {
        List<BoardPosition> temp;
        for (Character player : board.keySet()) {
            temp = board.get(player);
            for (BoardPosition boardPosition : temp) {
                if (boardPosition.equals(pos))
                    return player;
            }
        }
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        for (BoardPosition boardPosition : board.get(player)) {
            if (boardPosition.equals(pos))
                return true;
        }
        return false;
    }

    @Override
    public int getNumRows() {
        return rows;
    }

    @Override
    public int getNumColumns() {
        return columns;
    }

    @Override
    public int getNumToWin() {
        return winningNumber;
    }
}
