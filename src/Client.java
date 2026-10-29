package src;

import java.util.Objects;

public final class Client {

    private final String name;
    private final String surname;
    private final int age;
    private final Subscription subscription;

    public Client(
            String name,
            String surname,
            int age,
            Subscription subscription
    ) throws InvalidAgeException, InvalidSubscriptionException {
        if (name == null || name.trim().isEmpty()
                || surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Ім'я та прізвище не можуть бути порожніми."
            );
        }

        if (age < 1 || age > 120) {
            throw new InvalidAgeException(
                    "Вік клієнта повинен бути від 1 до 120 років.",
                    age
            );
        }

        if (subscription == null) {
            throw new InvalidSubscriptionException(
                    "Абонемент клієнта не може бути null.",
                    (Object) null
            );
        }

        this.name = name;
        this.surname = surname;
        this.age = age;
        this.subscription = subscription;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public String toString() {
        return String.format(
                "Клієнт: %s %s%n"
                        + "Вік: %d%n"
                        + "Тип абонемента: %s%n"
                        + "Переваги: %s%n"
                        + "%s%n"
                        + "Знижка: %.2f грн%n"
                        + "До сплати: %.2f грн",
                name,
                surname,
                age,
                subscription.getPlanName(),
                subscription.getBenefitsDescription(),
                subscription,
                subscription.calculateDiscount(),
                subscription.getFinalPrice()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Client other = (Client) object;
        return age == other.age
                && name.equals(other.name)
                && surname.equals(other.surname)
                && subscription.equals(other.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, subscription);
    }
}
