package nl.ordina.poolautoapi.repository;

import nl.ordina.poolautoapi.model.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository {

    public Car getCar(String licensePlateNumber) {
        return new Car(licensePlateNumber);
    }
}
