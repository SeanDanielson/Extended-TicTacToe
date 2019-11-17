package cpsc2150.extendedTicTacToe;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestGameBoardMem {
    //Gameboard factory
    private IGameBoard makeGameBoard(int rowSize, int columnSize, int winningNumber){
        return new GameBoardMem(rowSize, columnSize, winningNumber);
    }

    // Converts the car array board to a string
    private String boardToString(char board[][]){
        StringBuilder boardrep = new StringBuilder("    ");
        for (int iterator = 0; iterator < board[0].length; iterator++) {
            boardrep.append(iterator).append("|");
            if (iterator < GameBoard.SPACE_HANDLER)
                boardrep.append(" ");
        }
        boardrep.append("\n");

        for (int row_iterator = 0; row_iterator < board.length; row_iterator++) {
            if (row_iterator <= GameBoard.SPACE_HANDLER)
                boardrep.append(" ");
            boardrep.append(row_iterator).append("|");
            for (int col_iterator = 0; col_iterator < board[0].length; col_iterator++) {
                boardrep.append(board[row_iterator][col_iterator]).append(" |");
            }
            boardrep.append("\n");
        }
        return boardrep.toString();
    }

    // This test case is unique because it tests that the lower bound limit on the board size as well as the number to win
    @Test
    public void testConstructor_minVals(){
        char expected[][] = new char[3][3];
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                expected[r][c] = ' ';
            }
        }
        IGameBoard gb = makeGameBoard(3, 3, 3);

        assertTrue(boardToString(expected).equals(gb.toString()) && gb.getNumRows() == 3 && gb.getNumColumns() == 3 && gb.getNumToWin() == 3);
    }

    // This test case is unique because it tests that the upper bound limit on the board size as well as the number to win
    @Test
    public void testConstructor_maxVals(){
        char expected[][] = new char[100][100];
        for(int r = 0; r < 100; r++){
            for(int c = 0; c < 100; c++){
                expected[r][c] = ' ';
            }
        }
        IGameBoard gb = makeGameBoard(100, 100, 25);

        assertTrue(boardToString(expected).equals(gb.toString()) && gb.getNumRows() == 100 && gb.getNumColumns() == 100 && gb.getNumToWin() == 25);
    }

    // This test case is unique because it tests that you can have different values as rows and columns as well as testing the two ends of the spectrum
    @Test
    public void testConstructor_min_Max_min(){
        char expected[][] = new char[3][100];
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 100; c++){
                expected[r][c] = ' ';
            }
        }
        IGameBoard gb = makeGameBoard(3, 100, 3);

        assertTrue(boardToString(expected).equals(gb.toString()) && gb.getNumRows() == 3 && gb.getNumColumns() == 100 && gb.getNumToWin() == 3);
    }

    // This test is unique because the location on the board is occupied
    @Test
    public void testcheckSpace_occupied(){
        char expected[][] = new char[4][3];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 3, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');

        assertFalse(gb.checkSpace(new BoardPosition(0, 0)) && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because the location on the board is unoccupied and tests the size of the row
    @Test
    public void testcheckSpace_unoccupied_bottomLeft(){
        char expected[][] = new char[4][3];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 3, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');

        assertTrue(gb.checkSpace(new BoardPosition(3, 0)) && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because the location on the board is unoccupied and tests the size of the column
    @Test
    public void testcheckSpace_unoccupied_topRight(){
        char expected[][] = new char[4][3];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 3, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');

        assertTrue(gb.checkSpace(new BoardPosition(0, 2)) && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because the last item placed is in the middle of consecutive characters of the same team
    @Test
    public void testcheckHorizontalWin_mid_O_True(){
        char expected[][] = new char[4][3];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][0] = 'O';
        expected[2][1] = 'O';
        expected[2][2] = 'O';

        IGameBoard gb = makeGameBoard(4, 3, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'O');

        assertTrue(gb.checkHorizontalWin(new BoardPosition(2, 1), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because the last item placed is on the left side of consecutive characters of the same team AND there are more characters to the left that are not the same character
    @Test
    public void testcheckHorizontalWin_Left_O_True(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][3] = 'X';
        expected[2][0] = 'O';
        expected[0][1] = 'O';
        expected[0][2] = 'O';
        expected[0][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        gb.placeMarker(new BoardPosition(0,3), 'O');

        assertTrue(gb.checkHorizontalWin(new BoardPosition(0, 1), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because the last item placed is not in the middle of consecutive characters
    // of the same team (not a win) AND there are characters to the left of the last placed character
    // that are not the same character
    @Test
    public void testcheckHorizontalWin_alone_X_false(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][3] = 'X';
        expected[1][2] = 'X';
        expected[2][0] = 'O';
        expected[0][1] = 'O';
        expected[1][0] = 'O';
        expected[1][1] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'O');

        assertTrue(!gb.checkHorizontalWin(new BoardPosition(1, 2), 'X') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because the last item placed is on the right side of consecutive characters of the same team AND is on the column bound of the board
    @Test
    public void testcheckHorizontalWin_right_O_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][3] = 'X';
        expected[2][0] = 'O';
        expected[0][1] = 'O';
        expected[0][2] = 'O';
        expected[0][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        gb.placeMarker(new BoardPosition(0,3), 'O');

        assertTrue(gb.checkHorizontalWin(new BoardPosition(0, 3), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests that there is a vertical win when the last character was placed between the same character.
    @Test
    public void testcheckVerticalWin_mid_O_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][3] = 'X';
        expected[0][1] = 'O';
        expected[1][1] = 'O';
        expected[2][1] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'O');

        assertTrue(gb.checkVerticalWin(new BoardPosition(1, 1), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests that there is a vertical win when the last character was placed at the bottom of consecutive characters, being the end of the winning line.
    @Test
    public void testcheckVerticalWin_bottom_O_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][3] = 'X';
        expected[0][1] = 'O';
        expected[1][1] = 'O';
        expected[2][1] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'O');

        assertTrue(gb.checkVerticalWin(new BoardPosition(2, 1), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because the last item placed is not in the middle of consecutive characters
    // of the same team (not a win) AND there are characters above and below the last placed character
    // that are not the same character
    @Test
    public void testcheckVerticalWin_X_false(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][3] = 'X';
        expected[0][1] = 'O';
        expected[1][1] = 'O';
        expected[2][1] = 'X';
        expected[3][1] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(3,1), 'O');

        assertTrue(!gb.checkVerticalWin(new BoardPosition(2, 1), 'X') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests that there is a vertical win when the last character
    // was placed at the top of consecutive characters, being the end of the winning line.
    @Test
    public void testcheckVerticalWin_top_O_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[2][3] = 'X';
        expected[0][1] = 'O';
        expected[1][1] = 'O';
        expected[2][1] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'O');

        assertTrue(gb.checkVerticalWin(new BoardPosition(0, 1), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests that there is a diagonal win when the last character was
    // placed between the same characters on a top left to bottom right diagonal
    @Test
    public void testcheckDiagonalWin_leftdiag_mid_X_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[1][1] = 'X';
        expected[2][2] = 'X';
        expected[2][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'O');

        assertTrue(gb.checkDiagonalWin(new BoardPosition(1, 1), 'X') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests that there is a diagonal win when the last character
    // was placed at the bottom right of the winning line, with the line being on only one side of the characters
    @Test
    public void testcheckDiagonalWin_leftdiag_bottomRight_X_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[1][1] = 'X';
        expected[2][2] = 'X';
        expected[2][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'O');

        assertTrue(gb.checkDiagonalWin(new BoardPosition(2, 2), 'X') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests that there is a diagonal win when the last character
    // was placed at the top left of the winning line, with the line being on only one side of the characters
    @Test
    public void testcheckDiagonalWin_leftdiag_topLeft_X_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[3][3] = 'X';
        expected[1][1] = 'X';
        expected[2][2] = 'X';
        expected[2][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(3,3), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'O');

        assertTrue(gb.checkDiagonalWin(new BoardPosition(1, 1), 'X') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests that there is not a diagonal win when
    // the last character was placed between a different character and a space
    @Test
    public void testcheckDiagonalWin_X_false(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[1][1] = 'X';
        expected[2][2] = 'O';
        expected[2][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        gb.placeMarker(new BoardPosition(2,3), 'O');

        assertTrue(!gb.checkDiagonalWin(new BoardPosition(1, 1), 'X') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests that there is a diagonal win when
    // the last character was placed between the same characters on a bottom left to top right diagonal.
    @Test
    public void testcheckDiagonalWin_rightdiag_mid_X_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[0][3] = 'X';
        expected[1][2] = 'X';
        expected[2][1] = 'X';
        expected[2][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,3), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'O');

        assertTrue(gb.checkDiagonalWin(new BoardPosition(1, 2), 'X') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests that there is a diagonal win when the last character
    // was placed at the top right of the winning line, with the line being on only one side of the characters
    @Test
    public void testcheckDiagonalWin_rightdiag_topRight_X_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[3][0] = 'X';
        expected[1][2] = 'X';
        expected[2][1] = 'X';
        expected[2][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'O');

        assertTrue(gb.checkDiagonalWin(new BoardPosition(1, 2), 'X') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests that there is a diagonal win when the last character
    // was placed at the bottom left of the winning line, with the line being on only one side of the characters
    @Test
    public void testcheckDiagonalWin_rightdiag_bottomLeft_X_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                expected[r][c] = ' ';
            }
        }

        expected[3][0] = 'X';
        expected[1][2] = 'X';
        expected[2][1] = 'X';
        expected[2][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'O');

        assertTrue(gb.checkDiagonalWin(new BoardPosition(2, 1), 'X') && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests for a draw on a completely empty board
    @Test
    public void testcheckForDraw_empty_false(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        IGameBoard gb = makeGameBoard(4, 4, 3);

        assertTrue(!gb.checkForDraw() && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests for a draw on a completely full board where there is an actual draw
    @Test
    public void testcheckForDraw_full_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][1] = 'P';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';
        expected[3][0] = 'O';
        expected[3][1] = 'X';
        expected[3][2] = 'O';
        expected[3][3] = 'X';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'P');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(3,3), 'X');

        assertTrue(gb.checkForDraw() && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests for a draw on a board that is full except for the max column,
    // in order to test that the column size is being calculated correctly
    @Test
    public void testcheckForDraw_rightEmpty_false(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[1][0] = 'O';
        expected[1][1] = 'P';
        expected[1][2] = 'Z';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[3][0] = 'O';
        expected[3][1] = 'X';
        expected[3][2] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'P');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');

        assertTrue(!gb.checkForDraw() && boardToString(expected).equals(gb.toString()));
    }

    // This test is unique because it tests for a draw on a board that is full except for the max row,
    // in order to test that the row size is being calculated correctly
    @Test
    public void testcheckForDraw_bottomEmpty_false(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][1] = 'P';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'P');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');

        assertTrue(!gb.checkForDraw() && boardToString(expected).equals(gb.toString()));
    }

    // This tests that whatsAtPos processes the max number of rows correctly
    @Test
    public void testwhatsAtPos_bottomLeft(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][1] = 'P';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';
        expected[3][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'P');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'O');

        assertTrue(gb.whatsAtPos(new BoardPosition(3,0)) == 'O' && boardToString(expected).equals(gb.toString()));
    }

    // This tests that whatsAtPos processes the max number of columns correctly
    @Test
    public void testwhatsAtPos_topRight(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][1] = 'P';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'P');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');

        assertTrue(gb.whatsAtPos(new BoardPosition(0, 3)) == 'O' && boardToString(expected).equals(gb.toString()));
    }

    // This tests that whatsAtPos returns what’s at a basic location in the middle of the board
    @Test
    public void testwhatsAtPos_basic(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';
        expected[3][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'O');

        assertTrue(gb.whatsAtPos(new BoardPosition(2,2)) == 'Z' && boardToString(expected).equals(gb.toString()));
    }

    // This tests that whatsAtPos returns a space if the space that’s being checked is empty.
    // This is important especially for GameBoardMem because spaces are not saved in memory
    @Test
    public void testwhatsAtPos_space(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';
        expected[3][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'O');

        assertTrue(gb.whatsAtPos(new BoardPosition(1,1)) == ' ' && boardToString(expected).equals(gb.toString()));
    }

    // This tests that whatsAtPos returns the correct character when the character being checked
    // was the last one added to the board. This is unique especially for GameBoardMem when
    // all the characters are being stored in a map.
    @Test
    public void testwhatsAtPos_lastcharPlaced(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[1][0] = 'O';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][1] = 'P';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');

        assertTrue(gb.whatsAtPos(new BoardPosition(2,1)) == 'P' && boardToString(expected).equals(gb.toString()));
    }

    // This tests that isPlayerAtPos correctly returns that the specified character is at pos.
    @Test
    public void testisPlayerAtPos_basic_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';
        expected[3][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'O');

        assertTrue(gb.isPlayerAtPos(new BoardPosition(0,1), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This tests that isPlayerAtPos correctly returns that the specified character
    // is not at pos when pos is empty.
    @Test
    public void testisPlayerAtPos_empty_false(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';
        expected[3][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'O');

        assertTrue(!gb.isPlayerAtPos(new BoardPosition(1,1), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This tests that isPlayerAtPos correctly returns that the specified character
    // is not at pos when pos is occupied by another character.
    @Test
    public void testisPlayerAtPos_occupied_false(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';
        expected[3][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'O');

        assertTrue(!gb.isPlayerAtPos(new BoardPosition(1,2), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This tests that isPlayerAtPos correctly returns that the specified character is at pos when pos is at the max row
    @Test
    public void testisPlayerAtPos_bottomLeft_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';
        expected[3][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'O');

        assertTrue(gb.isPlayerAtPos(new BoardPosition(3,0), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This tests that isPlayerAtPos correctly returns that the specified character is at pos when pos is at the max column
    @Test
    public void testisPlayerAtPos_topRight_true(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'O';
        expected[1][0] = 'O';
        expected[1][2] = 'Z';
        expected[1][3] = 'X';
        expected[2][0] = 'X';
        expected[2][1] = 'P';
        expected[2][2] = 'Z';
        expected[2][3] = 'O';
        expected[3][0] = 'O';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'P');
        gb.placeMarker(new BoardPosition(2,2), 'Z');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'O');

        assertTrue(gb.isPlayerAtPos(new BoardPosition(0,3), 'O') && boardToString(expected).equals(gb.toString()));
    }

    // This tests that, in general, place marker puts the character player at the location on the board, marker
    @Test
    public void testplaceMarker_basic(){
        char expected[][] = new char[3][3];
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'O';

        IGameBoard gb = makeGameBoard(3, 3, 3);

        gb.placeMarker(new BoardPosition(0,0), 'O');

        assertEquals(boardToString(expected), gb.toString());
    }

    // This tests that placeMarker will put the character player on the max row
    @Test
    public void testplaceMarker_bottomLeft(){
        char expected[][] = new char[3][3];
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                expected[r][c] = ' ';
            }
        }

        expected[2][0] = 'O';

        IGameBoard gb = makeGameBoard(3, 3, 3);

        gb.placeMarker(new BoardPosition(2,0), 'O');

        assertEquals(boardToString(expected), gb.toString());
    }

    // This test is unique because it adds a brand new character to the board
    @Test
    public void testplaceMarker_newChar(){
        char expected[][] = new char[3][3];
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'Z';
        expected[0][2] = 'O';
        expected[1][1] = 'S';

        IGameBoard gb = makeGameBoard(3, 3, 3);

        gb.placeMarker(new BoardPosition(0,0), 'Z');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'S');

        assertEquals(boardToString(expected), gb.toString());
    }

    // This tests that placeMarker will put the character player on the max column
    @Test
    public void testplaceMarker_topRight(){
        char expected[][] = new char[3][3];
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][2] = 'O';

        IGameBoard gb = makeGameBoard(3, 3, 3);

        gb.placeMarker(new BoardPosition(0,2), 'O');

        assertEquals(boardToString(expected), gb.toString());
    }

    // This test is unique because it tests that the board can hold different characters in every space
    @Test
    public void testplaceMarker_diffChar_full(){
        char expected[][] = new char[4][4];
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                expected[r][c] = ' ';
            }
        }

        expected[0][0] = 'R';
        expected[0][1] = 'O';
        expected[0][2] = 'X';
        expected[0][3] = 'S';
        expected[1][0] = 'V';
        expected[1][1] = 'T';
        expected[1][2] = 'Z';
        expected[1][3] = 'L';
        expected[2][0] = 'Y';
        expected[2][1] = 'K';
        expected[2][2] = 'U';
        expected[2][3] = 'W';
        expected[3][0] = 'M';
        expected[3][1] = 'N';
        expected[3][2] = 'Q';
        expected[3][3] = 'G';

        IGameBoard gb = makeGameBoard(4, 4, 3);

        gb.placeMarker(new BoardPosition(0,0), 'R');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'S');
        gb.placeMarker(new BoardPosition(1,0), 'V');
        gb.placeMarker(new BoardPosition(1,1), 'T');
        gb.placeMarker(new BoardPosition(1,2), 'Z');
        gb.placeMarker(new BoardPosition(1,3), 'L');
        gb.placeMarker(new BoardPosition(2,1), 'K');
        gb.placeMarker(new BoardPosition(2,2), 'U');
        gb.placeMarker(new BoardPosition(2,3), 'W');
        gb.placeMarker(new BoardPosition(3,0), 'M');
        gb.placeMarker(new BoardPosition(3,1), 'N');
        gb.placeMarker(new BoardPosition(3,2), 'Q');
        gb.placeMarker(new BoardPosition(3,3), 'G');

        gb.placeMarker(new BoardPosition(2,0), 'Y');

        assertEquals(boardToString(expected), gb.toString());
    }
}
