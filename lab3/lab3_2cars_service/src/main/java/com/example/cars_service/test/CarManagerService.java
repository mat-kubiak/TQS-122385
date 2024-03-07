package com.example.cars_service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {

    final
    CarRepository carRepository;

    @Autowired
    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car oneCar) {
        return carRepository.save(oneCar);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId) {
        Car car = carRepository.findByCarId(carId);
        if (car == null)
            return Optional.empty();
        return Optional.of(car);
    }

}
