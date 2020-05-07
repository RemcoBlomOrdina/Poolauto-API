package nl.ordina.poolautoapi.repository;

import nl.ordina.poolautoapi.exception.NoDataFoundException;
import nl.ordina.poolautoapi.exception.ServerErrorException;
import nl.ordina.poolautoapi.externalapi.rdw.RDWCarDataFuel;
import nl.ordina.poolautoapi.externalapi.rdw.RDWCarDataGeneral;
import nl.ordina.poolautoapi.model.Car;
import nl.ordina.poolautoapi.helper.LicensePlateNumber;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class CarRepository {

    public Car getCar(LicensePlateNumber licensePlateNumber) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();

        RDWCarDataGeneral[] rdwCarDataGeneralArray = Optional.ofNullable(restTemplate.getForObject(RDWCarDataGeneral.API_URL + licensePlateNumber, RDWCarDataGeneral[].class))
                .orElse(new RDWCarDataGeneral[]{new RDWCarDataGeneral()});

        RDWCarDataFuel[] rdwCarDataFuelArray = Optional.ofNullable(restTemplate.getForObject(RDWCarDataFuel.API_URL + licensePlateNumber, RDWCarDataFuel[].class))
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
                rdwCarDataGeneral.getCatalogusprijs(),
                rdwCarDataGeneral.getDatum_eerste_afgifte_nederland(),
                rdwCarDataFuel.getCo2_uitstoot_gecombineerd()
        );
    }
}
