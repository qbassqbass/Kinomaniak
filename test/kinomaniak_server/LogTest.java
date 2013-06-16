/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kinomaniak_server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author qbass
 */
public class LogTest {
    
    public LogTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of doLog method, of class Log.
     */
    @Test
    public void testDoLog() {
        System.out.println("doLog");
        int type = 0;
        String tolog = "";
        Log instance = new Log();
        instance.doLog(type, tolog);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}