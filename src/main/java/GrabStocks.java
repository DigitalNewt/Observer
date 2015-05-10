/**
 * User: Brent Baker
 * Date: 10/15/13
 * Time: 9:26 AM
 */
public class GrabStocks {

    public static void main(String[] args){

        // Create the Subject object
        // It will handle updating all observers
        // as well as deleting and adding them

        StockGrabber stockGrabber = new StockGrabber();

        // Create an Observer that will be sent updates from Subject
        StockObserver observer1 = new StockObserver(stockGrabber);


        // Create 3 threads using the Runnable interface
        // GetTheStock implements Runnable, so it doesn't waste
        // it's one extendable class option
        Runnable getIBM = new GetTheStock(stockGrabber, 1, "IBM", 197.00);
        Runnable getAAPL = new GetTheStock(stockGrabber, 2, "AAPL", 677.60);
        Runnable getGOOG = new GetTheStock(stockGrabber, 3, "GOOG", 676.40);

        // Call for the code in run to execute
        new Thread(getIBM).start();
        new Thread(getAAPL).start();
        new Thread(getGOOG).start();

        waitSeconds(10);
        // Add a second observer
        StockObserver observer2 = new StockObserver(stockGrabber);

        waitSeconds(10);
        // Delete one of the observers
        stockGrabber.unregister(observer2);
    }


    /**
     * Wait based on the number of seconds selected.
     * @param seconds how long to wait in seconds
     */
    public static void waitSeconds(int seconds) {
        try{


            // Sleep for 2 seconds
            Thread.sleep(1000 * seconds);
            // Use Thread.sleep(startTime * 1000); to
            // make sleep time variable
        }
        catch(InterruptedException e)
        {}
    }
}
