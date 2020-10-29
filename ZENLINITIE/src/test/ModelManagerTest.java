package test;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.zenGame.ModelManager;

/**
 * JUNIT TEST : Test the menu of Zen l'initie game
 * @author Godet Louis-Xavier
 */
public class ModelManagerTest {
    /**
    * The object tested
    */
    ModelManager m;

    /**
     * Initialization of a ModelManager's object for each begin of test
     */
    @Before()
    public void initialize() {
      m = new ModelManager();
    }

    /**
     * End of the ModelManager's object for each end of test
     */
    @After()
    public void endTest() {
      m = null;
    }

    /**
     * Verify if the object has been initialized well
     */
    @Test()
    public void testModelManager() {
      assertNotNull(m);
    }
}