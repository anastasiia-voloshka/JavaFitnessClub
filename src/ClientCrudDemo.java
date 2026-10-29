
package src;

public class ClientCrudDemo {

    public static void main(String[] args)
            throws InvalidSubscriptionException, InvalidAgeException {

        ClientService service = new InMemoryClientService();

        Subscription standard = new StandardSubscription(3, 1200);

        service.save(new Client(
                201, "Анна", "Коваль", 25, standard
        ));

        service.save(new Client(
                202, "Олена", "Шевченко", 30, standard
        ));

        service.save(new Client(
                203, "Анна", "Бондар", 19, standard
        ));

        System.out.println("=== КЛІЄНТИ ДО СОРТУВАННЯ ===");
        for (Client client : service.findAll()) {
            System.out.println(
                    client.getId() + " | "
                            + client.getName() + " "
                            + client.getSurname() + " | "
                            + client.getAge()
            );
        }

        System.out.println("\n=== ЗА ІМ'ЯМ ТА ВІКОМ ===");
        for (Client client : service.findAllSortedByNameAndByAge()) {
            System.out.println(
                    client.getId() + " | "
                            + client.getName() + " "
                            + client.getSurname() + " | "
                            + client.getAge()
            );
        }

        System.out.println("\n=== ПОШУК ===");
        System.out.println(service.findById(201));

        System.out.println("\n=== ОНОВЛЕННЯ ===");
        service.save(new Client(
                201, "Анна", "Коваль", 26, standard
        ));

        System.out.println("Кількість записів: "
                + service.findAll().size());
        System.out.println("Оновлений вік: "
                + service.findById(201).getAge());

        System.out.println("\n=== ВИДАЛЕННЯ ===");
        System.out.println("ID 203: " + service.delete(203));
        System.out.println("ID 999: " + service.delete(999));
    }
}
