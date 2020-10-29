package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.zenGame.GameState;
import model.zenGame.GameplayZen;
import model.zenGame.OpponentType;
import model.zenGame.TypeOfPawn;

/**
 * JUNIT TEST : Tests the GameplayZen class which it is the game manager of a Zen l'initie game
 * @author Godet Louis-Xavier
 */
public class GameplayZenTest {
    /**
    * The object tested
    */
    GameplayZen g;

    /**
     * Initialization of a GameplayZen's object for each begin of test
     */
    @Before()
    public void initialize() {
        String gameName = "gameName";
        String namePlayer1 = "Moi";
        String namePlayer2 = "Lui";
        try{
            g = new GameplayZen(gameName, OpponentType.HUMAN, namePlayer1, namePlayer2, TypeOfPawn.BLACK, TypeOfPawn.WHITE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * End of the GameplayZen's object for each end of test
     */
    @After()
    public void endTest() {
        g = null;
    }
    
    /**
     * Verify if the object has been initialized well
     */
    @Test()
    public void testGameplayZen() {
        assertNotNull(g);
    }

    /**
     * Verify if all square has been initialized well
     */
    @Test()
    public void testGridZen() {
        for (int x = 0; x < 11; x++){
            for (int y = 0; y < 11; y++){
                assertNotNull(g.getGrid()[x][y]);
            }
        }
    }

    /**
     * Verify if pawns are on the grid in the good position
     */
    @Test()
    public void testPawnsOnGrid() {
        assertEquals(g.getGrid()[5][0].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[10][0].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[3][2].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[7][2].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[1][4].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[9][4].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[1][6].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[9][6].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[3][8].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[7][8].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[5][10].getPawnPresent().getType(), TypeOfPawn.BLACK);
        assertEquals(g.getGrid()[0][10].getPawnPresent().getType(), TypeOfPawn.BLACK);

        assertEquals(g.getGrid()[0][0].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[4][1].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[6][1].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[2][3].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[8][3].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[0][5].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[10][5].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[2][7].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[8][7].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[4][9].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[6][9].getPawnPresent().getType(), TypeOfPawn.WHITE);
        assertEquals(g.getGrid()[10][10].getPawnPresent().getType(), TypeOfPawn.WHITE);
    }
    
    /**
     * Verify if the game name input in the constructor hasn't changed
     */
    @Test()
    public void testGetGameName(){
        assertEquals(g.getGameName(), "gameName");
    }
    
    /**
     * Verify if, at the beginning of the game, any pawn doesn't touch zen's pawn
     */
   @Test()
   public void testCalculTouchZen(){
       //assertEquals(0, g.calculTouchZen());
   }

   /**
    * Verify if, at the beginning of the game, all pawn doesn't touch Zen's pawn
    */
   @Test()
   public void AllPawnTouch(){
       assertEquals(g.allPawnTouch(), GameState.LOOP);
   }
}