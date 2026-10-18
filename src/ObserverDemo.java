
package src;

public class ObserverDemo {

    public static void main(String[] args) {
        Subject subject = new Subject();

        Observer notificationObserver =
                new ConsoleNotificationObserver();

        Observer logObserver =
                new MembershipLogObserver();

        subject.addObserver(notificationObserver);
        subject.addObserver(logObserver);

        System.out.println("========== OBSERVER ==========");

        subject.notifyObservers(
                "Оформлено новий абонемент клієнта."
        );

        System.out.println("==============================");
    }
}
