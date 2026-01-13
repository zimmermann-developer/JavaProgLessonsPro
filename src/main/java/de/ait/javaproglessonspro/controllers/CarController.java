package de.ait.javaproglessonspro.controllers;


import de.ait.javaproglessonspro.model.Car;
import de.ait.javaproglessonspro.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {

    private final CarRepository carRepository;

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {

        /**return allCars.stream()
         .filter(car -> car.getId().equals(id))
         .findFirst()
         .orElse(null);*/
        return carRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }

    @GetMapping("/brand/{brand}")
    public List<Car> getCarByBrand(@PathVariable String brand) {
        return carRepository.findByBrand(brand);
    }


    @PostMapping
    public Long addCar(@RequestBody Car car) {
        return carRepository.save(car).getId();
    }

    @PutMapping("/{id}")
    public String updateCar(@PathVariable Long id, @RequestBody Car car) {
        if (carRepository.existsById(id)) {
            Car carToUpdate = carRepository.findById(id).orElse(null);
            carToUpdate.setBrand(car.getBrand());
            carToUpdate.setModel(car.getModel());
            carToUpdate.setProductionYear(car.getProductionYear());
            carToUpdate.setMileage(car.getMileage());
            carToUpdate.setPrice(car.getPrice());
            carToUpdate.setStatus(car.getStatus());
            return "updated car with id = " + id;
        }
        return "car with id = " + id + " not found";
    }
}
