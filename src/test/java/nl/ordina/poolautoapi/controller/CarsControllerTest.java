package nl.ordina.poolautoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import nl.ordina.poolautoapi.model.Car;
import nl.ordina.poolautoapi.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CarsController.class})
public class CarsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService mockCarService;

    private static final String VALID_API_URL = "/api/v1/cars/";

    @Test
    void getCarObjectReturns200() throws Exception {
        String validLicensePlateNumber = "85PHRX";
        Car car = Mockito.mock(Car.class);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        Mockito.when(mockCarService.getCar(Mockito.any(String.class))).thenReturn(car);
        mockMvc.perform(get(VALID_API_URL + "{licensePlateNumber}", validLicensePlateNumber))
                .andExpect(status().isOk());
    }

    @Test
    void illegalArgumentExceptionReturns400() throws Exception {
        String invalidLicensePlateNumber = "EF34HJK";
        Mockito.when(mockCarService.getCar(invalidLicensePlateNumber)).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(get(VALID_API_URL + "{licensePlateNumber}", invalidLicensePlateNumber))
                .andExpect(status().isBadRequest());
    }

    @Test
    void indexOutOfBoundsExceptionReturns404() throws Exception {
        String unknownLicensePlateNumber = "EF34HJ";
        Mockito.when(mockCarService.getCar(unknownLicensePlateNumber)).thenThrow(IndexOutOfBoundsException.class);
        mockMvc.perform(get(VALID_API_URL + "{licensePlateNumber}", unknownLicensePlateNumber))
                .andExpect(status().isNotFound());
    }

    @Test
    void restClientExceptionReturns500() throws Exception {
        String validLicensePlateNumber = "XVNZ71";
        Mockito.when(mockCarService.getCar(validLicensePlateNumber)).thenThrow(RestClientException.class);
        mockMvc.perform(get(VALID_API_URL + "{licensePlateNumber}", validLicensePlateNumber))
                .andExpect(status().isInternalServerError());
    }


}
