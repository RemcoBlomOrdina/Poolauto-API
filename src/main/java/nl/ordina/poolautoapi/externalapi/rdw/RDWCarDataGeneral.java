package nl.ordina.poolautoapi.externalapi.rdw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RDWCarDataGeneral {

    public static final String API_URL = "https://opendata.rdw.nl/resource/m9d7-ebf2.json?kenteken=";

//    private final String licensePlateNumber;          // kenteken
//    private final String brand;                       // merk
//    private final String model;                       // model
//    private final String type;                        // type
//    private final String color;                       // kleur
//    private final String version;                     // uitvoering
//    private final String[] accessories;               // accessoires
//    private final String engine;                      // motor
//    private final String fuel;                        // brandstof
//    private final int distanceInKm;                   // kilometrage
//    private final LocalDate endDateContract;          // resterende contractduur
//    private final double leaseAmount;                 // leasebedrag
//    private final double catalogValue;                // cataloguswaarde
//    private final double addition;                    // bijtelling
//    private final LocalDate latestAcquisitionDate;    // uiterste overnamedatum

    private String kenteken;
    private String merk;
    private String handelsbenaming;
    private String inrichting;
    private String eerste_kleur;
    private String uitvoering;
    private String aantal_cilinders;
    private String cilinderinhoud;
    private String catalogusprijs;



//        "kenteken":"22ZBR8",
//            "voertuigsoort":"Personenauto",
//            "merk":"SKODA",
//            "handelsbenaming":"CITIGO",
//            "vervaldatum_apk":"20210225",
//            "datum_tenaamstelling":"20200229",
//            "inrichting":"hatchback",
//            "aantal_zitplaatsen":"4",
//            "eerste_kleur":"WIT",
//            "tweede_kleur":"Niet geregistreerd",
//            "aantal_cilinders":"3",
//            "cilinderinhoud":"999",
//            "massa_ledig_voertuig":"840",
//            "toegestane_maximum_massa_voertuig":"1290",
//            "massa_rijklaar":"940",
//            "zuinigheidslabel":"A",
//            "datum_eerste_toelating":"20121005",
//            "datum_eerste_afgifte_nederland":"20121005",
//            "wacht_op_keuren":"Geen verstrekking in Open Data",
//            "catalogusprijs":"11779",
//            "wam_verzekerd":"Ja",
//            "aantal_deuren":"0",
//            "aantal_wielen":"4",
//            "afstand_hart_koppeling_tot_achterzijde_voertuig":"0",
//            "afstand_voorzijde_voertuig_tot_hart_koppeling":"0",
//            "lengte":"356","breedte":"0",
//            "europese_voertuigcategorie":"M1",
//            "plaats_chassisnummer":"r. motorruimte",
//            "technische_max_massa_voertuig":"1290",
//            "type":"AA",
//            "typegoedkeuringsnummer":"e13*2007/46*1169*04",
//            "variant":"ABCHYB",
//            "uitvoering":"FM5FM5CF0047MGVR2N1FA1SK",
//            "volgnummer_wijziging_eu_typegoedkeuring":"0",
//            "vermogen_massarijklaar":"0.06",
//            "wielbasis":"241",
//            "export_indicator":"Nee",
//            "openstaande_terugroepactie_indicator":"Nee",
//            "taxi_indicator":"Nee",
//            "maximum_massa_samenstelling":"0",
//            "aantal_rolstoelplaatsen":"0",
//            "api_gekentekende_voertuigen_assen":"https://opendata.rdw.nl/resource/3huj-srit.json",
//            "api_gekentekende_voertuigen_brandstof":"https://opendata.rdw.nl/resource/8ys7-d773.json",
//            "api_gekentekende_voertuigen_carrosserie":"https://opendata.rdw.nl/resource/vezc-m2t6.json",
//            "api_gekentekende_voertuigen_carrosserie_specifiek":"https://opendata.rdw.nl/resource/jhie-znh9.json",
//            "api_gekentekende_voertuigen_voertuigklasse":"https://opendata.rdw.nl/resource/kmfi-hrps.json"


}
