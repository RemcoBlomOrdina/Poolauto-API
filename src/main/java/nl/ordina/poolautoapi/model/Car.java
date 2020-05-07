package nl.ordina.poolautoapi.model;

import lombok.Getter;

import java.text.NumberFormat;
import java.util.Locale;

@Getter
public class Car {

    private final String kenteken;          // kenteken
    private final String merk;                       // merk
    private final String model;                       // model
    private final String type;                        // type
    private final String kleur;                       // kleur
    private final String uitvoering;                     // uitvoering
//    private final String[] accessoires;               // accessoires
    private final String motor;                      // motor
    private final String brandstof;                        // brandstof
//    private final String milieulabel;
//    private final int kilometrage;                   // kilometrage
//    private final LocalDate resterendeContractuur;          // resterende contractduur
//    private final double leasebedrag;                 // leasebedrag
    private final String cataloguswaarde;                // cataloguswaarde
    private final String bijtellingsPercentage;
    private final String brutoBijtellingPerMaand;                    // bijtelling
//    private final LocalDate uitersteOvernamedatum;    // uiterste overnamedatum
//    private List<byte[]> fotos;              // foto's
//    private List<String> eventueleSchade;               // eventuele schade


    public Car(String kenteken, String merk, String model, String type, String kleur, String uitvoering, String motor,
               String brandstof, String cataloguswaarde, String datumEersteAfgifteNederland, String co2UitstootGecombineerd) {
        this.kenteken = kenteken;
        this.merk = merk;
        this.model = model;
        this.type = type;
        this.kleur = kleur;
        this.uitvoering = uitvoering;
        this.motor = motor;
        this.brandstof = brandstof;
        this.cataloguswaarde = cataloguswaarde;
        this.bijtellingsPercentage = calculateBijtellingsPercentage(datumEersteAfgifteNederland, co2UitstootGecombineerd, brandstof, cataloguswaarde);
        this.brutoBijtellingPerMaand = calculateBrutoBijtellingPerMaand(cataloguswaarde, bijtellingsPercentage);
    }

    private String calculateBijtellingsPercentage(String datumEersteAfgifteNederland, String co2UitstootGecombineerd, String brandstof, String cataloguswaarde) {
        int jaarEersteAfgifteNederland;
        int co2UitstootGecombineerdInt;
        int cataloguswaardeInt;
        try {
            jaarEersteAfgifteNederland = Integer.parseInt(datumEersteAfgifteNederland.substring(0, 4));
            co2UitstootGecombineerdInt = Integer.parseInt(co2UitstootGecombineerd);
            cataloguswaardeInt = Integer.parseInt(cataloguswaarde);
        } catch (NumberFormatException e) {
            return "berekening niet mogelijk";
        }

        if (jaarEersteAfgifteNederland < 2015) {
            return "data voor 2015 niet beschikbaar";
        } else if (jaarEersteAfgifteNederland > 2020) {
            return "data na 2020 niet beschikbaar";
        } else if (brandstof.equals("Elektriciteit")){
            if(jaarEersteAfgifteNederland <= 2018) {
                return "4";
            } else if(jaarEersteAfgifteNederland == 2019) {
                if (cataloguswaardeInt <= 50000) {
                    return "4";
                } else {
                    return "22";
                }
            } else /* 2020 */ {
                if (cataloguswaardeInt <= 45000) {
                    return "8";
                } else {
                    return "22";
                }
            }
        } else {
            if (jaarEersteAfgifteNederland >= 2017) {
                return "22";
            } else if (jaarEersteAfgifteNederland == 2016) {
                if (co2UitstootGecombineerdInt <= 50) {
                    return "15";
                } else if (co2UitstootGecombineerdInt <= 106) {
                    return "21";
                } else {
                    return "25";
                }
            } else {
                if (co2UitstootGecombineerdInt <= 50) {
                    return "7";
                } else if (co2UitstootGecombineerdInt <= 82) {
                    return "14";
                } else if (co2UitstootGecombineerdInt <= 106) {
                    return "20";
                } else {
                    return "25";
                }
            }
        }
    }

    private String calculateBrutoBijtellingPerMaand(String cataloguswaarde, String bijtellingsPercentage) {
        int cataloguswaardeInt;
        int bijtellingsPercentageInt;
        try {
            cataloguswaardeInt = Integer.parseInt(cataloguswaarde);
            bijtellingsPercentageInt = Integer.parseInt(bijtellingsPercentage);
        } catch (NumberFormatException e) {
            return "onbekend";
        }

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
        return numberFormat.format(cataloguswaardeInt * ((double)bijtellingsPercentageInt / 100) / 12);
    }
}
