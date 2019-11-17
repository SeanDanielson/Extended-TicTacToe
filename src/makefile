default: cpsc2150/extendedTicTacToe/GameScreen.java cpsc2150/extendedTicTacToe/GameBoard.java cpsc2150/extendedTicTacToe/BoardPosition.java cpsc2150/extendedTicTacToe/GameBoardMem.java
	javac cpsc2150/extendedTicTacToe/GameScreen.java cpsc2150/extendedTicTacToe/GameBoard.java cpsc2150/extendedTicTacToe/BoardPosition.java cpsc2150/extendedTicTacToe/GameBoardMem.java

test: cpsc2150/extendedTicTacToe/GameScreen.java cpsc2150/extendedTicTacToe/GameBoard.java cpsc2150/extendedTicTacToe/BoardPosition.java cpsc2150/extendedTicTacToe/GameBoardMem.java cpsc2150/extendedTicTacToe/TestGameBoardMem.java cpsc2150/extendedTicTacToe/TestGameBoard.java
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/extendedTicTacToe/GameScreen.java cpsc2150/extendedTicTacToe/GameBoard.java cpsc2150/extendedTicTacToe/BoardPosition.java cpsc2150/extendedTicTacToe/GameBoardMem.java cpsc2150/extendedTicTacToe/TestGameBoardMem.java cpsc2150/extendedTicTacToe/TestGameBoard.java

run: cpsc2150/extendedTicTacToe/GameScreen.class cpsc2150/extendedTicTacToe/GameBoard.class cpsc2150/extendedTicTacToe/BoardPosition.class
	java cpsc2150.extendedTicTacToe.GameScreen

testGB: cpsc2150/extendedTicTacToe/GameScreen.class cpsc2150/extendedTicTacToe/GameBoard.class cpsc2150/extendedTicTacToe/BoardPosition.class cpsc2150/extendedTicTacToe/GameBoardMem.class cpsc2150/extendedTicTacToe/TestGameBoardMem.class cpsc2150/extendedTicTacToe/TestGameBoard.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedTicTacToe.TestGameBoard

testGBmem: cpsc2150/extendedTicTacToe/GameScreen.class cpsc2150/extendedTicTacToe/GameBoard.class cpsc2150/extendedTicTacToe/BoardPosition.class cpsc2150/extendedTicTacToe/GameBoardMem.class cpsc2150/extendedTicTacToe/TestGameBoardMem.class cpsc2150/extendedTicTacToe/TestGameBoard.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedTicTacToe.TestGameBoardMem

clean:
	rm cpsc2150/extendedTicTacToe/GameScreen.class cpsc2150/extendedTicTacToe/GameBoard.class cpsc2150/extendedTicTacToe/BoardPosition.class cpsc2150/extendedTicTacToe/AbsGameBoard.class cpsc2150/extendedTicTacToe/IGameBoard.class cpsc2150/extendedTicTacToe/GameBoardMem.class cpsc2150/extendedTicTacToe/TestGameBoardMem.class cpsc2150/extendedTicTacToe/TestGameBoard.class
