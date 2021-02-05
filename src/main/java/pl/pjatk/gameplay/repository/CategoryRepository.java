package pl.pjatk.gameplay.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pjatk.gameplay.model.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    Optional<Category> findByName(String name);
}
