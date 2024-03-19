package org.tbounsiar.api.bom;

import lombok.Getter;
import lombok.Setter;
import org.tbounsiar.api.model.Address;

@Getter
@Setter
public class AddressBom {
    private String streetNumber;
    private String streetName;
    private String suburb;
    private String postcode;
    private String city;
    private String state;

    public AddressBom(Address address) {
        streetNumber = address.getStreetNumber();
        streetName = address.getStreetName();
        suburb = address.getSuburb();
        postcode = address.getPostcode();
        city = address.getCity();
        state = address.getState();
    }
}
