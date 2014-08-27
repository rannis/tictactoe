import static org.junit.Assert.assertEquals;

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
 * @author annis
 *
 */

public class GameTest {
    
    private static final boolean WIN = true;
    private String [] gameBoard = {"1","2","3","4","5","6","7","8","9"};
    
    @Test
    public void assertBox1IsEmpty() {
        assertEquals(gameBoard[0], "1");
    }
    
    @Test
    public void aPlayerEntersACrossInAnEmptyBox1() {
        gameBoard[0] = "x";
        assertEquals(gameBoard[0], "x");
    }
    
    @Test(expected=RuntimeException.class)
    public void aPlayerEntersACrossInANonEmptyBox1() {
        addToBoxToWin("5", "x");
        addToBoxToWin("5", "o");
    }
    
    @Test
    public void computerStartsWithCentreBoxCrossedAndHumanPlayerChoosesAMiddleBox2GivingComputerAWin() {
        addToBoxToWin("5", "x");
        addToBoxToWin("2", "o");
        addToBoxToWin("3", "x");
        addToBoxToWin("7", "o");
        addToBoxToWin("9", "x");
        addToBoxToWin("1", "o");
        addToBoxToWin("6", "x");
        
        assertEquals(gameBoard[3-1]+gameBoard[6-1]+gameBoard[9-1], "xxx");
    }
    
    private void addToBoxToWin(String boxNumber, String noughtOrCross) {
        addToBox(boxNumber, noughtOrCross);
        String message = isAWin(noughtOrCross) ? "Stop. YOU HAVE WON" : "NO WIN";
        System.out.println(message);
        message = aTrianglePatternWin(noughtOrCross) ? "YOU HAVE WON WITH TRIANGULAR STRATEGY" : "NOT A TRIANGULAR WINS";
        System.out.println(message);
    }

    private boolean isAWin(String noughtOrCross) {
        String threeNoughtsOrCrosses = noughtOrCross + noughtOrCross + noughtOrCross;
        
        String row1 = gameBoard[1-1] + gameBoard[2-1] + gameBoard[3-1];
        String row2 = gameBoard[4-1] + gameBoard[5-1] + gameBoard[6-1];
        String row3 = gameBoard[7-1] + gameBoard[8-1] + gameBoard[9-1];
        
        String column1 = gameBoard[1-1] + gameBoard[4-1] + gameBoard[7-1];
        String column2 = gameBoard[2-1] + gameBoard[5-1] + gameBoard[8-1];
        String column3 = gameBoard[3-1] + gameBoard[6-1] + gameBoard[9-1];
        
        String diagonal1 = gameBoard[1-1] + gameBoard[5-1] + gameBoard[9-1];
        String diagonal2 = gameBoard[3-1] + gameBoard[5-1] + gameBoard[7-1];
        
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
    
    private boolean aTrianglePatternWin(String noughtOrCross) {
        if(gameBoard[3-1].equals("x") &&
           gameBoard[5-1].equals("x") &&
           gameBoard[9-1].equals("x") &&
           gameBoard[1-1].equals("1") &&
           gameBoard[6-1].equals("6")) {
            System.out.println(
                    "Box1: " + gameBoard[0]+ " Box2: " + gameBoard[1] + " Box3: " + gameBoard[2]+
                    " Box4: " + gameBoard[3] + " Box5: " + gameBoard[4]+ " Box6: " + gameBoard[5] +
                    " Box7: " + gameBoard[6]+ " Box8: " + gameBoard[7] + " Box9: " + gameBoard[8] );
            return true;
        }
            
        return false;
    }

    private void addToBox(String boxNumber, String noughtOrCross) {
        if(canAddTo(boxNumber)) {
            gameBoard[Integer.valueOf(boxNumber)-1] = noughtOrCross;
        } else {
            throw new RuntimeException(String.format("Box %s occupied", boxNumber));
        }
    }
    
    private boolean canAddTo(String boxNumber) {
        Integer arrayPosition = Integer.valueOf(boxNumber);
        return  gameBoard[arrayPosition-1].equals(boxNumber) ? true : false;
    }
}
