package nl.ordina.poolautoapi.model;

public class LicensePlateNumber {

    private final String licensePlateNumber;

    public LicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber.toUpperCase();
    }

    @Override
    public String toString() {
        return licensePlateNumber;
    }
}
