package com.example.cars_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    public Car findByCarId(Long carId);

    public List<Car> findAll();

}
