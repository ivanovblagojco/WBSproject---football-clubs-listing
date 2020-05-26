package football.clubs.web;

import football.clubs.models.City;
import football.clubs.models.exceptions.CityException;
import football.clubs.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityControler {

    @Autowired
    private CityService cityService;

    @PostMapping
    public City saveCity(@RequestParam City city){
        return cityService.save(city);
    }

    @GetMapping
    public List<City> getAll(){
        return cityService.findAll();
    }

    @GetMapping("/{cityId}")
    public City getById(@PathVariable Long cityId){
        return cityService.findById(cityId).orElseThrow(CityException::new);
    }

    @GetMapping("/{cityName}")
    public City getByName(@PathVariable String cityName){
        return cityService.findByCityName(cityName).orElseThrow(CityException::new);
    }

    @GetMapping("/details")
    public City getCityDetails(@RequestParam String cityLink){
        return cityService.getCityDetailsByLink(cityLink);
    }
}
