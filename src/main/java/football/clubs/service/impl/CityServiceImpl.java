package football.clubs.service.impl;

import football.clubs.models.City;
import football.clubs.repository.CityRepository;
import football.clubs.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public Optional<City> findByCityName(String Name) {
        return cityRepository.findByCityName(Name);
    }

    @Override
    public City getCityDetailsByLink(String cityLink) {
        return null;
    }

    @Override
    public City getCityDetailsByName(String Name) {
        return null;
    }
}
