package nl.ordina.poolautoapi.service;

import nl.ordina.poolautoapi.externalapi.rdw.RDWCarFuelAPI;
import nl.ordina.poolautoapi.externalapi.rdw.RDWCarFuelDataObject;
import nl.ordina.poolautoapi.externalapi.rdw.RDWCarGeneralAPI;
import nl.ordina.poolautoapi.externalapi.rdw.RDWCarGeneralDataObject;
import nl.ordina.poolautoapi.model.Car;
import nl.ordina.poolautoapi.helper.LicensePlateNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private RDWCarGeneralAPI rdwCarGeneralAPI;

    @Autowired
    private RDWCarFuelAPI rdwCarFuelAPI;

    public Car getCar(String licensePlateNumberString) {
        LicensePlateNumber licensePlateNumber = new LicensePlateNumber(licensePlateNumberString);

        RDWCarGeneralDataObject rdwCarGeneralDataObject = rdwCarGeneralAPI.getDataObject(licensePlateNumber);
        RDWCarFuelDataObject rdwCarFuelDataObject = rdwCarFuelAPI.getDataObject(licensePlateNumber);

        return new Car(
                rdwCarGeneralDataObject.getKenteken(),
                rdwCarGeneralDataObject.getMerk(),
                rdwCarGeneralDataObject.getHandelsbenaming(),
                rdwCarGeneralDataObject.getInrichting(),
                rdwCarGeneralDataObject.getEerste_kleur(),
                rdwCarGeneralDataObject.getUitvoering(),
                rdwCarGeneralDataObject.getAantal_cilinders(),
                rdwCarGeneralDataObject.getCilinderinhoud(),
                rdwCarFuelDataObject.getBrandstof_omschrijving(),
                rdwCarGeneralDataObject.getZuinigheidslabel(),
                rdwCarGeneralDataObject.getCatalogusprijs(),
                rdwCarGeneralDataObject.getDatum_eerste_afgifte_nederland(),
                rdwCarFuelDataObject.getCo2_uitstoot_gecombineerd()
        );

//        System.getProperties().keySet()
//        user home
    }
}
