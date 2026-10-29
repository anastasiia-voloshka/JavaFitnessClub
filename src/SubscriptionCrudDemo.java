package src;

import java.util.Collection;
import java.util.List;

public final class SubscriptionCrudDemo {

    private SubscriptionCrudDemo() {
    }

    public static void main(String[] args)
            throws InvalidSubscriptionException {

        SubscriptionService service =
                new InMemorySubscriptionService();

        // Додаємо чотири абонементи.
        service.save(new StandardSubscription(3, 1200));
        service.save(new StandardSubscription(6, 1000));
        service.save(new LongTermSubscription(12, 1200));
        service.save(new PersonalTrainingSubscription(12, 1800, 4));

        System.out.println("=== АБОНЕМЕНТИ ДО СОРТУВАННЯ ===");
        printAll(service.findAll());

        // Сортування за назвою, а потім за місячною ціною.
        System.out.println("\n=== СОРТУВАННЯ ЗА НАЗВОЮ ТА ЦІНОЮ ===");

        List<Subscription> sorted =
                service.findAllSortedByNameAndByMonthlyPrice();

        printAll(sorted);

        // Перевіряємо, що сортування не змінює сховище.
        System.out.println("\n=== ПЕРЕВІРКА СХОВИЩА ===");
        System.out.println(
                "Кількість записів: " + service.findAll().size()
        );

        // Оновлюємо перший абонемент, зберігаючи його ID.
        System.out.println("\n=== ОНОВЛЕННЯ ===");

        Subscription original = service.findAll().get(0);

        Subscription updated = new StandardSubscription(
                original.getId(),
                original.getName(),
                3,
                1300,
                4
        );

        service.save(updated);

        System.out.println(
                "Кількість після оновлення: "
                        + service.findAll().size()
        );
        System.out.println(
                "Оновлений абонемент: " + service.findById(updated.getId())
        );

        // Перевіряємо успішне й повторне видалення.
        System.out.println("\n=== ВИДАЛЕННЯ ===");
        System.out.println(
                "Видалення наявного ID: "
                        + service.delete(updated.getId())
        );
        System.out.println(
                "Повторне видалення того самого ID: "
                        + service.delete(updated.getId())
        );

        // Демонструємо wildcard ? extends BaseEntity.
        System.out.println("\n=== WILDCARD ===");
        printAll(service.findAll());
    }

    public static void printAll(
            Collection<? extends BaseEntity> items
    ) {
        for (BaseEntity item : items) {
            System.out.println(item);
            System.out.println("--------------------");
        }
    }
}