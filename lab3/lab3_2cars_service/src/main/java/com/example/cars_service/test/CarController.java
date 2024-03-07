package com.example.cars_service.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarManagerService managerService;

    public CarController(CarManagerService service) {
        this.managerService = service;
    }

    @PostMapping("/cars") public ResponseEntity<Car> createCar(@RequestBody Car oneCar) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = managerService.save(oneCar);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping(path = "/cars",  produces = "application/json")
    public List<Car> getAllCars() {
        return managerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId)
            throws ResourceNotFoundException {
        Car car = managerService.getCarDetails(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for id: " + carId));
        return ResponseEntity.ok().body(car);
    }

}
