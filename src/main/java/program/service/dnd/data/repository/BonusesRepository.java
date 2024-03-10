package program.service.dnd.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import program.service.dnd.data.entity.Bonuses;

@Repository
public interface BonusesRepository extends JpaRepository<Bonuses, Integer> {
}
