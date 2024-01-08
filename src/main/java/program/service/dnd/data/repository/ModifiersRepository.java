package program.service.dnd.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import program.service.dnd.data.entity.Modifiers;

@Repository
public interface ModifiersRepository extends JpaRepository<Modifiers, Integer> {
}
