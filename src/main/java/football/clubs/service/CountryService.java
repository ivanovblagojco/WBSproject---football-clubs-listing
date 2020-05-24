package football.clubs.service;

import football.clubs.models.Club;
import football.clubs.models.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    public Country save(Country country);
    public List<Country> findAll();
    public Optional<Country> findById(Long id);
    public Optional<Country> findCountryByName(String Name);
    public Country getCountryDetailsByLink(String countryLink);
    public Country getCountryDetailsByName(String Name);
}
