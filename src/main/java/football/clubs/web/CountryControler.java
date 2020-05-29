package football.clubs.web;

import football.clubs.models.Club;
import football.clubs.models.Country;
import football.clubs.models.exceptions.ClubException;
import football.clubs.models.exceptions.CountryException;
import football.clubs.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryControler {
    @Autowired
    private CountryService countryService;

    @PostMapping
    public Country saveCountry(@RequestParam Country country){
        return countryService.save(country);
    }

    @GetMapping
    public List<Country> getAll(){
        return countryService.findAll();
    }

    @GetMapping("/{countryId}")
    public Country getById(@PathVariable Integer countryId){
        return countryService.findById(countryId).orElseThrow(CountryException::new);
    }


}
