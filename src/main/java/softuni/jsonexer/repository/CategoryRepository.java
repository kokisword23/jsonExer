package softuni.jsonexer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.jsonexer.domain.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
