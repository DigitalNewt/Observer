/**
 * User: Brent Baker
 * Date: 2/8/15
 * Time: 12:57 PM
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TestStockGrabber {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    /**
     * This is a unit test for the observer pattern.
     * I am catching the output and storing it in outContent so I can assert the results.
     */
    @Test
    public void testStockGrabberObserver() {

        // Create the Subject object
        // It will handle updating all observers
        // as well as deleting and adding them

        StockGrabber stockGrabber = new StockGrabber();

        // Create an Observer that will be sent updates from Subject

        StockObserver observer1 = new StockObserver(stockGrabber);

        assertEquals("New Observer 1\n", outContent.toString());
        outContent.reset();

        stockGrabber.setIBMPrice(197.00);
        assertEquals("1\nIBM: 197.0\nAAPL: 0.0\nGOOG: 0.0\n\n", outContent.toString());
        outContent.reset();

        stockGrabber.setAAPLPrice(677.60);
        assertEquals("1\nIBM: 197.0\nAAPL: 677.6\nGOOG: 0.0\n\n", outContent.toString());
        outContent.reset();

        stockGrabber.setGOOGPrice(676.40);
        assertEquals("1\nIBM: 197.0\nAAPL: 677.6\nGOOG: 676.4\n\n", outContent.toString());
        outContent.reset();

        StockObserver observer2 = new StockObserver(stockGrabber);
        assertEquals("New Observer 2\n", outContent.toString());
        outContent.reset();

        stockGrabber.setIBMPrice(156.72);
        assertEquals("1\nIBM: 156.72\nAAPL: 677.6\nGOOG: 676.4\n\n" +
                     "2\nIBM: 156.72\nAAPL: 677.6\nGOOG: 676.4\n\n",
                outContent.toString());
        outContent.reset();

        stockGrabber.setAAPLPrice(118.93);
        assertEquals("1\nIBM: 156.72\nAAPL: 118.93\nGOOG: 676.4\n\n" +
                     "2\nIBM: 156.72\nAAPL: 118.93\nGOOG: 676.4\n\n",
                outContent.toString());
        outContent.reset();

        stockGrabber.setGOOGPrice(531.00);
        assertEquals("1\nIBM: 156.72\nAAPL: 118.93\nGOOG: 531.0\n\n" +
                     "2\nIBM: 156.72\nAAPL: 118.93\nGOOG: 531.0\n\n",
                outContent.toString());
        outContent.reset();


        // Delete one of the observers
        stockGrabber.unregister(observer1);
        assertEquals("Observer 1 deleted\n", outContent.toString());
        outContent.reset();

        stockGrabber.setIBMPrice(150.72);
        assertEquals("2\nIBM: 150.72\nAAPL: 118.93\nGOOG: 531.0\n\n", outContent.toString());
        outContent.reset();

        stockGrabber.setAAPLPrice(218.93);
        assertEquals("2\nIBM: 150.72\nAAPL: 218.93\nGOOG: 531.0\n\n", outContent.toString());
        outContent.reset();

        stockGrabber.setGOOGPrice(431.00);
        assertEquals("2\nIBM: 150.72\nAAPL: 218.93\nGOOG: 431.0\n\n", outContent.toString());
        outContent.reset();
    }

}
