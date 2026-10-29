
package src;

import java.util.Comparator;
import java.util.List;

public class InMemoryClientService
        extends InMemoryCrudService<Client>
        implements ClientService {

    @Override
    public List<Client> findAllSortedByNameAndByAge() {
        Comparator<Client> comparator =
                Comparator.comparing(Client::getName)
                        .thenComparingInt(Client::getAge);

        return findAllSorted(comparator);
    }
}
