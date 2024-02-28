package com.github.matkubiak.tqs;

import java.io.IOException;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.w3c.dom.ranges.RangeException;

public class AddressResolverService {
    private final ISimpleHttpClient client;
    private final String apiKey;

    public AddressResolverService(ISimpleHttpClient client, String apiKey) {
        this.client = client;
        this.apiKey = apiKey;
    }

    private String prepareUriForRemoteEndpoint(double latitude, double longitude) {
        return "https://www.mapquestapi.com/geocoding/v1/reverse?key=" + apiKey + "&location=" + latitude + "," + longitude;
    }

    public Optional<Address> findAddressForLocation(double latitude, double longitude) throws IllegalArgumentException {
        if (latitude > 90 || latitude < -90) {
            throw new IllegalArgumentException("latitude out of range");
        }
        if (longitude > 180 || longitude < -180) {
            throw new IllegalArgumentException("longitude out of range");
        }

        try {
            String response = client.doHttpGet(prepareUriForRemoteEndpoint(latitude, longitude));

            ObjectMapper mapper = new ObjectMapper();
            JsonNode location = mapper.readTree(response).get("results").get(0).get("locations").get(0);
//            System.out.println("JSON: " + mapper.writeValueAsString(location);

            String street = location.path("street").asText();
            String city = location.path("adminArea5").asText();
            String zip = location.path("postalCode").asText();

            return Optional.of(new Address(street, "", zip, city));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
