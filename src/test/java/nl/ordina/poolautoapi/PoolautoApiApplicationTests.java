package nl.ordina.poolautoapi;

import com.fasterxml.jackson.databind.SerializationFeature;
import nl.ordina.poolautoapi.model.Car;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PoolautoApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String VALID_API_URL = "/api/v1/cars/";

    @Test
    void getCarObjectForValidLicensePlateNumber() throws Exception {
        String validLicensePlateNumber = "85PHRX";
        Car car = Mockito.mock(Car.class);
        MvcResult mvcResult = mockMvc.perform(get(VALID_API_URL + "{licensePlateNumber}", validLicensePlateNumber)).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
//                .andExpect(status().isOk());
    }

}
