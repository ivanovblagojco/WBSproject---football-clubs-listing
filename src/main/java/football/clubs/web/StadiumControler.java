package football.clubs.web;

import football.clubs.models.Stadium;
import football.clubs.models.exceptions.StatidumException;
import football.clubs.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stadium")
public class StadiumControler {

    @Autowired
    private StadiumService stadiumService;

    @PostMapping
    public Stadium saveStadium(@RequestParam Stadium stadium){
        return stadiumService.save(stadium);
    }

    @GetMapping
    public List<Stadium> getAll(){
        return stadiumService.findAll();
    }

    @GetMapping("/{stadiumId}")
    public Stadium getById(@PathVariable Long stadiumId){
        return stadiumService.findById(stadiumId).orElseThrow(StatidumException::new);
    }

    @GetMapping("/details")
    public Stadium getStadiumDetails(@RequestParam String stadiumLink){
        return stadiumService.getStadiumDetailsByLink(stadiumLink);
    }
}
