package nl.ordina.poolautoapi.repository;

import nl.ordina.poolautoapi.externalapi.rdw.RDWCarDataFuel;
import nl.ordina.poolautoapi.externalapi.rdw.RDWCarDataGeneral;
import nl.ordina.poolautoapi.model.Car;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class CarRepository {

    public Car getCar(String licensePlateNumber) {
        RestTemplate restTemplate = new RestTemplate();
        RDWCarDataGeneral[] rdwCarDataGeneralArray =
                Optional.ofNullable(restTemplate.getForObject(RDWCarDataGeneral.API_URL + licensePlateNumber, RDWCarDataGeneral[].class))
                        .orElse(new RDWCarDataGeneral[]{new RDWCarDataGeneral()});
        RDWCarDataFuel[] rdwCarDataFuelArray =
                Optional.ofNullable(restTemplate.getForObject(RDWCarDataFuel.API_URL + licensePlateNumber, RDWCarDataFuel[].class))
                        .orElse(new RDWCarDataFuel[]{new RDWCarDataFuel()});

        RDWCarDataGeneral rdwCarDataGeneral = rdwCarDataGeneralArray[0];
        RDWCarDataFuel rdwCarDataFuel = rdwCarDataFuelArray[0];

        return new Car(
                rdwCarDataGeneral.getKenteken(),
                rdwCarDataGeneral.getMerk(),
                rdwCarDataGeneral.getHandelsbenaming(),
                rdwCarDataGeneral.getInrichting(),
                rdwCarDataGeneral.getEerste_kleur(),
                rdwCarDataGeneral.getUitvoering(),
                rdwCarDataGeneral.getAantal_cilinders() + " cilinders met een inhoud van " + rdwCarDataGeneral.getCilinderinhoud(),
                rdwCarDataFuel.getBrandstof_omschrijving(),
                rdwCarDataGeneral.getCatalogusprijs()
        );
    }
}
