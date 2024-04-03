package com.example.cars_service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    CarRepository repo;

    @InjectMocks
    CarManagerService service;

    private Car car1 = new Car(1L, "Civic", "Honda");
    private Car car2 = new Car(1L, "Cyber Truck", "Tesla");
    private List<Car> list = List.of(car1, car2);

    @Test
    public void testFindAllEmpty() {
        when(repo.findAll()).thenReturn(Collections.emptyList());

        List<Car> response = service.getAllCars();

        assertTrue(response.isEmpty());
    }

    @Test
    public void testFindAll() {
        when(repo.findAll()).thenReturn(list);

        List<Car> response = service.getAllCars();

        assertFalse(response.isEmpty());
        assertTrue(response.contains(car1));
        assertTrue(response.contains(car2));
    }

    @Test
    public void testFindCarDetailsEmpty() {
        when(repo.findByCarId(1L)).thenReturn(null);

        Optional<Car> response = service.getCarDetails(1L);

        assertTrue(response.isEmpty());
    }

    @Test
    public void testFindCarDetails() {
        when(repo.findByCarId(1L)).thenReturn(car1);

        Optional<Car> response = service.getCarDetails(1L);

        assertFalse(response.isEmpty());
        assertEquals(car1, response.get());
    }

    @Test
    public void testSave() {
        when(repo.save(car2)).thenReturn(car2);

        Car response = service.save(car2);

        response.equals(car2);
    }
}
