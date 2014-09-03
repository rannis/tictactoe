import static org.junit.Assert.assertEquals;

import java.io.Console;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * PLEASE NOTE THAT THIS SECTION IS A BRAIN DUMB (INITIAL THOUGHTS).
 * 
 * Numbered boxes of a 3 by 3 Tic-tac-toe game board
 *   ___ ___ ___
 *  | 1 | 2 | 3 |
 *  |___|___|___|
 *  | 4 | 5 | 6 |
 *  |___|___|___|
 *  | 7 | 8 | 9 |
 *  |___|___|___|
 *  
 *  Terms:
 *  Centre: box 5
 *  Middle: boxes 2,4,8,6
 *  Corner: 1,3,9,7
 *  
 *  The game always starts with a player1 entering a cross in one of the boxes.
 *  Player1 can either be a human player or a computer player.
 *  
 *  At the start of the game the human player chooses to either start the game as player1 or player2.
 *  
 * @author annis
 *
 */


public class GameTest {
    
    private static final boolean WIN = true;
    private static String [] gameBoardBoxNumber = {"1","2","3","4","5","6","7","8","9"};
    private static String promptToPlay = "Enter box number: ";
    
    @Test
    public void assertBox1IsEmpty() {
        assertEquals(gameBoardBoxNumber[0], "1");
    }
    
    @Test
    public void aPlayerEntersACrossInAnEmptyBox1() {
        gameBoardBoxNumber[0] = "x";
        assertEquals(gameBoardBoxNumber[0], "x");
    }
    
	@Test(expected=RuntimeException.class)
    public void aPlayerEntersACrossInANonEmptyBox1() {
    	addToBox("5", "x");
    	addToBox("5", "o");
    }
    
    @Test
    public void computerCrossesCentreBoxAtStartOftheGameAndOpponentMakesABadMoveOnBox2GivingComputerAWin() {
        addToBoxToWin("5", "x");
        addToBoxToWin("2", "o");
        addToBoxToWin("3", "x");
        addToBoxToWin("7", "o");
        addToBoxToWin("9", "x");
        addToBoxToWin("1", "o");
        addToBoxToWin("6", "x");
        
        assertEquals(gameBoardBoxNumber[3-1]+gameBoardBoxNumber[6-1]+gameBoardBoxNumber[9-1], "xxx");
    }
    
    @Test
    public void blockAPotentialWin() {
        addToBoxToWin("1", "o");
        addToBoxToWin("3", "o");
        
        blockAPotentialWinWithThis("x");
        
        assertEquals(gameBoardBoxNumber[1-1]+gameBoardBoxNumber[2-1]+gameBoardBoxNumber[3-1], "oxo");
    }

	private void blockAPotentialWinWithThis(String piece) {
        String c = opponentOfThis(piece);
        
        // Rows
        if((gameBoardBoxNumber[1-1] + gameBoardBoxNumber[2-1] + gameBoardBoxNumber[3-1]).equals('1'+ c + c)) gameBoardBoxNumber[1-1] = piece;
        if((gameBoardBoxNumber[1-1] + gameBoardBoxNumber[2-1] + gameBoardBoxNumber[3-1]).equals(c + '2' + c)) gameBoardBoxNumber[2-1] = piece;
        if((gameBoardBoxNumber[1-1] + gameBoardBoxNumber[2-1] + gameBoardBoxNumber[3-1]).equals(c + c+ '3')) gameBoardBoxNumber[3-1] = piece;
        
        if((gameBoardBoxNumber[4-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[6-1]).equals('4'+ c + c)) gameBoardBoxNumber[4-1] = piece;
        if((gameBoardBoxNumber[4-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[6-1]).equals(c + '5' + c)) gameBoardBoxNumber[5-1] = piece;
        if((gameBoardBoxNumber[4-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[6-1]).equals(c + c+ '6')) gameBoardBoxNumber[6-1] = piece;
        
        if((gameBoardBoxNumber[7-1] + gameBoardBoxNumber[8-1] + gameBoardBoxNumber[9-1]).equals('7'+ c + c)) gameBoardBoxNumber[7-1] = piece;
        if((gameBoardBoxNumber[7-1] + gameBoardBoxNumber[8-1] + gameBoardBoxNumber[9-1]).equals(c + '8' + c)) gameBoardBoxNumber[8-1] = piece;
        if((gameBoardBoxNumber[7-1] + gameBoardBoxNumber[8-1] + gameBoardBoxNumber[9-1]).equals(c + c+ '9')) gameBoardBoxNumber[9-1] = piece;
        
        // Columns
        if((gameBoardBoxNumber[1-1] + gameBoardBoxNumber[4-1] + gameBoardBoxNumber[7-1]).equals('1'+ c + c)) gameBoardBoxNumber[1-1] = piece;
        if((gameBoardBoxNumber[1-1] + gameBoardBoxNumber[4-1] + gameBoardBoxNumber[7-1]).equals(c + '4' + c)) gameBoardBoxNumber[4-1] = piece;
        if((gameBoardBoxNumber[1-1] + gameBoardBoxNumber[4-1] + gameBoardBoxNumber[7-1]).equals(c + c+ '7')) gameBoardBoxNumber[7-1] = piece;
        
        if((gameBoardBoxNumber[2-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[8-1]).equals('2'+ c + c)) gameBoardBoxNumber[2-1] = piece;
        if((gameBoardBoxNumber[2-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[8-1]).equals(c + '5' + c)) gameBoardBoxNumber[5-1] = piece;
        if((gameBoardBoxNumber[2-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[8-1]).equals(c + c+ '8')) gameBoardBoxNumber[8-1] = piece;
        
        if((gameBoardBoxNumber[3-1] + gameBoardBoxNumber[6-1] + gameBoardBoxNumber[9-1]).equals('3'+ c + c)) gameBoardBoxNumber[3-1] = piece;
        if((gameBoardBoxNumber[3-1] + gameBoardBoxNumber[6-1] + gameBoardBoxNumber[9-1]).equals(c + '6' + c)) gameBoardBoxNumber[6-1] = piece;
        if((gameBoardBoxNumber[3-1] + gameBoardBoxNumber[6-1] + gameBoardBoxNumber[9-1]).equals(c + c+ '9')) gameBoardBoxNumber[9-1] = piece;
        
        // Diagonals
        if((gameBoardBoxNumber[1-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[9-1]).equals('1'+ c + c)) gameBoardBoxNumber[1-1] = piece;
        if((gameBoardBoxNumber[1-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[9-1]).equals(c + '5' + c)) gameBoardBoxNumber[5-1] = piece;
        if((gameBoardBoxNumber[1-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[9-1]).equals(c + c+ '9')) gameBoardBoxNumber[9-1] = piece;
        
        if((gameBoardBoxNumber[3-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[7-1]).equals('3'+ c + c)) gameBoardBoxNumber[3-1] = piece;
        if((gameBoardBoxNumber[3-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[7-1]).equals(c + '5' + c)) gameBoardBoxNumber[5-1] = piece;
        if((gameBoardBoxNumber[3-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[7-1]).equals(c + c+ '7')) gameBoardBoxNumber[9-1] = piece;
    }

    private static String opponentOfThis(String piece) {
        return piece == "x" ? "o" : "x";
    }

    private void addToBoxToWin(String aBoxNumber, String piece) {
        addToBox(aBoxNumber, piece);
        String message = isAWin(piece) ? "Stop. YOU HAVE WON" : "NO WIN";
        System.out.println(message);
//        message = aTrianglePatternWin(piece) ? "YOU HAVE WON WITH TRIANGULAR STRATEGY" : "NOT A TRIANGULAR WINS";
        System.out.println(message);
    }

    private boolean isAWin(String piece) {
        String threeNoughtsOrCrosses = piece + piece + piece;
        
        String row1 = gameBoardBoxNumber[1-1] + gameBoardBoxNumber[2-1] + gameBoardBoxNumber[3-1];
        String row2 = gameBoardBoxNumber[4-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[6-1];
        String row3 = gameBoardBoxNumber[7-1] + gameBoardBoxNumber[8-1] + gameBoardBoxNumber[9-1];
        
        String column1 = gameBoardBoxNumber[1-1] + gameBoardBoxNumber[4-1] + gameBoardBoxNumber[7-1];
        String column2 = gameBoardBoxNumber[2-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[8-1];
        String column3 = gameBoardBoxNumber[3-1] + gameBoardBoxNumber[6-1] + gameBoardBoxNumber[9-1];
        
        String diagonal1 = gameBoardBoxNumber[1-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[9-1];
        String diagonal2 = gameBoardBoxNumber[3-1] + gameBoardBoxNumber[5-1] + gameBoardBoxNumber[7-1];
        
        if(row1.equals(threeNoughtsOrCrosses)) return WIN;
        if(row2.equals(threeNoughtsOrCrosses)) return WIN;
        if(row3.equals(threeNoughtsOrCrosses)) return WIN;
        
        if(column1.equals(threeNoughtsOrCrosses)) return WIN;
        if(column2.equals(threeNoughtsOrCrosses)) return WIN;
        if(column3.equals(threeNoughtsOrCrosses)) return WIN;
        
        if(diagonal1.equals(threeNoughtsOrCrosses)) return WIN;
        if(diagonal2.equals(threeNoughtsOrCrosses)) return WIN;
        
        return false;
    }

    private void addToBox(String aBoxNumber, String piece) {
        if(canAddTo(aBoxNumber)) {
            gameBoardBoxNumber[Integer.valueOf(aBoxNumber)-1] = piece;
        } else {
            throw new RuntimeException(String.format("Box %s occupied", aBoxNumber));
        }
    }
    
    private boolean canAddTo(String aBoxNumber) {
        Integer arrayPosition = Integer.valueOf(aBoxNumber);
        return  arrayPosition > 0 && gameBoardBoxNumber[arrayPosition-1].equals(aBoxNumber) ? true : false;
    }
    
    public static void main(String[] args) {
    	Console c = connectToGameConsole();
        play(c, selectPlayerToStartGame(c));
    }

	private static String selectPlayerToStartGame(Console c) {
		String player = c.readLine("Select (C)omputer or (H)uman to start the game: ").equalsIgnoreCase("C") ? "Computer" : "Human";
        String prompt = buildBoardGame() + "\n" + player + " starts with (x). " + promptToPlay;
        String boxNumber = c.readLine(prompt);
        gameBoardBoxNumber[Integer.valueOf(boxNumber) - 1] = "x";
        return player;
	}

	private static Console connectToGameConsole() {
		Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
		return c;
	}
    
    private static void play(Console c, String player) {
    	for(int play = 1 ;  play < gameBoardBoxNumber.length; play++) {
    		String piece = play % 2 > 0 ? "o" : "x";
    		player = opponentOf(player);
    		String boxNumber = c.readLine(buildBoardGame() + "\n" + player + " player is assigned with (" + piece +"). Enter box number: ");
    		gameBoardBoxNumber[Integer.valueOf(boxNumber) - 1] = piece;
    	}
	}
    
    private static String buildBoardGame() {
    	return new String(" ___ ___ ___\n| " +gameBoardBoxNumber[0]+" | " +gameBoardBoxNumber[1]+" | " +gameBoardBoxNumber[2]+" |\n|___|___|___|\n| "+gameBoardBoxNumber[3]+" | "+gameBoardBoxNumber[4]+" | "+gameBoardBoxNumber[5]+" |\n|___|___|___|\n| "+gameBoardBoxNumber[6]+" | "+gameBoardBoxNumber[7]+" | "+gameBoardBoxNumber[8]+" |\n|___|___|___|");
    }

	private static String opponentOf(String player) {
		return player.equalsIgnoreCase("Computer") ? "Human" : "Computer";
	}
}
