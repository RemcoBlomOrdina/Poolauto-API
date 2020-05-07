package nl.ordina.poolautoapi.controller;

import lombok.AllArgsConstructor;
import nl.ordina.poolautoapi.exception.IncorrectFormatException;
import nl.ordina.poolautoapi.exception.NoDataFoundException;
import nl.ordina.poolautoapi.exception.ServerErrorException;
import nl.ordina.poolautoapi.model.Car;
import nl.ordina.poolautoapi.helper.LicensePlateNumber;
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
        try {
            return carRepository.getCar(new LicensePlateNumber(licensePlateNumber));
        } catch (IllegalArgumentException e) {
            throw new IncorrectFormatException(e);
        } catch (IndexOutOfBoundsException e) {
            throw new NoDataFoundException(e);
        } catch (Exception e) {
            throw new ServerErrorException(e);
        }
    }


}
