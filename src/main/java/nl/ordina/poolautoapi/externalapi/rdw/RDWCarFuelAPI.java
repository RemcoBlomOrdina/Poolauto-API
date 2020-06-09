package nl.ordina.poolautoapi.externalapi.rdw;

import nl.ordina.poolautoapi.helper.LicensePlateNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class RDWCarFuelAPI extends RestTemplate {

    @Value("${poolauto.cardata.fuel.url}")
    private String API_URL;

    public RDWCarFuelDataObject getDataObject(LicensePlateNumber licensePlateNumber) {
        System.out.println("------TEST-------" + API_URL);
        RDWCarFuelDataObject[] rdwCarFuelDataObjectArray = Optional.ofNullable(
                getForObject(API_URL + licensePlateNumber, RDWCarFuelDataObject[].class))
                .orElse(new RDWCarFuelDataObject[]{new RDWCarFuelDataObject()});

        return rdwCarFuelDataObjectArray[0];
    }
}
