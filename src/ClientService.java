
package src;

import java.util.List;

public interface ClientService extends CRUDService<Client> {

    List<Client> findAllSortedByNameAndByAge();
}
