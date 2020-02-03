# FKApplyDesign

TIC-TAC-TOE GAME

Input is given in the form of (x,y) coordinates for the game(starting from (1,1) ). There are two players with name "X" and "O". Program alternatively asks the user for input. Player 2 can be computer if the user wishes to play with the computer. Each time user gives input (x,y) ,the board[x-1][y-1] gets marked with the corrresponding symbol ("X" for player1 and "O" for player2) and display the game board. Then ,it will check for the winning status and displays message accordingly or repeat the process.

FUNCTIONALITIES->

1. Human vs Human game
2. Human vs Computer game
3. Normal 3 * 3 or 4 * 4 tictactoe game
4. Enhanced tictactoe game (board size to be multiple of 3 like 9 * 9 and so on)
5. Players can go multiple level in enhanced tictactoe game
6. Super titactoe game with regular hexagonal board 
7. Leaderboard scores

GUIDELINES:

1. Users can play two types of ticTacToe game. One is the normal tictactoe game where user can input the size of the board. Other is the hexagonal tictactoe where user inputs the size of the edge of the hexagon.
2. User has to give input in the form of (x,y) coordinates starting from (1,1) irrespective of the normal tictactoe or hexagonal tictactoe.
3. There is an option available where either 2 users can play the game or the user can play with the computer.
4. In normal tictactoe, if user inputs the size of board in the multiple of 3 like 9 * 9 or  27 * 27 or more, then user should plan the strategy to win by considereing smaller 3 * 3(in case of 9 * 9 ; 9 * 9 in case of 27 * 27 and so on) board and winning them in a row or column or diagonal to win the game.
5. In hexagonal tictactoe, user need 4 consecutive marks in a row or diagonals to win the game.
6. In hexagonal tictactoe, the space marked with "-" cannot be filled. User can fill the coordinates with empty space. The space marked with "-" are just for creating the hexagonal tictactoe.


DESIGN PLAN:

Class Diagram->

![Class Diagram](https://github.com/yashdhingra2198/FKApplyDesign/blob/Dev/screenshots/classDiagram.jpg)

Sequence Diagram->

![Sequence Diagram](https://github.com/yashdhingra2198/FKApplyDesign/blob/Dev/screenshots/sequenceDiagram.png)

Directory Structure for the project->

![Directory Structure](https://github.com/yashdhingra2198/FKApplyDesign/blob/Dev/screenshots/directory%20structure.png)

Further improvements:

Algorithm can be designed for the computer to play the game.

