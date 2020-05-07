package nl.ordina.poolautoapi.helper;

public class LicensePlateNumber {

    private final String licensePlateNumber;

    public LicensePlateNumber(String licensePlateNumber) {
        if(!licensePlateNumber.matches("^[0-9A-Za-z]*$") || licensePlateNumber.length() != 6) {
            throw new IllegalArgumentException();
        }
        this.licensePlateNumber = licensePlateNumber.toUpperCase();
    }

    @Override
    public String toString() {
        return licensePlateNumber;
    }
}
