package football.clubs.service;

import football.clubs.models.Club;
import football.clubs.models.Stadium;

import java.util.List;
import java.util.Optional;

public interface StadiumService {
    public Stadium save(Stadium stadium);
    public List<Stadium> findAll();
    public Optional<Stadium> findById(Long id);
    public Stadium getStadiumDetailsByLink(String stadiumLink);
}
