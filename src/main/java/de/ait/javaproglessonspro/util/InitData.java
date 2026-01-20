package de.ait.javaproglessonspro.util;

import de.ait.javaproglessonspro.model.Car;
import de.ait.javaproglessonspro.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitData {
    /**@Bean
    CommandLineRunner initDatabase(CarRepository carRepository) {
        return args -> {
            if(carRepository.count() == 0){
                carRepository.save(new Car("BMW", "X5", 2019, 100000, 50000, "AVAILABLE"));
                carRepository.save(new Car("Audi", "A4", 2018, 100000, 40000, "AVAILABLE"));
                carRepository.save(new Car("Mercedes", "C-Class", 2020, 80000, 60000, "AVAILABLE"));
            }
        };
    }*/
}
