package com.example.cars_service.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repo;

    @Test
    void whenFindCarByCarId_thenReturnCar() {
        Car car = new Car("Toyota", "Camry");
        entityManager.persistAndFlush(car);

        Car foundCar = repo.findByCarId(car.getId());

        assertNotNull(foundCar);
        assertEquals(car.getId(), foundCar.getId());
    }

    @Test
    void whenInvalidCarId_thenReturnNull() {
        Car foundCar = repo.findByCarId(-1L);
        assertNull(foundCar);
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car car1 = new Car("Toyota", "Corolla");
        Car car2 = new Car("Ford", "Focus");
        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.flush();

        List<Car> allCars = repo.findAll();

        assertEquals(2, allCars.size());
        assertTrue(allCars.contains(car1));
        assertTrue(allCars.contains(car2));
    }
}
