package nl.ordina.poolautoapi.externalapi.rdw;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import nl.ordina.poolautoapi.helper.LicensePlateNumber;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClientException;

@SpringBootTest
@ActiveProfiles("test")
public class RDWCarFuelAPITest extends WireMockTest {

    @Autowired
    private RDWCarFuelAPI rdwCarFuelAPI;

    @Test
    void getCorrectRDWCarFuelDataObjectOfLicensePlateNumber() {
        String licensePlateNumberString = "85PHRX";

        wireMockServer.stubFor(WireMock.get("/fuel/" + licensePlateNumberString)
                .willReturn(ResponseDefinitionBuilder.responseDefinition()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                "[{\"kenteken\":\"85PHRX\",\"brandstof_volgnummer\":\"1\",\"brandstof_omschrijving\":\"Benzine\",\"" +
                                        "brandstofverbruik_buiten\":\"7.80\",\"brandstofverbruik_gecombineerd\":\"10.10\",\"" +
                                        "brandstofverbruik_stad\":\"14.60\",\"co2_uitstoot_gecombineerd\":\"245\",\"" +
                                        "geluidsniveau_rijdend\":\"73\",\"geluidsniveau_stationair\":\"79\",\"emissiecode_omschrijving\"" +
                                        ":\"4\",\"milieuklasse_eg_goedkeuring_licht\":\"70/220*2001/100B\",\"" +
                                        "nettomaximumvermogen\":\"154.00\",\"toerental_geluidsniveau\":\"3750\"}]"
        )));

        LicensePlateNumber licensePlateNumber = Mockito.mock(LicensePlateNumber.class);
        Mockito.when(licensePlateNumber.toString()).thenReturn(licensePlateNumberString);
        RDWCarFuelDataObject dataObject = rdwCarFuelAPI.getDataObject(licensePlateNumber);
        Assertions.assertEquals(dataObject.getBrandstof_omschrijving().toLowerCase(), "benzine");
    }

    @Test
    void invalidLicensePlateNumberResultsInRestClientException() {
        String licensePlateNumberString = "12&BCD";

        wireMockServer.stubFor(WireMock.get("/fuel/" + licensePlateNumberString)
                .willReturn(ResponseDefinitionBuilder.responseDefinition()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                "{\"error\" : true, \"message\" : \"Unrecognized arguments [BCD]\"}"
                        )));

        LicensePlateNumber licensePlateNumberMock = Mockito.mock(LicensePlateNumber.class);
        Mockito.when(licensePlateNumberMock.toString()).thenReturn(licensePlateNumberString);
        Assertions.assertThrows(RestClientException.class, () -> rdwCarFuelAPI.getDataObject(licensePlateNumberMock));
    }

    @Test
    void unknownLicensePlateNumberResultsInIndexOutOfBoundsException() {
        String licensePlateNumberString = "12ABCD";

        wireMockServer.stubFor(WireMock.get("/fuel/" + licensePlateNumberString)
                .willReturn(ResponseDefinitionBuilder.responseDefinition()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                "[]"
                        )));

        LicensePlateNumber licensePlateNumberMock = Mockito.mock(LicensePlateNumber.class);
        Mockito.when(licensePlateNumberMock.toString()).thenReturn(licensePlateNumberString);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> rdwCarFuelAPI.getDataObject(licensePlateNumberMock));
    }
}
