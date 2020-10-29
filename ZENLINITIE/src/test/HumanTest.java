package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.zenGame.TypeOfPawn;
import model.zenGame.player.Human;

/**
 * JUNIT TEST : tests the human's player class
 * @author Godet Louis-Xavier
 */
public class HumanTest {
    /**
    * The object tested
    */
    Human h;

    /**
     * Initialization of an object of Human type for each begin of test
     */
    @Before()
    public void initialize() {
        String playerName = "LouisHuman";
        TypeOfPawn typePawn =  TypeOfPawn.BLACK;
        try{
            h = new Human(playerName, typePawn);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * End of the Human's object for each end of test
     */
    @After()
    public void endTest() {
        h = null;
    }
    
    /**
     * Verify if the object has been initialized well
     */
    @Test()
    public void testHuman() {
        assertNotNull(h);
    }
    
    /**
     * Verify that the name of the player input in the constructor hasn't changed
     */
    @Test()
    public void testGetPlayerName(){
        assertEquals("LouisHuman", h.getPlayerName());
    }

    /**
     * Verify that type of the pawn input in the constructor hasn't changed
     */
    @Test()
    public void testGetPawnType(){
        assertEquals(TypeOfPawn.BLACK, h.getPawnType());
        //Checks if the player don't have a zen pawn
        assertNotEquals(TypeOfPawn.ZEN, h.getPawnType());
    }

    /**
     * Verify that the method updateNbPawnTouchPawn refresh well the attribute "nbPawnTouchZen"
     */
    @Test()
    public void testUpdateNbTouchZen(){
        //Add pawn touching zen in nbPawnTouchZen
        h.updateNbPawnTouchZen(2);
        assertEquals(h.getNbPawnTouchZen(), 2);

        //Not add and not remove pawn touching zen in nbPawnTouchZen
        h.updateNbPawnTouchZen(0);
        assertEquals(h.getNbPawnTouchZen(), 0);
    } 
}