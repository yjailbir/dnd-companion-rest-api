package program.service.dnd.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import program.service.dnd.data.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
