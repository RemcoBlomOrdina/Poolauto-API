package nl.ordina.poolautoapi.helper;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LicensePlateNumberTest {

    @Test
    void constructorThrowsIllegalArgumentExceptionOnStringParameterWithSevenNumbersAndLetters() {
        assertThrows(IllegalArgumentException.class, () -> new LicensePlateNumber("AB12CDE"));
    }

    @Test
    void constructorThrowsIllegalArgumentExceptionOnStringParameterWithFiveNumbersAndLetters() {
        assertThrows(IllegalArgumentException.class, () -> new LicensePlateNumber("FG45H"));
    }

    @Test
    void toStringReturnsCapitalizedInputLicenseNumber() {
        assertEquals("JK67LM", new LicensePlateNumber("jk67lm").toString());
    }

    @Test
    void constructorThrowsIllegalArgumentExceptionOnStringParameterWithDollarCharacter() {
        assertThrows(IllegalArgumentException.class, () -> new LicensePlateNumber("NP89R$"));
    }

    @Test
    void constructorThrowsIllegalArgumentExceptionOnStringParameterWithCharacterContainingDiaeresis() {
        assertThrows(IllegalArgumentException.class, () -> new LicensePlateNumber("TÜ01VW"));
    }
}
