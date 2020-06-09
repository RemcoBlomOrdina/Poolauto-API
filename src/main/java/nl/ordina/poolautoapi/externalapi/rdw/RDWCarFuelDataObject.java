package nl.ordina.poolautoapi.externalapi.rdw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RDWCarFuelDataObject {

    private String brandstof_omschrijving;
    private String co2_uitstoot_gecombineerd;
}
