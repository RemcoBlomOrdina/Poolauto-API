package nl.ordina.poolautoapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Car {

    private final String licensePlateNumber;          // kenteken
    private final String brand;                       // merk
    private final String model;                       // model
    private final String type;                        // type
    private final String color;                       // kleur
    private final String version;                     // uitvoering
//    private final String[] accessories;               // accessoires
    private final String engine;                      // motor
    private final String fuel;                        // brandstof
//    private final int distanceInKm;                   // kilometrage
//    private final LocalDate endDateContract;          // resterende contractduur
//    private final double leaseAmount;                 // leasebedrag
    private final String catalogValue;                // cataloguswaarde
//    private final double addition;                    // bijtelling
//    private final LocalDate latestAcquisitionDate;    // uiterste overnamedatum
//    private List<byte[]> pictures;              // foto's
//    private List<String> damages;               // eventuele schade

}
