package org.tbounsiar.api.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.tbounsiar.api.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheMongoRepositoryBase<Address, String> {

    public Address get(String id) {
        return findById(id);
    }
}
