package org.learning.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:location.properties")
public class LocationConfiguration {

    public final Location location;

    public LocationConfiguration(@Value("${city}") String city,
                                 @Value("${street}") String street,
                                 @Value("${postalCode}") String postalCode) {

        location = new Location(city, street, postalCode);
    }
}
