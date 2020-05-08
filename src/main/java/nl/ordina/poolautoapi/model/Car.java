package nl.ordina.poolautoapi.model;

import lombok.Getter;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

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

    private String calculateBijtellingsPercentage(String datumEersteAfgifteNederland, String co2UitstootGecombineerd, String brandstof, String cataloguswaarde) {
        try {
            String jaarEersteAfgifteNederland = datumEersteAfgifteNederland.substring(0,4);
            String brandstofLowerCase = brandstof.toLowerCase();
            int co2UitstootGecombineerdInt = Integer.parseInt(co2UitstootGecombineerd);
            int cataloguswaardeInt = Integer.parseInt(cataloguswaarde);
            ResourceBundle resourceBundle = ResourceBundle.getBundle("bijtelling");

            return resourceBundle.keySet().stream()
                    .map(k -> new BijtellingProperty(k, resourceBundle.getString(k)))
                    .filter(b -> b.jaar.equals(jaarEersteAfgifteNederland))
                    .filter(b -> b.brandstof.contains(brandstofLowerCase))
                    .filter(b -> {
                        if (brandstofLowerCase.equals("elektriciteit")) {
                            return b.minimumwaarde <= cataloguswaardeInt && b.maximumwaarde >= cataloguswaardeInt;
                        } else {
                            return b.minimumwaarde <= co2UitstootGecombineerdInt && b.maximumwaarde >= co2UitstootGecombineerdInt;
                        }
                    })
                    .findAny().get().bijtellingspercentage;
        } catch (NumberFormatException | NoSuchElementException e) {
            return "berekening niet mogelijk";
        }
    }

    private static class BijtellingProperty {
        private final String jaar;
        private final String brandstof;
        private final int minimumwaarde;
        private final int maximumwaarde;
        private final String bijtellingspercentage;

        private BijtellingProperty(String resourceBundleKey, String resourceBundleValue) {
            String[] resourceBundleKeyParts = resourceBundleKey.split("_");
            jaar = resourceBundleKeyParts[0];
            brandstof = resourceBundleKeyParts[1];
            String[] waardes = resourceBundleKeyParts[2].split("-");
            minimumwaarde = Integer.parseInt(waardes[0]);
            if (waardes[1].equals("max")){
                maximumwaarde = Integer.MAX_VALUE;
            } else {
                maximumwaarde = Integer.parseInt(waardes[1]);
            }
            bijtellingspercentage = resourceBundleValue;
        }
    }
}
