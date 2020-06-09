package nl.ordina.poolautoapi.externalapi.rdw;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import nl.ordina.poolautoapi.helper.LicensePlateNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class RDWCarGeneralAPITest extends WireMockTest{

    @Autowired
    private RDWCarGeneralAPI rdwCarGeneralAPI;

    @Test
    void getCorrectRDWCarGeneralDataObjectOfLicensePlateNumber() {
        String licensePlateNumberString = "85PHRX";

        wireMockServer.stubFor(WireMock.get("/general/" + licensePlateNumberString)
                .willReturn(ResponseDefinitionBuilder.responseDefinition()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                "[{\"kenteken\":\"85PHRX\",\"voertuigsoort\":\"Personenauto\",\"merk\":\"VOLVO\"," +
                                        "\"handelsbenaming\":\"V70\",\"vervaldatum_apk\":\"20210128\",\"" +
                                        "datum_tenaamstelling\":\"20190129\",\"bruto_bpm\":\"13051\",\"inrichting\":\"" +
                                        "stationwagen\",\"aantal_zitplaatsen\":\"5\",\"eerste_kleur\":\"GRIJS\",\"" +
                                        "tweede_kleur\":\"Niet geregistreerd\",\"aantal_cilinders\":\"5\",\"" +
                                        "cilinderinhoud\":\"2521\",\"massa_ledig_voertuig\":\"1566\",\"" +
                                        "toegestane_maximum_massa_voertuig\":\"2150\",\"massa_rijklaar\":\"1666\",\"" +
                                        "maximum_massa_trekken_ongeremd\":\"750\",\"maximum_trekken_massa_geremd\":" +
                                        "\"1800\",\"zuinigheidslabel\":\"E\",\"datum_eerste_toelating\":\"20040701\"," +
                                        "\"datum_eerste_afgifte_nederland\":\"20040701\",\"wacht_op_keuren\":\"Geen " +
                                        "verstrekking in Open Data\",\"wam_verzekerd\":\"Ja\",\"aantal_deuren\":\"4" +
                                        "\",\"aantal_wielen\":\"4\",\"afstand_hart_koppeling_tot_achterzijde_voertuig" +
                                        "\":\"0\",\"afstand_voorzijde_voertuig_tot_hart_koppeling\":\"0\",\"lengte\":" +
                                        "\"471\",\"breedte\":\"0\",\"europese_voertuigcategorie\":\"M1\",\"" +
                                        "plaats_chassisnummer\":\"midden tegen schutbord onder motorkap\",\"" +
                                        "technische_max_massa_voertuig\":\"2150\",\"typegoedkeuringsnummer\":\"" +
                                        "e4*2001/116*0040*08\",\"variant\":\"SW59\",\"uitvoering\":\"SW59G9A0\",\"" +
                                        "volgnummer_wijziging_eu_typegoedkeuring\":\"0\",\"vermogen_massarijklaar\"" +
                                        ":\"0.09\",\"wielbasis\":\"276\",\"export_indicator\":\"Nee\",\"" +
                                        "openstaande_terugroepactie_indicator\":\"Nee\",\"taxi_indicator\":\"Nee\"," +
                                        "\"maximum_massa_samenstelling\":\"3950\",\"aantal_rolstoelplaatsen\":\"0\"," +
                                        "\"api_gekentekende_voertuigen_assen\":\"https://opendata.rdw.nl/resource/3huj-srit.json" +
                                        "\",\"api_gekentekende_voertuigen_brandstof\":\"https://opendata.rdw.nl/resource/8ys7-d773.json" +
                                        "\",\"api_gekentekende_voertuigen_carrosserie\":\"https://opendata.rdw.nl/resource/vezc-m2t6.json" +
                                        "\",\"api_gekentekende_voertuigen_carrosserie_specifiek\":\"https://opendata.rdw.nl/resource/jhie-znh9.json" +
                                        "\",\"api_gekentekende_voertuigen_voertuigklasse\":\"https://opendata.rdw.nl/resource/kmfi-hrps.json\"}]"
                        )));

        LicensePlateNumber licensePlateNumber = Mockito.mock(LicensePlateNumber.class);
        Mockito.when(licensePlateNumber.toString()).thenReturn(licensePlateNumberString);
        RDWCarGeneralDataObject dataObject = rdwCarGeneralAPI.getDataObject(licensePlateNumber);
        Assertions.assertEquals(dataObject.getKenteken(), licensePlateNumber.toString());
    }
}
