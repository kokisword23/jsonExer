package softuni.jsonexer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.jsonexer.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
