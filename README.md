# Extended-TicTacToe
Extended TicTacToe game for CPSC 2150 at Clemson University. Created to demonstrate knowledge of object-oriented programming, separation of concerns, inheritance, and testing.

## Functional Requirements
o	As a player, I can easily tell what player’s turn it is so I can play on my turn.
o	As a player, I can see the game board, so I know what is available and what is not.
o	As a player, I can specify whether or not I want to play again so that I can play multiple games.
o	As a player, I understand how the coordinate system works so I can input my moves correctly.
o	As a player, I can pick again if I pick an unavailable space, so I don’t lose my turn
o	As a player, if I get the defined winning number in a row horizontally, I will win the game, so I can win the game
o	As a player, if I get the defined winning number in a row vertically, I will win the game, so I can win the game
o	As a player, if I get the defined winning number in a row diagonally, I will win the game, so I can win the game
o	As a player, I can end the game in a tie by taking the last space on the board without getting the winning number in a row, so the game can end 
o	As a player, I can input the bounds of the board, so that each game is different.
o	As a player, I can input the number required in a row to win.
o	As a player, I can input the number of players in the game so that I can play with multiple players.
o	As a player, I can decide what character to represent myself as on the gameboard so I can be different from other players.
o	As a player, I can decide whether or not I want a faster implementation or a memory efficient implementation so I can make tradeoffs for my games.
o	As a player, I can reenter the rules of the game if I decide to play again, so I can play multiple games with different settings.
o	As a player, I can choose again if input too many or too few players.
o	As a player, I can choose again if the character has been taken.
## Nonfunctional Requirements
o	The system checks for winning moves frequently.
o	The system checks for wins in all directions.
o	The system checks for draws frequently.
o	The system does not have a user interface.
o	The system is direct in its prompts.
o	The system uses the command line for user interaction.
o	The system checks for player character conflicts.
o	The system is developed in Java.
o	The system was created with Intellij IDEA.
o	The system runs on Unix.
o	The system codes to the interface of IGameBoard for ease of game board creation between memory efficient and speed efficient implementations.
o	The system keeps track of whose turn it is.
o	0,0 is the top left of the board
o	The board size cannot exceed 100x100.
o	The board size cannot be lower than 3x3
o	The number to win cannot exceed 25
o	The number to win cannot be lower than 3.
o	The number of players must be between 2 and 10 inclusive.


## How To Run
You must have java 8 installed on your computer before you can run Extended TicTacToe. Download the repository and navigate to the src folder. When you're in the src folder, type the following command:

```bash
make
```

This will compile the source files of the project.

To run it, type the command:

```bash
make run
```
 
