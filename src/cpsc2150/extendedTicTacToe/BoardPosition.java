package cpsc2150.extendedTicTacToe;

public class BoardPosition {
    /**
     * @invariant
     * 0 <= row <= GameBoard.MAX_ROWS
     * 0 <= column <= GameBoard.MAX_COLUMNS
     */
    private int row;
    private int column;

    /**
     * @param row row location on gameboard
     * @param column column location on gameboard
     */
    BoardPosition(int row, int column){

    }

    /**
     * @return row location
     */
    public int getRow(){
        return row;
    }

    /**
     * @return column location
     */
    public int getColumn(){
        return column;
    }

    /**
     * @param pos BoardPosition being compared
     * @return true if the row and column of compared BoardPositions
     * are the same
     */
    public boolean equals(BoardPosition pos){
        return true;
    }

    /**
     * @return row and column in the format "<row>,<column>"
     */
    public String toString(){
        return "";
    }
}
