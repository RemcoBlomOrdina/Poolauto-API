package nl.ordina.poolautoapi.repository;

import nl.ordina.poolautoapi.externalapi.rdw.RDWCarFuelAPI;
import nl.ordina.poolautoapi.externalapi.rdw.RDWCarFuelDataObject;
import nl.ordina.poolautoapi.externalapi.rdw.RDWCarGeneralAPI;
import nl.ordina.poolautoapi.externalapi.rdw.RDWCarGeneralDataObject;
import nl.ordina.poolautoapi.model.Car;
import nl.ordina.poolautoapi.helper.LicensePlateNumber;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class CarRepository {

    public Car getCar(String licensePlateNumberString) {
        LicensePlateNumber licensePlateNumber = new LicensePlateNumber(licensePlateNumberString);

        RDWCarGeneralDataObject rdwCarGeneralDataObject = new RDWCarGeneralAPI().getDataObject(licensePlateNumber);
        RDWCarFuelDataObject rdwCarFuelDataObject = new RDWCarFuelAPI().getDataObject(licensePlateNumber);

        return new Car(
                rdwCarGeneralDataObject.getKenteken(),
                rdwCarGeneralDataObject.getMerk(),
                rdwCarGeneralDataObject.getHandelsbenaming(),
                rdwCarGeneralDataObject.getInrichting(),
                rdwCarGeneralDataObject.getEerste_kleur(),
                rdwCarGeneralDataObject.getUitvoering(),
                rdwCarGeneralDataObject.getAantal_cilinders() + " cilinders met een inhoud van " + rdwCarGeneralDataObject.getCilinderinhoud(),
                rdwCarFuelDataObject.getBrandstof_omschrijving(),
                rdwCarGeneralDataObject.getCatalogusprijs(),
                rdwCarGeneralDataObject.getDatum_eerste_afgifte_nederland(),
                rdwCarFuelDataObject.getCo2_uitstoot_gecombineerd()
        );
    }
}
