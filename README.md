# MaxConnect4
A simple game developed as part of algorithms and data structures project

Introduction:
•	Run MaxConnect.java from command prompt.
•	This program uses a search algorithm called the Minimax algorithm where the search considers each possible action available to it at a given moment; it then considers its subsequent moves from each of those states, and so on, in an attempt to find terminal states which satisfy the goal conditions it was given. Upon finding a goal state, it then follows the steps it knows are necessary to achieve that state.
•	The program is designed such that two player's can play the game in which the mini max algorithm uses the fact that the two players are 	working towards opposite goals to make predictions about which future 	states will be reached as the game progresses, and then proceeds accordingly to optimize its chance of victory. The theory behind minimax is that the algorithm's opponent will be trying to minimize whatever value the algorithm is trying to maximize.
•	The program MaxConnect.java displays a board of size 6*7(row-column).It asks initially to chose the player option. On choosing the player, the player can input a number between 1 and 7. Once the player inputs the column number, the number is stored at the bottom of the specified column respectively. The next player plays the next turn similarly.
•	Scores are updated once a consecutive set of similar numbers are attained either,
	
		1. Row wise
		2. Column wise or
		3. Diagonally
		
Also when each and every time a consecutive set is attained by a player, that player attains "One point". By this way, on playing the entire board score are updated sequentially and the result of the game is thrown at the end. The entire result set is captured in a file.
	
Modules Description:

1. Fetch Input:Input is read using the scanner class.
	
2. Place disk:The number got from the user is placed onto the board based on the field position, column number and player. For Example, Player 1 enters the position of the number to be placed in the board, say 3, then "1" will be placed in the bottom of the 3rd column in the board or on top of the last row in the same column until and unless there is an empty space.
 	
3. Who Wins:Winning is determined by the score points attained by each player. In this case, it is determined based on the player where there are four consecutive similar numbers.
 	
4. Is Consecutive Four:This module determines the similar numbers which are of the same number either row or column or diagonally and increments the score for each player when it finds the pattern.

File Breakdown:
Java File 		: MaxConnect.java 
                ConnectFour.java				  
	       			  HumanPlayer.java
				        SimpleBoard.java
Output File		: Result.txt

Requirements:
Platform 		: Java
Compiler 		: Java Compiler
Java Version: 1.8.0_25

What the program does well:

1. Player selection is made interactive.
2. Score computing is done precisely based on the moves made by each player for each consecutive set of numbers either row, column wise or diagonally.
3. Score reporting into a text file is working perfectly.
	
Data structure design:

1. Read input entered by the player using scanner class.
2. Place the input in the specified column on the board.
3. Update the score for every point scored by the player when a consecutive set of same numbers for a player is detected either in a row or column or diagonally.
4. Once the entire board is played, finally the winner of the game is printed based on the score points.
5. The entire step is stored into a file.

