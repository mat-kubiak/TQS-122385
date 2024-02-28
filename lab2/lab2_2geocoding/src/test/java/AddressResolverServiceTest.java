
import com.github.matkubiak.tqs.Address;
import com.github.matkubiak.tqs.AddressResolverService;
import com.github.matkubiak.tqs.ISimpleHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressResolverServiceTest {
    @Mock
    private ISimpleHttpClient client;

    private AddressResolverService resolver;

    private String apiKey = "API_KEY";
    private String httpClientInput = "https://www.mapquestapi.com/geocoding/v1/reverse?key=" + apiKey + "&location=30.333472,-81.470448";
    private Address correctResponse;
    private String httpClientResponse = """            
        {
          \"info\": {
            \"statuscode\": 0,
            \"copyright\": {
              \"text\": \"© 2024 MapQuest, Inc.\",
              \"imageUrl\": \"https://api.mqcdn.com/res/mqlogo.gif\",
              \"imageAltText\": "© 2024 MapQuest, Inc.\"
            },
            \"messages\": []
          },
          \"options\": {
            \"maxResults\": 1,
            \"thumbMaps\": true,
            \"ignoreLatLngInput\": false
          },
          \"results\": [
            {
              \"providedLocation\": {
                \"latLng\": {
                  \"lat\": 30.333472,
                  \"lng\": -81.470448
                }
              },
              \"locations\": [
                {
                  \"street\": \"12714 Ashley Melisse Blvd\",
                  \"adminArea6\": \"\",
                  \"adminArea6Type\": \"Neighborhood\",
                  \"adminArea5\": \"Jacksonville\",
                  \"adminArea5Type\": \"City\",
                  \"adminArea4\": \"Duval\",
                  \"adminArea4Type\": \"County\",
                  \"adminArea3\": \"FL\",
                  \"adminArea3Type\": \"State\",
                  \"adminArea1\": \"US\",
                  \"adminArea1Type\": \"Country\",
                  \"postalCode\": \"32225\",
                  \"geocodeQualityCode\": \"L1AAA\",
                  \"geocodeQuality\": \"ADDRESS\",
                  \"dragPoint\": false,
                  \"sideOfStreet\": \"R\",
                  \"linkId\": \"0\",
                  \"unknownInput\": \"\",
                  \"type\": \"s\",
                  \"latLng\": {
                    \"lat\": 30.33472,
                    \"lng\": -81.470448
                  },
                  \"displayLatLng\": {
                    \"lat\": 30.333472,
                    \"lng\": -81.470448
                  },
                  \"mapUrl\": \"https://www.mapquestapi.com/staticmap/v4/getmap?key=KEY&type=map&size=225,160&pois=purple-1,30.3334721,-81.4704483,0,0,|&center=30.3334721,-81.4704483&zoom=15&rand=-553163060\",
                  \"nearestIntersection\": {
                    \"streetDisplayName\": \"Posey Cir\",
                    \"distanceMeters\": \"851755.1608527573\",
                    \"latLng\": {
                      \"longitude\": -87.523761,
                      \"latitude\": 35.013434
                    },
                    \"label\": \"Danley Rd & Posey Cir\"
                  },
                  \"roadMetadata\": {
                    \"speedLimitUnits\": \"mph\",
                    \"tollRoad\": null,
                    \"speedLimit\": 40
                  }
                }
              ]
            }
          ]
        }
    """;

    @BeforeEach
    void setup() throws IOException {
        client = mock(ISimpleHttpClient.class);
        resolver = new AddressResolverService(client, apiKey);
        correctResponse = new Address("12714 Ashley Melisse Blvd", "", "32225", "Jacksonville");

        when(client.doHttpGet(httpClientInput)).thenReturn(httpClientResponse);
    }

    @Test
    void testFindAddressFromLocation() throws IOException {
        Optional<Address> actual = resolver.findAddressForLocation(30.333472,-81.470448);

        assertFalse(actual.isEmpty(), "address was not found!");
        assertEquals(correctResponse, actual.get(), "addresses do not match!");

        verify(client).doHttpGet(httpClientInput);

        assertThrows(IllegalArgumentException.class, () -> resolver.findAddressForLocation(-92, 0));
        assertThrows(IllegalArgumentException.class, () -> resolver.findAddressForLocation(0, 185));
    }
}
