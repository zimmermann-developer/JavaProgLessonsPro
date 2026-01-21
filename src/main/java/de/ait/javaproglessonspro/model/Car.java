package de.ait.javaproglessonspro.model;


import de.ait.javaproglessonspro.enums.CarStatus;
import de.ait.javaproglessonspro.enums.FuelType;
import de.ait.javaproglessonspro.enums.Transmission;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Brand must not be empty")
    private String brand;

    @Column(nullable = false)
    @NotBlank(message = "Model must not be empty")
    private String model;

    @Column(name = "production_year")
    @Min(value = 1900, message = "Year must be greater than 1900")
    private int productionYear;

    @Min(value = 0, message = "Mileage must be greater than 0")
    private int mileage;

    @Min(value = 1, message = "Price must be greater than 0")
    private int price;

    @NotNull(message = "Status must not be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus status;

    @NotNull(message = "Fuel type must not be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @NotNull(message = "Transmission must not be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transmission transmission;

    @Column(nullable = false)
    @NotBlank(message = "Color must not be empty")
    private String color;

    @Column(nullable = false)
    @Min(value = 1, message = "Horsepower must be greater than 0")
    private int horsepower;


    public Car(String brand, String model,
               int productionYear,
               int mileage,
               int price,
               String status,
               String color,
               int horsepower,
               String fuelType,
               String transmission) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.mileage = mileage;
        this.price = price;
        this.status = CarStatus.valueOf(status);
        this.color = color;
        this.horsepower = horsepower;
        this.fuelType = FuelType.valueOf(fuelType);
        this.transmission = Transmission.valueOf(transmission);
    }
}
