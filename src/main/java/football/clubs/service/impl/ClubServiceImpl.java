package football.clubs.service.impl;

import football.clubs.models.Club;
import football.clubs.repository.ClubRepository;
import football.clubs.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public Club save(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    @Override
    public Optional<Club> findById(Long id) {
        return clubRepository.findById(id);
    }

    @Override
    public Optional<Club> findByClubName(String Name) {
        return clubRepository.findByClubName(Name);
    }

    @Override
    public Club getClubDetailsByLink(String clubLink) {
        return null;
    }

    @Override
    public Club getClubDetailsByName(String Name) {
        return null;
    }
}
