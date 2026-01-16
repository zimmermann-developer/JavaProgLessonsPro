package de.ait.javaproglessonspro.controllers;


import de.ait.javaproglessonspro.model.Car;
import de.ait.javaproglessonspro.repository.CarRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Car management API")
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository carRepository;

    @Value("${app.dealership.name:Welcome to AIT Gr.59 API}")
    private String dealershipName;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok("Welcome to the " + dealershipName + " car dealership!");
    }

    @Operation(summary = "Get all cars")
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ofNullable(carRepository.findById(id).orElse(null));
    }

    @Operation(summary = "Delete a car by id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        carRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //api/cars/search?brand=BMW
    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCars(@RequestParam String brand) {
        return ResponseEntity.ok(carRepository.findByBrand(brand));
    }


    @Operation(summary = "Add a new car")
    @PostMapping
    public ResponseEntity<Long> addCar(@RequestBody Car car) {
        Car savedCar = carRepository.save(car);
        if (savedCar == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity(HttpStatusCode.valueOf(201));
    }

    @Operation(summary = "Update one car by id")
    @PutMapping("/{id}")
    public ResponseEntity updateCar(@PathVariable Long id, @RequestBody Car car) {
        if (carRepository.existsById(id)) {
            Car carToUpdate = carRepository.findById(id).orElse(null);
            carToUpdate.setBrand(car.getBrand());
            carToUpdate.setModel(car.getModel());
            carToUpdate.setProductionYear(car.getProductionYear());
            carToUpdate.setMileage(car.getMileage());
            carToUpdate.setPrice(car.getPrice());
            carToUpdate.setStatus(car.getStatus());
            carRepository.save(carToUpdate);
            return ResponseEntity.ok("updated car with id = " + id);
        }
        return ResponseEntity.notFound().build();
    }

    // GET /api/cars/by-price?min=10000&max=20000
    @GetMapping("/by-price")
    public ResponseEntity<List<Car>> searchByPriceBetween(
            @RequestParam int min, @RequestParam int max
    ) {
        return ResponseEntity.ok(carRepository.findByPriceBetween(min, max));
    }
}
