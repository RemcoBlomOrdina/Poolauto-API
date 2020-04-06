package nl.ordina.poolautoapi.externalapi.rdw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RDWCarDataFuel {

    public static final String API_URL = "https://opendata.rdw.nl/resource/8ys7-d773.json?kenteken=";

    private String brandstof_omschrijving;
}
