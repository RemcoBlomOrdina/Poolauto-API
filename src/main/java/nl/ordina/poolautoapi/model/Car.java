package nl.ordina.poolautoapi.model;

import lombok.Getter;

@Getter
public class Car {

    private String brand;
    private String type;

    public Car(String licensePlateNumber) {
        this.brand = "BMW";
        this.type = "M3 GTR";
    }
}
