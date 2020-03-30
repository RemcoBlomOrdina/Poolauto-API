package nl.ordina.poolautoapi.controller;

import nl.ordina.poolautoapi.model.Car;
import nl.ordina.poolautoapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cars")
public class CarsController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    @RequestMapping("{licensePlateNumber}")
    public Car getCar(@PathVariable String licensePlateNumber) {
        return carRepository.getCar(licensePlateNumber);
    }
}
