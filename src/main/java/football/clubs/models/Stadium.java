package football.clubs.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Lob
    private String description;
    private String yearOfConstruction;
    private String yearOfOpening;
    private String capacity;
    private String place;

    public Stadium(Integer id, String name){
        this.id =id;
        this.name = name;
    }

}
