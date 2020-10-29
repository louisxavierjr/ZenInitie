package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.zenGame.TypeOfPawn;
import model.zenGame.player.Computer;

/**
 * JUNIT TEST : tests the computer's player class
 * @author Godet Louis-Xavier
 */
public class ComputerTest {
    /**
    * The object tested
    */
    Computer c;

    /**
     * Initialization of an object of Computer type for each begin of test
     */
    @Before()
    public void initialize() {
        String playerName = "LouisBot";
        TypeOfPawn typePawn =  TypeOfPawn.BLACK;
        try{
            c = new Computer(playerName, typePawn);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * End of the Computer's object for each end of test
     */
    @After()
    public void endTest() {
        c = null;
    }
    
    /**
     * Verify if the object has been initialized well
     */
    @Test()
    public void testComputer() {
        assertNotNull(c);
    }
    
    /**
     * Verify that the name of the player input in the constructor hasn't changed
     */
    @Test()
    public void testGetPlayerName(){
        assertEquals("LouisBot", c.getPlayerName());
    }

    /**
     * Verify that type of the pawn input in the constructor hasn't changed
     */
    @Test()
    public void testGetPawnType(){
        assertEquals(TypeOfPawn.BLACK, c.getPawnType());
        //Checks if the player don't have a zen pawn
        assertNotEquals(TypeOfPawn.ZEN, c.getPawnType());
    }

    /**
     * Verify that the method updateNbPawnTouchPawn refresh well the attribute "nbPawnTouchZen"
     */
    @Test()
    public void testUpdateNbTouchZen(){
        //Add pawn touching zen in nbPawnTouchZen
        c.updateNbPawnTouchZen(2);
        assertEquals(c.getNbPawnTouchZen(), 2);

        //Not add and not remove pawn touching zen in nbPawnTouchZen
        c.updateNbPawnTouchZen(0);
        assertEquals(c.getNbPawnTouchZen(), 0);
    } 
}