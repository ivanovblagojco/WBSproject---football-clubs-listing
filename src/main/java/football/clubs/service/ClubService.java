package football.clubs.service;

import football.clubs.models.Club;

import java.util.List;
import java.util.Optional;

public interface ClubService {

    public Club save(Club club);
    public List<Club> findAll();
    public Optional<Club> findById(Long id);
    public Optional<Club> findClubByName(String Name);
    public Club getClubDetailsByLink(String clubLink);
    public Club getClubDetailsByName(String Name);

}
