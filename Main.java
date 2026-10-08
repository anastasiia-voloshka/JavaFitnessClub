import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть кількість клієнтів: ");
            int n = readInt(scanner);

            Client[] clients = new Client[n];

            for (int i = 0; i < n; i++) {
                System.out.println();
                System.out.println("Клієнт №" + (i + 1));

                System.out.print("Введіть ім'я клієнта: ");
                String name = scanner.nextLine();

                System.out.print("Введіть прізвище клієнта: ");
                String surname = scanner.nextLine();

                System.out.print("Введіть вік клієнта: ");
                int age = readInt(scanner);

                System.out.print("Введіть тип абонемента: ");
                String subscriptionType = scanner.nextLine();

                System.out.print("Введіть кількість місяців: ");
                int months = readInt(scanner);

                System.out.print("Введіть вартість одного місяця: ");
                double monthlyPrice = readDouble(scanner);

                clients[i] = new Client(
                        name,
                        surname,
                        age,
                        subscriptionType,
                        months,
                        monthlyPrice
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

            System.out.println(
                    "Кількість клієнтів з абонементом від 6 місяців: " + count
            );

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
            int searchAge = readInt(scanner);

            System.out.print("Введіть тип абонемента для пошуку: ");
            String searchSubscriptionType = scanner.nextLine();

            System.out.print("Введіть кількість місяців для пошуку: ");
            int searchMonths = readInt(scanner);

            System.out.print("Введіть вартість одного місяця для пошуку: ");
            double searchMonthlyPrice = readDouble(scanner);

            Client searchClient = new Client(
                    searchName,
                    searchSurname,
                    searchAge,
                    searchSubscriptionType,
                    searchMonths,
                    searchMonthlyPrice
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

        } catch (InputMismatchException e) {
            System.out.println(
                    "Помилка: введено некоректний тип даних. "
                            + "Для числового поля потрібно ввести число."
            );

        } catch (NegativeArraySizeException e) {
            System.out.println(
                    "Помилка: кількість клієнтів не може бути від'ємною."
            );

        } catch (DomainException e) {
            System.out.println(
                    "Помилка даних клієнта: " + e.getMessage()
            );
            System.out.println(
                    "Некоректне значення: " + e.getInvalidValue()
            );

        } finally {
            scanner.close();
            System.out.println("Роботу програми завершено.");
        }
    }

    // Метод для введення цілого числа
    // Частково обробляє InputMismatchException
    // і передає його далі через throw e.
    private static int readInt(Scanner scanner) {
        try {
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;

        } catch (InputMismatchException e) {
            System.out.println(
                    "Помилка введення: очікується ціле число."
            );

            throw e;
        }
    }

    // Метод для введення дробового числа
    // Частково обробляє InputMismatchException
    // і передає його далі через throw e.
    private static double readDouble(Scanner scanner) {
        try {
            double value = scanner.nextDouble();
            scanner.nextLine();
            return value;

        } catch (InputMismatchException e) {
            System.out.println(
                    "Помилка введення: очікується числове значення."
            );

            throw e;
        }
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
           String subscriptionType, int months, double monthlyPrice)
            throws InvalidAgeException, InvalidSubscriptionException {

        if (age < 1 || age > 120) {
            throw new InvalidAgeException(
                    "Вік клієнта повинен бути від 1 до 120 років.",
                    age
            );
        }

        if (months <= 0) {
            throw new InvalidSubscriptionException(
                    "Кількість місяців абонемента повинна бути більшою за 0.",
                    months
            );
        }

        if (monthlyPrice <= 0) {
            throw new InvalidSubscriptionException(
                    "Вартість одного місяця повинна бути більшою за 0.",
                    monthlyPrice
            );
        }

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
                name,
                surname,
                age,
                subscriptionType,
                months,
                monthlyPrice,
                totalPrice,
                discount,
                finalPrice
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