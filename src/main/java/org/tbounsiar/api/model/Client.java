package org.tbounsiar.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.tbounsiar.api.bom.ClientCreateBom;

@Getter
@Setter
@NoArgsConstructor
@MongoEntity(collection = "client")
public class Client {
    @BsonId
    private String id;
    @BsonProperty("first_name")
    private String firstName;
    @BsonProperty("last_name")
    private String lastName;
    private String email;
    @JsonIgnore
    @BsonProperty("address_id")
    private String addressId;

    public Client(ClientCreateBom client){
        setId(client.getId());
        setFirstName(client.getFirstName());
        setLastName(client.getLastName());
        setEmail(client.getEmail());
        setAddressId(client.getAddressId());
    }
}
