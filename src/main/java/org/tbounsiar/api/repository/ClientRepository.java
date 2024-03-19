package org.tbounsiar.api.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.tbounsiar.api.model.Client;

import java.util.List;

@ApplicationScoped
public class ClientRepository implements PanacheMongoRepositoryBase<Client, String> {
    public List<Client> all() {
        return listAll();
    }

    public Client get(String id) {
        return findById(id);
    }

    public Client create(Client newClient) {
        persist(newClient);
        return newClient;
    }
}
