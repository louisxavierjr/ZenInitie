package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.zenGame.SquareGrid;

/**
 * JUNIT TEST : Tests the SquareGrid's class which represents a square of a grid in a Zen's game
 * @author Godet Louis-Xavier
 */
public class SquareGridTest {
    /**
    * The object tested
    */
    SquareGrid s;

    /**
     * Initialization of a SquareGrid's object for each begin of test
     */
    @Before()
    public void initialize() {
      s = new SquareGrid(1,2);
    }

    /**
     * End of the SquareGrid's object for each end of test
     */
    @After()
    public void endTest() {
      s = null;
    }

    /**
     * Verify if the object has been initialized well
     */
    @Test()
    public void testSquareGrid() {
      assertNotNull(s);
    }

    @Test()
    /**
     * Verify, at the creation of a SquareGrid, that there are no pawns in 
     */
    public void testIsOccupied(){
        assertFalse(s.isOccupied());
    }

    @Test()
    /**
     * Verify, at the creation of a SquareGrid, that there are no Zen Pawn in 
     */
    public void testIsZen(){
        assertFalse(s.isZen());
    }
}