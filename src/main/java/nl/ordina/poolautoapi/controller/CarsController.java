package nl.ordina.poolautoapi.controller;

import lombok.AllArgsConstructor;
import nl.ordina.poolautoapi.model.Car;
import nl.ordina.poolautoapi.repository.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cars")
@AllArgsConstructor
public class CarsController {

    private final CarRepository carRepository;

    @GetMapping
    @RequestMapping("{licensePlateNumber}")
    public Car getCar(@PathVariable String licensePlateNumber) {
        return carRepository.getCar(licensePlateNumber);
    }
}
