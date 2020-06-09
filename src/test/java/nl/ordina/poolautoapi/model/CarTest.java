package nl.ordina.poolautoapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    void electricCarOfYear2015Is4PercentAddition() {
        Car car = new Car("12ABCD","TestCar", "ModelT", "SUV", "Wit", "1234",
                "3", "999", "elektriciteit","A","41000", "20150712", "0");

        Assertions.assertEquals(car.getBijtellingspercentage(), "4");
    }

    @Test
    void electricCarOfYear2019WithCatalogueValueOf50001Is22PercentAddition() {
        Car car = new Car("12ABCD","TestCar", "ModelT", "SUV", "Wit", "1234",
                "3", "999", "elektriciteit","A","50001", "20190912", "0");
        Assertions.assertEquals(car.getBijtellingspercentage(), "22");
    }

    @Test
    void electricCarOfYear2019WithCatalogueValueOf50000Is4PercentAddition() {
        Car car = new Car("12ABCD","TestCar", "ModelT", "SUV", "Wit", "1234",
                "3", "999", "elektriciteit","A","50000", "20190912", "0");
        Assertions.assertEquals(car.getBijtellingspercentage(), "4");
    }

    @Test
    void electricCarOfYear2020WithCatalogueValueOf45001Is22PercentAddition() {
        Car car = new Car("12ABCD","TestCar", "ModelT", "SUV", "Wit", "1234",
                "3", "999", "elektriciteit","A","45001", "20200212", "0");
        Assertions.assertEquals(car.getBijtellingspercentage(), "22");
    }

    @Test
    void electricCarOfYear2020WithCatalogueValueOf45000Is8PercentAddition() {
        Car car = new Car("12ABCD","TestCar", "ModelT", "SUV", "Wit", "1234",
                "3", "999", "elektriciteit","A","45000", "20200111", "0");
        Assertions.assertEquals(car.getBijtellingspercentage(), "8");
    }

    @Test
    void electricCarOfYear2013Is0PercentAddition() {
        Car car = new Car("12ABCD","TestCar", "ModelT", "SUV", "Wit", "1234",
                "3", "999", "elektriciteit","A","50000", "20130922", "0");
        Assertions.assertEquals(car.getBijtellingspercentage(), "0");
    }

    @Test
    void petrolCarOfYear2020Is22PercentAddition() {
        Car car = new Car("12ABYZ","TestCar", "ModelB", "SUV", "Wit", "1234",
                "3", "999", "benzine","C","50000", "20200922", "100");
        Assertions.assertEquals(car.getBijtellingspercentage(), "22");
    }

    @Test
    void dieselCarOfYear2020Is22PercentAddition() {
        Car car = new Car("12ABYZ","TestCar", "ModelD", "SUV", "Wit", "1234",
                "3", "999", "diesel","C","50000", "20200922", "110");
        Assertions.assertEquals(car.getBijtellingspercentage(), "22");
    }

    @Test
    void petrolCarOfYear2010ReturnsNull() {
        Car car = new Car("12ABYZ","TestCar", "ModelB", "SUV", "Wit", "1234",
                "3", "999", "benzine","C","50000", "20100922", "100");
        Assertions.assertNull(car.getBijtellingspercentage());
    }
}
