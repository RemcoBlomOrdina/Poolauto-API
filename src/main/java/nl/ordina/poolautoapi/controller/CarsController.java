package nl.ordina.poolautoapi.controller;

import lombok.AllArgsConstructor;
import nl.ordina.poolautoapi.model.Car;
import nl.ordina.poolautoapi.model.LicensePlateNumber;
import nl.ordina.poolautoapi.repository.CarRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cars")
@AllArgsConstructor
@CrossOrigin
public class CarsController {

    private final CarRepository carRepository;

    @GetMapping
    @RequestMapping("{licensePlateNumber}")
    public Car getCar(@PathVariable String licensePlateNumber) {
        return carRepository.getCar(new LicensePlateNumber(licensePlateNumber));
    }
}
