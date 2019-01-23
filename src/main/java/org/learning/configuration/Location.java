package org.learning.configuration;

import static org.springframework.util.Assert.notNull;

public final class Location {

    public final String city;
    public final String street;
    public final String postalCode;

    public Location(String city, String street, String postalCode) {
        notNull(city, "City cannot be null.");
        notNull(street, "Street cannot be null.");
        notNull(postalCode, "PostalCode cannot be null.");
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return String.format("%s, %s %s", city, street, postalCode);
    }
}
