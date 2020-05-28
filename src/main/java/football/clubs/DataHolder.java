package football.clubs;

import lombok.Getter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
@Component
@Getter
public class DataHolder {



    public DataHolder(){ }

    @PostConstruct
    public void init(){

    }
}
