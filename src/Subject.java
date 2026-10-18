
package src;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException(
                    "Спостерігач не може бути null."
            );
        }

        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
