package src;

import java.util.List;

public interface SubscriptionService extends CRUDService<Subscription> {

    List<Subscription> findAllSortedByNameAndByMonthlyPrice();
}
