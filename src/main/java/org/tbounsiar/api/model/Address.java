package org.tbounsiar.api.model;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

@MongoEntity(collection = "address")
@Getter
@Setter
public class Address extends PanacheMongoEntityBase {
    @BsonId
    private String id;

    @BsonProperty("street_number")
    private String streetNumber;
    @BsonProperty("street_name")
    private String streetName;
    private String suburb;
    private String postcode;
    private String city;
    private String state;
}
