package football.clubs.web;


import football.clubs.models.Club;

import football.clubs.models.exceptions.ClubException;
import football.clubs.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubControler {
    @Autowired
    private ClubService clubService;


    @PostMapping
    public Club saveClub(@RequestParam Club club){
        return clubService.save(club);
    }

    @GetMapping
    public List<Club> getAll(){
        return clubService.findAll();
    }

    @GetMapping("/{clubId}")
    public Club getById(@PathVariable Integer clubId){
        return clubService.findById(clubId).orElseThrow(ClubException::new);
    }

}
