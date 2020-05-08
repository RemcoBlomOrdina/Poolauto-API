package nl.ordina.poolautoapi.externalapi.rdw;

import nl.ordina.poolautoapi.helper.LicensePlateNumber;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class RDWCarGeneralAPI extends RestTemplate {

    public static final String API_URL = "https://opendata.rdw.nl/resource/m9d7-ebf2.json?kenteken=";

    public RDWCarGeneralDataObject getDataObject(LicensePlateNumber licensePlateNumber) {
        RDWCarGeneralDataObject[] rdwCarGeneralDataObjectArray = Optional.ofNullable(
                getForObject(API_URL + licensePlateNumber, RDWCarGeneralDataObject[].class))
                .orElse(new RDWCarGeneralDataObject[]{new RDWCarGeneralDataObject()});

        return rdwCarGeneralDataObjectArray[0];
    }
}
