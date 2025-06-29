package Repository;

import Entities.Store;
import Persistence.StorePersistence;

public class StoreRepository {
    private final Store store;

    public StoreRepository(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public void save() {
        StorePersistence.save(store);
    }
}
