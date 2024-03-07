package com.example.cars_service.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

	@Mock
	private CarManagerService carManagerService;

	@InjectMocks
	private CarController carRestController;

	private Car car1 = new Car(1L, "Toyota", "Camry");
	private Car car2 = new Car(2L, "Honda", "Civic");
	private List<Car> cars = Arrays.asList(car1, car2);

	@Test
	public void testGerAllCarsEmpty() {
		when(carManagerService.getAllCars()).thenReturn(Collections.emptyList());

		List<Car> response = carRestController.getAllCars();

		assertTrue(response.isEmpty());
	}

	@Test
	public void testGetAllCars() {
		when(carManagerService.getAllCars()).thenReturn(cars);

		List<Car> response = carRestController.getAllCars();

		assertTrue(response.contains(car1));
		assertTrue(response.contains(car2));
		assertEquals(2, response.size());
	}

	@Test
	public void testGetCarByIdEmpty() {
		when(carManagerService.getCarDetails(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> carRestController.getCarById(1L));
	}

	@Test
	public void testGetCarById() throws ResourceNotFoundException {
		when(carManagerService.getCarDetails(1L)).thenReturn(Optional.of(car1));

		ResponseEntity<Car> response = carRestController.getCarById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(1L, response.getBody().getId());
		assertEquals("Toyota", response.getBody().getBrand());
		assertEquals("Camry", response.getBody().getModel());
	}

}
