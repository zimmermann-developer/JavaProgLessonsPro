package de.ait.javaproglessonspro.model;


import de.ait.javaproglessonspro.enums.CarStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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

    @Enumerated(EnumType.STRING)
    private CarStatus status;


    public Car(String brand, String model, int productionYear, int mileage, int price, String status) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.mileage = mileage;
        this.price = price;
        this.status = CarStatus.valueOf(status);
    }
}
