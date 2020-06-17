package nl.ordina.poolautoapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Visits")
@NoArgsConstructor
@Getter
@Setter
public class Visit {

    public Visit(Timestamp timestamp, String userinput) {
        this.timestamp = timestamp;
        this.userinput = userinput;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp timestamp;
    private String userinput;
}
