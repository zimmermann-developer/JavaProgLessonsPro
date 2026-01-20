package de.ait.javaproglessonspro.model;

import de.ait.javaproglessonspro.enums.CarStatus;
import de.ait.javaproglessonspro.enums.FuelType;
import de.ait.javaproglessonspro.enums.Transmission;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand must not be empty")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "Model must not be empty")
    @Column(nullable = false)
    private String model;

    @Min(value = 1900, message = "Year must be 1900 or greater")
    @Column(name = "production_year", nullable = false)
    private int productionYear;

    @Min(value = 0, message = "Mileage must be 0 or greater")
    @Column(nullable = false)
    private int mileage;

    @Min(value = 1, message = "Price must be 1 or greater")
    @Column(nullable = false)
    private int price;

    @NotNull(message = "Status must not be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus status;

    @NotNull(message = "FuelType must not be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;

    @NotNull(message = "Transmission must not be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transmission transmission;

    @NotBlank(message = "Color must not be empty")
    @Column(nullable = false)
    private String color;

    @Min(value = 1, message = "Horsepower must be 1 or greater")
    @Column(nullable = false)
    private int horsepower;
}