
package src;

import java.util.Comparator;
import java.util.List;

public class InMemorySubscriptionService
        extends InMemoryCrudService<Subscription>
        implements SubscriptionService {

    @Override
    public List<Subscription> findAllSortedByNameAndByMonthlyPrice() {
        Comparator<Subscription> comparator =
                Comparator.comparing(
                        Subscription::getName,
                        String.CASE_INSENSITIVE_ORDER
                ).thenComparingDouble(
                        Subscription::getMonthlyPrice
                );

        return findAllSorted(comparator);
    }
}
