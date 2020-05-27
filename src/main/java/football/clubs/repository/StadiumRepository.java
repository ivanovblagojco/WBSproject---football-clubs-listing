package football.clubs.repository;

import football.clubs.models.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StadiumRepository extends JpaRepository<Stadium,Long> {

}
