
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість клієнтів: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Client[] clients = new Client[n];

        for (int i = 0; i < n; i++) {
            System.out.println();
            System.out.println("Клієнт №" + (i + 1));

            System.out.print("Введіть ім'я клієнта: ");
            String name = scanner.nextLine();

            System.out.print("Введіть прізвище клієнта: ");
            String surname = scanner.nextLine();

            System.out.print("Введіть вік клієнта: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Введіть тип абонемента: ");
            String subscriptionType = scanner.nextLine();

            System.out.print("Введіть кількість місяців: ");
            int months = scanner.nextInt();

            System.out.print("Введіть вартість одного місяця: ");
            double monthlyPrice = scanner.nextDouble();
            scanner.nextLine();

            clients[i] = new Client(
                    name, surname, age,
                    subscriptionType, months, monthlyPrice
            );
        }

        System.out.println();
        System.out.println("========== УСІ КЛІЄНТИ ==========");

        for (Client client : clients) {
            System.out.println(client);
            System.out.println("---------------------------------");
        }

        int count = 0;

        for (Client client : clients) {
            if (client.months >= 6) {
                count++;
            }
        }

        System.out.println("Кількість клієнтів з абонементом від 6 місяців: " + count);


        for (int i = 0; i < clients.length - 1; i++) {
            for (int j = 0; j < clients.length - 1 - i; j++) {
                if (clients[j].monthlyPrice > clients[j + 1].monthlyPrice) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                }
            }
        }

        System.out.println();
        System.out.println("========== ПІСЛЯ СОРТУВАННЯ ==========");

        for (Client client : clients) {
            System.out.println(client);
            System.out.println("---------------------------------");
        }

        System.out.println();
        System.out.println("========== ПОШУК КЛІЄНТА ==========");

        System.out.print("Введіть ім'я для пошуку: ");
        String searchName = scanner.nextLine();

        System.out.print("Введіть прізвище для пошуку: ");
        String searchSurname = scanner.nextLine();

        System.out.print("Введіть вік для пошуку: ");
        int searchAge = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введіть тип абонемента для пошуку: ");
        String searchSubscriptionType = scanner.nextLine();

        System.out.print("Введіть кількість місяців для пошуку: ");
        int searchMonths = scanner.nextInt();

        System.out.print("Введіть вартість одного місяця для пошуку: ");
        double searchMonthlyPrice = scanner.nextDouble();

        Client searchClient = new Client(
                searchName, searchSurname, searchAge,
                searchSubscriptionType, searchMonths, searchMonthlyPrice
        );

        boolean found = false;

        for (Client client : clients) {
            if (client.equals(searchClient)) {
                System.out.println();
                System.out.println("Клієнта знайдено:");
                System.out.println(client);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Клієнта не знайдено.");
        }

        scanner.close();
    }
}

class Client {
    String name;
    String surname;
    int age;
    String subscriptionType;
    int months;
    double monthlyPrice;

    Client(String name, String surname, int age,
           String subscriptionType, int months, double monthlyPrice) {

        this.name = name;
        this.surname = surname;
        this.age = age;
        this.subscriptionType = subscriptionType;
        this.months = months;
        this.monthlyPrice = monthlyPrice;
    }

    @Override
    public String toString() {
        double totalPrice = monthlyPrice * months;
        double discount;

        if (months >= 6) {
            discount = totalPrice * 10 / 100;
        } else {
            discount = 0;
        }

        double finalPrice = totalPrice - discount;

        return String.format(
                "Клієнт: %s %s%n" +
                "Вік: %d%n" +
                "Тип абонемента: %s%n" +
                "Кількість місяців: %d%n" +
                "Вартість одного місяця: %.2f грн%n" +
                "Вартість без знижки: %.2f грн%n" +
                "Знижка: %.2f грн%n" +
                "До сплати: %.2f грн",
                name, surname, age, subscriptionType,
                months, monthlyPrice, totalPrice, discount, finalPrice
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Client)) {
            return false;
        }

        Client other = (Client) obj;

        return name.equals(other.name)
                && surname.equals(other.surname)
                && age == other.age
                && subscriptionType.equals(other.subscriptionType)
                && months == other.months
                && Double.compare(monthlyPrice, other.monthlyPrice) == 0;
    }
}