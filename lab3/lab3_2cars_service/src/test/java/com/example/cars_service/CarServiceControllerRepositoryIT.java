package com.example.cars_service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarServiceControllerRepositoryIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repo;

    @AfterEach
    public void resetDb() {
        repo.deleteAll();
    }

    @Test
    void testWhenValidInput_thenCreateCar() {
        Car micra = new Car("niisan", "micra");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", micra, Car.class);

        List<Car> found = repo.findAll();
        Assertions.assertThat(found).extracting(Car::getModel).containsOnly("micra");
    }

    @Test
    void givenCars_whenGetCars_thenStatus200()  {
        createTestCar("honda", "civic");
        createTestCar("niisan", "micra");

        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("civic", "micra");

    }

    private void createTestCar(String brand, String model) {
        Car emp = new Car(brand, model);
        repo.saveAndFlush(emp);
    }

}
