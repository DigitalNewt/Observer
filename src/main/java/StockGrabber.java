import java.util.ArrayList;
import java.util.List;

/**
 * User: Brent Baker
 * Date: 10/15/13
 * Time: 9:13 AM
 * StockGrabber class that implements Subject
 */
public class StockGrabber implements Subject {

    private List<Observer> observers;
    private double ibmPrice;
    private double aaplPrice;
    private double googPrice;

    /**
     * Constructor creates an ArrayList to hold all observers
     */
    public StockGrabber() {

        observers = new ArrayList<Observer>();
    }

    /**
     *  Adds a new observer to the ArrayList
     * @param newObserver new observer object
     */
    public void register(Observer newObserver) {

        observers.add(newObserver);
    }

    /**
     * Deletes Observer from the list
     * @param deleteObserver Observer object to delete
     */
    public void unregister(Observer deleteObserver) {
        // Get the index of the observer to delete
        int observerIndex = observers.indexOf(deleteObserver);

        // Print out message (Have to increment index to match)
        System.out.println("Observer " + (observerIndex + 1) + " deleted");

        // Removes observer from the ArrayList
        observers.remove(observerIndex);
    }

    /**
     * Cycle through all observers and notifies them of
     * price changes
     */
    public void notifyObserver() {
        for(Observer observer : observers){
            observer.update(ibmPrice, aaplPrice, googPrice);
        }
    }


    /**
     * Change price for IBM stocks and notifies observers of changes
     * @param newIBMPrice IBM Price to set
     */
    public void setIBMPrice(double newIBMPrice){
        this.ibmPrice = newIBMPrice;
        notifyObserver();
    }

    /**
     * Change price for Apple stocks and notifies observers of changes
     * @param newAAPLPrice Apple Price to set
     */
    public void setAAPLPrice(double newAAPLPrice){
        this.aaplPrice = newAAPLPrice;
        notifyObserver();
    }

    /**
     * Change price for Google stocks and notifies observers of changes
     * @param newGOOGPrice Google Price to set
     */
    public void setGOOGPrice(double newGOOGPrice){
        this.googPrice = newGOOGPrice;
        notifyObserver();
    }

}
