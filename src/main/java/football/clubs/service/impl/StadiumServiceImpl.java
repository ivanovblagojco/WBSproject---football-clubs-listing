package football.clubs.service.impl;

import football.clubs.models.Stadium;
import football.clubs.repository.StadiumRepository;
import football.clubs.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StadiumServiceImpl implements StadiumService {

    @Autowired
    private StadiumRepository stadiumRepository;

    @Override
    public Stadium save(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    @Override
    public List<Stadium> findAll() {
        return stadiumRepository.findAll();
    }

    @Override
    public Optional<Stadium> findById(Integer id) {
        return stadiumRepository.findById(id);
    }

    @Override
    public Stadium getStadiumDetailsByLink(String stadiumLink) {
        return null;
    }

}
