package program.service.dnd.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import program.service.dnd.data.entity.Character;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
    List<Character> getAllByUserId(Integer userId);
}
