package nl.ordina.poolautoapi.repository;

import nl.ordina.poolautoapi.externalapi.rdw.RDWCarDataFuel;
import nl.ordina.poolautoapi.externalapi.rdw.RDWCarDataGeneral;
import nl.ordina.poolautoapi.model.Car;
import nl.ordina.poolautoapi.model.LicensePlateNumber;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class CarRepository {

    public Car getCar(LicensePlateNumber licensePlateNumber) {
        RestTemplate restTemplate = new RestTemplate();

        RDWCarDataGeneral[] rdwCarDataGeneralArray = null;
        try {
            rdwCarDataGeneralArray = Optional.ofNullable(restTemplate.getForObject(RDWCarDataGeneral.API_URL + licensePlateNumber, RDWCarDataGeneral[].class))
                    .orElse(new RDWCarDataGeneral[]{new RDWCarDataGeneral()});
        } catch (RestClientException e) {
            System.out.println("RestClientException RDWCarDataGeneral");
            rdwCarDataGeneralArray = new RDWCarDataGeneral[]{new RDWCarDataGeneral()};
            e.printStackTrace();
        }

        RDWCarDataFuel[] rdwCarDataFuelArray = null;
        try {
            rdwCarDataFuelArray = Optional.ofNullable(restTemplate.getForObject(RDWCarDataFuel.API_URL + licensePlateNumber, RDWCarDataFuel[].class))
                    .orElse(new RDWCarDataFuel[]{new RDWCarDataFuel()});
        } catch (RestClientException e) {
            System.out.println("RestClientException RDWCarDataFuel");
            rdwCarDataFuelArray = new RDWCarDataFuel[]{new RDWCarDataFuel()};
            e.printStackTrace();
        }

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
