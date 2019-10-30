package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard{
    /**
     * @return a string representation of the game board
     * @post
     * [toString returns the contents of the game board in a
     * format that specifies what row and column every character is
     * in]
     */
    @Override
    public String toString() {
        StringBuilder boardrep = new StringBuilder("    ");
        BoardPosition bp;
        for (int iterator = 0; iterator < getNumColumns(); iterator++){
            boardrep.append(iterator).append("|");
            if (iterator < GameBoard.SPACE_HANDLER)
                boardrep.append(" ");
        }
        boardrep.append("\n");

        for (int row_iterator = 0; row_iterator < getNumRows(); row_iterator++){
            if(row_iterator <= GameBoard.SPACE_HANDLER)
                boardrep.append(" ");
            boardrep.append(row_iterator).append("|");
            for (int col_iterator = 0; col_iterator < getNumColumns(); col_iterator++){
                bp = new BoardPosition(row_iterator, col_iterator);
                boardrep.append(whatsAtPos(bp)).append(" |");
            }
            boardrep.append("\n");
        }
        return boardrep.toString();
    }
}
