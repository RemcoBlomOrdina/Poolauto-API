package nl.ordina.poolautoapi.externalapi.rdw;

import nl.ordina.poolautoapi.helper.LicensePlateNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class RDWCarGeneralAPI extends RestTemplate {

    @Value("${poolauto.cardata.general.url}")
    private String API_URL;

    public RDWCarGeneralDataObject getDataObject(LicensePlateNumber licensePlateNumber) {
        RDWCarGeneralDataObject[] rdwCarGeneralDataObjectArray = Optional.ofNullable(
                getForObject(API_URL + licensePlateNumber, RDWCarGeneralDataObject[].class))
                .orElse(new RDWCarGeneralDataObject[]{new RDWCarGeneralDataObject()});

        return rdwCarGeneralDataObjectArray[0];
    }
}
