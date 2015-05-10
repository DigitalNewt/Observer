import java.text.DecimalFormat;

/**
 * User: Brent Baker
 * Date: 10/15/13
 * Time: 12:27 PM
 */
public class GetTheStock implements Runnable {

    // Could be used to set how many seconds to wait
    // in Thread.sleep() below

    private int startTime;
    private String stock;
    private double price;

    // Will hold reference to the StockGrabber object
    private Subject stockGrabber;

    /**
     * Constructor to sets the stock name price and number of seconds to wait for display
     * @param stockGrabber stock grabber object
     * @param newStartTime number of seconds to wait until displayed
     * @param newStock stock name
     * @param newPrice stock price
     */
    public GetTheStock(Subject stockGrabber, int newStartTime, String newStock, double newPrice){

        // Store the reference to the stockGrabber object so
        // I can make calls to its methods
        this.stockGrabber = stockGrabber;

        startTime = newStartTime;  //Not used to have variable sleep time
        stock = newStock;
        price = newPrice;

    }

    /**
     * Wait based on the number of startTime seconds.
     */
    public void startTimeWait(){
        try {
            Thread.sleep(startTime * 1000);
        } catch(InterruptedException ie) {
        }
    }

    public void run(){
        for(int i = 1; i <= 20; i++){
            startTimeWait();

            // Generates a random number between -.03 and .03
            double randNum = (Math.random() * (.06)) - .03;

            // Formats decimals to 2 places
            DecimalFormat df = new DecimalFormat("#.##");

            // Change the price and then convert it back into a double
            price = Double.valueOf(df.format((price + randNum)));

            if(stock == "IBM") ((StockGrabber) stockGrabber).setIBMPrice(price);
            if(stock == "AAPL") ((StockGrabber) stockGrabber).setAAPLPrice(price);
            if(stock == "GOOG") ((StockGrabber) stockGrabber).setGOOGPrice(price);

            System.out.println(stock + ": " + df.format((price + randNum)) +
                    " " + df.format(randNum));

            System.out.println();
        }
    }
}
