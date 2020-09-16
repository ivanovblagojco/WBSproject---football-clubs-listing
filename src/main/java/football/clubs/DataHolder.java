package football.clubs;

import football.clubs.models.Club;
import football.clubs.models.Country;
import football.clubs.models.Stadium;
import football.clubs.repository.ClubRepository;
import football.clubs.repository.CountryRepository;
import football.clubs.repository.StadiumRepository;
import lombok.Getter;
import org.apache.jena.query.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public List<Country> countries = new ArrayList<>();
    public static final List<Club> clubsEngland = new ArrayList<>();
    public static final List<Club> clubsGermany = new ArrayList<>();
    public static final List<Club> clubsItaly = new ArrayList<>();
    public static final List<Club> clubsSpain = new ArrayList<>();
    public List<String> clubs = new ArrayList<>();

    public final CountryRepository countryRepository;
    public final ClubRepository clubRepository;
    public final StadiumRepository stadiumRepository;


    public DataHolder(CountryRepository countryRepository, ClubRepository clubRepository, StadiumRepository stadiumRepository) {
        this.countryRepository = countryRepository;
        this.clubRepository = clubRepository;
        this.stadiumRepository = stadiumRepository;
    }

    public List<String> getAllClubs(String coutry) {
        String SPARQLEndpoint = "https://dbpedia.org/sparql";
        clubs.clear();
        String query =
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX dbo: <http://dbpedia.org/ontology/> " +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                        "PREFIX db: <http://dbpedia.org/resource/>" +
                        "PREFIX dct: <http://purl.org/dc/terms/>" +
                        "SELECT distinct ?club WHERE { " +
                        "db:Category:Football_clubs_in_" + coutry + " ^dct:subject ?club. " +
                        "}" +
                        "Limit 150";

        Query sparqlQuery = QueryFactory.create(query);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(SPARQLEndpoint, sparqlQuery)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution solution = resultSet.nextSolution();
                String[] arr = (solution.get("club").toString()).split("/");
                String club = arr[arr.length - 1];
                clubs.add(club);
            }
        }
        return clubs;
    }

    public void getClubDetails(String club, String country) {
        String SPARQLEndpoint = "https://dbpedia.org/sparql";
        String query =
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX dbo: <http://dbpedia.org/ontology/> " +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                        "PREFIX db: <http://dbpedia.org/resource/>" +
                        "PREFIX dbp:  <http://dbpedia.org/property/>" +
                        "SELECT ?abstract ?chairman ?manager ?ground ?champions WHERE { " +
                        "OPTIONAL {db:" + club + " dbo:abstract ?abstract.}" +
                        "OPTIONAL {db:" + club + " dbo:chairman ?chairman.}" +
                        "OPTIONAL {db:" + club + " dbo:manager ?manager.}" +
                        "OPTIONAL {db:" + club + " dbo:ground ?ground.}" +
                        "OPTIONAL {db:" + club + " ^dbp:champions ?champions.}" +
                        "FILTER (lang(?abstract) = 'en')" +
                        "}" +
                        "LIMIT 1";
        Club newClub = new Club();
        Query sparqlQuery = QueryFactory.create(query);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(SPARQLEndpoint, sparqlQuery)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution solution = resultSet.nextSolution();

                String[] arr = (solution.get("abstract").toString()).split("/");
                String abstracts = arr[arr.length - 1];
                newClub.setDescription(abstracts);
                if (solution.contains("chairman")) {
                    arr = (solution.get("chairman").toString()).split("/");
                    String chairman = arr[arr.length - 1];
                    newClub.setChairman(chairman);
                }
                if (solution.contains("manager")) {
                    arr = (solution.get("manager").toString()).split("/");
                    String manager = arr[arr.length - 1];
                    newClub.setManager(manager);
                }
                if (solution.contains("ground")) {
                    arr = (solution.get("ground").toString()).split("/");
                    String ground = arr[arr.length - 1];
                    newClub.setStadium(new Stadium(0, ground));
                }
                if (solution.contains("champions")) {
                    arr = (solution.get("champions").toString()).split("/");
                    String champions = arr[arr.length - 1];
                    newClub.setChampionsof(champions);
                }
                newClub.setClubName(club);
                newClub.setId(0);
                if (country.equals("Italy")) clubsItaly.add(newClub);
                if (country.equals("Spain")) clubsSpain.add(newClub);
                if (country.equals("Germany")) clubsGermany.add(newClub);
                if (country.equals("England")) clubsEngland.add(newClub);
                break;
            }
        }
    }

    public void getStadiumDetails(String stadium, String country) {
        String SPARQLEndpoint = "https://dbpedia.org/sparql";

        String query =
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX dbo: <http://dbpedia.org/ontology/> " +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                        "PREFIX db: <http://dbpedia.org/resource/>" +
                        "PREFIX dbp:  <http://dbpedia.org/property/>" +
                        "SELECT ?abstract ?buildingStartDate ?opened ?seatingCapacity ?location WHERE { " +
                        "OPTIONAL {db:" + stadium + " dbo:abstract ?abstract.}" +
                        "OPTIONAL {db:" + stadium + " dbo:buildingStartDate ?buildingStartDate.}" +
                        "OPTIONAL {db:" + stadium + " dbp:opened ?opened.}" +
                        "OPTIONAL {db:" + stadium + " dbp:seatingCapacity ?seatingCapacity.}" +
                        "OPTIONAL {db:" + stadium + " ^dbo:location ?location.}" +
                        "FILTER (lang(?abstract) = 'en')" +
                        "}" +
                        "Limit 1";

        Stadium newStadium = new Stadium();

        Query sparqlQuery = QueryFactory.create(query);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(SPARQLEndpoint, sparqlQuery)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution solution = resultSet.nextSolution();
                String[] arr = (solution.get("abstract").toString()).split("/");
                String abstracts = arr[arr.length - 1];
                newStadium.setDescription(abstracts);
                if (solution.contains("buildingStartDate")) {
                    arr = (solution.get("buildingStartDate").toString()).split("/");
                    String buildingStartDate = arr[arr.length - 1];
                    newStadium.setYearOfConstruction(buildingStartDate);
                }
                if (solution.contains("opened")) {
                    arr = (solution.get("opened").toString()).split("/");
                    String opened = arr[arr.length - 1];
                    newStadium.setYearOfOpening(opened);
                }
                if (solution.contains("seatingCapacity")) {
                    arr = (solution.get("seatingCapacity").toString()).split("/");
                    String seatingCapacity = arr[arr.length - 1];
                    newStadium.setCapacity(seatingCapacity);
                }
                if (solution.contains("location")) {
                    arr = (solution.get("location").toString()).split("/");
                    String location = arr[arr.length - 1];
                    newStadium.setPlace(location);
                }
                newStadium.setName(stadium);
                newStadium.setId(0);
                if (country.equals("Italy")) {
                    for (Club club : clubsItaly) {
                        if (club.getStadium() != null && club.getStadium().getName().equals(stadium)) {
                            club.setStadium(newStadium);
                        }
                    }
                }
                if (country.equals("Spain")) {
                    for (Club club : clubsSpain) {
                        if (club.getStadium() != null && club.getStadium().getName().equals(stadium)) {
                            club.setStadium(newStadium);
                        }
                    }
                }
                if (country.equals("Germany")) {
                    for (Club club : clubsGermany) {
                        if (club.getStadium() != null && club.getStadium().getName().equals(stadium)) {
                            club.setStadium(newStadium);
                        }
                    }
                }
                if (country.equals("England")) {
                    for (Club club : clubsEngland) {
                        if (club.getStadium() != null && club.getStadium().getName().equals(stadium)) {
                            club.setStadium(newStadium);
                        }
                    }
                }

                break;
            }
        }
    }


    @PostConstruct
    public void init() {

        List<String> italyClubs = new ArrayList<>(this.getAllClubs("Italy"));
        List<String> germanyClubs = new ArrayList<>(this.getAllClubs("Germany"));
        List<String> spainClubs = new ArrayList<>(this.getAllClubs("Spain"));
        List<String> englandClubs = new ArrayList<>(this.getAllClubs("England"));

        for (String club : italyClubs) {
            if (!club.contains("'") && !club.contains("&") && !club.contains("2013")) {
                if (club.endsWith(".")) {
                    String c = club.substring(0, club.length() - 1);
                    c = c + "\\.";
                    club = c;
                }
                this.getClubDetails(club, "Italy");
            }

        }
        for (String club : spainClubs) {
            if (!club.contains("'") && !club.contains("&") && !club.contains(")")) {
                if (club.endsWith(".")) {
                    String c = club.substring(0, club.length() - 1);
                    c = c + "\\.";
                    club = c;
                }
                this.getClubDetails(club, "Spain");
            }

        }
        for (String club : germanyClubs) {
            if (!club.contains("'") && !club.contains("&") && !club.contains(")")) {
                if (club.endsWith(".")) {
                    String c = club.substring(0, club.length() - 1);
                    c = c + "\\.";
                    club = c;
                }
                this.getClubDetails(club, "Germany");
            }

        }
        for (String club : englandClubs) {
            if (!club.contains("'") && !club.contains("&") && !club.contains(")")) {
                if (club.endsWith(".")) {
                    String c = club.substring(0, club.length() - 1);
                    c = c + "\\.";
                    club = c;
                }
                this.getClubDetails(club, "England");
            }

        }


        for (Club club : clubsItaly) {
            if (club.getStadium() != null && !club.getStadium().getName().contains(",") && !club.getStadium().getName().contains("'")) {
                this.getStadiumDetails(club.getStadium().getName(), "Italy");
                if (club.getStadium() != null) {
                    stadiumRepository.save(club.getStadium());
                }
            }
        }

        for (Club club : clubsSpain) {
            if (club.getStadium() != null && !club.getStadium().getName().contains(",") && !club.getStadium().getName().contains("'") && !club.getStadium().getName().contains(")")) {
                this.getStadiumDetails(club.getStadium().getName(), "Spain");
                if (club.getStadium() != null) {
                    stadiumRepository.save(club.getStadium());
                }
            }
        }

        for (Club club : clubsGermany) {
            if (club.getStadium() != null && !club.getStadium().getName().contains(",") && !club.getStadium().getName().contains(")")) {
                this.getStadiumDetails(club.getStadium().getName(), "Germany");
                if (club.getStadium() != null) {
                    stadiumRepository.save(club.getStadium());
                }
            }
        }
        for (Club club : clubsEngland) {
            if (club.getStadium() != null && !club.getStadium().getName().contains(",")
                    && !club.getStadium().getName().contains("=")
                    && !club.getStadium().getName().contains(")")
                    && !club.getStadium().getName().contains("_")) {
                this.getStadiumDetails(club.getStadium().getName(), "England");
                if (club.getStadium() != null) {
                    stadiumRepository.save(club.getStadium());
                }
            }
        }

        countries = Arrays.asList(
                new Country(0, "Italy", clubsItaly),
                new Country(0, "Spain", clubsSpain),
                new Country(0, "Germany", clubsGermany),
                new Country(0, "England", clubsEngland)
        );

        this.clubRepository.saveAll(clubsEngland);
        this.clubRepository.saveAll(clubsGermany);
        this.clubRepository.saveAll(clubsItaly);
        this.clubRepository.saveAll(clubsSpain);
        this.countryRepository.saveAll(countries);


    }
}
