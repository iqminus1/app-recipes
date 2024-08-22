package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Category;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @NonNull
    default Category getById(@NonNull UUID id) {
        return findById(id).orElseThrow(() -> NotFoundException.errorById("Category", id));
    }

    Optional<Category> findByName(String name);

}
