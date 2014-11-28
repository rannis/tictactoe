Feature: Preventing a Computer from losing a game of TickTacToe. Explore strategies.

In order for the computer to never lose
As a developer, I must define a number of strategies 
that guarantees a computer from losing regardless of the opponent's moves.

Acceptance Criteria: Given that a human player starts the game (to give him the best chance of winning),
the computer must be able to respond strategically to avoid losing.   

Note: Player 1 is always plays with crosses and player 2 plays with noughts.

Scenario: Human player starts the game by placing the first cross on one of the boxes following the diamond pattern:
	Example 1: human player enters a cross on top middle box for the the first play
				1.1 Computer places 'O' in the:
					1.1.1 centre box, or
					1.1.2 next to 'X' or
					1.1.3 opposite to 'X'
			1.2 Human player takes their turn
			1.3 Computer places 'O':
				1.3.1 Block 'X', or
				1.3.2 in the centre, or
				1.3.3 next to 'X' or
				1.3.4 opposite to 'X' 
			1.4 Human player takes their turn
			1.5 Computer places 'O':
				1.5.1 an block 'X', or
				1.5.2 Go for a win.
				1.5.3 in the centre, or
				1.5.4 next to 'X' or
				1.5.5 opposite to 'X'
		
Scenario: Human player starts the game by placing the first cross on one of the corner boxes
			2.1 'O' is placed in the centre box
			2.2	
			2.3 'O' second play is placed on one of the boxes on the diamond pattern that must force
 			Human to block noughts so that nought does not lose.
			
Scenario: Human starts the game placing the first cross in the centre box
			3.1 Nought is placed in one of the corner boxes
			3.2 Cross places diagonally opposite nought
			3.3 Nought must be play in one of the other corner boxes.
			
Scenario: Computer starts the game placing the first cross in one of the corner boxes