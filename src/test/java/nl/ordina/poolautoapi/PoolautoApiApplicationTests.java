package nl.ordina.poolautoapi;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import nl.ordina.poolautoapi.externalapi.rdw.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PoolautoApiApplicationTests extends WireMockTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String VALID_API_URL = "/api/v1/cars/";

    @Test
    void getCarObjectForValidLicensePlateNumber() throws Exception {
        String kenteken = "95HFS9";

        wireMockServer.stubFor(WireMock.get("/general/" + kenteken)
                .willReturn(ResponseDefinitionBuilder.responseDefinition()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"kenteken\":\"95HFS9\",\"voertuigsoort\":\"Personenauto\",\"merk\":\"VOLKSWAGEN\"" +
                                        ",\"handelsbenaming\":\"GOLF\",\"vervaldatum_apk\":\"20210223\",\"datum_tenaamstelling" +
                                        "\":\"20150223\",\"bruto_bpm\":\"5052\",\"inrichting\":\"stationwagen\",\"" +
                                        "aantal_zitplaatsen\":\"5\",\"eerste_kleur\":\"ZWART\",\"tweede_kleur\":\"" +
                                        "Niet geregistreerd\",\"aantal_cilinders\":\"4\",\"cilinderinhoud\":\"1595\",\"" +
                                        "massa_ledig_voertuig\":\"1148\",\"toegestane_maximum_massa_voertuig\":\"1800" +
                                        "\",\"massa_rijklaar\":\"1248\",\"maximum_massa_trekken_ongeremd\":\"620\"," +
                                        "\"maximum_trekken_massa_geremd\":\"1200\",\"zuinigheidslabel\":\"D\",\"" +
                                        "datum_eerste_toelating\":\"20081110\",\"datum_eerste_afgifte_nederland\":\"" +
                                        "20081110\",\"wacht_op_keuren\":\"Geen verstrekking in Open Data\",\"catalogusprijs" +
                                        "\":\"22196\",\"wam_verzekerd\":\"Ja\",\"aantal_deuren\":\"0\",\"aantal_wielen" +
                                        "\":\"4\",\"afstand_hart_koppeling_tot_achterzijde_voertuig\":\"0\",\"" +
                                        "afstand_voorzijde_voertuig_tot_hart_koppeling\":\"0\",\"lengte\":\"0\",\"" +
                                        "breedte\":\"0\",\"europese_voertuigcategorie\":\"M1\",\"plaats_chassisnummer" +
                                        "\":\"r. op voorwielscherm onder motorkap\",\"technische_max_massa_voertuig\"" +
                                        ":\"1800\",\"type\":\"1K\",\"typegoedkeuringsnummer\":\"e1*2001/116*0242*24\"" +
                                        ",\"variant\":\"ACBSEX0\",\"uitvoering\":\"FM5FM5AF006N0GGN1\",\"" +
                                        "volgnummer_wijziging_eu_typegoedkeuring\":\"0\",\"vermogen_massarijklaar\":\"" +
                                        "0.06\",\"wielbasis\":\"257\",\"export_indicator\":\"Nee\",\"" +
                                        "openstaande_terugroepactie_indicator\":\"Nee\",\"taxi_indicator\":\"Nee\",\"" +
                                        "maximum_massa_samenstelling\":\"3000\",\"aantal_rolstoelplaatsen\":\"0\",\"" +
                                        "api_gekentekende_voertuigen_assen\":\"https://opendata.rdw.nl/resource/3huj-srit.json" +
                                        "\",\"api_gekentekende_voertuigen_brandstof\":\"https://opendata.rdw.nl/resource/8ys7-d773.json" +
                                        "\",\"api_gekentekende_voertuigen_carrosserie\":\"https://opendata.rdw.nl/resource/vezc-m2t6.json" +
                                        "\",\"api_gekentekende_voertuigen_carrosserie_specifiek\":\"https://opendata.rdw.nl/resource/jhie-znh9.json" +
                                        "\",\"api_gekentekende_voertuigen_voertuigklasse\":\"https://opendata.rdw.nl/resource/kmfi-hrps.json\"}]")));

        wireMockServer.stubFor(WireMock.get("/fuel/" + kenteken)
                .willReturn(ResponseDefinitionBuilder.responseDefinition()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"kenteken\":\"95HFS9\",\"brandstof_volgnummer\":\"1\",\"brandstof_omschrijving\"" +
                                ":\"Benzine\",\"brandstofverbruik_buiten\":\"6.10\",\"brandstofverbruik_gecombineerd\"" +
                                ":\"7.40\",\"brandstofverbruik_stad\":\"9.90\",\"co2_uitstoot_gecombineerd\":\"176\"," +
                                "\"geluidsniveau_stationair\":\"81\",\"emissiecode_omschrijving\":\"4\",\"" +
                                "milieuklasse_eg_goedkeuring_licht\":\"70/220*2006/96B\",\"nettomaximumvermogen\":\"" +
                                "75.00\",\"toerental_geluidsniveau\":\"4200\"}]")));

        MvcResult mvcResult = mockMvc.perform(get(VALID_API_URL + "{licensePlateNumber}", kenteken)).andReturn();

        String result = "{\"kenteken\":\"95HFS9\",\"merk\":\"VOLKSWAGEN\",\"model\":\"GOLF\",\"type\":\"stationwagen\"," +
                "\"kleur\":\"zwart\",\"uitvoering\":\"FM5FM5AF006N0GGN1\",\"aantal_cilinders\":\"4\",\"cilinderinhoud\":" +
                "\"1595\",\"brandstof\":\"benzine\",\"milieulabel\":\"D\",\"cataloguswaarde\":\"22196\",\"" +
                "datum_eerste_afgifte\":\"20081110\",\"bijtellingspercentage\":null,\"" +
                "bruto_bijtelling_per_maand\":null}";

        Assertions.assertEquals(result, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void invalidCharacterResultsInInvalidCharacterExceptionSoUsersGet400APIError() throws Exception {
        String kenteken = "95HF&9";

        wireMockServer.stubFor(WireMock.get("/general/" + kenteken)
                .willReturn(ResponseDefinitionBuilder.responseDefinition()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"error\" : true,\n" +
                                "  \"message\" : \"Unrecognized arguments [9]\"\n" +
                                "}")));

        wireMockServer.stubFor(WireMock.get("/fuel/" + kenteken)
                .willReturn(ResponseDefinitionBuilder.responseDefinition()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"error\" : true,\n" +
                                "  \"message\" : \"Unrecognized arguments [9]\"\n" +
                                "}")));

        MvcResult mvcResult = mockMvc.perform(get(VALID_API_URL + "{licensePlateNumber}", kenteken)).andReturn();

        String result = "{\"error\":true,\"httpStatusCode\":400,\"httpStatusBeschrijving\":\"Bad Request\",\"oorzaak" +
                "\":\"Het opgegeven kenteken is ongeldig. Een geldig kenteken bestaat uit zes alfnumerieke tekens.\"}";

        Assertions.assertEquals(result, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
}
