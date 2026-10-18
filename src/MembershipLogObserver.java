
package src;

public class MembershipLogObserver implements Observer {

    @Override
    public void update(String message) {
        System.out.println("[ЖУРНАЛ] Подія записана: " + message);
    }
}
