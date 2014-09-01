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
 *  Triangle win pattern: 1,5,7 -> 3,4 free; 1,5,3-> 7,2 free; 3,5,9 -> 1,6 free; 7,5,9 -> 3,8 free.
 *  The L win pattern: 1,2,4 -> 3,7 free; 2,3,6 -> 1,9 free; 6,9,8 -> 3,7 free; 4,7,8 -> 1,9 free.
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
    private static String board = " ___ ___ ___\n| " +gameBoardBoxNumber[0]+" | " +gameBoardBoxNumber[1]+" | " +gameBoardBoxNumber[2]+" |\n|___|___|___|\n| "+gameBoardBoxNumber[3]+" | "+gameBoardBoxNumber[4]+" | "+gameBoardBoxNumber[5]+" |\n|___|___|___|\n| "+gameBoardBoxNumber[6]+" | "+gameBoardBoxNumber[7]+" | "+gameBoardBoxNumber[8]+" |\n|___|___|___|";
    private static String promptToPlay = "Choose a box number: ";
    
//    private Map<Integer,String> gameBoard = new HashMap<Integer,String>();
    
    @Test
    public void assertBox1IsEmpty() {
        assertEquals(gameBoardBoxNumber[0], "1");
    }
    
    @Test
    public void aPlayerEntersACrossInAnEmptyBox1() {
        gameBoardBoxNumber[0] = "x";
        assertEquals(gameBoardBoxNumber[0], "x");
    }
    
//    @Test
//    public void player1StartsGameByEnteringACrossAndPlayer2IsPromptedToEnterNought() {
//    	String piece = "";
//    	gameBoard[8] = "x";
//    	for(int play = 0 ;  play < gameBoard.length; play++) {
//        	piece = play % 2 > 0 ? "o" : "x";
//    	}
//    	assertEquals("o", piece);
//    }
//    
//    private void round(int i, String aBox, String anotherBox) {
//    	for(int x = 0; x < i ; i++) {
//
//    	}
//	}
    
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

    private void addToBoxToWin(String boxNumber, String piece) {
        addToBox(boxNumber, piece);
        String message = isAWin(piece) ? "Stop. YOU HAVE WON" : "NO WIN";
        System.out.println(message);
        message = aTrianglePatternWin(piece) ? "YOU HAVE WON WITH TRIANGULAR STRATEGY" : "NOT A TRIANGULAR WINS";
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
    
    private boolean aTrianglePatternWin(String piece) {
        if(gameBoardBoxNumber[3-1].equals("x") &&
           gameBoardBoxNumber[5-1].equals("x") &&
           gameBoardBoxNumber[9-1].equals("x") &&
           gameBoardBoxNumber[1-1].equals("1") &&
           gameBoardBoxNumber[6-1].equals("6")) {
            System.out.println(
                    "Box1: " + gameBoardBoxNumber[0]+ " Box2: " + gameBoardBoxNumber[1] + " Box3: " + gameBoardBoxNumber[2]+
                    " Box4: " + gameBoardBoxNumber[3] + " Box5: " + gameBoardBoxNumber[4]+ " Box6: " + gameBoardBoxNumber[5] +
                    " Box7: " + gameBoardBoxNumber[6]+ " Box8: " + gameBoardBoxNumber[7] + " Box9: " + gameBoardBoxNumber[8] );
            return true;
        }
            
        return false;
    }

    private void addToBox(String boxNumber, String piece) {
        if(canAddTo(boxNumber)) {
            gameBoardBoxNumber[Integer.valueOf(boxNumber)-1] = piece;
        } else {
            throw new RuntimeException(String.format("Box %s occupied", boxNumber));
        }
    }
    
    private boolean canAddTo(String boxNumber) {
        Integer arrayPosition = Integer.valueOf(boxNumber);
        return  arrayPosition > 0 && gameBoardBoxNumber[arrayPosition-1].equals(boxNumber) ? true : false;
    }
    
    public static void main(String[] args) {
    	Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        String player1 = c.readLine("Select (C)omputer or (H)uman to start the game: ").equalsIgnoreCase("C") ? "Computer" : "Human";
        updateBoard(c, player1);
    }
    
    private static void updateBoard(Console c, String player) {
        String prompt = board + "\n" + player + "(x). " + promptToPlay;
        String boxNumber = c.readLine(prompt);
		board = board.replace(gameBoardBoxNumber[Integer.valueOf(boxNumber) - 1], "x");
    	for(int play = 1 ;  play < gameBoardBoxNumber.length; play++) {
    		String piece = play % 2 > 0 ? "o" : "x";
    		player = opponentOf(player);
    		boxNumber = c.readLine(board + "\n" + player + " (" + piece +"). Chose box number: ");
    		board = board.replace(gameBoardBoxNumber[Integer.valueOf(boxNumber) - 1], piece);
    	}
	}

	private static String opponentOf(String player) {
		return player.equalsIgnoreCase("Computer") ? "Human" : "Computer";
	}
}
