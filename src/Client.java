
package src;

public final class Client extends BaseEntity {

    private final String surname;
    private final int age;
    private final Subscription subscription;

    public Client(
            String name,
            String surname,
            int age,
            Subscription subscription
    ) throws InvalidAgeException, InvalidSubscriptionException {
        this(EntityIdGenerator.nextId(), name, surname, age, subscription);
    }

    public Client(
            long id,
            String name,
            String surname,
            int age,
            Subscription subscription
    ) throws InvalidAgeException, InvalidSubscriptionException {
        super(id, name);

        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Прізвище не може бути порожнім."
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

        this.surname = surname.trim();
        this.age = age;
        this.subscription = subscription;
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
                        + "ID: %d%n"
                        + "Вік: %d%n"
                        + "Тип абонемента: %s%n"
                        + "Переваги: %s%n"
                        + "%s%n"
                        + "Знижка: %.2f грн%n"
                        + "До сплати: %.2f грн",
                getName(),
                surname,
                getId(),
                age,
                subscription.getPlanName(),
                subscription.getBenefitsDescription(),
                subscription,
                subscription.calculateDiscount(),
                subscription.getFinalPrice()
        );
    }
}
