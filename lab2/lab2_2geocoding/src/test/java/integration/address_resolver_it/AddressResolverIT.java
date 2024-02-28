package integration.address_resolver_it;

import com.github.matkubiak.tqs.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {

    private ISimpleHttpClient client;
    private AddressResolverService resolver;
    private String apiKey = "API_KEY";
    private Address correctResponse;

    @BeforeEach
    void setup() {
        apiKey = Config.GetProperty("mapquest_key");
        client = new TqsBasicHttpClient();
        resolver = new AddressResolverService(client, apiKey);
        correctResponse = new Address("802 Arkenstone Dr", "", "32225", "Jacksonville");
    }

    @Test
    void testFindAddressFromLocation() {
        Optional<Address> actual = resolver.findAddressForLocation(30.333472,-81.470448);

        assertFalse(actual.isEmpty(), "address was not found!");
        assertEquals(correctResponse, actual.get(), "addresses do not match!");

        assertThrows(IllegalArgumentException.class, () -> resolver.findAddressForLocation(-92, 0));
        assertThrows(IllegalArgumentException.class, () -> resolver.findAddressForLocation(0, 185));
    }
}
