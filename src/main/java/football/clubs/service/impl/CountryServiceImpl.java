package football.clubs.service.impl;

import football.clubs.models.Country;
import football.clubs.repository.CountryRepository;
import football.clubs.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Integer id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> findByCountryName(String Name) {
        return countryRepository.findByCountryName(Name);
    }

    @Override
    public Country getCountryDetailsByLink(String countryLink) {
        return null;
    }

    @Override
    public Country getCountryDetailsByName(String Name) {
        return null;
    }
}
