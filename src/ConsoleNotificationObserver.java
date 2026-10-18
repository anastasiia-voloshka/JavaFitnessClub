
package src;

public class ConsoleNotificationObserver implements Observer {

    @Override
    public void update(String message) {
        System.out.println("[СПОВІЩЕННЯ] " + message);
    }
}
