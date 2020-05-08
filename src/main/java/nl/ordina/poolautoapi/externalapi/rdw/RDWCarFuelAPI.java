package nl.ordina.poolautoapi.externalapi.rdw;

import nl.ordina.poolautoapi.helper.LicensePlateNumber;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class RDWCarFuelAPI extends RestTemplate {

    public static final String API_URL = "https://opendata.rdw.nl/resource/8ys7-d773.json?kenteken=";

    public RDWCarFuelDataObject getDataObject(LicensePlateNumber licensePlateNumber) {
        RDWCarFuelDataObject[] rdwCarFuelDataObjectArray = Optional.ofNullable(
                getForObject(API_URL + licensePlateNumber, RDWCarFuelDataObject[].class))
                .orElse(new RDWCarFuelDataObject[]{new RDWCarFuelDataObject()});

        return rdwCarFuelDataObjectArray[0];
    }
}
