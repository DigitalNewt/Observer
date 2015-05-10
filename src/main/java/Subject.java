/**
 * User: Brent Baker
 * Date: 10/15/13
 * Time: 9:11 AM
 * Subject Interface
 */
public interface Subject {

    public void register(Observer o);
    public void unregister(Observer o);
    public void notifyObserver();
}
