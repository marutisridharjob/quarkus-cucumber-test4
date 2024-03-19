package org.tbounsiar.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.tbounsiar.api.bom.AddressBom;
import org.tbounsiar.api.bom.ClientBom;
import org.tbounsiar.api.bom.ClientCreateBom;
import org.tbounsiar.api.bom.ResponseBom;
import org.tbounsiar.api.model.Address;
import org.tbounsiar.api.model.Client;
import org.tbounsiar.api.repository.AddressRepository;
import org.tbounsiar.api.repository.ClientRepository;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final AddressRepository addressRepository;

    public ResponseBom<List<Client>> all() {
        return new ResponseBom<>(repository.all());
    }

    public ResponseBom<ClientBom> get(String id) {
        Client client = repository.get(id);
        if (client == null) {
            return new ResponseBom<>(null);
        }
        ClientBom clientBom = new ClientBom(client);
        Address address = addressRepository.get(client.getAddressId());
        if (address != null) {
            clientBom.setAddress(new AddressBom(address));
        }
        return new ResponseBom<>(clientBom);
    }

    public ResponseBom<ClientBom> create(ClientCreateBom client) {
        return createOrUpdate(client, false);
    }

    public ResponseBom<ClientBom> update(ClientCreateBom client) {
        return createOrUpdate(client, true);
    }

    private ResponseBom<ClientBom> createOrUpdate(ClientCreateBom client, boolean update) {
        Client newClient = new Client(client);
        Address address = addressRepository.get(client.getAddressId());
        if (address == null) {
            newClient.setAddressId(null);
        }
        if (update) {
            repository.update(newClient);
        } else {
            repository.persist(newClient);
        }
        ClientBom clientBom = new ClientBom(newClient);
        if (address != null) {
            clientBom.setAddress(new AddressBom(address));
        }
        return new ResponseBom<>(clientBom);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
