package cpsc2150.extendedTicTacToe;

/**
 * @invariant
 * 0 <= row <= GameBoard.MAX_ROWS_AND_COLUMNS
 * 0 <= column <= GameBoard.MIN_ROWS_AND_COLUMNS
 */
public class BoardPosition {

    private int row;
    private int column;

    /**
     * @param row row location on Gameboard
     * @param column column location on Gameboard
     * @pre
     * @post
     * self.row = row and self.column = column
     */
    BoardPosition(int row, int column){
        this.row = row;
        this.column = column;
    }

    /**
     * @return row location
     * @post
     * getRow = row
     */
    public int getRow(){
        return row;
    }

    /**
     * @return column location
     * @post
     * getColumn = column
     */
    public int getColumn(){
        return column;
    }

    /**
     * @param pos BoardPosition being compared
     * @return true if the row and column of compared BoardPositions
     * are the same
     * @post
     * row = #row and col = #col
     */
    public boolean equals(BoardPosition pos){
        return this.row == pos.row && this.column == pos.column;
    }

    /**
     * @return row and column in the format "<row>,<column>"
     * @post
     * row = #row and col = #col
     */
    @Override
    public String toString(){
        return this.row + "," + this.column;
    }
}
