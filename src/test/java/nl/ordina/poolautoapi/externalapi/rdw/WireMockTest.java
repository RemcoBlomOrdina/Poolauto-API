package nl.ordina.poolautoapi.externalapi.rdw;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class WireMockTest {

    protected static int wireMockPort = 8090;

    protected static WireMockServer wireMockServer;

    @BeforeAll
    protected static void startWireMockServer() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(wireMockPort));
        wireMockServer.start();
    }

    @AfterAll
    protected static void stopWireMockServer() {
        wireMockServer.stop();
    }
}
