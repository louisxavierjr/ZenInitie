package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.zenGame.Pawn;
import model.zenGame.TypeOfPawn;

/**
 * JUNIT TEST : Tests the Pawn's class which represents a pawn in a Zen's game
 * @author Godet Louis-Xavier
 */
public class PawnTest {
    /**
    * The object tested
    */
    Pawn p;

    /**
     * Initialization of a Pawn's object for each begin of test
     */
    @Before()
    public void initialize() {
      p = new Pawn(0,0, TypeOfPawn.WHITE);
    }

    /**
     * End of the Pawn's object for each end of test
     */
    @After()
    public void endTest() {
      p = null;
    }

    /**
     * Verify if the object has been initialized well
     */
    @Test()
    public void testPawn() {
      assertNotNull(p);
    }

    /**
     * Verify if the pawn's type input in the constructor hasn't changed
     */
    @Test()
    public void testGetType(){
        assertEquals(p.getType(), TypeOfPawn.WHITE);
    }

    /**
     * Verify if the pawn's type has changed with the method setType
     */
    @Test()
    public void testSetType(){
        p.setType(TypeOfPawn.BLACK);
        assertEquals(p.getType(), TypeOfPawn.BLACK);
    }

    @Test()
    /**
     * Verify if the pawn's X coordinate inputs in the constructor hasn't changed
     */
    public void testGetXPosition(){
        assertEquals(p.getXPosition(), 0);
    }
    
    @Test()
    /**
     * Verify if the pawn's Y coordinate input in the constructor hasn't changed
     */
    public void testGetYPosition(){
        assertEquals(p.getYPosition(), 0);
    }

    @Test()
    /**
     * Verify if pawn's coordinates inputs have changed with the method setCoordinates
     */
    public void testSetCoordinates(){
       p.setCoordinates(1, 2);
       assertEquals(p.getXPosition(), 1);
       assertEquals(p.getYPosition(), 2);
    }
}