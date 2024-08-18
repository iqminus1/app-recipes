package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.pdp.apprecipes.model.Ingredient;

import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {

}
