package nl.ordina.poolautoapi.model;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;

@Getter
public class Car {

    private static final String PATH_BIJTELLING_PROPERTIES = System.getProperty("user.home")
            + "/ApplicationProperties/poolauto-api/bijtelling.properties";

    private String kenteken;          // kenteken
    private String merk;                       // merk
    private String model;                       // model
    private String type;                        // type
    private String kleur;                       // kleur
    private String uitvoering;                     // uitvoering
//    private final String[] accessoires;               // accessoires
//    private String motor;                      // motor
    private String aantal_cilinders;
    private String cilinderinhoud;
    private String brandstof;                        // brandstof
    private String milieulabel;
//    private final int kilometrage;                   // kilometrage
//    private final LocalDate resterendeContractuur;          // resterende contractduur
//    private final double leasebedrag;                 // leasebedrag
    private String cataloguswaarde;                // cataloguswaarde
    private String datum_eerste_afgifte;
    private String bijtellingspercentage;
    private String bruto_bijtelling_per_maand;                    // bijtelling
//    private final LocalDate uitersteOvernamedatum;    // uiterste overnamedatum
//    private List<byte[]> fotos;              // foto's
//    private List<String> eventueleSchade;               // eventuele schade

    private static final String ERROR_MESSAGE = "berekening niet mogelijk";
    private static final NumberFormat dutchCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));

    public Car(String kenteken, String merk, String model, String type, String kleur, String uitvoering, String aantalCilinders, String cilinderInhoud,
               String brandstof, String milieulabel, String cataloguswaarde, String datumEersteAfgifteNederland, String co2UitstootGecombineerd) {
        this.kenteken = kenteken;
        this.merk = merk;
        this.model = model;
        this.type = type;
        this.kleur = kleur.toLowerCase();
        this.uitvoering = uitvoering;
        this.aantal_cilinders = aantalCilinders;
        this.cilinderinhoud = cilinderInhoud;
        this.brandstof = brandstof.toLowerCase();
        this.milieulabel = milieulabel;
        this.cataloguswaarde = cataloguswaarde;
        this.datum_eerste_afgifte = datumEersteAfgifteNederland;
        this.bijtellingspercentage = calculateBijtellingsPercentage(datumEersteAfgifteNederland, co2UitstootGecombineerd, brandstof, cataloguswaarde);
        this.bruto_bijtelling_per_maand = calculateBrutoBijtellingPerMaand(cataloguswaarde, bijtellingspercentage);
    }

    private String calculateBrutoBijtellingPerMaand(String cataloguswaarde, String bijtellingsPercentage) {
        int cataloguswaardeInt;
        int bijtellingsPercentageInt;
        try {
            cataloguswaardeInt = Integer.parseInt(cataloguswaarde);
            bijtellingsPercentageInt = Integer.parseInt(bijtellingsPercentage);
        } catch (NumberFormatException e) {
            return null;
        }

        DecimalFormat decimalFormat = new DecimalFormat("####0.00", DecimalFormatSymbols.getInstance(Locale.US));
        return String.valueOf(decimalFormat.format(cataloguswaardeInt * ((double)bijtellingsPercentageInt / 100) / 12));
    }

    private String calculateBijtellingsPercentage(String datumEersteAfgifteNederland, String co2UitstootGecombineerd, String brandstof, String cataloguswaarde) {
        String result;
        try {
            String jaarEersteAfgifteNederland = datumEersteAfgifteNederland.substring(0,4);
            String brandstofLowerCase = brandstof.toLowerCase();
            int co2UitstootGecombineerdInt = Integer.parseInt(co2UitstootGecombineerd);
            int cataloguswaardeInt = Integer.parseInt(cataloguswaarde);

            Properties properties = new Properties();
            properties.load(new FileInputStream(PATH_BIJTELLING_PROPERTIES));

            Optional<BijtellingProperty> optional = properties.keySet().stream()
                    .map(k -> new BijtellingProperty(k.toString(), properties.getProperty(k.toString())))
                    .filter(b -> b.jaar.equals(jaarEersteAfgifteNederland))
                    .filter(b -> b.brandstof.contains(brandstofLowerCase))
                    .filter(b -> {
                        if (brandstofLowerCase.equals("elektriciteit")) {
                            return b.minimumwaarde <= cataloguswaardeInt && b.maximumwaarde >= cataloguswaardeInt;
                        } else {
                            return b.minimumwaarde <= co2UitstootGecombineerdInt && b.maximumwaarde >= co2UitstootGecombineerdInt;
                        }
                    })
                    .findAny();
            result = optional.map(bijtellingProperty -> bijtellingProperty.bijtellingspercentage).orElse(null);
        } catch (NumberFormatException | IOException e) {
            result = null;
        }
        return result;
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
