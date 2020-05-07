package nl.ordina.poolautoapi.controller;

import nl.ordina.poolautoapi.exception.ErrorHandler;
import nl.ordina.poolautoapi.exception.NoDataFoundException;
import nl.ordina.poolautoapi.helper.LicensePlateNumber;
import nl.ordina.poolautoapi.model.Car;
import nl.ordina.poolautoapi.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CarsController.class, ErrorHandler.class})
public class CarsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext context;

    @MockBean
    private CarRepository mockCarRepository;

    @MockBean
    private LicensePlateNumber mockLicensePlateNumber;

    @MockBean
    private Car car;

    private static final String VALID_API_URL = "/api/v1/cars/";

//    @Test
//    void validInputReturns200() throws Exception {
//        String validLicensePlateNumber = "85PHRX";
//        Mockito.when(carRepository.getCar(licensePlateNumber)).thenReturn(car);
//        mockMvc.perform(get(VALID_API_URL + "{licensePlateNumber}", validLicensePlateNumber))
//                .andExpect(status().isOk());
//    }

    @Test
    void invalidInputReturns400() throws Exception {
        String invalidLicensePlateNumber = "EF34HJK";
        mockMvc.perform(get(VALID_API_URL + "{licensePlateNumber}", invalidLicensePlateNumber))
                .andExpect(status().isBadRequest());
    }

    @Test
    void unknownLicensePlateNumberReturns404() throws Exception {
        String unknownLicensePlateNumber = "EF34HJ";
        Mockito.when(mockCarRepository.getCar(mockLicensePlateNumber)).thenThrow(IndexOutOfBoundsException.class);

        //CarRepository carRepository = context.getBean(CarRepository.class);

        MvcResult result = mockMvc.perform(get(VALID_API_URL + "{licensePlateNumber}", unknownLicensePlateNumber).contentType("application/json")).andReturn();
        String json = result.getResponse().getContentAsString();
        System.out.println("Test " + json);
        Assertions.assertTrue(json.contains("404"));
    }
}
