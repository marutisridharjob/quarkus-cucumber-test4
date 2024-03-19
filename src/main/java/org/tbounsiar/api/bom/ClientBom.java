package org.tbounsiar.api.bom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tbounsiar.api.model.Client;

@Getter
@Setter
@NoArgsConstructor
public class ClientBom {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressBom address;

    public ClientBom(Client client) {
        id = client.getId();
        firstName = client.getFirstName();
        lastName = client.getLastName();
        email = client.getEmail();
    }
}
