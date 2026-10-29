package src;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Демонстрація вимог ЛР №7–9 на основних сутностях фітнес-клубу:
 * Client та Subscription.
 */
public final class CrudServiceDemo {

    private CrudServiceDemo() {
    }

    public static void main(String[] args)
            throws InvalidSubscriptionException, InvalidAgeException {
        demonstrateClients();
        demonstrateSubscriptions();
        demonstrateGenericsAndWildcards();
    }

    private static void demonstrateClients()
            throws InvalidSubscriptionException, InvalidAgeException {
        System.out.println("\n========== CRUD КЛІЄНТІВ ==========");

        ClientService service = new InMemoryClientService();
        Subscription standard = new StandardSubscription(3, 1200);

        service.save(new Client(201, "Анна", "Коваль", 25, standard));
        service.save(new Client(202, "Олена", "Шевченко", 30, standard));
        service.save(new Client(203, "Анна", "Бондар", 19, standard));
        service.save(new Client(204, "Анна", "Мельник", 22, standard));

        System.out.println("\nДо сортування:");
        printAll(service.findAll());

        System.out.println("\nЗа ім'ям:");
        printAll(service.findAllSortedByName());

        System.out.println("\nЗа ім'ям, а потім за віком:");
        printAll(service.findAllSortedByNameAndByAge());

        System.out.println("\n--- ОНОВЛЕННЯ ---");
        int beforeUpdate = service.findAll().size();
        service.save(new Client(201, "Анна", "Коваль", 26, standard));
        int afterUpdate = service.findAll().size();
        System.out.println("Записів до оновлення: " + beforeUpdate);
        System.out.println("Записів після оновлення: " + afterUpdate);
        System.out.println("Оновлений клієнт: " + service.findById(201));

        System.out.println("\n--- ПОШУК НЕІСНУЮЧОГО ID ---");
        try {
            service.findById(999);
        } catch (EntityNotFoundException exception) {
            System.out.println("Оброблено виняток: " + exception.getMessage());
            System.out.println("ID помилки: " + exception.getId());
        }

        System.out.println("\n--- ВИДАЛЕННЯ ---");
        System.out.println("Видалення ID 204: " + service.delete(204));
        System.out.println("Видалення ID 999: " + service.delete(999));

        System.out.println("\n--- ПЕРЕВІРКА, ЩО СОРТУВАННЯ НЕ ЗМІНЮЄ СХОВИЩЕ ---");
        List<Client> beforeSort = service.findAll();
        service.findAllSortedByNameAndByAge();
        List<Client> afterSort = service.findAll();
        System.out.println("Склад і порядок сховища не змінилися: "
                + beforeSort.equals(afterSort));
    }

    private static void demonstrateSubscriptions()
            throws InvalidSubscriptionException {
        System.out.println("\n========== CRUD АБОНЕМЕНТІВ ==========");

        SubscriptionService service = new InMemorySubscriptionService();

        // Два абонементи мають однакову назву: ціна покаже вторинне сортування.
        service.save(new StandardSubscription(301, "Стандартний", 3, 1200, 4));
        service.save(new StandardSubscription(302, "Стандартний", 6, 1000, 4));
        service.save(new LongTermSubscription(12, 1200));
        service.save(new PersonalTrainingSubscription(304, "Персональне тренування", 12, 1800, 4));

        System.out.println("\nДо сортування:");
        printAll(service.findAll());

        System.out.println("\nЗа назвою, а потім за місячною ціною:");
        List<Subscription> beforeSort = service.findAll();
        printAll(service.findAllSortedByNameAndByMonthlyPrice());
        List<Subscription> afterSort = service.findAll();
        System.out.println("Склад і порядок сховища не змінилися: "
                + beforeSort.equals(afterSort));

        System.out.println("\n--- ОНОВЛЕННЯ ---");
        int beforeUpdate = service.findAll().size();
        service.save(new StandardSubscription(301, "Стандартний", 3, 1300, 4));
        int afterUpdate = service.findAll().size();
        System.out.println("Записів до оновлення: " + beforeUpdate);
        System.out.println("Записів після оновлення: " + afterUpdate);
        System.out.println("Оновлений абонемент: " + service.findById(301));

        System.out.println("\n--- ВИДАЛЕННЯ ---");
        System.out.println("Видалення ID 304: " + service.delete(304));
        System.out.println("Повторне видалення ID 304: " + service.delete(304));
    }

    private static void demonstrateGenericsAndWildcards()
            throws InvalidSubscriptionException, InvalidAgeException {
        System.out.println("\n========== GENERICS, WILDCARDS ТА EQUALS/HASHCODE ==========");

        Subscription standard = new StandardSubscription(3, 1200);
        List<Client> clients = List.of(
                new Client(401, "Олена", "Коваль", 25, standard),
                new Client(402, "Анна", "Шевченко", 20, standard)
        );

        System.out.println("printAll(Collection<? extends BaseEntity>) приймає List<Client>:");
        printAll(clients);

        ClientService service = new InMemoryClientService();
        for (Client client : clients) {
            service.save(client);
        }

        Comparator<BaseEntity> byId =
                Comparator.comparingLong(BaseEntity::getId);

        System.out.println("\nComparator<BaseEntity> сортує Client:");
        printAll(service.findAllSorted(byId));

        Client first = new Client(501, "Іра", "Тест", 20, standard);
        Client sameId = new Client(501, "Інше ім'я", "Інше прізвище", 35, standard);
        System.out.println("\nОднаковий клас та ID — equals(): "
                + first.equals(sameId));
        System.out.println("Однаковий hashCode(): "
                + (first.hashCode() == sameId.hashCode()));
    }

    /**
     * Приймає колекцію BaseEntity або будь-якого її підкласу.
     */
    public static void printAll(Collection<? extends BaseEntity> items) {
        for (BaseEntity item : items) {
            System.out.println(item);
            System.out.println("--------------------");
        }
    }
}
