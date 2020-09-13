package football.clubs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String clubName;
    @Lob
    private String description;
    private String chairman;
    private String manager;
    @OneToOne(cascade = CascadeType.ALL)
    private Stadium stadium;
    private String championsof;
}
