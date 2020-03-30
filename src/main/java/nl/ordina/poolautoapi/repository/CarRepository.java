package nl.ordina.poolautoapi.repository;

import nl.ordina.poolautoapi.model.Car;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class CarRepository {

    public Car getCar(String licensePlateNumber) {
        return new Car(
                "22ZBR8",
                "Skoda",
                "Citigo",
                "hatchback, 4 zitplaatsen",
                "wit",
                "FM5FM5CF0047MGVR2N1FA1SK",
                new String[]{"airco","panoramadak","multimediasysteem"},
                "3 cilinders, 999 inhoud",
                "benzine",
                125000,
                LocalDate.of(2020, 10, 31),
                450,
                11779.00,
                200,
                LocalDate.of(2020,4,30)
        );
    }
}
