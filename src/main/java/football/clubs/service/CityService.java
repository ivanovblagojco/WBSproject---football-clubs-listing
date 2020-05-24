package football.clubs.service;

import football.clubs.models.City;
import football.clubs.models.Club;

import java.util.List;
import java.util.Optional;

public interface CityService {
    public City save(City city);
    public List<City> findAll();
    public Optional<City> findById(Long id);
    public Optional<City> findCityByName(String Name);
    public City getCityDetailsByLink(String cityLink);
    public City getCityDetailsByName(String Name);
}
