package football.clubs;

import football.clubs.models.Club;
import football.clubs.models.Country;
import football.clubs.models.Stadium;
import football.clubs.repository.ClubRepository;
import football.clubs.repository.CountryRepository;
import football.clubs.repository.StadiumRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static final List<Country> countries=new ArrayList<>();
    public static final List<Club> clubsEngland=new ArrayList<>();
    public static final List<Club> clubsGermany=new ArrayList<>();
    public static final List<Club> clubsItaly=new ArrayList<>();
    public static final List<Club> clubsSpain=new ArrayList<>();


    public final CountryRepository countryRepository;
    public final ClubRepository clubRepository;
    public final StadiumRepository stadiumRepository;


    public DataHolder(CountryRepository countryRepository, ClubRepository clubRepository, StadiumRepository stadiumRepository){
        this.countryRepository = countryRepository;
        this.clubRepository = clubRepository;
        this.stadiumRepository = stadiumRepository;
    }

    @PostConstruct
    public void init(){

        Stadium s1 = new Stadium(1, "Example","Example stadium for all the teams", "2008", "2010", "40000", "Example City");
        clubsEngland.add(new Club(1, "Chelsea", "Chelsea is a club from London","Roman Abramovich", "Frank Lampard", s1, "Premier League"));
        clubsEngland.add(new Club(2, "Manchester United", "Manchester is a club from Manchester","John Johnson", "Paul Scholes", s1, ""));
        clubsEngland.add(new Club(3, "Manchester City", "Manchester is a club from Manchester","John Peterson", "Paul John", s1, "FA Cup"));
        countries.add(new Country(1,"England", clubsEngland));


        clubsGermany.add(new Club(4, "Bayern Munich", "Bayern is a club from Munich","Oliver Kahn", "Mario Gotze", s1, "Bundesliga"));
        clubsGermany.add(new Club(5, "Borussia Dortmund", "Borussia is a club from Dortmund","Oliver Oliver", "Stephan Kalz", s1, "Cup"));
        countries.add(new Country(2,"Germany", clubsGermany));



        clubsItaly.add(new Club(6, "Juventus", "Juventus is a club from Torino","Luka Toni", "Alegri", s1, "Seria A"));
        clubsItaly.add(new Club(7, "Torino", "Torino is a club from Torino","Inzagi", "Alegri", s1, ""));
        countries.add(new Country(3,"Italy", clubsItaly));


        clubsSpain.add(new Club(8, "Barcelona", "Barcelona is a club from Barcelona","Englesias", "Messi", s1, "Primera"));
        clubsSpain.add(new Club(9, "Real Madrid", "Real Madrid is a club from Madrid","Stephan Luis", "Zidane", s1, ""));
        countries.add(new Country(4,"Spain", clubsSpain));



        if(this.countryRepository.count()==0)
        {
            this.stadiumRepository.save(s1);
            this.clubRepository.saveAll(clubsEngland);
            this.clubRepository.saveAll(clubsGermany);
            this.clubRepository.saveAll(clubsItaly);
            this.clubRepository.saveAll(clubsSpain);
            this.countryRepository.saveAll(countries);
        }
    }
}
